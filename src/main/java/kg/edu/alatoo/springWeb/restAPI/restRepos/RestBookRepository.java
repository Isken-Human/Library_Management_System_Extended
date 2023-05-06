package kg.edu.alatoo.springWeb.restAPI.restRepos;


import kg.edu.alatoo.springWeb.restAPI.restModules.RestBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RestBookRepository extends JpaRepository<RestBook, Long> {
//    Book findByIsbn(String isbn);
//
//
//    List<Book> findAll();
//    List<Book> findByTitleContainingIgnoreCase(String searchTerm);
//
//    List<Book> findByAuthorsNameContainingIgnoreCase(String searchTerm);
//
//    List<Book> findByIsbnContainingIgnoreCase(String searchTerm);
}
