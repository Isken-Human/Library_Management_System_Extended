package kg.edu.alatoo.springWeb.repos;

import kg.edu.alatoo.springWeb.modules.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
