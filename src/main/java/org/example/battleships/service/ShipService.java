package org.example.battleships.service;

import org.example.battleships.model.service.ShipServiceModel;

import java.util.List;

public interface ShipService {
    ShipServiceModel findByName(String name);

    void add(ShipServiceModel shipServiceModel);

    List<ShipServiceModel> findCurrentUsersShips();

    List<ShipServiceModel> findOtherUsersShips();
}
