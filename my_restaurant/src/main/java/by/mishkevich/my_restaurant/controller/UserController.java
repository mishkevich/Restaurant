package by.mishkevich.my_restaurant.controller;

import by.mishkevich.my_restaurant.entity.Order;
import by.mishkevich.my_restaurant.entity.User;
import by.mishkevich.my_restaurant.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public String getUserPage(@PathVariable("id") Long id, Model model, HttpSession session) {
        model.addAttribute("user", userService.findById(id));
        return "user/details";
    }

    @GetMapping("orders")
    public String getOrderPage(Model model, HttpSession session) {
        List<User> users = userService.findAll();
        String username = (String) session.getAttribute("username");
        List<Order> orderList = (List<Order>) userService.findByUsername(username);
        model.addAttribute("orders", orderList);
        return "orders";
    }


}
