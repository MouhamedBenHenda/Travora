
        package org.example.travora.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "qr_code")
public class QrCode {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private QrCodeStatusEnum qrCodeStatusEnum;

    @Column(name = "value", nullable = false)
    private String values;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QrCode qrCode = (QrCode) o;

        return Objects.equals(getId(), qrCode.getId())
                && getQrCodeStatusEnum() == qrCode.getQrCodeStatusEnum()
                && Objects.equals(getValues(), qrCode.getValues())
                && Objects.equals(getCreateAt(), qrCode.getCreateAt())
                && Objects.equals(getUpdatedAt(), qrCode.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getQrCodeStatusEnum(),
                getValues(),
                getCreateAt(),
                getUpdatedAt()
        );
    }
}
