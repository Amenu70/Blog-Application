package cs544.config;

import cs544.Model.Role;
import cs544.Model.User;
import cs544.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private IUserRepository userRepository;

    @Autowired
    public DataLoader(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        // Create a new user with the role ADMIN
        User adminUser1 = User.builder()
                .name("Amensisa Wirtu")
                .email("amensisawirtu.edossa@miu.edu")
                .password(new BCryptPasswordEncoder().encode("secretpassword1"))
                .about("I am an admin user1")
                .role(Role.ADMIN)
                .build();
        User adminUser2 = User.builder()
                .name("Fraol Gerba")
                .email("firaolduguma.geraba@miu.edu")
                .password(new BCryptPasswordEncoder().encode("secretpassword2"))
                .about("I am an admin user2")
                .role(Role.ADMIN)
                .build();
        // Save the user to the database
        userRepository.save(adminUser1);
        userRepository.save(adminUser2);
    }
}
