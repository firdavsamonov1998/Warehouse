package com.company.warehouse.controller;

import com.company.warehouse.entity.OutPut_product;
import com.company.warehouse.payload.Message;
import com.company.warehouse.payload.OutputProductDTO;
import com.company.warehouse.service.OutputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/outputProduct")
public class OutputProductController {

    private final OutputProductService outputProductService;

    @Autowired
    private OutputProductController(OutputProductService outputProductService) {
        this.outputProductService = outputProductService;
    }

    @PostMapping("/add")
    private Message add(@RequestBody OutputProductDTO outputProductDTO) {
        return outputProductService.add(outputProductDTO);
    }

    @GetMapping("/get")
    private List<OutPut_product> getAll() {
        return outputProductService.getAll();
    }

    @GetMapping("/get/{id}")
    private OutPut_product getById(@PathVariable Integer id) {
        return outputProductService.getById(id);
    }


    @PutMapping("/edite/{id}")
    private Message edite(@PathVariable Integer id, @RequestBody OutputProductDTO outputProductDTO) {
        return outputProductService.edite(id, outputProductDTO);
    }

    @DeleteMapping("/delete/{id}")
    private Message delete(@PathVariable Integer id) {
        return outputProductService.delete(id);
    }
}
