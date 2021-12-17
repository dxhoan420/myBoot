package academy.kata.myboot.model;

import lombok.*;
import org.hibernate.NonUniqueResultException;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Entity @Table(name = "roles")
@Getter @Setter @ToString
@NoArgsConstructor @RequiredArgsConstructor
public class Role implements GrantedAuthority {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String authority;
    @Transient
    private String cleanRole;

    public String getCleanRole() {
        if (cleanRole != null) {
            return cleanRole;
        } else if (authority.contains("ROLE_")) {
            cleanRole = authority.substring(5);
            return cleanRole;
        } else {
            return "ERROR";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return getAuthority().equals(role.getAuthority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthority());
    }
}
