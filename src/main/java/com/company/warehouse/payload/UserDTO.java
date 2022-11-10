package com.company.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.coyote.http11.filters.SavedRequestInputFilter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String first_name;
    private String last_name;
    private String phone;
    private String password;
    private Integer warehouse_id;
}
