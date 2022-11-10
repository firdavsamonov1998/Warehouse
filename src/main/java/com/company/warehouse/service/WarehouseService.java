package com.company.warehouse.service;

import com.company.warehouse.entity.Warehouse;
import com.company.warehouse.payload.Message;
import com.company.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    //this method adds new warehouse data in DB
    public Message add(Warehouse warehouse) {
        boolean exists = warehouseRepository.existsByName(warehouse.getName());
        if (exists) return new Message("This warehouse is already exist", false);
        Warehouse save = warehouseRepository.save(warehouse);
        return new Message("Successfully saved", true);
    }

    //this method returns all the warehouse data
    public List<Warehouse> getAll() {
        return warehouseRepository.findAll();
    }

    //this method returns the warehouse data by id
    public Warehouse getById(Integer id) {
        Optional<Warehouse> optional = warehouseRepository.findById(id);
        if (optional.isEmpty()) return new Warehouse();
        return optional.get();
    }


    //this method edites the warehouse data in DB
    public Message edite(Integer id, Warehouse warehouse) {
        Optional<Warehouse> optional = warehouseRepository.findById(id);
        if (optional.isEmpty()) return new Message("Not founded", false);

        Warehouse optionalWarehouse = optional.get();
        optionalWarehouse.setName(warehouse.getName());
        warehouseRepository.save(optionalWarehouse);
        return new Message("Successfully edited", true);
    }


    //this method deletes the warehouse data in DB
    public Message delete(Integer id) {
        Optional<Warehouse> optional = warehouseRepository.findById(id);
        if (optional.isEmpty()) return new Message("Not founded", false);
        warehouseRepository.delete(optional.get());
        return new Message("Successfully deleted", true);
    }

}
