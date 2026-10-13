package org.example.travora.service;

import lombok.RequiredArgsConstructor;
import org.example.travora.model.*;
import org.example.travora.repository.EventRepository;
import org.example.travora.repository.ParticipationRepository;
import org.example.travora.repository.UserRepository;
import org.example.travora.service.interfaceS.ParticipationService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipationServiceImpl implements ParticipationService {
    private final UserRepository userRepository ;
    private final ParticipationRepository participationRepository ;
    private final EventRepository eventRepository ;

    @Override
    public Participation requestToJoinEvent(String userId, String eventId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(" user not found")) ;
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("event not found")) ;
        if (participationRepository.existsByUserIdAndEventId(userId , eventId)) {
            throw  new RuntimeException("Participation request already exists") ;


        }

        Participation participation = new Participation() ;
        participation.setUser(user);
        participation.setEvent(event);
        participation.setStatus(ParticipationStatus.PENDING);
        return participationRepository.save(participation);

    }

    @Override
    public Participation acceptParticipation(String participationId, String creatorId) {

        Participation participation = participationRepository.findById(participationId).orElseThrow(() -> new RuntimeException("participation not found")) ;
        Event event = participation.getEvent() ;
        if (event == null) {
            throw  new RuntimeException("event not found") ;

        }

        if(participation.getStatus() != ParticipationStatus.PENDING) {
            throw new RuntimeException("the participation is not pending") ;
        }
        ChatGroup chatGroup = event.getChatGroup() ;
        if (chatGroup == null) {
            throw new RuntimeException("chat group not found") ;

        }
        participation.setChatGroup(chatGroup);
        participation.setStatus(ParticipationStatus.ACCEPTED);

        User user = userRepository.findById(creatorId).orElseThrow(() -> new RuntimeException( "user not found")) ;
        user.setRole(Role.NORMAL_ADVENTURER);
        participation.setUser(user);
        return  participationRepository.save(participation) ;

    }

    @Override
    public Participation rejectParticipation(String participationId, String creatorId) {
        Participation participation = participationRepository.findById(participationId)
                .orElseThrow(() -> new RuntimeException("Participation not found"));

        Event event = participation.getEvent();

        if (event == null) {
            throw new RuntimeException("Event not found");
        }


        if (event.getUser() == null ||
                !event.getUser().getId().equals(creatorId)) {
            throw new RuntimeException("You are not the creator of this event");
        }

        // Vérifier que la demande est encore en attente
        if (participation.getStatus() != ParticipationStatus.PENDING) {
            throw new RuntimeException("Participation is not pending");
        }

        // Refuser la demande
        participation.setStatus(ParticipationStatus.REJECTED);
        User user = userRepository.findById(creatorId).orElseThrow(() -> new RuntimeException("user notr found")) ;
        user.setRole(Role.USER);

        participation.setUser(user);

        return participationRepository.save(participation);
    }
}
