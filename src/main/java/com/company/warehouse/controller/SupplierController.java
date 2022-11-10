package com.company.warehouse.controller;

import com.company.warehouse.entity.Supplier;
import com.company.warehouse.payload.Message;
import com.company.warehouse.service.SupplierService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/add") // this method adds new supplier data
    private Message add(@RequestBody Supplier supplier) {
        return supplierService.add(supplier);
    }

    @GetMapping("/get") //this method returns all the suplier data
    private List<Supplier> getAll() {
        return supplierService.getAll();
    }

    @GetMapping("/get/{id}") //this method returns the supplier data by id in DB
    private Supplier getById(@PathVariable Integer id) {
        return supplierService.getById(id);
    }

    @PutMapping("/update/{id}") //this method updates the supplier data in DB
    private Message edite(@PathVariable Integer id, @RequestBody Supplier supplier) {
        return supplierService.edite(id, supplier);
    }

    @DeleteMapping("/delete/{id}") // this method deletes the supplier data in DB
    private Message delete(@PathVariable Integer id) {
        return supplierService.delete(id);
    }
}
