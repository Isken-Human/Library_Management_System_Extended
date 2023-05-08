package kg.edu.alatoo.springWeb.repos;


import kg.edu.alatoo.springWeb.modules.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByIsbn(String isbn);


    List<Book> findByTitleContainingIgnoreCase(String searchTerm);

    List<Book> findByAuthorsNameContainingIgnoreCase(String searchTerm);

    List<Book> findByIsbnContainingIgnoreCase(String searchTerm);







    @Query(value = "SELECT image FROM Book WHERE id = :id", nativeQuery = true)
    byte[] findImageById(@Param("id") Long id);


}