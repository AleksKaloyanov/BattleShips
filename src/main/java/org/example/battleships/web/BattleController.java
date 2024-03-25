package org.example.battleships.web;

import jakarta.validation.Valid;
import org.example.battleships.model.dto.StartBattleDTO;
import org.example.battleships.service.BattleService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BattleController {
    public final BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @PostMapping("/battle")
    public String battle(@Valid StartBattleDTO startBattleDTO) {

        if (startBattleDTO.getAttackerId()==null || startBattleDTO.getDefenderId()==null){
            return "redirect:/";
        }

        battleService.battle(startBattleDTO.getAttackerId(),startBattleDTO.getDefenderId());

        return "redirect:/";
    }

}
