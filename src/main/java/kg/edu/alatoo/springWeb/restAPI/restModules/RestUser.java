package kg.edu.alatoo.springWeb.restAPI.restModules;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "auth_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestUser {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RestRole> roles = new HashSet<>();



}

