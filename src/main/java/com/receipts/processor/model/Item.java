package com.receipts.processor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Pattern(regexp = "^[\\w\\s\\-]+$", message = "Invalid shortDescription format")
    private String shortDescription;
    
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Invalid price format")
    private String price;
}
