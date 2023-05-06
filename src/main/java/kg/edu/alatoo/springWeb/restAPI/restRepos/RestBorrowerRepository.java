package kg.edu.alatoo.springWeb.restAPI.restRepos;

import kg.edu.alatoo.springWeb.restAPI.restModules.RestBorrower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestBorrowerRepository extends JpaRepository<RestBorrower, Long> {

//    List<Borrower> findAll(Sort sort);
}
