package org.example.travora.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.travora.model.Role;

import java.lang.reflect.Member;
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


}
