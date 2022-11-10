package com.company.warehouse.controller;

import com.company.warehouse.entity.User;
import com.company.warehouse.payload.Message;
import com.company.warehouse.payload.UserDTO;
import com.company.warehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")//this method adds new user data in DB
    private Message add(@RequestBody UserDTO userDTO) {
        return userService.add(userDTO);
    }

    @GetMapping("/get")//this method returns all the user data in DB
    private List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/get/{id}")//this method returns the user data by id
    private User getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PutMapping("/edite/{id}")//this method edites the user data in DB
    private Message edite(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        return userService.edite(id, userDTO);
    }

    @DeleteMapping("/delete/{id}")//this method deletes the user data in DB
    private Message delete(@PathVariable Integer id) {
        return userService.delete(id);
    }
}
