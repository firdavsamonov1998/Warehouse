package com.company.warehouse.service;

import com.company.warehouse.entity.Measurment;
import com.company.warehouse.payload.Message;
import com.company.warehouse.repository.MeasurmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurmentService {
    private final MeasurmentRepository measurmentRepository;

    @Autowired
    public MeasurmentService(MeasurmentRepository measurmentRepository) {
        this.measurmentRepository = measurmentRepository;
    }


    public Message add(Measurment measurment) {
        boolean name = measurmentRepository.existsMeasurmentByName(measurment.getName());
        if (name) return new Message("This measurment is already exist", false);
        measurmentRepository.save(measurment);
        return new Message("Successfully saved", true);
    }

    public List<Measurment> getAll() {
        return measurmentRepository.findAll();
    }

    public Measurment getById(Integer id) {
        Optional<Measurment> optional = measurmentRepository.findById(id);
        if (optional.isEmpty()) return new Measurment();
        return optional.get();
    }


    public Message edite(Integer id, Measurment measurment) {
        Optional<Measurment> optional = measurmentRepository.findById(id);
        if (optional.isEmpty()) return new Message("Not founded", false);
        Measurment updatedMeasurment = optional.get();
        updatedMeasurment.setName(measurment.getName());
        measurmentRepository.save(updatedMeasurment);
        return new Message("Successfully updated", true);
    }

    public Message delete(Integer id) {
        Optional<Measurment> optional = measurmentRepository.findById(id);
        if (optional.isEmpty()) return new Message("Not founded", false);
        measurmentRepository.deleteById(id);
        return new Message("Successfully deleted", true);
    }
}
