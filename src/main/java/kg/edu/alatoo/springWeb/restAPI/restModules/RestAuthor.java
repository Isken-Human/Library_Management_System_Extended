package kg.edu.alatoo.springWeb.restAPI.restModules;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class RestAuthor {

    @Id @GeneratedValue
    private long id;

    private String name;
    private String surname;

    @ManyToMany(mappedBy = "authors")
    @JsonBackReference
    private Set<RestBook> books;
}
