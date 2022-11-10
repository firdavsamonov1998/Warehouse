package com.company.warehouse.service;

import com.company.warehouse.entity.Input;
import com.company.warehouse.entity.Input_product;
import com.company.warehouse.entity.Product;
import com.company.warehouse.payload.InputProductDTO;
import com.company.warehouse.payload.Message;
import com.company.warehouse.repository.InputProductRepository;
import com.company.warehouse.repository.InputRepositopry;
import com.company.warehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {


    private final InputProductRepository inputProductRepository;

    private final InputRepositopry inputRepositopry;


    private final ProductRepository productRepository;

    @Autowired
    public InputProductService(InputProductRepository inputProductRepository, InputRepositopry inputRepositopry, ProductRepository productRepository) {
        this.inputProductRepository = inputProductRepository;
        this.inputRepositopry = inputRepositopry;
        this.productRepository = productRepository;
    }


    //this method adds new inputProduct data
    public Message add(InputProductDTO inputProductDTO) {

        Optional<Input> optionalInput = inputRepositopry.findById(inputProductDTO.getInput_id());
        if (optionalInput.isEmpty()) return new Message("Input not founded", false);
        Input input = optionalInput.get();

        Optional<Product> optionalProduct = productRepository.findById(inputProductDTO.getProduct_id());
        if (optionalProduct.isEmpty()) return new Message("Product not founded", false);
        Product product = optionalProduct.get();

        Input_product input_product = new Input_product();
        input_product.setInput(input);
        input_product.setProduct(product);
        input_product.setPrice(input_product.getPrice());
        input_product.setAmount(inputProductDTO.getAmount());
        input_product.setExpire_date(input_product.getExpire_date());

        inputProductRepository.save(input_product);

        return new Message("Successfully saved", true);
    }

    //this method returs all the input_product data
    public List<Input_product> getAll() {
        return inputProductRepository.findAll();
    }


    //this method returns input_product data by id
    public Input_product getById(Integer id) {
        Optional<Input_product> optional = inputProductRepository.findById(id);
        if (optional.isEmpty()) return new Input_product();
        return optional.get();
    }

    //this method edites the inputProduct data in DB
    public Message edite(Integer id, InputProductDTO inputProductDTO) {
        Optional<Input_product> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isEmpty()) return new Message("InputProduct Not founded", false);

        Optional<Input> inputOptional = inputRepositopry.findById(inputProductDTO.getInput_id());
        if (inputOptional.isEmpty()) return new Message("Input not founded", false);
        Input input = inputOptional.get();

        Optional<Product> optionalProduct = productRepository.findById(inputProductDTO.getProduct_id());
        if (optionalProduct.isEmpty()) return new Message("Product not founded", false);
        Product product = optionalProduct.get();

        Input_product input_product = optionalInputProduct.get();
        input_product.setInput(input);
        input_product.setProduct(product);
        input_product.setPrice(input_product.getPrice());
        input_product.setAmount(inputProductDTO.getAmount());
        input_product.setExpire_date(inputProductDTO.getExpireDate());
        inputProductRepository.save(input_product);

        return new Message("Successfully edited", true);
    }

    //this method deletes the inputProduct data in DB
    public Message delete(Integer id) {
        Optional<Input_product> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isEmpty()) return new Message("Not founded", false);
        Input_product input_product = optionalInputProduct.get();
        inputProductRepository.delete(input_product);
        return new Message("Successfully deleted", true);
    }
}
