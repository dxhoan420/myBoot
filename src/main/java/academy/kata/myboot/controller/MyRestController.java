package academy.kata.myboot.controller;

import academy.kata.myboot.model.User;
import academy.kata.myboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class MyRestController {

    private UserService userService;

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
}
