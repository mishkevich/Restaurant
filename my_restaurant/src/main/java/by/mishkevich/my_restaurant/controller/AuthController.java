package by.mishkevich.my_restaurant.controller;

import by.mishkevich.my_restaurant.entity.User;
import by.mishkevich.my_restaurant.entity.UserNull;
import by.mishkevich.my_restaurant.entity.enums.Role;
import by.mishkevich.my_restaurant.exeptions.InvalidData;
import by.mishkevich.my_restaurant.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String getLoginPage() {
        return "/auth/login";
    }

    @GetMapping("logout")
    public String getLogoutPage() {
        return "index_2";
    }
//    Отображается при логауте стандарт страница логина TODO

    @GetMapping("registration")
    public String registrationForm(Model model) {
        model.addAttribute("user", new UserNull());
        return "/auth/registration";
    }

    // Проверить регистрацию на ошибки TODO
    @PostMapping("registration")
    public String registrationUserSubmit(@ModelAttribute("user") @Valid UserNull preUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/auth/registration";

        User newUser = new User(preUser.getUsername(),
                preUser.getEmail(),
                passwordEncoder.encode(preUser.getPassword()),
                Role.USER,
                preUser.getFirstName(),
                preUser.getSurname(),
                preUser.getDateOfBirth(),
                preUser.getPhoneNumber(),
                preUser.getBalance(),
                preUser.getDiscount(),
                preUser.getStatus(),
                preUser.isEnabled());

        if (newUser.getUsername().isBlank()) {
            throw new InvalidData("Введите имя пользователя");
        } else {
            if (preUser.getUsername().equals(userService.findByUsername(newUser.getUsername()))) {
                throw new InvalidData("Такой пользователь уже существует");
            } else {
                userService.create(newUser);
                System.out.println(newUser);
                return "user/confirm";
            }
        }
    }
}