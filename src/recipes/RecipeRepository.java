package recipes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    List<Recipe> findRecipesByNameIgnoreCaseContainsOrderByDateDesc(String name);

    List<Recipe> findRecipesByCategoryIgnoreCaseLikeOrderByDateDesc(String category);

    List<Recipe> findAllByIdNotNullOrderByDateDesc();

    void deleteById(long id);
}
