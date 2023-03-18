package kg.edu.alatoo.springWeb.repos;

import kg.edu.alatoo.springWeb.modules.Author;
import kg.edu.alatoo.springWeb.modules.Borrower;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {

    List<Borrower> findAll(Sort sort);
}
