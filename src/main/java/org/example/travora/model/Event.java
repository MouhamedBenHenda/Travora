package org.example.travora.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Event {
@Id

private String id  = UUID.randomUUID().toString();
private String name ;
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

    private LocalDateTime startDateSale ;
    private  LocalDateTime finalDateofSale ;
    private String venue ;



    @Enumerated(EnumType.STRING)
    private EventType eventType ;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private ChatGroup chatGroup ;
@Enumerated(EnumType.STRING)
@Column(nullable = false)
    private EventSatatusEnum eventSatatusEnum ;

@ManyToMany(mappedBy = "attendingEvents" )
    private List<User> attendes = new ArrayList<>() ;
@ManyToMany(mappedBy = "staffingEvents")
    private List<User> staffEvent = new ArrayList<>() ;

@CreatedDate
    @Column(name = "create_at" , updatable = false , nullable = false)
    private LocalDateTime createAt ;
@LastModifiedDate
    @Column(name = "updated_at" , nullable = false)
    private LocalDateTime updatedAt ;
@OneToMany(mappedBy = "event" , cascade = CascadeType.ALL)
    private List<TicketType> ticketTypes = new ArrayList<>() ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(getId(), event.getId()) && Objects.equals(getName(), event.getName()) && Objects.equals(getDescription(), event.getDescription()) && Objects.equals(getDate_Begin_Event(), event.getDate_Begin_Event()) && Objects.equals(getDate_final_Event(), event.getDate_final_Event()) && Objects.equals(getPlaceEvent(), event.getPlaceEvent()) && Objects.equals(getLatitude(), event.getLatitude()) && Objects.equals(getLongitude(), event.getLongitude()) && Objects.equals(getNbr_Of_Personne(), event.getNbr_Of_Personne()) && getRole() == event.getRole() && Objects.equals(getCategory(), event.getCategory()) && Objects.equals(getGroupEvents(), event.getGroupEvents()) && Objects.equals(getStartDateSale(), event.getStartDateSale()) && Objects.equals(getFinalDateofSale(), event.getFinalDateofSale()) && Objects.equals(getVenue(), event.getVenue()) && getEventType() == event.getEventType() && Objects.equals(getChatGroup(), event.getChatGroup()) && getEventSatatusEnum() == event.getEventSatatusEnum() && Objects.equals(getCreateAt(), event.getCreateAt()) && Objects.equals(getUpdatedAt(), event.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getDate_Begin_Event(), getDate_final_Event(), getPlaceEvent(), getLatitude(), getLongitude(), getNbr_Of_Personne(), getRole(), getCategory(), getGroupEvents(), getStartDateSale(), getFinalDateofSale(), getVenue(), getEventType(), getChatGroup(), getEventSatatusEnum(), getCreateAt(), getUpdatedAt());
    }
}
