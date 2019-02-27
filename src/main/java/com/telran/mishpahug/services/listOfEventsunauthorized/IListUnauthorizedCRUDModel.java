package com.telran.mishpahug.services.listOfEventsunauthorized;

import com.telran.mishpahug.api.RequestsDTO.ListOfEventsInProgressDTO.FiltersDTO;
import com.telran.mishpahug.api.RequestsDTO.ListOfEventsInProgressDTO.ListOfEventsDTO;
import com.telran.mishpahug.api.RequestsDTO.ListOfEventsInProgressDTO.LocationRadiusDTO;
import com.telran.mishpahug.api.ResponseDTO.ListOfEventsInProgressDTOResponse.AddressDTORes;
import com.telran.mishpahug.api.ResponseDTO.ListOfEventsInProgressDTOResponse.ContentDTORes;
import com.telran.mishpahug.api.ResponseDTO.ListOfEventsInProgressDTOResponse.ListOfEventsDTORes;
import com.telran.mishpahug.api.ResponseDTO.ListOfEventsInProgressDTOResponse.OwnerDTORes;
import com.telran.mishpahug.api.ResponseDTO.MessageDTORes;
import com.telran.mishpahug.entities.Event;
import com.telran.mishpahug.entities.FoodEvent;
import com.telran.mishpahug.entities.Profile;
import com.telran.mishpahug.repository.unauthorizedList.IListUnauthorizedCRUD;
import com.telran.mishpahug.services.parser.IParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class IListUnauthorizedCRUDModel implements IListUnauthorized {

    @Autowired
    IListUnauthorizedCRUD findByRadiusRepo;

    @Autowired
    IParser parseListUnauthorized;

    @Override
    @Transactional
    public ResponseEntity getListOfEventsByRadius(int page, int size, ListOfEventsDTO data) {
        if(!isValid(data)){
            return new ResponseEntity<>(new MessageDTORes(422,"Invalid filter parameters!"),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if(data.getLocation().getLat()<=0||data.getLocation().getLng()<=0||data.getLocation().getRadius()<=0){
           data.setLocation(new LocationRadiusDTO(32.062764,34.770914,1.5));
        }
        return new ResponseEntity<>(getPages(data,size,page),HttpStatus.OK);
    }

    private ArrayList<ContentDTORes> pagination(ArrayList<ContentDTORes> list,int size,int page ){
        int from = page==0?0:size*page;
        int to = from+size>list.size()?list.size():from+size;
        try {
            return new ArrayList<>(list.subList(from,to));
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private ListOfEventsDTORes getPages(ListOfEventsDTO data, int size, int page){
        LocalDate from = LocalDate.parse(data.getFilters().getDateFrom());
        LocalDate to = LocalDate.parse(data.getFilters().getDateTo());
         ArrayList<ContentDTORes> content = (ArrayList<ContentDTORes>) findByRadiusRepo.
                 getEventsByDateBetweenAndStatus(from,to,"In progress").stream().
                 filter(x->isMatched(x,data)&&
                         InRadius(data.getLocation().getLat(),data.getLocation().getLng(),data.getLocation().getRadius(),x)).
                 sorted((a,b)->a.compare(a,b)).map(this::getContent).collect(Collectors.toList());
         return new ListOfEventsDTORes(pagination(content,size,page),content.size(),getTotalPages(content.size(),size),
                 size,page+1, size,page==0,page==(getTotalPages(content.size(),size)-1),null);
    }

    private int getTotalPages(int listSize, int size){
        if(listSize == 0){return 1;}
        if(size==0){return 0;}
        return listSize%size!=0?(listSize/size)+(listSize%size):listSize/size;
    }

    private ContentDTORes getContent(Event event){
        AddressDTORes address = new AddressDTORes(event.getCity());
        ArrayList<String>food = (ArrayList<String>) event.getFoodsOfEvent().stream().
                map(FoodEvent::getFood).collect(Collectors.toList());
        return  new ContentDTORes(event.getEventId(),event.getTitle(),event.getHoliday(),event.getConfession(),
                event.getDate(),event.getTime(),event.getDuration(),address,food,event.getDescription(),getOwner(event));
    }

    private OwnerDTORes getOwner(Event event){
        Profile profile = event.getOwner_email();
        return new OwnerDTORes(profile.getFullName(),profile.getConfession(),profile.getGender(),profile.getAge(),
                parseListUnauthorized.getListOfPictures(profile),profile.getMaritalStatus(),
                parseListUnauthorized.getListOfFoods(profile),parseListUnauthorized.getListOfLanguages(profile),
                profile.getRate());
    }

    private boolean isMatched(Event event, ListOfEventsDTO data){
        if(event.getHoliday().equals(data.getFilters().getHolidays())){
            if(event.getConfession().equals(data.getFilters().getConfession())){
                Set<String> foods = event.getFoodsOfEvent().stream().map(FoodEvent::getFood).collect(Collectors.toSet());
                return foods.contains(data.getFilters().getFood());
            }
        }
        return false;
    }

    private boolean InRadius(double geoLat, double geoLng, double radiusGeo, Event event) {
		double eventLat = event.getLat();double eventLng = event.getLng();
		return (radiusGeo*1000) > 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(Math.toRadians((geoLat - eventLat) / 2)), 2)
				+ Math.cos(Math.toRadians(geoLat)) * Math.cos(Math.toRadians(eventLat))
						* Math.pow(Math.sin(Math.toRadians((geoLng - eventLng) / 2)), 2))) * 6378.245;
	}

	private boolean isValid(ListOfEventsDTO data){
        FiltersDTO filter = data.getFilters();
        if(filter.getConfession()==null||filter.getFood()==null||filter.getHolidays()==null||
        filter.getDateFrom()==null||filter.getDateTo()==null){
            return false;
        }
        return isLocalDate(filter);
    }
    private boolean isLocalDate(FiltersDTO filter){
        try {
            LocalDate.parse(filter.getDateFrom());
            LocalDate.parse(filter.getDateTo());
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
