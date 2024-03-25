package org.example.battleships.model.dto;

public class ShipDTO {
    private Long id;
    private String name;
    private Integer health;
    private Integer power;

    public ShipDTO() {
    }

    public Long getId() {
        return id;
    }

    public ShipDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShipDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getHealth() {
        return health;
    }

    public ShipDTO setHealth(Integer health) {
        this.health = health;
        return this;
    }

    public Integer getPower() {
        return power;
    }

    public ShipDTO setPower(Integer power) {
        this.power = power;
        return this;
    }
}
