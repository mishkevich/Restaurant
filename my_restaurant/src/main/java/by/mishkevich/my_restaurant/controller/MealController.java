package by.mishkevich.my_restaurant.controller;

import by.mishkevich.my_restaurant.entity.*;
import by.mishkevich.my_restaurant.entity.MealNull;
import by.mishkevich.my_restaurant.exeptions.InvalidData;
import by.mishkevich.my_restaurant.service.impl.MealService;
import by.mishkevich.my_restaurant.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MealController {

    @Autowired
    private MealService mealService;
    @Autowired
    private OrderService orderService;
    private List<Meal> mealsInBasket = new ArrayList<>();


    @GetMapping("/{id}")
    public String getMealPage(@PathVariable("id") Long id, Model model, HttpSession session) {
        model.addAttribute("meal", mealService.findById(id));
        return "meal/meal";
    }

    @GetMapping("new")
    public String newMeal (Model model) {
        model.addAttribute("meal", new MealNull());
        return "meal/new";
    }

    @GetMapping("add_new")
    public String newMealConfirm (Model model) {
        return "meal/confirm";
    }

    @PostMapping("new")
    public String newMealSubmit(@ModelAttribute("meal") @Valid MealNull mealNull, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/meal/new";

        Meal newMeal = new Meal(mealNull.getName(),
                mealNull.getCategory(),
                mealNull.getPrice(),
                mealNull.getDescription(),
                mealNull.getEnabled());
        if (newMeal.getName().equals(mealService.findByName(newMeal.getName()))) {
            throw new InvalidData("Такое блюдо уже существует"); }
        //Отработка ошибки не работает
        mealService.create(newMeal);
        System.out.println("Добавлено новое блюдо" + newMeal);
        return "redirect: menu";
    }


    @GetMapping("addToBasket")
    public String addMealsToBasket(@RequestParam(name = "menuId") Long mealId, HttpSession session) {
        Meal meal = mealService.findById(mealId);
        mealsInBasket.add(meal);
        session.setAttribute("basket_quantity", String.valueOf(mealsInBasket.size()));
        System.out.println("Успешно добавлено блюдо - " + meal.getName());
        return "redirect:/menu";
    }

    @GetMapping("/basket")
    public String getBasket(Model model, HttpSession session) {
        model.addAttribute("mealsInBasket", mealsInBasket);
        System.out.println("Вывести корзину");
        return "basket";
    }

    @Transactional
    @GetMapping("deleteFromBasket")
    public String deleteMealFromBasket(@RequestParam(name = "menuId") Long menuId, HttpSession session) {
        Meal meal = null;
        try {
            meal = mealsInBasket.stream()
                    .filter(mealInBasket -> mealInBasket.getId().equals(menuId))
                    .findFirst()
                    .orElseThrow(() -> new ClassNotFoundException("Meal with ID: " + menuId + " not found"));
        } catch (ClassNotFoundException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        System.out.println("Вы удаляете блюдо из корзины" + meal);
        mealsInBasket.remove(meal);
        session.setAttribute("basket_quantity", String.valueOf(mealsInBasket.size()));
        return "redirect:/menu/basket";
    }


//    @GetMapping("formAnOrder")
//    public String formAnOrder(HttpSession session) {
//        String username = (String) session.getAttribute("username");
//        Long orderId = orderService.formAnOrder(username, mealsInBasket);
//
//        if (!orderId.equals(0L)){
//            System.out.println(orderId);
//            mealsInBasket.clear();
//            session.setAttribute("basket_quantity", 0);
//            session.setAttribute("orderId", orderId);
//            return "form_order/accepted";
//        } else {
//            return "form_order/rejected";
//        }
//    }
}
