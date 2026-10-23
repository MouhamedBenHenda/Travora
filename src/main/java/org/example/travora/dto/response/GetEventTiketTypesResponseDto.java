package org.example.travora.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEventTiketTypesResponseDto {
private UUID id ;
private String name ;
private Double price ;
private String description ;
private LocalDateTime createdAt ;
    private LocalDateTime updateTimeAt ;




}
