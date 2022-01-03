package academy.kata.myboot.controller;

import academy.kata.myboot.model.Role;
import academy.kata.myboot.model.User;
import academy.kata.myboot.service.RoleService;
import academy.kata.myboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class MyRestController {

    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    @Secured("ROLE_ADMIN")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/current")
    @PreAuthorize("isAuthenticated()")
    public User getCurrent(Principal principal) {
        return userService.findUserByEmail(principal.getName());
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping("/users")
    @Secured("ROLE_ADMIN")
    public User createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.saveUser(user);
        return user;
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (userService.deleteUser(id)) {
            return "User with email '" + userService.getUser(id).getEmail() + "' was deleted";
        } else {
            return "Can't find user with id: " + id;
        }
    }
}
