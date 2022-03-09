package by.mishkevich.my_restaurant.service.impl;

import by.mishkevich.my_restaurant.entity.Meal;
import by.mishkevich.my_restaurant.entity.enums.Category;
import by.mishkevich.my_restaurant.exeptions.UserNotFoundException;
import by.mishkevich.my_restaurant.repository.MealRepository;
import by.mishkevich.my_restaurant.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Validated
public class MealService implements IService<Meal, Long> {

    @Autowired
    private MealRepository mealRepository;

    @Override
    public List<Meal> findAll() {
        return mealRepository.findAll();
    }

    @Override
    public Meal findById(Long id) {
        Optional<Meal> mealId = mealRepository.findById(id);
        if (mealId.isEmpty()) {
            throw new UserNotFoundException("There is no meal with ID = " + id + " in database");
        }
        return mealId.get();
    }

    @Override
    public void create(Meal entity) {
        mealRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        mealRepository.deleteById(id);
    }

    public List<Meal> findAllByCategory(Category category) {
        return mealRepository.findAllByCategory(category);
    }

    public Meal findByName(String name) {
        return mealRepository.findByName(name);
    }

    public List<Meal> findAllByEnabled(String enabled) {
        List<Meal> mealEnabled = mealRepository.findAllByEnabled(enabled);
        if (mealEnabled.isEmpty()) {
            throw new UserNotFoundException("There is no meal with status = " + enabled + " in database");}
        return mealRepository.findAllByEnabled(enabled);
    }
}
