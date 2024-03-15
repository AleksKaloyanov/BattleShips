package org.example.battleships.repository;

import jakarta.validation.constraints.Size;
import org.example.battleships.model.entity.ShipEntity;
import org.example.battleships.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, Long> {
    Optional<ShipEntity> findByName(@Size(min = 2, max = 10) String name);

    List<ShipEntity> findAllByUser(UserEntity user);

    List<ShipEntity> findAllByUserNot(UserEntity user);
}