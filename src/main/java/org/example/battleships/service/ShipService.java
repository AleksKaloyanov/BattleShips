package org.example.battleships.service;

import org.example.battleships.model.dto.ShipDTO;
import org.example.battleships.model.service.ShipServiceModel;

import java.util.List;

public interface ShipService {
    ShipServiceModel findByName(String name);

    void add(ShipServiceModel shipServiceModel);

    List<ShipDTO> getShipsOwnedBy(Long id);

    List<ShipDTO> getShipsNotOwnedBy(Long id);

    List<ShipDTO> getAllShipsOrderedByNameHealthAndPower();

    void battle(Long attackerId, Long defenderId);
}
