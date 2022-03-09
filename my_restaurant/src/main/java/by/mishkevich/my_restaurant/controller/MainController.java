package by.mishkevich.my_restaurant.controller;


import by.mishkevich.my_restaurant.auth.ApplicationUser;
import by.mishkevich.my_restaurant.entity.Meal;
import by.mishkevich.my_restaurant.service.impl.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private MealService mealService;


    @GetMapping("main")
    public String getMainPage(HttpSession session) {
        return "main";
    }

    @GetMapping("main_reg")
    public String getMainReg(HttpSession session) {
        ApplicationUser user = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("username", user.getUsername());
        session.setAttribute("basket_quantity", "0");
        return "main_reg";
    }

    @GetMapping("menu")
    public String getMenuPage(Model model, HttpSession session) {
        List<Meal> mealsList = mealService.findAll();
        model.addAttribute("meal", mealsList);
        return "menu";
    }

    @GetMapping("about")
    public String getAboutPage(HttpSession session) {
        return "about";
    }
}
