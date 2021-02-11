package com.user.user_entry.api;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;
import com.user.user_entry.services.*;
import com.user.user_entry.utils.Messages;
import com.user.user_entry.models.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

    @Autowired
    private UserService service;

    // Verify User
    @GetMapping("/verify_user")
    public ResponseEntity<?> verify_user(@RequestParam String token) {
        User new_user = service.verifyToken(token);
        if (new_user == null)
            return new ResponseEntity<>(Messages.setData("bad", "Unauthorized!, Invalid token."), HttpStatus.OK);
        String jsonString = Messages.convertObjIntoJSON(new_user);
        return new ResponseEntity<>(Messages.setData("ok", jsonString), HttpStatus.OK);
    }

    // User Login
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String message = service.verifyUser(user.email, user.password);
        if (message != "")
            return new ResponseEntity<>(Messages.setMessage("bad", message), HttpStatus.OK);
        else {
            String token = service.generateTokenString(60);
            User new_user = service.getUser(user.email);
            new_user.setToken(token);
            service.save(new_user);
            return new ResponseEntity<>(Messages.setData("ok", token), HttpStatus.OK);
        }

    }

    // Register User
    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        String message = service.doesExistUser(user.email);
        if (message != "")
            return new ResponseEntity<>(Messages.setMessage("bad", message), HttpStatus.OK);
        service.save(user);
        return new ResponseEntity<>(Messages.setMessage("ok", "User registered successfully."), HttpStatus.OK);
    }

    // Update User
    @PutMapping("/users")
    public ResponseEntity<?> update(@RequestBody User user) {
        try {
            User temp_user = service.get(user.id);
            String message = service.doesExistUser(user.email);
            if (message != "")
                if (!temp_user.email.equals(user.email))
                    return new ResponseEntity<>(Messages.setMessage("bad", message), HttpStatus.OK);
            service.save(user);
            return new ResponseEntity<>(Messages.setMessage("ok", "Profile updated successfully."), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete User
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(Messages.setMessage("ok", "User deleted successfully"), HttpStatus.OK);

    }

}