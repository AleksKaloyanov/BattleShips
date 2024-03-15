package org.example.battleships.service;

import org.example.battleships.model.entity.UserEntity;
import org.example.battleships.model.service.UserServiceModel;

public interface UserService {
    boolean findByUsernameOrEmail(String username, String email);

    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    void logout();

    UserEntity findById(Long id);
}
