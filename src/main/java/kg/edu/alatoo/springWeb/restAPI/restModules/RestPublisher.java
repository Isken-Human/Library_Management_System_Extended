package kg.edu.alatoo.springWeb.restAPI.restModules;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "publishers")
@Entity
public class RestPublisher {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column(name = "created_year")
    private Integer year;

    @Column
    private String address;

    @OneToMany(mappedBy = "publisher")
    @JsonManagedReference
    private Set<RestBook> books;
}
