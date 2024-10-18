package com.kakaom.aapi.cleanapi.repository;

import com.kakaom.aapi.cleanapi.entity.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettlementRepository extends JpaRepository<Settlement, Long> {
    List<Settlement> findAll();

    Settlement save(Settlement settlement);
}
