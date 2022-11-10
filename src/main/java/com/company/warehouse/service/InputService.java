package com.company.warehouse.service;

import com.company.warehouse.entity.Currency;
import com.company.warehouse.entity.Input;
import com.company.warehouse.entity.Supplier;
import com.company.warehouse.entity.Warehouse;
import com.company.warehouse.payload.Code;
import com.company.warehouse.payload.InputDTO;
import com.company.warehouse.payload.Message;
import com.company.warehouse.repository.CurrencyRepository;
import com.company.warehouse.repository.InputRepositopry;
import com.company.warehouse.repository.SupplierRepository;
import com.company.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class InputService {

    @Autowired
    private InputRepositopry inputRepositopry;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    //this method adds new input data in DB
    public Message add(InputDTO inputDTO) {

        boolean exists = inputRepositopry.existsByFacture(inputDTO.getFacture());
        if (exists) return new Message("This facture " + inputDTO.getFacture() + " is already exist", false);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDTO.getWarehouse_id());
        if (optionalWarehouse.isEmpty()) return new Message("Warehouse not founded", false);
        Warehouse warehouse = optionalWarehouse.get();

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplier_id());
        if (optionalSupplier.isEmpty()) return new Message("Supplier not founded", false);
        Supplier supplier = optionalSupplier.get();

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrency_id());
        if (optionalCurrency.isEmpty()) return new Message("Currency not founded", false);
        Currency currency = optionalCurrency.get();


        Input input = new Input();
        input.setFacture(inputDTO.getFacture());
        input.setWarehouse(warehouse);
        input.setSupplier(supplier);
        input.setCurrency(currency);
        Integer code = autogenerator();
        input.setCode(String.valueOf(code));
        input.setDate(LocalDateTime.now());

        inputRepositopry.save(input);

        return new Message("Successfully saved", true);
    }

    //this method returns all the input data
    public List<Input> getAll() {
        return inputRepositopry.findAll();
    }


    //this method returns the input data by id;
    public Input getById(Integer id) {
        Optional<Input> inputOptional = inputRepositopry.findById(id);
        if (inputOptional.isEmpty()) return new Input();
        return inputOptional.get();
    }


    //this method edites the input data in DB
    public Message edite(Integer id, InputDTO inputDTO) {
        Optional<Input> inputOptional = inputRepositopry.findById(id);
        if (inputOptional.isEmpty()) return new Message("Input data not founded", false);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDTO.getWarehouse_id());
        if (optionalWarehouse.isEmpty()) return new Message("Warehouse not founded", false);
        Warehouse warehouse = optionalWarehouse.get();

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplier_id());
        if (optionalSupplier.isEmpty()) return new Message("Supplier not founded", false);
        Supplier supplier = optionalSupplier.get();

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrency_id());
        if (optionalCurrency.isEmpty()) return new Message("Currency not founded", false);
        Currency currency = optionalCurrency.get();


        Input input = inputOptional.get();
        input.setFacture(inputDTO.getFacture());
        input.setWarehouse(warehouse);
        input.setSupplier(supplier);
        input.setCurrency(currency);
        input.setCode(input.getCode());
        inputRepositopry.save(input);

        return new Message("Successfully edited", true);
    }

    //this method deletes the input data in DB
    public Message delete(Integer id) {
        Optional<Input> optionalInput = inputRepositopry.findById(id);
        if (optionalInput.isEmpty()) return new Message("Input data not founded", false);
        inputRepositopry.delete(optionalInput.get());
        return new Message("Successfully deleted", true);

    }

    //this method generates the code automatically
    public Integer autogenerator() {
        Code code = new Code();
        return code.getCode();
    }
}
