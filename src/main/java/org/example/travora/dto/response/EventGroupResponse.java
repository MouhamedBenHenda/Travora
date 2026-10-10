package org.example.travora.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EventGroupResponse {
    private String id;
    private String name;
    private String description;
    private String userId;
    private String eventId;
}
