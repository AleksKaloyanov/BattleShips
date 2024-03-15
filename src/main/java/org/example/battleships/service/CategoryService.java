package org.example.battleships.service;

import org.example.battleships.model.entity.CategoryEntity;
import org.example.battleships.model.entity.enums.CategoryNameEnum;

public interface  CategoryService {
    void initDatabase();

    CategoryEntity findByName(CategoryNameEnum category);
}
