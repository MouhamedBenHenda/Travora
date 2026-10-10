package org.example.travora.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventGroupRequest {
    private String name;
    private String description;
    private String userId;
    private String eventId;
}
