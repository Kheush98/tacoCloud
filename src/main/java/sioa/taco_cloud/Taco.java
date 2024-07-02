package sioa.taco_cloud;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Data
public class Taco {
    @NotNull
    @Size(min = 5, message = "Le nom doit comporter d'au moins 5 caracteres")
    private String name;

    @NotNull
    @Size(min = 1, message = "Vous devez choisir au moins un ingredient")
    private List<Ingredient> ingredients;
}
