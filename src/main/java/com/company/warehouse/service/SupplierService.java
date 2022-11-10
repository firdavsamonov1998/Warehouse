package com.company.warehouse.service;

import com.company.warehouse.entity.Supplier;
import com.company.warehouse.payload.Message;
import com.company.warehouse.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }


    //this method adds new supplier data in DB
    public Message add(Supplier supplier) {
        boolean exists = supplierRepository.existsByPhone(supplier.getPhone());
        if (exists) return new Message("This phone is already exist", false);
        Supplier save = supplierRepository.save(supplier);
        return new Message("Successfully saved", true);
    }

    //this method returns all the supplier data in DB
    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    //this method returns the supplier data by id IN DB
    public Supplier getById(Integer id) {
        Optional<Supplier> optional = supplierRepository.findById(id);
        if (optional.isEmpty()) return new Supplier();
        return optional.get();
    }


    //this method edites the supplier data in DB
    public Message edite(Integer id, Supplier supplier) {
        Optional<Supplier> optional = supplierRepository.findById(id);
        if (optional.isEmpty()) return new Message("Not founded", false);
        Supplier optionalSupplier = optional.get();
        optionalSupplier.setName(supplier.getName());
        if (supplier.getPhone() == null) {
            optionalSupplier.setPhone(optionalSupplier.getPhone());
        } else {
            optionalSupplier.setPhone(supplier.getPhone());
        }
        supplierRepository.save(optionalSupplier);
        return new Message("Successfully edited", true);
    }


    //this method deletes the supplier data by id from DB
    public Message delete(Integer id) {
        Optional<Supplier> optional = supplierRepository.findById(id);
        if (optional.isEmpty()) return new Message("Not founded", false);
        supplierRepository.delete(optional.get());
        return new Message("Successfully deleted", true);
    }
}
