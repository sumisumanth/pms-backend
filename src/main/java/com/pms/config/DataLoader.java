package com.pms.config;








import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pms.entity.User;
import com.pms.repository.UserRepository;

@Configuration
public class DataLoader {

@Bean
CommandLineRunner init(UserRepository repo, PasswordEncoder encoder) {
    return args -> {

        if (repo.findByEmail("admin@gmail.com").isEmpty()) {

            User admin = new User();
            admin.setName("Admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(encoder.encode("admin"));
            admin.setRole("ADMIN");

            repo.save(admin);
        }

    };
}
}
