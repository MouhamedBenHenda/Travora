package org.example.travora.service.interfaceS;

import org.example.travora.model.Participation;

public interface ParticipationService {

    Participation  requestToJoinEvent(String userId , String eventId) ;
    Participation acceptParticipation(String participationId , String creatorId) ;
    Participation rejectParticipation(String participationId , String creatorId) ;

}
