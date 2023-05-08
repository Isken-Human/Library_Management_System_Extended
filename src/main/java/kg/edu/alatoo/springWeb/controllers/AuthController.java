package kg.edu.alatoo.springWeb.controllers;

import jakarta.validation.Valid;
import kg.edu.alatoo.springWeb.dto.UserDto;
import kg.edu.alatoo.springWeb.modules.PasswordResetToken;
import kg.edu.alatoo.springWeb.modules.User;
import kg.edu.alatoo.springWeb.repos.BookRepository;
import kg.edu.alatoo.springWeb.services.EmailService;
import kg.edu.alatoo.springWeb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.util.UUID;

@Controller
public class AuthController {

    @Autowired
    private BookRepository bookRepository;
    private UserService userService;
    private EmailService emailService;

    public AuthController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @PostMapping("/reset-password-request")
    public ResponseEntity<?> resetPasswordRequest(@RequestParam("email") String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);
        try {
            emailService.sendPasswordResetEmail(user.getEmail(), "Password Reset Request", token);
        } catch (MessagingException e) {
            // You can log the exception here or return an error message to the user
            return new ResponseEntity<>("Failed to send password reset email", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>("Password reset email sent", HttpStatus.OK);
    }


    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam("token") String token,
                                           @RequestParam("password") String newPassword) {
        PasswordResetToken passwordResetToken = userService.getPasswordResetToken(token);
        if (passwordResetToken == null) {
            return new ResponseEntity<>("Invalid token", HttpStatus.BAD_REQUEST);
        }

        User user = passwordResetToken.getUser();
        userService.updatePassword(user, newPassword);
        userService.deletePasswordResetToken(passwordResetToken);

        return new ResponseEntity<>("Password successfully updated", HttpStatus.OK);
    }

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password";
    }

}