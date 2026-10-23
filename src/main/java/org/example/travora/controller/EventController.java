        package org.example.travora.controller;

import lombok.RequiredArgsConstructor;
import org.example.travora.dto.request.EventRequest;
import org.example.travora.dto.response.EventResponse;
import org.example.travora.model.EventSatatusEnum;
import org.example.travora.service.interfaceS.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EventController {

    private final EventService eventService;

    // Ajouter un événement
    @PostMapping("/add/{userId}")
    public ResponseEntity<EventResponse> addEvent(
            @RequestBody EventRequest eventRequest,
            @PathVariable String userId) {

        EventResponse response =
                eventService.addEvent(eventRequest, userId);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Modifier un événement
    @PutMapping("/{eventId}")
    public ResponseEntity<EventResponse> updateEvent(
            @PathVariable String eventId,
            @RequestBody EventRequest eventRequest) {

        EventResponse response =
                eventService.updateEvent(eventRequest, eventId);

        return ResponseEntity.ok(response);
    }

    // Supprimer un événement
    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(
            @PathVariable String eventId) {

        eventService.deleteEvent(eventId);

        return ResponseEntity.noContent().build();
    }

    // Récupérer tous les événements
    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvent() {

        List<EventResponse> events = eventService.getAllEvent();

        return ResponseEntity.ok(events);
    }

    // Récupérer un événement par son ID
    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponse> getEventbyId(
            @PathVariable String eventId) {

        EventResponse event = eventService.getEventbyId(eventId);

        return ResponseEntity.ok(event);
    }

    // Récupérer tous les événements d'un organisateur
    @GetMapping("/organiser/{userId}")
    public ResponseEntity<List<EventResponse>> getEventAllByOrganiser(
            @PathVariable String userId) {

        List<EventResponse> events =
                eventService.getEventAllByOrganiser(userId);

        return ResponseEntity.ok(events);
    }

    // Récupérer les événements par statut (PUBLISHED, DRAFT, etc.)
    @GetMapping("/status/{eventSatatusEnum}")
    public ResponseEntity<List<EventResponse>> getAllEventByStauts(
            @PathVariable EventSatatusEnum eventSatatusEnum) {

        List<EventResponse> events =
                eventService.getAllEventByStauts(eventSatatusEnum);

        return ResponseEntity.ok(events);
    }
}
