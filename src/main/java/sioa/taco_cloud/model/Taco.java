package sioa.taco_cloud.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Data
public class Taco {
    private long id;
    private Date createdAt = new Date();
    @NotNull
    @Size(min = 5, message = "Le nom doit comporter d'au moins 5 caracteres")
    private String name;
    @NotNull
    @Size(min = 1, message = "Vous devez choisir au moins un ingredient")
    private List<Ingredient> ingredients;
}
