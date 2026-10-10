package org.example.travora.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Category {
@Id
    private String id =  UUID.randomUUID().toString();
private String name ;
@OneToMany(cascade = CascadeType.ALL , mappedBy = "category")
    private List<Event> events ;




}
