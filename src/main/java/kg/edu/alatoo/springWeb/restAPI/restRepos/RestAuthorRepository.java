package kg.edu.alatoo.springWeb.restAPI.restRepos;



import kg.edu.alatoo.springWeb.restAPI.restModules.RestAuthor;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RestAuthorRepository extends CrudRepository<RestAuthor, Long> {

    Set<RestAuthor> findAll();
}
