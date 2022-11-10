package com.company.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputDTO {
    private Integer warehouse_id;
    private Integer client_id;
    private Integer currency_id;
    private String facture;
}
