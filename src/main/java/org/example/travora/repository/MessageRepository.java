package org.example.travora.repository;

import org.example.travora.model.ChatGroup;
import org.example.travora.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message , String > {

    List<Message> findByChatGroup_IdOrderByCreLocalDateAsc(String groupId);
}
