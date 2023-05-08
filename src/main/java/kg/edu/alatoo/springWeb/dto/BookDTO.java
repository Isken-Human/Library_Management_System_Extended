package kg.edu.alatoo.springWeb.dto;

import jakarta.persistence.*;
import kg.edu.alatoo.springWeb.modules.Author;
import kg.edu.alatoo.springWeb.modules.Borrower;
import kg.edu.alatoo.springWeb.modules.Publisher;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class BookDTO {
    @Id
    private long id;
    @Column
    private String title;
    @Column
    private String isbn;
    @Column
    private int publishedYear;
    @ManyToMany
    private Set<Author> authors;

    @ManyToOne
    private Publisher publisher;
    public Set<String> getAuthorsNames() {
        Set<String> authorNames = new HashSet<>();
        for (Author author : authors) {
            authorNames.add(author.getName() + " " + author.getSurname());
        }
        return authorNames;
    }
    public String getPublisherName() {
        return publisher.getName();
    }

}
