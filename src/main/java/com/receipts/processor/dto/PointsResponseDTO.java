package com.receipts.processor.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for points response
 */
@NoArgsConstructor
@AllArgsConstructor
public class PointsResponseDTO {
    private int points;
    
    // Getters and setters
    public int getPoints() {
        return points;
    }
    
    public void setPoints(int points) {
        this.points = points;
    }
}
