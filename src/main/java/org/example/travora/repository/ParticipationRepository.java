package org.example.travora.repository;

import org.example.travora.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation , String> {
    List<Participation> findByEventId(String eventId);

    List<Participation> findByUserId(String userId);
   boolean existsByUserIdAndEventId(String userId, String eventId);



}
