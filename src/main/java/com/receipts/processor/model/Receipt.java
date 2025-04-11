package com.receipts.processor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {
    @Pattern(regexp = "^[\\w\\s\\-&]+$", message = "Invalid retailer format")
    private String retailer;
    
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid purchase date format. Expected: YYYY-MM-DD")
    private String purchaseDate;
    
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Invalid purchase time format. Expected: HH:MM")
    private String purchaseTime;
    
    @NotEmpty(message = "Items cannot be empty")
    private List<Item> items;
    
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Invalid total format")
    private String total;
}
