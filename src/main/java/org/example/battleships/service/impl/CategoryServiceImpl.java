package org.example.battleships.service.impl;

import org.example.battleships.model.entity.CategoryEntity;
import org.example.battleships.model.entity.enums.CategoryNameEnum;
import org.example.battleships.repository.CategoryRepository;
import org.example.battleships.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initDatabase() {
        if (categoryRepository.count() == 0) {

            for (CategoryNameEnum name : CategoryNameEnum.values()) {

                CategoryEntity category = new CategoryEntity();
                String description = "";

                switch (name) {
                    case CARGO -> description = "this is a cargo ship";
                    case BATTLE -> description = "this is a battle ship";
                    case PATROL -> description = "this is a patrol ship";
                }

                categoryRepository.save(category.setName(name)
                        .setDescription(description));
            }
        }
    }

    @Override
    public CategoryEntity findByName(CategoryNameEnum category) {
        return categoryRepository.findByName(category).orElse(null);
    }
}
