package com.user.user_entry.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.user.user_entry.models.Item;
import com.user.user_entry.repositories.ItemRepository;

@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository repo;

    public List<Item> listAll(Integer id) {
        return repo.findAllByUserId(id);
    }

    public void save(Item product) {
        repo.save(product);
    }

    public Item get(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}