package com.lingh.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1332162822494069342L;
    
    private long orderItemId;
    
    private long orderId;
    
    private int userId;
    
    private String phone;
    
    private String status;
}
