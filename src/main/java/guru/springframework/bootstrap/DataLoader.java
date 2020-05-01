package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category mexican = categoryRepository.findByDescription("Mexican").get();
        Category dip = categoryRepository.findByDescription("Dip").get();
        Category vegetarian = categoryRepository.findByDescription("Vegetarian").get();
        Category grill = categoryRepository.findByDescription("Grill").get();
        Category chicken = categoryRepository.findByDescription("Chicken").get();


        UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure dash = unitOfMeasureRepository.findByDescription("Dash").get();

        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(createIngredient("ripe avocados", 2, null));
        ingredients.add(createIngredient("salt, more to taste", 0.25, teaspoon));
        ingredients.add(createIngredient("fresh lime juice or lemon juice", 1, tablespoon));
        ingredients.add(createIngredient("to 1/4 cup of minced red onion or thinly sliced green onion", 2, tablespoon));
        ingredients.add(createIngredient("serrano chiles, stems and seeds removed, minced", 2, null));
        ingredients.add(createIngredient("cilantro (leaves and tender stems), finely chopped", 2, tablespoon));
        ingredients.add(createIngredient("of freshly grated black pepper", 1, dash));
        ingredients.add(createIngredient("ripe tomato, seeds and pulp removed, chopped", 0.5, null));
        ingredients.add(createIngredient("Red radishes or jicama, to garnish", 1, null));
        ingredients.add(createIngredient("Tortilla chips, to serve", 1, null));

        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setCategories(new HashSet<>(Arrays.asList(mexican, dip, vegetarian)));
        guacamole.setIngredients(ingredients);
        guacamole.setPrepTime(10);
        recipeRepository.save(guacamole);
        System.out.println("Guacamole saved");

        ingredients = new HashSet<>();
        ingredients.add(createIngredient("tablespoons ancho chili powder", 2, tablespoon));
        ingredients.add(createIngredient("teaspoon dried oregano", 1, teaspoon));
        ingredients.add(createIngredient("teaspoon dried cumin", 1, teaspoon));
        ingredients.add(createIngredient("teaspoon sugar", 1, teaspoon));
        ingredients.add(createIngredient("teaspoon salt", 0.5, teaspoon));
        ingredients.add(createIngredient("clove garlic, finely chopped", 1, null));
        ingredients.add(createIngredient("tablespoon finely grated orange zest", 1, tablespoon));
        ingredients.add(createIngredient("tablespoons fresh-squeezed orange juice", 3, tablespoon));
        ingredients.add(createIngredient("tablespoons olive oil", 2, tablespoon));
        ingredients.add(createIngredient("skinless, boneless chicken thighs (1 1/4 pounds)", 5, null));

        Recipe tacos = new Recipe();
        tacos.setDescription("Spicy Grilled Chicken Tacos");
        tacos.setCategories(new HashSet<>(Arrays.asList(mexican, grill, chicken)));
        tacos.setPrepTime(5);
        tacos.setCookTime(30);
        recipeRepository.save(tacos);

        System.out.println("Tacos saved");
    }

    private Ingredient createIngredient(String desc, double amount, UnitOfMeasure uom) {
        Ingredient ing = new Ingredient();
        ing.setDescription(desc);
        ing.setAmount(BigDecimal.valueOf(amount));
        return ing;
    }
}
