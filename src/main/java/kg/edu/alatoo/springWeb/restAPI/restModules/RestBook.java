package kg.edu.alatoo.springWeb.restAPI.restModules;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RestBook {

    @Id @GeneratedValue
    private long id;

    @Column
    private String title;
    private String isbn;

    private int publishedYear;

    @ManyToMany
    @JsonManagedReference
    private Set<RestAuthor> authors;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name = "book_borrower",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "borrower_id")
    )
    private Set<RestBorrower> borrowers;

    @ManyToOne
    @JsonBackReference
    private RestPublisher publisher;









}
