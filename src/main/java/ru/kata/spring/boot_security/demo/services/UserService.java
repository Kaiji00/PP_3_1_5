package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.models.User;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByName(String username) {
        return userRepository.findByUserName(username);
    }


    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
    @Transactional
    public void saveUser(User user) {
        userRepository.saveUser(user);
    }
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteUserById(id);
    }
    @Transactional
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByName(username);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                user.getAuthorities());
    }
}
