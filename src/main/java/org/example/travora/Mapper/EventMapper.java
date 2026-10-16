package org.example.travora.Mapper;

import org.example.travora.dto.response.EventResponse;
import org.example.travora.model.Event;
import org.springframework.stereotype.Service;

@Service

public class EventMapper {
    public EventResponse fromEvent (Event event){


            EventResponse response = new EventResponse();
           response.setNameEvent(event.getName());
            response.setId(event.getId());
            response.setDescription(event.getDescription());
            response.setDate_Begin_Event(event.getDate_Begin_Event());
            response.setDate_final_Event(event.getDate_final_Event());
            response.setPlaceEvent(event.getPlaceEvent());
            response.setLatitude(event.getLatitude());
            response.setLongitude(event.getLongitude());
            response.setNbr_Of_Personne(event.getNbr_Of_Personne());
            response.setRole(event.getEventType());
            response.setStartDateSale(event.getStartDateSale());
            response.setFinalDateofSale(event.getFinalDateofSale());
            response.setEventType(event.getEventType());

            response.setUserId(
                    event.getUser() == null ? null : event.getUser().getId()
            );

            response.setCategoryId(
                    event.getCategory() == null ? null : event.getCategory().getId()
            );

            return response;
        }



}
