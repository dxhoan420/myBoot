package academy.kata.myboot.controller;

import academy.kata.myboot.model.User;
import academy.kata.myboot.service.RoleService;
import academy.kata.myboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class UserController {
    private RoleService roleService;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String getRoot() {
        return "redirect: /user";
    }

    @GetMapping("/user")
    public String getUserPage() {
        return "new_user";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "new_admin";
    }

//    @PostMapping("/admin")
//    public String createUser(@ModelAttribute("newuser") User user,
//                             @RequestParam(value="checked", required = false) Long[] checked) {
//        user.setAuthorities(roleService.getRolesById(checked));
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userService.saveUser(user);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/admin/delete/{id}")
//    public String getDeleteForm(@PathVariable("id") long id) {
//        userService.deleteUser(id);
//        return "redirect:/admin";
//    }
//
//    @PatchMapping("/admin/{id}")
//    public String editUser(@PathVariable("id") Long id, @ModelAttribute("user") User user,
//                           @RequestParam(value="checked", required = false) Long[] checked) {
//        user.setAuthorities(roleService.getRolesById(checked));
//        userService.editUser(user, id);
//        return "redirect:/admin";
//    }
}