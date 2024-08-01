package sioa.taco_cloud.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sioa.taco_cloud.model.Ingredient;
import sioa.taco_cloud.model.Ingredient.Type;
import sioa.taco_cloud.repository.JdbcIngredientRepo;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private JdbcIngredientRepo repo;

    @Autowired
    public IngredientByIdConverter(JdbcIngredientRepo repo) {
        this.repo = repo;
    }

    @Override
    public Ingredient convert(String id) {
        return repo.findById(id).orElse(null);
    }
}
