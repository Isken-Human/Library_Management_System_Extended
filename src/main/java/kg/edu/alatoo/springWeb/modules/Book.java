package kg.edu.alatoo.springWeb.modules;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Book {

    @Id @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String isbn;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB",nullable = false)
    private byte[] image;
    @Column(nullable = false)
    private int publishedYear;

    @ManyToMany
    private Set<Author> authors;

    @ManyToMany(mappedBy = "books")
    private Set<Borrower> borrowers;

    @ManyToOne
    private Publisher publisher;

    public Book() {
    }

    public Book(String title, String isbn) {
            this.title = title;
            this.isbn = isbn;
    }

    public long getId() {
        return id;
    }

    public Book setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<Author> getAuthors() {
        return authors;
    }


    public Set<String> getAuthorsNames() {
        Set<String> authours_name = new HashSet<>() ;
        for (Author author:
             authors) { authours_name.add(author.getName()+" "+author.getSurname());}

        return authours_name;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }




    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", image='" + image +
                '}';
    }

    public Book addAuthor(Author author) {
        if (authors == null)
            authors = new HashSet<>();

        authors.add(author);
        return this;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public Publisher getPublisher() {
        return publisher;
    }
    public String getPublisherName() {
        return publisher.getName();
    }


    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void setPublishedYear(int published_year) {
        this.publishedYear = published_year;
    }


}
