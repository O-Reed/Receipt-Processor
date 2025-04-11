package com.receipts.processor.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

/**
 * Data Transfer Object for Item information
 */
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    @Pattern(regexp = "^[\\w\\s\\-]+$", message = "Invalid shortDescription format")
    private String shortDescription;
    
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Invalid price format")
    private String price;
    
    // Getters and setters
    public String getShortDescription() {
        return shortDescription;
    }
    
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    
    public String getPrice() {
        return price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
}
