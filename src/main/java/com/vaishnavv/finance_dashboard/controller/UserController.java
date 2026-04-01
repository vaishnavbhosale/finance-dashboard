package com.vaishnavv.finance_dashboard.controller;

import com.vaishnavv.finance_dashboard.dto.UpdateRoleRequest;
import com.vaishnavv.finance_dashboard.model.User;
import com.vaishnavv.finance_dashboard.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}/role")
    public User updateUserRole(@PathVariable Long id,
                               @RequestBody UpdateRoleRequest request) {
        return userService.updateUserRole(id, request.getRole());
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }
}