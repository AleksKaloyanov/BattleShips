package org.example.battleships.service.impl;

import org.example.battleships.model.dto.ShipDTO;
import org.example.battleships.service.BattleService;
import org.example.battleships.service.ShipService;
import org.springframework.stereotype.Service;

@Service
public class BattleServiceImpl implements BattleService {

    private final ShipService shipService;

    public BattleServiceImpl(ShipService shipService) {
        this.shipService = shipService;
    }

    @Override
    public void battle(Long attackerId, Long defenderId) {
        shipService.battle(attackerId,defenderId);
    }
}
