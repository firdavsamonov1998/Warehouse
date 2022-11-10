package com.company.warehouse.service;

import com.company.warehouse.entity.Client;
import com.company.warehouse.entity.Currency;
import com.company.warehouse.entity.Output;
import com.company.warehouse.entity.Warehouse;
import com.company.warehouse.payload.Code;
import com.company.warehouse.payload.Message;
import com.company.warehouse.payload.OutputDTO;
import com.company.warehouse.repository.ClientRepository;
import com.company.warehouse.repository.CurrencyRepository;
import com.company.warehouse.repository.OutputRepository;
import com.company.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OutputService {
    private final OutputRepository outputRepository;
    private final ClientRepository clientRepository;

    private final WarehouseRepository warehouseRepository;

    private final CurrencyRepository currencyRepository;

    @Autowired
    private OutputService(OutputRepository outputRepository, ClientRepository clientRepository, WarehouseRepository warehouseRepository, CurrencyRepository currencyRepository) {
        this.outputRepository = outputRepository;
        this.clientRepository = clientRepository;
        this.warehouseRepository = warehouseRepository;
        this.currencyRepository = currencyRepository;
    }


    //this method adds new output data in DB
    public Message add(OutputDTO outputDTO) {

        Optional<Client> optionalClient = clientRepository.findById(outputDTO.getClient_id());
        if (optionalClient.isEmpty()) return new Message("Client not founded", false);
        Client client = optionalClient.get();

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDTO.getWarehouse_id());
        if (optionalWarehouse.isEmpty()) return new Message("Warehouse not founded", false);
        Warehouse warehouse = optionalWarehouse.get();


        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDTO.getCurrency_id());
        if (optionalCurrency.isEmpty()) return new Message("Currency not founded", false);
        Currency currency = optionalCurrency.get();


        Output output = new Output();
        output.setDate(LocalDateTime.now());
        output.setCode(String.valueOf(autogenerator()));
        output.setFacture(outputDTO.getFacture());
        output.setClient(client);
        output.setWarehouse(warehouse);
        output.setCurrency(currency);
        outputRepository.save(output);

        return new Message("Successfully saved", true);
    }


    //this method returns all the output data
    public List<Output> getAll() {
        return outputRepository.findAll();
    }

    //this method returns the output data
    public Output getById(Integer id) {
        Optional<Output> outputOptional = outputRepository.findById(id);
        if (outputOptional.isEmpty()) return new Output();
        return outputOptional.get();
    }


    //this method edites the output data in DB
    public Message edite(Integer id, OutputDTO outputDTO) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isEmpty()) return new Message("Output not founded", false);
        Output output = optionalOutput.get();


        Optional<Client> optionalClient = clientRepository.findById(outputDTO.getClient_id());
        if (optionalClient.isEmpty()) return new Message("Client not founded", false);
        Client client = optionalClient.get();

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDTO.getWarehouse_id());
        if (optionalWarehouse.isEmpty()) return new Message("Warehouse not founded", false);
        Warehouse warehouse = optionalWarehouse.get();

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDTO.getCurrency_id());
        if (optionalCurrency.isEmpty()) return new Message("Currency not founded", false);
        Currency currency = optionalCurrency.get();

        output.setDate(LocalDateTime.now());
        output.setCode(output.getCode());
        output.setFacture(outputDTO.getFacture());
        output.setClient(client);
        output.setWarehouse(warehouse);
        output.setCurrency(currency);
        outputRepository.save(output);

        return new Message("Successfully updated", true);

    }


    //this method deletes the output data by id in DB
    public Message delete(Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isEmpty()) return new Message("Output data not founded", false);
        Output output = optionalOutput.get();
        outputRepository.delete(output);
        return new Message("Successfully deleted", true);
    }

    //this method generates the code automatically
    public Integer autogenerator() {
        Code code = new Code();
        return code.getCode();
    }
}
