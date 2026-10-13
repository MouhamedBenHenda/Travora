package org.example.travora.controller;

import lombok.RequiredArgsConstructor;
import org.example.travora.model.Participation;
import org.example.travora.service.interfaceS.ParticipationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/participations")
@RequiredArgsConstructor
public class ParticipationController {
    private final ParticipationService participationService;


    // Demander à rejoindre un événement
    @PostMapping("/request/{userId}/{eventId}")
    public Participation requestToJoinEvent(
            @PathVariable String userId,
            @PathVariable String eventId) {

        return participationService.requestToJoinEvent(userId, eventId);
    }

    // Accepter une demande de participation
    @PutMapping("/accept/{participationId}/{creatorId}")
    public Participation acceptParticipation(
            @PathVariable String participationId,
            @PathVariable String creatorId) {

        return participationService.acceptParticipation(
                participationId, creatorId);
    }

    // Refuser une demande de participation
    @PutMapping("/reject/{participationId}/{creatorId}")
    public Participation rejectParticipation(
            @PathVariable String participationId,
            @PathVariable String creatorId) {

        return participationService.rejectParticipation(
                participationId, creatorId);
    }

}
