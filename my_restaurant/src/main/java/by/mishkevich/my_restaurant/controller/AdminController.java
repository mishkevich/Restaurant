package by.mishkevich.my_restaurant.controller;

import by.mishkevich.my_restaurant.entity.Meal;
import by.mishkevich.my_restaurant.entity.User;
import by.mishkevich.my_restaurant.service.impl.MealService;
import by.mishkevich.my_restaurant.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private MealService mealService;


    @GetMapping("users")
    public String getAllUsers(Model model, HttpSession session) {
        List<User> usersList = userService.findAll();
        model.addAttribute("user", usersList);
        return "/admin/users";
    }

    @GetMapping("meals")
    public String getAllMeals(Model model, HttpSession session) {
        List<Meal> mealsList = mealService.findAll();
        model.addAttribute("meal", mealsList);
        return "/admin/meals";
    }
}

//    @GetMapping("users/change")
//    public String changeUser (Model model, HttpSession session) {
//
//    }


//    Optional<User> user = userService.findByUsername(username);
//    String username = (String) session.getAttribute("username");
//    List<Order> orderList = (List<Order>) userService.findByUsername(username);
//        model.addAttribute("orders", orderList);
////
//    @GetMapping("users")
//    public String getAllUsers (Model model, HttpSession session) {
//        List<User> users = userService.findAll();
//        String username = (String) session.getAttribute("username");
//        List<Order> orderList = (List<Order>) userService.findByUsername(username);
//        model.addAttribute("orders", orderList);
//        return "orders";
//    }



