package com.company.warehouse.controller;

import com.company.warehouse.entity.Input_product;
import com.company.warehouse.payload.InputProductDTO;
import com.company.warehouse.payload.Message;
import com.company.warehouse.service.InputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inputproduct")
public class InputProductController {


    private final InputProductService inputProductService;

    @Autowired
    public InputProductController(InputProductService inputProductService) {
        this.inputProductService = inputProductService;
    }


    @PostMapping("/add")
    private Message add(@RequestBody InputProductDTO inputProductDTO) {
        return inputProductService.add(inputProductDTO);
    }

    @GetMapping("/get")
    private List<Input_product> getAll() {
        return inputProductService.getAll();
    }

    @GetMapping("/get/{id}")
    private Input_product getById(@PathVariable Integer id) {
        return inputProductService.getById(id);
    }


    @PutMapping("/edite/{id}")
    private Message edite(@PathVariable Integer id, @RequestBody InputProductDTO inputProductDTO) {
        return inputProductService.edite(id, inputProductDTO);
    }

    @DeleteMapping("/delete/{id}")
    private Message delete(@PathVariable Integer id) {
        return inputProductService.delete(id);
    }
}
