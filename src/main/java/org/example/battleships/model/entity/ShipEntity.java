package org.example.battleships.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ships")
public class ShipEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 10)
    private String name;
    @Positive
    @NotNull
    private Integer health;
    @Positive
    @NotNull
    private Integer power;
    @PastOrPresent
    @NotNull
    private LocalDate created;
    @ManyToOne
    private CategoryEntity category;
    @ManyToOne
    private UserEntity user;

    public ShipEntity() {
    }

    public String getName() {
        return name;
    }

    public ShipEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getHealth() {
        return health;
    }

    public ShipEntity setHealth(Integer health) {
        this.health = health;
        return this;
    }

    public Integer getPower() {
        return power;
    }

    public ShipEntity setPower(Integer power) {
        this.power = power;
        return this;
    }

    public ShipEntity setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public ShipEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public ShipEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }
}
