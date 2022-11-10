package com.company.warehouse.service;

import com.company.warehouse.entity.Currency;
import com.company.warehouse.payload.Message;
import com.company.warehouse.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    //this method adds new currency data in DB
    public Message add(Currency currency) {
        boolean exists = currencyRepository.existsByName(currency.getName());
        if (exists) return new Message("This currency is already exist", false);
        Currency save = currencyRepository.save(currency);
        return new Message("Successfully added", true);
    }


    //this method returns all currency data
    public List<Currency> getAll() {
        return currencyRepository.findAll();
    }


    //this method returns currency data by id from DB
    public Currency getById(Integer id) {
        Optional<Currency> optional = currencyRepository.findById(id);
        if (optional.isEmpty()) return new Currency();
        return optional.get();
    }

    //this method edites the currency data
    public Message edite(Integer id, Currency currency) {
        Optional<Currency> optional = currencyRepository.findById(id);
        if (optional.isEmpty()) return new Message("Not founded", false);
        Currency optionalCurrency = optional.get();
        optionalCurrency.setName(currency.getName());
        currencyRepository.save(optionalCurrency);
        return new Message("Successfully edited", true);
    }


    //this method deletes the currency data from DB
    public Message delete(Integer id) {
        Optional<Currency> optional = currencyRepository.findById(id);
        if (optional.isEmpty()) return new Message("Not founded", false);
        Currency currency = optional.get();
        currencyRepository.delete(currency);
        return new Message("Successfully deleted", true);
    }
}

