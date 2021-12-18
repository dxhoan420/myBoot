package academy.kata.myboot.model;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity @Table(name = "users")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @ToString.Exclude
    private Collection<Role> authorities;

    public String getAllRoles() {
        StringBuilder resultString = new StringBuilder();
        if (authorities != null) {
            for (Role role: authorities) {
                resultString.append(role.getRoleName());
                resultString.append(' ');
            }
        }
        return resultString.toString();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
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
