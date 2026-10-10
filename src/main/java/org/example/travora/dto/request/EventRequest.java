package org.example.travora.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    ;
    private String description ;
    private Date date_Begin_Event ;
    private Date date_final_Event ;
    private String placeEvent ;
    private Double latitude;

    private String CategoryId ;


    private Double longitude;

    private Long nbr_Of_Personne ;

}
