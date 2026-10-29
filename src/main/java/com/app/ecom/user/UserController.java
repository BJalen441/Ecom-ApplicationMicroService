package com.app.ecom.user;

import dto.UserRequest;
import dto.UserResponse;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

//No longer using User.java but instead uses UserResponse
    @GetMapping // All users
    public List<UserResponse> getAllUsers() {
        return userService.fetchAllUser();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) { //Searches for id
        return userService.fetchUser(id);
    }

//@PostMapping ("/api/users") no longer needed with @RequestMapping
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        User savedUser = userService.addUser(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser); //return status 201 now
//        public List<User> createUser(@RequestBody User user)
//    }
    @PostMapping //Handled with import jakarta.validation.Valid; now
//Parameter before update was User user; now using UserRequest request
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(request));
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest request) {
        return userService.updateUser(id, request);
    }
}