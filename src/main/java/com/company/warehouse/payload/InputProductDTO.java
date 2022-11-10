package com.company.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.internal.LoadingCache;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputProductDTO {
    private Integer product_id;
    private Double amount;
    private Double price;
    private LocalDate expireDate;
    private Integer input_id;
}
