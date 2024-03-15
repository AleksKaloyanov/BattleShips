package org.example.battleships.repository;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.example.battleships.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsernameOrEmail(@Size(min = 3, max = 10) String username, @Email String email);

    Optional<UserEntity> findByUsernameAndPassword(@Size(min = 3, max = 10) String username, @Size(min = 3) String password);
}
