package org.example.travora.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tiket_validations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketValidation {
@Id
    @Column(name = "id" ,nullable = false , updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;

    @Column(name = "status" , nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketValidationStatus status ;


    @Column(name = "validation_methode" , nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketValidationMethodEnum validationMethodEnum ;


    @CreatedDate
    @Column(name = "created_at" , updatable = false , nullable = false)
    private LocalDateTime createAt ;


    @LastModifiedDate
    @Column(name = "update_at" ,nullable = false)
    private LocalDateTime updatedAt ;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "ticket_id")
    private Ticket ticket ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketValidation that = (TicketValidation) o;
        return Objects.equals(getId(), that.getId()) && getStatus() == that.getStatus() && Objects.equals(getCreateAt(), that.getCreateAt()) && Objects.equals(getUpdatedAt(), that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getCreateAt(), getUpdatedAt());
    }
}
