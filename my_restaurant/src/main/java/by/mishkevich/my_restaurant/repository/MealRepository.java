package by.mishkevich.my_restaurant.repository;

import by.mishkevich.my_restaurant.entity.Meal;
import by.mishkevich.my_restaurant.entity.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findAllByCategory(Category category);

    Meal findByName(String name);

    List<Meal> findAllByEnabled(String enabled);


}
