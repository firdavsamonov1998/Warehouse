package com.company.warehouse.controller;

import com.company.warehouse.entity.Currency;
import com.company.warehouse.payload.Message;
import com.company.warehouse.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @PostMapping("/add") //this method adds new currency data in DB
    private Message add(@RequestBody Currency currency) {
        return currencyService.add(currency);
    }

    @GetMapping("/get") // this method returns the all currency data from DB
    private List<Currency> getAll() {
        return currencyService.getAll();
    }

    @GetMapping("/get/{id}") // this method returns the currency data by id from DB
    private Currency getById(@PathVariable Integer id) {
        return currencyService.getById(id);
    }


    @PutMapping("/update/{id}") //this method edites the currency data from DB
    private Message edite(@PathVariable Integer id, @RequestBody Currency currency) {
        return currencyService.edite(id, currency);
    }

    @DeleteMapping("/delete/{id}") //this method deletes the currency data from DB
    private Message delete(@PathVariable Integer id) {
        return currencyService.delete(id);
    }

}
