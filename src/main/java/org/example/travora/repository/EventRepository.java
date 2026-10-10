package org.example.travora.repository;

import org.example.travora.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event , String> {

}
