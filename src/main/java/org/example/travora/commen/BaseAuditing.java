package org.example.travora.commen;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseAuditing {

    @CreatedDate
    @Column(name = "createDate" , nullable = true , updatable = false)
    private LocalDateTime creLocalDate ;
    @LastModifiedDate
    @Column(name= "lastModifiedData" , nullable = true , updatable = false)
    private LocalDateTime lastDate ;

}
