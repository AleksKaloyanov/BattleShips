package org.example.battleships.web;

import org.example.battleships.model.service.ShipServiceModel;
import org.example.battleships.service.ShipService;
import org.example.battleships.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

        List<ShipServiceModel> currentUsersShips = shipService.findCurrentUsersShips();
        List<ShipServiceModel> otherUsersShips = shipService.findOtherUsersShips();

        model.addAttribute("currentUserShips", shipService.findCurrentUsersShips())
                .addAttribute("otherUsersShips", shipService.findOtherUsersShips());

        return "home";

    }

}
