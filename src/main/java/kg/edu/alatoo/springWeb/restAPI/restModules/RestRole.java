package kg.edu.alatoo.springWeb.restAPI.restModules;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestRole {
    @Id
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<RestUser> users;

    public RestRole(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "ROLE_" + roleName;
    }
}