package sioa.taco_cloud.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import sioa.taco_cloud.model.Ingredient;
import sioa.taco_cloud.model.Ingredient.Type;
import sioa.taco_cloud.model.Taco;
import sioa.taco_cloud.model.TacoOrder;
import sioa.taco_cloud.repository.JdbcIngredientRepo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final JdbcIngredientRepo repo;

    @Autowired
    public DesignTacoController(JdbcIngredientRepo repo) {
        this.repo = repo;
    }

    @ModelAttribute
    public void addIngredientsToModel (Model model) {
        List<Ingredient> ingredients = (List<Ingredient>) repo.findAll();

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order () {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if(errors.hasErrors()) {
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing Taco: {}", taco);
        return "redirect:/orders/current";
    }
}
