package ibm.java.academy.cerfiticationsapp.service;

// import java.text.MessageFormat;

// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import ibm.java.academy.cerfiticationsapp.model.User;
// import ibm.java.academy.cerfiticationsapp.repository.UserJpaRepository;
// import lombok.AllArgsConstructor;

// @Service
// @AllArgsConstructor
// public class UserService implements UserDetailsService{
    
//     private final UserJpaRepository userRepository;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         User user = userRepository.findByEmail(username);
//         if (user != null) {
//             return user;
//         }else {
//             throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", username));
//         }
//     }


// }
