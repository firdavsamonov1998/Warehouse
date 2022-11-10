package com.company.warehouse.service;

import com.company.warehouse.entity.Attachment;
import com.company.warehouse.entity.Category;
import com.company.warehouse.entity.Measurment;
import com.company.warehouse.entity.Product;
import com.company.warehouse.payload.Message;
import com.company.warehouse.payload.ProductDTO;
import com.company.warehouse.repository.AttachmentRepository;
import com.company.warehouse.repository.CategoryRepository;
import com.company.warehouse.repository.MeasurmentRepository;
import com.company.warehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private MeasurmentRepository measurmentRepository;


    //this method adds new product in DB
    public Message add(ProductDTO productDTO) {

        boolean existsByName = productRepository.existsByName(productDTO.getName());
        if (existsByName) return new Message("This product is already exist", false);

        Integer countByAttachmentid = productRepository.countByAttachmentid(productDTO.getAttachment_id());
        if (countByAttachmentid > 0)
            return new Message("You cant this attachment because of this attchment belong to other product", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategory_id());
        if (optionalCategory.isEmpty()) return new Message("Category not founded", false);
        Category category = optionalCategory.get();

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDTO.getAttachment_id());
        if (optionalAttachment.isEmpty()) return new Message("Attachment not founded", false);
        Attachment attachment = optionalAttachment.get();

        Optional<Measurment> optionalMeasurment = measurmentRepository.findById(productDTO.getMeasurment_id());
        if (optionalMeasurment.isEmpty()) return new Message("Measurment not founded", false);
        Measurment measurment = optionalMeasurment.get();

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setCategory(category);
        product.setAttachment(attachment);
        product.setMeasurment(measurment);

        String code = autogenerator();
        boolean existsByCode = productRepository.existsByCode(code);
        if (existsByCode) {
            product.setCode(autogenerator());
        } else {
            product.setCode(code);
        }

        productRepository.save(product);

        return new Message("Successfully saved", true);

    }


    //this method returns all the product
    public List<Product> getAll() {
        return productRepository.findAll();
    }


    //this method returns the product data by id
    public Product getById(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) return new Product();
        return optionalProduct.get();
    }


    //this method edites the product data in DB
    public Message edite(Integer id, ProductDTO productDTO) {


        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategory_id());
        if (optionalCategory.isEmpty()) return new Message("Category not founded", false);
        Category category = optionalCategory.get();

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDTO.getAttachment_id());
        if (optionalAttachment.isEmpty()) return new Message("Attachment not founded", false);
        Attachment attachment = optionalAttachment.get();


        Optional<Measurment> optionalMeasurment = measurmentRepository.findById(productDTO.getMeasurment_id());
        if (optionalMeasurment.isEmpty()) return new Message("Measurment not founded", false);
        Measurment measurment = optionalMeasurment.get();

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) return new Message("This product not founded", false);

        Product product = optionalProduct.get();
        product.setName(productDTO.getName());
        product.setCategory(category);
        product.setAttachment(attachment);
        product.setMeasurment(measurment);
        product.setCode(product.getCode());

        productRepository.save(product);

        return new Message("Successfully edited", true);
    }


    //this method deletes the product data in DB
    public Message delete(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) return new Message("Product not founded", false);
        Product product = optionalProduct.get();
        Attachment attachment = product.getAttachment();
        attachmentRepository.delete(attachment);
        productRepository.delete(product);

        return new Message("Successfully deleted", true);
    }


    //this method generates the code automatically
    public String autogenerator() {
        Random random = new Random();
        int number = random.nextInt(1000, 9999);
        return String.valueOf(number);
    }

}
