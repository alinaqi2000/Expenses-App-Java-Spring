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
public class ItemController {

    @Autowired
    private ItemService service;
    @Autowired
    private UserService userSer;

    private User checkAuth(String token) {
        User new_user = userSer.verifyToken(token);
        return new_user;
    }

    // Get Items
    @GetMapping("/items")
    public ResponseEntity<?> list(@RequestParam String token) {
        if (checkAuth(token) != null) {
            User user = userSer.verifyToken(token);
            return new ResponseEntity<>(Messages.setItemData("ok", service.listAll(user.id)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Messages.setData("bad", "Unauthorized!, Invalid token."), HttpStatus.OK);
        }
    }

    // Get User Chart
    @GetMapping("/items/chart")
    public ResponseEntity<?> chart(@RequestParam String token) {
        if (checkAuth(token) != null) {
            User user = userSer.verifyToken(token);
            List<Item> items = service.listAll(user.id);
            Chart chartData = new Chart();
            String prev_date = "";
            for (Item item : items) {
                if (!item.date.equals(prev_date)) {
                    chartData.dates.add(item.date);
                    chartData.prices.add(item.amount);
                } else {
                    int lastIndex = chartData.prices.size() - 1;
                    Double new_price = chartData.prices.get(lastIndex) + item.amount;
                    chartData.prices.set(lastIndex, new_price);
                }
                prev_date = item.date;
            }
            return new ResponseEntity<>(Messages.setChartData("ok", chartData), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Messages.setData("bad", "Unauthorized!, Invalid token."), HttpStatus.OK);
        }
    }

    // Create Item
    @PostMapping("/items")
    public ResponseEntity<?> add(@RequestBody Item item, @RequestParam String token) {
        if (checkAuth(token) != null) {
            User user = userSer.verifyToken(token);
            item.setUserId(user.id);
            service.save(item);
            return new ResponseEntity<>(Messages.setMessage(item.id.toString(), "Item added to expenses list."),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Messages.setMessage("bad", "Unauthorized!, Invalid token."), HttpStatus.OK);
        }
    }

    // Update Item
    @PutMapping("/items")
    public ResponseEntity<?> update(@RequestBody Item item, @RequestParam String token) {
        if (checkAuth(token) != null) {
            try {
                service.save(item);
                return new ResponseEntity<>(Messages.setMessage("ok", "Item updated successfully."), HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(Messages.setMessage("bad", "Unauthorized!, Invalid token."), HttpStatus.OK);
        }

    }

    // Delete Item
    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id, @RequestParam String token) {
        if (checkAuth(token) != null) {
            service.delete(id);
            return new ResponseEntity<>(Messages.setMessage("ok", "Item deleted successfully"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Messages.setMessage("bad", "Unauthorized!, Invalid token."), HttpStatus.OK);
        }

    }

}