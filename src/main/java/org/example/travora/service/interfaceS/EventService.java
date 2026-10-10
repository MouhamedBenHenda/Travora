package org.example.travora.service.interfaceS;

import org.example.travora.dto.request.EventRequest;
import org.example.travora.dto.response.EventResponse;

import java.util.List;

public interface EventService {

    public EventResponse addEvent (EventRequest eventRequest , String userId) ;
    public  EventResponse updateEvent (EventRequest eventRequest , String eventId) ;
    public void  deleteEvent(String eventId) ;
    public List<EventResponse> getAllEvent() ;
    public EventResponse getEventbyId(String eventId) ;



}
