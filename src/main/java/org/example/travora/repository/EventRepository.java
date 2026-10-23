package org.example.travora.repository;

import org.example.travora.model.Event;
import org.example.travora.model.EventSatatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event , String> {
   public Event findByUserId (String userId) ;

    Optional<Event> findByIdAndUserId(String eventId , String userId);
  List<Event> findByEventSatatusEnum(EventSatatusEnum eventSatatusEnum) ;



}
