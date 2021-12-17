package academy.kata.myboot.service;

import academy.kata.myboot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUser();

    User getUser(Long id);

    boolean saveUser(User user);

    boolean deleteUser(Long id);

    public User findUserByEmail(String email);
}
