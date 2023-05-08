package kg.edu.alatoo.springWeb.repos;

import kg.edu.alatoo.springWeb.dto.BookDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDTORepository extends JpaRepository<BookDTO, Long> {
    BookDTO findByIsbn(String isbn);


    List<BookDTO> findByTitleContainingIgnoreCase(String searchTerm);

    List<BookDTO> findByAuthorsNameContainingIgnoreCase(String searchTerm);

    List<BookDTO> findByIsbnContainingIgnoreCase(String searchTerm);

}
