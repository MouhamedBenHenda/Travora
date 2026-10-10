package org.example.travora.model;

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


public class Chat extends BaseAuditing {

    @Id
    private String id = UUID.randomUUID().toString() ;
    private String name ;
    private String description ;
    private Long reciverId ;
    private Long senderId ;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "chat")
    private List<Message> messages ;

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }}
}
