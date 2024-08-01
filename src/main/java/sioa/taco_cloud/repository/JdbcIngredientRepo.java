package sioa.taco_cloud.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sioa.taco_cloud.model.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcIngredientRepo implements IngredientRepo{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcIngredientRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        String sql = "SELECT * FROM ingredient";
        return jdbcTemplate.query(sql, this::mapRowToIngredient);
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        String sql = "SELECT * FROM ingredient WHERE id = ?";
        List<Ingredient> ingredients = jdbcTemplate.query(sql, this::mapRowToIngredient, id);
        return ingredients.isEmpty() ?
                Optional.empty() : Optional.of(ingredients.getFirst());
    }

    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type"))
        );
    }
    @Override
    public Ingredient save(Ingredient ingredient) {
        String sql = "INSERT INTO ingredient (id, name, type) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, ingredient.getId(), ingredient.getName(), ingredient.getType().toString());
        return ingredient;
    }
}
