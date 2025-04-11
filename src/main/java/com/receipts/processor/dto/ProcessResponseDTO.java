package com.receipts.processor.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for process response
 */
@NoArgsConstructor
@AllArgsConstructor
public class ProcessResponseDTO {
    private String id;
    
    // Getters and setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
}
