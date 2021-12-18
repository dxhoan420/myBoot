package academy.kata.myboot.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public final class PasswordService {
    @Bean
    @Scope("singleton")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
