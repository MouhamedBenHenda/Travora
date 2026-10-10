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
public class GroupEvent {
    @Id
    private  String id  = UUID.randomUUID().toString();
    private String name ;
    private String description ;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "group_id")
    private User user ;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "groupEvent_id")
    private Event event ;
    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }


}
