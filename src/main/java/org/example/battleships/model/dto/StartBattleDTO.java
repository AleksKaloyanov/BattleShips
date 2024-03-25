package org.example.battleships.model.dto;

import jakarta.validation.constraints.Positive;

public class StartBattleDTO {
    @Positive
    private Long attackerId;
    @Positive
    private Long defenderId;

    public StartBattleDTO() {
    }

    public Long getAttackerId() {
        return attackerId;
    }

    public StartBattleDTO setAttackerId(Long attackerId) {
        this.attackerId = attackerId;
        return this;
    }

    public Long getDefenderId() {
        return defenderId;
    }

    public StartBattleDTO setDefenderId(Long defenderId) {
        this.defenderId = defenderId;
        return this;
    }
}
