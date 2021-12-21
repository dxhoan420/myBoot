package academy.kata.myboot.service;

import academy.kata.myboot.Repository.UserRepository;
import academy.kata.myboot.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    @Transactional
    public boolean saveUser(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    @Transactional
    public boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Transactional
    public boolean editUser(User user, Long id) {
        if (!user.getId().equals(id))
            return false;
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            userRepository.flush();
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username);
    }
}
