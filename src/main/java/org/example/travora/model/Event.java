package org.example.travora.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Event {
@Id

private String id  = UUID.randomUUID().toString();
    ;
private String description ;
private Date date_Begin_Event ;
private Date date_final_Event ;
private String placeEvent ;
    private Double latitude;

    private Double longitude;

private Long nbr_Of_Personne ;

    @Enumerated(EnumType.STRING)
    private Role role;

@ManyToOne
@JoinColumn(name = "user_id")
@JsonIgnore
    private User user ;
@ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category ;

@OneToMany(cascade = CascadeType.ALL , mappedBy = "event")
    private List<GroupEvent> groupEvents ;

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

}
