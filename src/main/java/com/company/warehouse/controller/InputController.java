package com.company.warehouse.controller;

import com.company.warehouse.entity.Input;
import com.company.warehouse.payload.InputDTO;
import com.company.warehouse.payload.Message;
import com.company.warehouse.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {

    @Autowired
    private InputService inputService;


    @PostMapping("/add") //this method adds new input data
    private Message add(@RequestBody InputDTO inputDTO) {
        return inputService.add(inputDTO);
    }

    @GetMapping("/get") //this method returns all the input data in DB
    private List<Input> getAll() {
        return inputService.getAll();
    }

    @GetMapping("/get/{id}") //this method returns the input data by id
    private Input getById(@PathVariable Integer id) {
        return inputService.getById(id);
    }

    @PutMapping("/edite/{id}") //this method edtes the input data in DB
    private Message edite(@PathVariable Integer id, @RequestBody InputDTO inputDTO) {
        return inputService.edite(id, inputDTO);
    }

    //this method deletes the input data in DB
    @DeleteMapping("/delete/{id}")
    private Message deletes(@PathVariable Integer id) {
        return inputService.delete(id);
    }
}
