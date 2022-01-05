package recipes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Recipe")
@Table(name = "RECIPES")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id", nullable = false)
    private long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "category")
    private String category;


    @Column(name = "date")
    private LocalDateTime date = LocalDateTime.now();

    @NotBlank
    @Column(name = "description")
    private String description;

    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "INGREDIENTS", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "ingredients")
    private List<String> ingredients = new ArrayList<>();

    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "DIRECTIONS", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "directions")
    private List<String> directions = new ArrayList<>();


    @Column(name = "author")
    private String author;

    public Map<String, Object> objectToReturn() {
        Map<String, Object> recipeFormatted = new LinkedHashMap<String, Object>();
        recipeFormatted.put("name", this.getName());
        recipeFormatted.put("category", this.getCategory());
        recipeFormatted.put("date", this.getDate());
        recipeFormatted.put("description", this.getDescription());
        recipeFormatted.put("ingredients", this.getIngredients());
        recipeFormatted.put("directions", this.getDirections());
        return recipeFormatted;
    }

    public static List<Object> listToReturn(List<Recipe> list) {
        List<Object> listToReturn = new ArrayList<>();
        for (Recipe recipe : list) {
            listToReturn.add(recipe.objectToReturn());
        }
        return listToReturn;
    }
}
