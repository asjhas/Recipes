package recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

//Service to manage CRUD actions on repository
@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;

    public List<Object> getAllRecipes(String author) {
        try {
            List<Recipe> list = recipeRepository.findAllByIdNotNullOrderByDateDesc();
            List<Recipe> listForAuthor = list.stream().filter(recipe -> recipe.getAuthor() == author).collect(Collectors.toList());
            return Recipe.listToReturn(listForAuthor);
        } catch (Exception e) {
            return List.of();
        }
    }

    //get all records matching name
    public List<Object> getRecipeByName(String name) {
        try {
            List<Recipe> list = recipeRepository.findRecipesByNameIgnoreCaseContainsOrderByDateDesc(name);
/*
            List<Recipe> listForAuthor = list.stream().filter(recipe -> recipe.getAuthor() == author).collect(Collectors.toList());
            return Recipe.listToReturn(listForAuthor);
 */
            return Recipe.listToReturn(list);
        } catch (Exception e) {
            return List.of();
        }
    }

    //get all records matching category
    public List<Object> getRecipeByCategory(String category) {
        try {
            List<Recipe> list = recipeRepository.findRecipesByCategoryIgnoreCaseLikeOrderByDateDesc(category);
/*
            List<Recipe> listForAuthor = list.stream().filter(recipe -> recipe.getAuthor() == author).collect(Collectors.toList());
            return Recipe.listToReturn(listForAuthor);
*/
            return Recipe.listToReturn(list);
        } catch (Exception e) {
            return List.of();
        }
    }

    //getting record by id
    public Optional<Recipe> getRecipeById(long id) {
        return recipeRepository.findById(id);
    }

    //save or update recipe by id
    public Long saveOrUpdate(Recipe recipe, long id, String author) {
        if (id == 0) {
            recipe.setAuthor(author);
            Recipe newRecipe = recipeRepository.save(recipe);
            return newRecipe.getId();
        } else {
            recipe.setAuthor(author);
            recipe.setId(id);
            recipeRepository.save(recipe);
            return id;
        }
    }

    //deleting a specific record
    public void deleteById(long id) {
        recipeRepository.deleteById(id);
    }
}

