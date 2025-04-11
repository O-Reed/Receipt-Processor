package com.receipts.processor.controller;

import com.receipts.processor.dto.PointsResponseDTO;
import com.receipts.processor.dto.ProcessResponseDTO;
import com.receipts.processor.dto.ReceiptDTO;
import com.receipts.processor.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public ResponseEntity<ProcessResponseDTO> processReceipt(@Valid @RequestBody ReceiptDTO receiptDTO) {
        // Use the DTO directly
        String id = receiptService.processReceipt(receiptDTO);
        ProcessResponseDTO response = new ProcessResponseDTO();
        response.setId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<?> getPoints(@PathVariable String id) {
        Integer points = receiptService.getPoints(id);
        if (points == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No receipt found for that ID.");
        }
        PointsResponseDTO response = new PointsResponseDTO();
        response.setPoints(points);
        return ResponseEntity.ok(response);
    }
}
