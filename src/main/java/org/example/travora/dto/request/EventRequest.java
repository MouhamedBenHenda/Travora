package org.example.travora.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.travora.model.EventType;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    private String nameEvent ;
    private String description ;
    private Date date_Begin_Event ;
    private Date date_final_Event ;
    private String placeEvent ;
    private Double latitude;

    private String CategoryId ;


    private Double longitude;

    private Long nbr_Of_Personne ;
    private LocalDateTime startDateSale ;
    private  LocalDateTime finalDateofSale ;
    @Enumerated(EnumType.STRING)
    private EventType eventType ;

}
