package org.example.battleships.service.impl;

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
    public List<ShipServiceModel> findCurrentUsersShips() {
        return shipRepository
                .findAllByUser(userService.findById(currentUser.getId()))
                .stream()
                .map(shipEntity -> modelMapper.map(shipEntity, ShipServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShipServiceModel> findOtherUsersShips() {
        return shipRepository
                .findAllByUserNot(userService.findById(currentUser.getId()))
                .stream()
                .map(shipEntity -> modelMapper.map(shipEntity, ShipServiceModel.class))
                .collect(Collectors.toList());
    }
}
