package com.company.warehouse.controller;

import com.company.warehouse.entity.Output;
import com.company.warehouse.payload.Message;
import com.company.warehouse.payload.OutputDTO;
import com.company.warehouse.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/output")
public class OutputController {

    private final OutputService outputService;

    @Autowired
    private OutputController(OutputService outputService) {
        this.outputService = outputService;
    }

    @PostMapping("/add")
    private Message add(@RequestBody OutputDTO outputDTO) {
        return outputService.add(outputDTO);
    }

    @GetMapping("/get")
    private List<Output> getALl() {
        return outputService.getAll();
    }

    @GetMapping("/get/{id}")
    private Output getById(@PathVariable Integer id) {
        return outputService.getById(id);
    }

    @PutMapping("/edite/{id}")
    private Message edite(@PathVariable Integer id, @RequestBody OutputDTO outputDTO) {
        return outputService.edite(id, outputDTO);
    }

    @DeleteMapping("/delete/{id}")
    private Message delete(@PathVariable Integer id) {
        return outputService.delete(id);
    }
}
