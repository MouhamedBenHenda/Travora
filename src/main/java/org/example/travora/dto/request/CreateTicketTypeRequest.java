package org.example.travora.dto.request;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketTypeRequest {
    private String name ;
    @PositiveOrZero(message = "price must be zero or greater")
    
    private Double price ;
    private Integer totalAvailable;
    private String description ;
}
