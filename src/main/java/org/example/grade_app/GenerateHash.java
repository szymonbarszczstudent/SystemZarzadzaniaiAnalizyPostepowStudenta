package org.example.grade_app;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class GenerateHash {
    public static void main(String[] args) {
        String rawPassword = "test123";
        String hash = Argon2PasswordEncoder
                .defaultsForSpringSecurity_v5_8()
                .encode(rawPassword);

        System.out.println(hash);
    }
}
