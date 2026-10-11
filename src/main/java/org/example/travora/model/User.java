package org.example.travora.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User implements UserDetails {
    private static final int LAST_ACTIVE_INTERVAL = 1;
    @Id

    private String id = UUID.randomUUID().toString();
    ;

 private String password ;

    @Column(unique = true, nullable = false)
    private String mail;
    private String firstname ;
    private String lastName ;
    private Long age ;
@OneToMany(cascade = CascadeType.ALL , mappedBy = "user")
private List<Event> events;
@OneToMany(cascade = CascadeType.ALL , mappedBy = "user")
    private List<Participation> participations ;
@Enumerated(EnumType.STRING)
    private Role role ;
@Enumerated(EnumType.STRING)
private UserMessageStatus userMessageStatus ;
private LocalDateTime lastSeen ;
    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }


@OneToMany(cascade = CascadeType.ALL , mappedBy = "user")
private List<GroupEvent> groupEvents ;

@OneToMany(cascade = CascadeType.ALL , mappedBy = "user")
private List<ChatGroup> chatGroups ;

    @Transient
    public boolean isUseronline() { // difiend is user online or not

        // lastseen = 10:5
        // now 10:9 -> online
        // now = 10:12 -> offline
        return lastSeen != null && lastSeen.isAfter(LocalDateTime.now().minusMinutes(LAST_ACTIVE_INTERVAL));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return mail ;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true ;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
