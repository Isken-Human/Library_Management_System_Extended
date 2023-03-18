package kg.edu.alatoo.springWeb.modules;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "borrowers")
public class Borrower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private long phone_number;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "borrower_book",
            joinColumns = @JoinColumn(name = "borrower_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books;

    public Set<String> getBooksTitle() {
        Set<String> book_title = new HashSet<>() ;
        for (Book books:
                books) { book_title.add(books.getTitle());}

        return book_title;
    }


    public Borrower() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrower borrower = (Borrower) o;
        return id.equals(borrower.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Borrower{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", phone_number=" + phone_number + ", books=" + books + '}';
    }


    public Borrower(Long id, String name, String email, long phone_number, Set<Book> books) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.books = books;
    }
}
