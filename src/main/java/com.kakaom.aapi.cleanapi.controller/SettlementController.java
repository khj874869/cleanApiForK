package com.kakaom.aapi.cleanapi.controller;

import com.kakaom.aapi.cleanapi.entity.Settlement;
import com.kakaom.aapi.cleanapi.service.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/settlements")
public class SettlementController {

    @Autowired
    private SettlementService settlementService;

    @PostMapping
    public ResponseEntity<Settlement> createSettlement(@RequestBody Settlement settlement) {
        Settlement createdSettlement = settlementService.createSettlement(settlement);
        return ResponseEntity.ok(createdSettlement);
    }

    @GetMapping
    public ResponseEntity<List<Settlement>> getAllSettlements() {
        List<Settlement> settlements = settlementService.getAllSettlements();
        return ResponseEntity.ok(settlements);
    }
}