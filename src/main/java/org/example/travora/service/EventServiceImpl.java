package org.example.travora.service;

import lombok.RequiredArgsConstructor;
import org.example.travora.Mapper.EventMapper;
import org.example.travora.dto.request.EventRequest;
import org.example.travora.dto.response.EventResponse;
import org.example.travora.model.Category;
import org.example.travora.model.Event;
import org.example.travora.model.Role;
import org.example.travora.model.User;
import org.example.travora.repository.CategoryRepository;
import org.example.travora.repository.EventRepository;
import org.example.travora.repository.UserRepository;
import org.example.travora.service.interfaceS.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final UserRepository userRepository ;
    private final EventRepository eventRepository  ;
    private final CategoryRepository categoryRepository ;
    private final EventMapper eventMapper ;
    @Override
    public EventResponse addEvent(EventRequest eventRequest ,String userId ) {


        Event event = new Event() ;
        event.setDescription(eventRequest.getDescription());
        event.setDate_Begin_Event(eventRequest.getDate_Begin_Event());
        event.setDate_final_Event(eventRequest.getDate_final_Event());
        event.setPlaceEvent(eventRequest.getPlaceEvent());
        event.setLatitude(eventRequest.getLatitude());
        event.setLongitude(eventRequest.getLongitude());
        event.setNbr_Of_Personne(eventRequest.getNbr_Of_Personne());

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not founnd")) ;
        user.setRole(Role.CREATOR);
        userRepository.save(user) ;
        event.setUser(user);

        Category category = categoryRepository.findById(eventRequest.getCategoryId()).orElseThrow(() -> new RuntimeException("category not found")) ;
        event.setCategory(category);
        Event savedEvent = eventRepository.save(event) ;
        return  eventMapper.fromEvent(savedEvent) ;


    }

    @Override
    public EventResponse updateEvent(EventRequest eventRequest, String eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("event not found")) ;
        if (event != null) {
            event.setDescription(eventRequest.getDescription());
            event.setDate_Begin_Event(eventRequest.getDate_Begin_Event());
            event.setDate_final_Event(eventRequest.getDate_final_Event());
            event.setPlaceEvent(eventRequest.getPlaceEvent());
            event.setLatitude(eventRequest.getLatitude());
            event.setLongitude(eventRequest.getLongitude());
            event.setNbr_Of_Personne(eventRequest.getNbr_Of_Personne());



            Category category = categoryRepository.findById(eventRequest.getCategoryId()).orElseThrow(() -> new RuntimeException("category not found")) ;
            event.setCategory(category);
            Event savedEvent = eventRepository.save(event) ;
            return  eventMapper.fromEvent(savedEvent) ;

        }

        return null;
    }

    @Override
    public void deleteEvent(String eventId) {
     Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("event not found")) ;
        eventRepository.delete(event);
    }

    @Override
    public List<EventResponse> getAllEvent() {

        return eventRepository.findAll()
                .stream()
                .map(eventMapper::fromEvent)
                .collect(Collectors.toList());
    }
    @Override
    public EventResponse getEventbyId(String eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        return eventMapper.fromEvent(event);
    }
}
