package org.example.travora.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tickets")
public class Ticket {
    @Id
    @Column(name = "id" , nullable = false , updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;
    @Column(name = "status" , nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus status ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_type_id")
    private TicketType ticketType ;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "purchaser_id")
    private User purchaser ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(getId(), ticket.getId()) && getStatus() == ticket.getStatus() && Objects.equals(getCreateAt(), ticket.getCreateAt()) && Objects.equals(getUpdatedAt(), ticket.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getCreateAt(), getUpdatedAt());
    }

    @CreatedDate
    @Column(name = "created_at" , updatable = false , nullable = false)
    private LocalDateTime createAt ;
@LastModifiedDate
    @Column(name = "updated_at" , nullable = false)
    private LocalDateTime updatedAt ;
@OneToMany(mappedBy ="ticket" , cascade = CascadeType.ALL)
    private List<TicketValidation> ticketValidations = new ArrayList<>() ;

@OneToMany(mappedBy = "ticket" , cascade = CascadeType.ALL)
    private List<QrCode> qrCodes = new ArrayList<>() ;
}
