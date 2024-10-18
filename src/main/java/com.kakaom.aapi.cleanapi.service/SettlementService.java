package com.kakaom.aapi.cleanapi.service;

import com.kakaom.aapi.cleanapi.entity.Settlement;
import com.kakaom.aapi.cleanapi.repository.SettlementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettlementService {

    @Autowired
    private SettlementRepository settlementRepository;

    public Settlement createSettlement(Settlement settlement) {
        return settlementRepository.save(settlement);
    }

    public List<Settlement> getAllSettlements() {
        return settlementRepository.findAll();
    }

}
