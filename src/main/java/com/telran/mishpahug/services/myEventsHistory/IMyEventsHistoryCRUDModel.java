package com.telran.mishpahug.services.myEventsHistory;

import com.telran.mishpahug.api.ResponseDTO.MyEventHistoryDTORes.ListMyEventHistoryDTORes;
import com.telran.mishpahug.api.ResponseDTO.MyEventHistoryDTORes.MyEventHistoryDTORes;
import com.telran.mishpahug.entities.Event;
import com.telran.mishpahug.entities.FoodEvent;
import com.telran.mishpahug.entities.Profile;
import com.telran.mishpahug.repository.IMyEventsHistoryCRUD;
import com.telran.mishpahug.services.parser.IParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class IMyEventsHistoryCRUDModel implements IMyEventsHistory {

    @Autowired
    IParser parseHistoryList;

    @Autowired
    IMyEventsHistoryCRUD historyListRepo;

    @Override
    @Transactional
    public ResponseEntity getMyEventsHistoryList(String token64) {
        String[] emailPass = parseHistoryList.parseToken(token64);
        Profile profile = historyListRepo.findProfile(emailPass[0], emailPass[1]);
        if(profile!=null){
          List<Event> sortedEvents = getSortedListHistory(profile);
          return new ResponseEntity<>(new ListMyEventHistoryDTORes(getListItems(sortedEvents)),HttpStatus.OK);
        }
        return new ResponseEntity<>("{{Error_401_sample}}", HttpStatus.UNAUTHORIZED);
    }

    private List<Event> getSortedListHistory(Profile profile){
        return profile.getOwners().stream().filter(x->x.getStatus().equals("Done")).
                sorted(new Comparator<Event>() {
                    @Override
                    public int compare(Event a, Event b) {
                         return a.getDate().equals(b.getDate())?0:a.getDate().isBefore(b.getDate())?1:-1;
                    }
                }).
                collect(Collectors.toList());
    }


    private ArrayList<MyEventHistoryDTORes> getListItems(List<Event>events){
        ArrayList<MyEventHistoryDTORes> res = new ArrayList<>();
        for (Event event: events) {
            res.add(getDTOModel(event));
        }
        return res;
    }


    private MyEventHistoryDTORes getDTOModel(Event event){
        ArrayList<String> foods =(ArrayList<String>) event.getFoodsOfEvent().stream().
                map(FoodEvent::getFood).collect(Collectors.toList());
        return new MyEventHistoryDTORes(event.getEventId(),event.getTitle(),event.getHoliday(),
                event.getConfession(),event.getDate(),foods,event.getDescription(),event.getStatus());
    }
}
