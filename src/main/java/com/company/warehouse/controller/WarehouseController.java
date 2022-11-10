package com.company.warehouse.controller;

import com.company.warehouse.entity.Warehouse;
import com.company.warehouse.payload.Message;
import com.company.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }


    @PostMapping("/add")//this method adds new warehouse data in DB
    private Message add(@RequestBody Warehouse warehouse) {
        return warehouseService.add(warehouse);
    }

    @GetMapping("/get")//this method returns all the warehouse data in DB
    private List<Warehouse> getAll() {
        return warehouseService.getAll();
    }


    @GetMapping("/get/{id}") //this method returns the warehouse data in DB by id
    private Warehouse getById(@PathVariable Integer id) {
        return warehouseService.getById(id);
    }

    @PutMapping("/update/{id}")//this method updates the warehouse data in DB
    private Message edite(@PathVariable Integer id, @RequestBody Warehouse warehouse) {
        return warehouseService.edite(id, warehouse);
    }

    @DeleteMapping("/delete/{id}") //this method deletes the warehouse data in DB
    private Message delete(@PathVariable Integer id) {
        return warehouseService.delete(id);
    }

}
