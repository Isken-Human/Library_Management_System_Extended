package kg.edu.alatoo.springWeb.repos;

import kg.edu.alatoo.springWeb.modules.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
