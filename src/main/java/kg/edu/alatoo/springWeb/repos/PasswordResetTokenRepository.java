package kg.edu.alatoo.springWeb.repos;

import kg.edu.alatoo.springWeb.modules.PasswordResetToken;
import kg.edu.alatoo.springWeb.modules.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
    Optional<PasswordResetToken> findByUser(User user);
}