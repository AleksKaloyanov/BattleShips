package org.example.battleships.service.impl;

import org.example.battleships.model.dto.ShipDTO;
import org.example.battleships.model.entity.ShipEntity;
import org.example.battleships.model.service.ShipServiceModel;
import org.example.battleships.repository.ShipRepository;
import org.example.battleships.service.CategoryService;
import org.example.battleships.service.ShipService;
import org.example.battleships.service.UserService;
import org.example.battleships.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipServiceImpl implements ShipService {

    private final ShipRepository shipRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final CategoryService categoryService;
    private final UserService userService;

    public ShipServiceImpl(ShipRepository shipRepository,
                           ModelMapper modelMapper,
                           CurrentUser currentUser,
                           CategoryService categoryService,
                           UserService userService) {
        this.shipRepository = shipRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public ShipServiceModel findByName(String name) {
        return shipRepository.findByName(name)
                .map(shipEntity -> modelMapper.map(shipEntity, ShipServiceModel.class))
                .orElse(null);
    }

    @Override
    public void add(ShipServiceModel shipServiceModel) {
        ShipEntity shipEntity = modelMapper
                .map(shipServiceModel, ShipEntity.class)
                .setCategory(categoryService.findByName(shipServiceModel.getCategory()))
                .setUser(userService.findById(currentUser.getId()));

        shipRepository.save(shipEntity);
    }

    @Override
    public List<ShipDTO> getShipsOwnedBy(Long id) {
        return shipRepository
                .findAllByUser(userService.findById(id))
                .stream()
                .map(shipEntity -> modelMapper.map(shipEntity, ShipDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShipDTO> getShipsNotOwnedBy(Long id) {
        return shipRepository
                .findAllByUserNot(userService.findById(id))
                .stream()
                .map(shipEntity -> modelMapper.map(shipEntity, ShipDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShipDTO> getAllShipsOrderedByNameHealthAndPower() {
        return shipRepository.findAllOrderedByNameHealthAndPower()
                .stream()
                .map(shipEntity -> modelMapper.map(shipEntity, ShipDTO.class))
                .collect(Collectors.toList());
    }

    public ShipEntity findById(Long id) {
        return shipRepository.findById(id)
                .orElse(null);
    }

    @Override
    public void battle(Long attackerId, Long defenderId) {
        ShipEntity attacker = findById(attackerId);
        ShipEntity defender = findById(defenderId);

        defender.setHealth(defender.getHealth() - attacker.getPower());

        if (defender.getHealth() <= 0) {
            shipRepository.deleteById(defenderId);
        } else {
            shipRepository.save(defender);
        }
    }
}
