package sioa.taco_cloud.repository;

import sioa.taco_cloud.model.Ingredient;

import java.util.Optional;

public interface IngredientRepo {
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);
}
