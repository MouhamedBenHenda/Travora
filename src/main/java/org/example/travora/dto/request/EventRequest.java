package org.example.travora.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.travora.model.EventType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
//information al local venue
    private String venue ;
    private Double longitude;

    private Long nbr_Of_Personne ;
    private LocalDateTime startDateSale ;
    private  LocalDateTime finalDateofSale ;
    @Enumerated(EnumType.STRING)
    private EventType eventType ;
//zedet hethith zedaa ta3 tikiyett
    private List<CreateTicketTypeRequest> ticketTypeRequests = new ArrayList<>() ;

}
