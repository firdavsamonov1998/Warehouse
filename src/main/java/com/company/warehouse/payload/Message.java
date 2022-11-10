package com.company.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {
    private String message;
    private boolean success;

    private Object object;

    public Message(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public Message(String message, boolean success, Object object) {
        this.message = message;
        this.success = success;
        this.object = object;
    }
}
