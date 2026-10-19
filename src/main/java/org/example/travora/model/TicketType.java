package org.example.travora.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "table_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketType {
    @Id
    @Column(name = "id" , nullable = false , updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;

    @Column(name = "name" , nullable = false)
    private String name ;

    @Column(name = "description" , nullable = false)
    private String description ;

    @Column(name = "price" , nullable = false)
    private Double price ;
    @Column(name = "total_available")
    private Integer totalAvailable ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event ;

    @CreatedDate
    @Column(name = "create_at" , nullable = false , updatable = false)
    private LocalDateTime createAt ;
    @LastModifiedDate
    @Column(name = "update_at" , nullable = false)
    private LocalDateTime updateAt ;

    @OneToMany(mappedBy = "ticketType" , cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>() ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketType that = (TicketType) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getPrice(), that.getPrice()) && Objects.equals(getTotalAvailable(), that.getTotalAvailable()) && Objects.equals(getCreateAt(), that.getCreateAt()) && Objects.equals(getUpdateAt(), that.getUpdateAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getTotalAvailable(), getCreateAt(), getUpdateAt());
    }
}
