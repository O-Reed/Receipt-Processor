package com.receipts.processor.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Data Transfer Object for Receipt information
 */
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDTO {
    @Pattern(regexp = "^[\\w\\s\\-&]+$", message = "Invalid retailer format")
    private String retailer;
    
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid purchase date format. Expected: YYYY-MM-DD")
    private String purchaseDate;
    
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Invalid purchase time format. Expected: HH:MM")
    private String purchaseTime;
    
    @NotEmpty(message = "Items cannot be empty")
    private List<ItemDTO> items;
    
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Invalid total format")
    private String total;
    
    // Getters and setters
    public String getRetailer() {
        return retailer;
    }
    
    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }
    
    public String getPurchaseDate() {
        return purchaseDate;
    }
    
    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
    public String getPurchaseTime() {
        return purchaseTime;
    }
    
    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }
    
    public List<ItemDTO> getItems() {
        return items;
    }
    
    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }
    
    public String getTotal() {
        return total;
    }
    
    public void setTotal(String total) {
        this.total = total;
    }
}
