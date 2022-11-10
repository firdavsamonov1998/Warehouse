package com.company.warehouse.service;

import com.company.warehouse.entity.OutPut_product;
import com.company.warehouse.entity.Output;
import com.company.warehouse.entity.Product;
import com.company.warehouse.payload.Message;
import com.company.warehouse.payload.OutputProductDTO;
import com.company.warehouse.repository.OutputProductRepository;
import com.company.warehouse.repository.OutputRepository;
import com.company.warehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {
    private final OutputProductRepository outputProductRepository;
    private final ProductRepository productRepository;
    private final OutputRepository outputRepository;

    @Autowired
    public OutputProductService(OutputProductRepository outputProductRepository, ProductRepository productRepository, OutputRepository outputRepository) {
        this.outputProductRepository = outputProductRepository;
        this.productRepository = productRepository;
        this.outputRepository = outputRepository;
    }

    public Message add(OutputProductDTO outputProductDTO) {

        Optional<Product> optionalProduct = productRepository.findById(outputProductDTO.getProduct_id());
        if (optionalProduct.isEmpty()) return new Message("Product not founded", false);
        Product product = optionalProduct.get();

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDTO.getOutput_id());
        if (optionalOutput.isEmpty()) return new Message("Output not founded", false);
        Output output = optionalOutput.get();

        OutPut_product outPut_product = new OutPut_product();
        outPut_product.setProduct(product);
        outPut_product.setOutput(output);
        outPut_product.setPrice(outputProductDTO.getPrice());
        outPut_product.setAmount(outputProductDTO.getAmount());

        outputProductRepository.save(outPut_product);

        return new Message("Successfully saved", true);
    }

    public List<OutPut_product> getAll() {
        return outputProductRepository.findAll();
    }

    public OutPut_product getById(Integer id) {
        Optional<OutPut_product> optionalOutPutProduct = outputProductRepository.findById(id);
        if (optionalOutPutProduct.isEmpty()) return new OutPut_product();
        return optionalOutPutProduct.get();
    }

    public Message edite(Integer id, OutputProductDTO outputProductDTO) {
        Optional<OutPut_product> optionalOutPutProduct = outputProductRepository.findById(id);
        if (optionalOutPutProduct.isEmpty()) return new Message("Data not founded", false);

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDTO.getOutput_id());
        if (optionalOutput.isEmpty()) return new Message("Output data not founded", false);
        Output output = optionalOutput.get();

        Optional<Product> optionalProduct = productRepository.findById(outputProductDTO.getProduct_id());
        if (optionalProduct.isEmpty()) return new Message("Product data not founded", false);
        Product product = optionalProduct.get();

        OutPut_product outPut_product = optionalOutPutProduct.get();
        outPut_product.setOutput(output);
        outPut_product.setProduct(product);
        outPut_product.setPrice(outputProductDTO.getPrice());
        outPut_product.setAmount(outputProductDTO.getAmount());

        outputProductRepository.save(outPut_product);

        return new Message("Succesfully edited", true);

    }

    public Message delete(Integer id) {
        Optional<OutPut_product> productOptional = outputProductRepository.findById(id);
        if (productOptional.isEmpty()) return new Message("Not founded", false);
        outputProductRepository.delete(productOptional.get());
        return new Message("Successfully deleted", true);
    }

}
