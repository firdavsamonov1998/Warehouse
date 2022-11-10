package com.company.warehouse.service;

import com.company.warehouse.entity.User;
import com.company.warehouse.entity.Warehouse;
import com.company.warehouse.payload.Message;
import com.company.warehouse.payload.UserDTO;
import com.company.warehouse.repository.UserRepository;
import com.company.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final WarehouseRepository warehouseRepository;

    @Autowired
    public UserService(UserRepository userRepository, WarehouseRepository warehouseRepository) {
        this.userRepository = userRepository;
        this.warehouseRepository = warehouseRepository;
    }



    //this method adds new user data in DB
    public Message add(UserDTO userDTO) {
        boolean phone = userRepository.existsByPhone(userDTO.getPhone());
        if (phone) return new Message("This phone is already exist", false);

        boolean existsByPassword = userRepository.existsByPassword(userDTO.getPassword());
        if (existsByPassword) return new Message("This password is already exist", false);
        User user = new User();
        user.setFirst_name(userDTO.getFirst_name());
        user.setLast_name(userDTO.getLast_name());
        user.setPhone(userDTO.getPhone());
        user.setPassword(userDTO.getPassword());
        String code = autogenerator();

        boolean existsByCode = userRepository.existsByCode(code);
        if (existsByCode) {
            user.setCode(autogenerator());
        } else {
            user.setCode(code);
        }

        Optional<Warehouse> optional = warehouseRepository.findById(userDTO.getWarehouse_id());
        if (optional.isEmpty()) return new Message("warehouse not founded", false);
        Warehouse warehouse = optional.get();
        Set<Warehouse> warehouseSet = new HashSet<>();
        warehouseSet.add(warehouse);
        user.setWarehouse(warehouseSet);

        userRepository.save(user);

        return new Message("Successfully saved", true);


    }


    //this method returns all user data in DB
    public List<User> getAll() {
        return userRepository.findAll();
    }


    //this method returns the user data by id
    public User getById(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) return new User();
        return optional.get();
    }


    //this method edites the user data in DB
    public Message edite(Integer id, UserDTO userDTO) {

        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) return new Message("User not founded", false);
        User user = optional.get();
        user.setFirst_name(userDTO.getFirst_name());
        user.setLast_name(userDTO.getLast_name());

        if (userDTO.getPhone() == null) {
            user.setPhone(user.getPhone());
        } else {
            user.setPhone(userDTO.getPhone());
        }

        if (userDTO.getPassword() == null) {
            user.setPassword(user.getPassword());
        } else {
            user.setPassword(userDTO.getPassword());
        }

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(userDTO.getWarehouse_id());
        if (optionalWarehouse.isEmpty()) return new Message("Warehouse not founded", false);
        Warehouse warehouse = optionalWarehouse.get();
        Set<Warehouse> warehouseSet = new HashSet<>();
        warehouseSet.add(warehouse);
        user.setWarehouse(warehouseSet);

        userRepository.save(user);

        return new Message("Successfully edited", true);

    }


    //this method deletes the user data by id in DB
    public Message delete(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) return new Message("This user not founded", false);
        userRepository.delete(userOptional.get());
        return new Message("Successfully deleted", true);
    }


    //this method auto generates the code
    public String autogenerator() {
        Random random = new Random();
        int number = random.nextInt(1000, 9999);
        return String.valueOf(number);
    }
}
