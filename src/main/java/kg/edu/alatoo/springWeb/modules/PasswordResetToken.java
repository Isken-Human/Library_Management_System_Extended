package kg.edu.alatoo.springWeb.modules;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class PasswordResetToken {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String token;

        @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
        @JoinColumn(nullable = false, name = "user_id")
        private User user;

        private LocalDateTime expiryDate;


        public PasswordResetToken(String token, User user, LocalDateTime expiryDate) {
            this.token = token;
            this.user = user;
            this.expiryDate = expiryDate;
        }
    }

