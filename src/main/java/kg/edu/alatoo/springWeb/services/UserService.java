package kg.edu.alatoo.springWeb.services;

import kg.edu.alatoo.springWeb.dto.UserDto;
import kg.edu.alatoo.springWeb.modules.PasswordResetToken;
import kg.edu.alatoo.springWeb.modules.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
    void createPasswordResetTokenForUser(User user, String token);

    PasswordResetToken getPasswordResetToken(String token);

    void updatePassword(User user, String newPassword);

    void deletePasswordResetToken(PasswordResetToken passwordResetToken);
}
