package org.example.travora.service;

import lombok.RequiredArgsConstructor;
import org.example.travora.model.Event;
import org.example.travora.model.Participation;
import org.example.travora.model.ParticipationStatus;
import org.example.travora.model.User;
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
        return null;
    }

    @Override
    public Participation rejectParticipation(String participationId, String creatorId) {
        return null;
    }
}
