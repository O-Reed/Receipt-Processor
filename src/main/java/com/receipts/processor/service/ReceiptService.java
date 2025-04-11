package com.receipts.processor.service;

import com.receipts.processor.dto.ItemDTO;
import com.receipts.processor.dto.ReceiptDTO;
import com.receipts.processor.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public String processReceipt(ReceiptDTO receipt) {
        String id = UUID.randomUUID().toString();
        receiptRepository.saveReceipt(id, receipt);
        
        int points = calculatePoints(receipt);
        receiptRepository.savePoints(id, points);
        
        return id;
    }

    public Integer getPoints(String id) {
        return receiptRepository.findPointsById(id).orElse(null);
    }

    private int calculatePoints(ReceiptDTO receipt) {
        int points = 0;
        
        // Rule 1: One point for every alphanumeric character in the retailer name
        points += countAlphanumericChars(receipt.getRetailer());
        
        // Rule 2: 50 points if the total is a round dollar amount with no cents
        if (isRoundDollarAmount(receipt.getTotal())) {
            points += 50;
        }
        
        // Rule 3: 25 points if the total is a multiple of 0.25
        if (isMultipleOf25Cents(receipt.getTotal())) {
            points += 25;
        }
        
        // Rule 4: 5 points for every two items on the receipt
        points += (receipt.getItems().size() / 2) * 5;
        
        // Rule 5: Description length multiple of 3, multiply price by 0.2 and round up
        for (ItemDTO item : receipt.getItems()) {
            String trimmedDesc = item.getShortDescription().trim();
            if (trimmedDesc.length() % 3 == 0) {
                double price = Double.parseDouble(item.getPrice());
                points += (int) Math.ceil(price * 0.2);
            }
        }
        
        // Rule 6: 5 points if the total is greater than 10.00 (LLM rule)
        if (Double.parseDouble(receipt.getTotal()) > 10.00) {
            points += 5;
        }
        
        // Rule 7: 6 points if the day in the purchase date is odd
        LocalDate date = LocalDate.parse(receipt.getPurchaseDate());
        if (date.getDayOfMonth() % 2 != 0) {
            points += 6;
        }
        
        // Rule 8: 10 points if the time of purchase is after 2:00pm and before 4:00pm
        LocalTime time = LocalTime.parse(receipt.getPurchaseTime());
        LocalTime start = LocalTime.of(14, 0);
        LocalTime end = LocalTime.of(16, 0);
        if (time.isAfter(start) && time.isBefore(end)) {
            points += 10;
        }
        
        return points;
    }

    private int countAlphanumericChars(String str) {
        return (int) str.chars().filter(c -> Character.isLetterOrDigit(c)).count();
    }

    private boolean isRoundDollarAmount(String total) {
        return total.endsWith(".00");
    }

    private boolean isMultipleOf25Cents(String total) {
        double value = Double.parseDouble(total);
        double remainder = value % 0.25;
        // Using a small epsilon for floating point comparison
        return Math.abs(remainder) < 0.00001;
    }
}
