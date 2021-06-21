package ibm.java.academy.cerfiticationsapp.UserInit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import ibm.java.academy.cerfiticationsapp.model.User;
import ibm.java.academy.cerfiticationsapp.repository.UserJpaRepository;

@Component
class UserCreator {

    @Autowired
    private UserJpaRepository userRepository;

    @PostConstruct
    public void init() {
        userRepository.save(new User(1L, "admin", "admin", "admin@localhost", new BCryptPasswordEncoder().encode("password")));
        userRepository.save(new User(2L, "manager", "manager", "manager@localhost", new BCryptPasswordEncoder().encode("password")));
        userRepository.save(new User(3L, "student", "student", "student@localhost", new BCryptPasswordEncoder().encode("password")));
    }
}
