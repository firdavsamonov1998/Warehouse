package com.company.warehouse.controller;

import com.company.warehouse.entity.Measurment;
import com.company.warehouse.payload.Message;
import com.company.warehouse.service.MeasurmentService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/measurment")
public class MeasurmentController {

    private final MeasurmentService measurmentService;

    @Autowired
    public MeasurmentController(MeasurmentService measurmentService) {
        this.measurmentService = measurmentService;
    }


    @PostMapping("/add") //this method add new measurment
    private Message add(@RequestBody Measurment measurment) {
        return measurmentService.add(measurment);
    }


    @GetMapping("/get") //this method get all measurment
    private List<Measurment> getAll() {
        return measurmentService.getAll();
    }


    @GetMapping("/get/{id}") //this method returns by id
    private Measurment getById(@PathVariable Integer id) {
        return measurmentService.getById(id);
    }

    @PutMapping("/updated/{id}") //this method updates the measurment data in DB
    private Message edite(@PathVariable Integer id, @RequestBody Measurment measurment) {
        return measurmentService.edite(id, measurment);
    }

    @DeleteMapping("/delete/{id}") //this method delete by id
    private Message delete(@PathVariable Integer id) {
        return measurmentService.delete(id);
    }

}
