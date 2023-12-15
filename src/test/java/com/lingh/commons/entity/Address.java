package com.lingh.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 4743102234543827855L;
    
    private Long addressId;
    
    private String addressName;
}
