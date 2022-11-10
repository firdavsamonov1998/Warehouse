package com.company.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputDTO {

    private Integer warehouse_id;
    private Integer supplier_id;
    private Integer product_id;
    private Integer currency_id;
    private String facture;
}
