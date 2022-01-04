package academy.kata.myboot.controller;

import academy.kata.myboot.model.Role;
import academy.kata.myboot.model.User;
import academy.kata.myboot.service.RoleService;
import academy.kata.myboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        String result;
        if (userService.deleteUser(id)) {
            result = "User with id '" + id + "' was deleted";
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON)
                    .body("{\"status\":\"fail\", \"message\":\"" + result + "\"}");
        } else {
            result = "Can't find user with id: '" + id + "'";
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON)
                    .body("{\"status\":\"success\", \"message\":\"" + result + "\"}");
        }
    }
}
