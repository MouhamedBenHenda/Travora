package org.example.travora.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.travora.commen.BaseAuditing;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class ChatGroup extends BaseAuditing {
@Id
    private String id = UUID.randomUUID().toString() ;
private String name ;
private String description ;

private String userCreateChatId ;
    @ElementCollection
    private List<String> memberId ;

@OneToMany(cascade = CascadeType.ALL , mappedBy = "chatGroup")
    private List<Message> messages ;
@OneToOne(mappedBy = "chatGroup")
private Event event ;
@ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    User user ;

}
