package org.example.travora.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.travora.commen.BaseAuditing;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Message extends BaseAuditing {
@Id
    private  String id = UUID.randomUUID().toString() ;
private String content ;
@ManyToOne
    @JoinColumn(name = "chat_group_id")
    @JsonIgnore
    private ChatGroup chatGroup ;
     private String senderId ;

     private MessageState messageState ;

@ManyToOne
    @JoinColumn(name = "chat_id")
    @JsonIgnore
    private Chat chat ;
private MessageType messageType ;
private ChatParticipation chatParticipation ;
private String mediaFilePath ;
}
