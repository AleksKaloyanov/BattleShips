package org.example.battleships.web;

import org.example.battleships.model.dto.ShipDTO;
import org.example.battleships.model.dto.StartBattleDTO;
import org.example.battleships.service.ShipService;
import org.example.battleships.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final ShipService shipService;

    public HomeController(CurrentUser currentUser, ShipService shipService) {
        this.currentUser = currentUser;
        this.shipService = shipService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }

        Long loggedUserId = this.currentUser.getId();

        List<ShipDTO> ownShips = shipService.getShipsOwnedBy(loggedUserId);
        List<ShipDTO> enemyShips = shipService.getShipsNotOwnedBy(loggedUserId);
        List<ShipDTO> allShips = shipService.getAllShipsOrderedByNameHealthAndPower();

        model.addAttribute("ownShips", ownShips)
                .addAttribute("enemyShip0s", enemyShips)
                .addAttribute("shipBattleDTO", new ShipDTO())
                .addAttribute("allShips", allShips);

        return "home";

    }

    @ModelAttribute("startBattleDTO")
    public StartBattleDTO initBattleForm() {
        return new StartBattleDTO();
    }

}
