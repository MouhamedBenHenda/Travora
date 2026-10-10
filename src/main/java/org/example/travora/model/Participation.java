package org.example.travora.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Participation {

    @Id
    private String id  = UUID.randomUUID().toString() ;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore

    private  User user ;
    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnore

    private Event event ;


    @Enumerated(EnumType.STRING)
    private ParticipationStatus status;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    @JsonIgnore
    private Chat chat ;


    @ManyToOne
    @JoinColumn(name = "chat_group_id")
    @JsonIgnore
    private ChatGroup chatGroup ;

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }

        if (this.status == null) {
            this.status = ParticipationStatus.PENDING;
        }
    }
}
