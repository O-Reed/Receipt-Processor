package com.receipts.processor.repository;

import com.receipts.processor.dto.ReceiptDTO;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ReceiptRepository {
    
    private final Map<String, ReceiptDTO> receiptStorage = new ConcurrentHashMap<>();
    private final Map<String, Integer> pointsStorage = new ConcurrentHashMap<>();
    
    public void saveReceipt(String id, ReceiptDTO receipt) {
        receiptStorage.put(id, receipt);
    }
    
    public Optional<ReceiptDTO> findById(String id) {
        return Optional.ofNullable(receiptStorage.get(id));
    }
    
    public void savePoints(String id, int points) {
        pointsStorage.put(id, points);
    }
    
    public Optional<Integer> findPointsById(String id) {
        return Optional.ofNullable(pointsStorage.get(id));
    }
}
