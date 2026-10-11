package org.example.travora.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.travora.model.EventType;
import org.example.travora.model.Role;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {


    private String id;
    private String description;
    private Date date_Begin_Event;
    private Date date_final_Event;
    private String placeEvent;
    private Double latitude;
    private Double longitude;
    private Long nbr_Of_Personne;
    private Role role;

    private String userId;
    private String categoryId;

    private LocalDateTime startDateSale ;
    private  LocalDateTime finalDateofSale ;
    @Enumerated(EnumType.STRING)
    private EventType eventType ;

}
