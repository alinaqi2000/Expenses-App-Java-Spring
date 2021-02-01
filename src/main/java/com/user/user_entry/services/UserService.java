package com.user.user_entry.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.user.user_entry.models.User;
import com.user.user_entry.repositories.UserRepository;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> listAll() {
        return repo.findAll();
    }

    public User verifyToken(String token) {
        List<User> users = repo.findAllByToken(token);
        return users.size() > 0 ? users.get(0) : null;
    }

    public User getUser(String email) {
        List<User> users = repo.findAllByEmail(email);
        return users.get(0);
    }

    public String doesExistUser(String email) {
        List<User> users = repo.findAllByEmail(email);
        if (users.size() > 0)
            return "A user already exists with this email";
        return "";
    }

    public String verifyUser(String email, String password) {
        List<User> users = repo.findAllByEmail(email);
        if (users.size() == 0)
            return "No user found with this email";
        users = repo.findAllByEmailAndPassword(email, password);
        if (users.size() == 0)
            return "Invalid password!";
        return "";
    }

    public String generateTokenString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}