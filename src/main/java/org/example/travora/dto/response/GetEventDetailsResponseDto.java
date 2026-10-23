package org.example.travora.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.travora.model.EventSatatusEnum;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEventDetailsResponseDto {
private String id ;
private String name ;
private LocalDateTime start ;
private LocalDateTime end ;
private  String venue ;
private LocalDateTime salesStart ;
private LocalDateTime salesEnd ;
private EventSatatusEnum eventSatatusEnum ; 

}
