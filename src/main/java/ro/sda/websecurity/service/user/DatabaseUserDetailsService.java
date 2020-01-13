package ro.sda.websecurity.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.sda.websecurity.persistence.user.UserEntity;
import ro.sda.websecurity.persistence.user.UserRepository;

/*
 * UserDetailsService = logica de conversie de la formatul nostru (DB) la formatul care il vrea Spring
 * MyUser = implementare pentru formatul ce il vrea Spring
 * SecurityConfig = spunem care logica de conversie sa fie folosita
 */

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    @Autowired
    public DatabaseUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByUsername(username);
        if (userEntity == null){
            throw new UsernameNotFoundException("Could not find username");
        }
        if (userEntity.getRole() == null || userEntity.getRole().isEmpty()) {
            throw new UsernameNotFoundException("No role found for user");
        }
        return new MyUser(userEntity.getUsername(), userEntity.getPassword(), userEntity.getRole(), userEntity.getAge());
    }
}