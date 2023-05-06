package kg.edu.alatoo.springWeb.restAPI.restModules;

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
@Table(name = "borrowers")
public class RestBorrower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private long phone_number;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "borrower_book",
//            joinColumns = @JoinColumn(name = "borrower_id"),
//            inverseJoinColumns = @JoinColumn(name = "book_id"))
//    private Set<Book> books;


    @ManyToMany
    @JsonManagedReference
    private Set<RestBook> books;


}
