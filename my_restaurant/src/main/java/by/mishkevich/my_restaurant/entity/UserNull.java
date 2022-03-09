package by.mishkevich.my_restaurant.entity;


import by.mishkevich.my_restaurant.entity.enums.Status;
import lombok.*;
import java.time.LocalDate;
import javax.validation.constraints.*;
import static by.mishkevich.my_restaurant.entity.enums.Status.ACTIVE;
import static org.hibernate.cfg.AvailableSettings.USER;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNull {

    @NotBlank(message = "Введите имя пользователя - от 2 до 20 символов")
    @Pattern(regexp = "^\\w{2,20}$", message = "Введите имя пользователя - от 2 до 20 символов")
    private String username;

    @NotBlank(message = "Введите ваш e-mail")
    @Email(message = "Введите ваш e-mail")
    @Pattern(regexp = "\\\\w+([\\\\.-]?\\\\w+)*@\\\\w+([\\\\.-]?\\\\w+)*\\\\.\\\\w{2,4}", message = "Введите ваш e-mail")
    private String email;

    @NotBlank(message = "Пожалуйста введите пароль, от 4 до 10 символов")
    @Pattern(regexp = "^\\w{4,10}$", message = "Пожалуйста введите пароль, от 4 до 10 символов")
    private String password;
    private String role = USER;
    private String firstName;
    private String surname;

    @Past (message = "Введите корректную дату рождения в формате ДД.ММ.ГГГГ")
    @Pattern(regexp = "(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((19|20)\\d\\d)", message = "Введите дату рождения в формате ДД.ММ.ГГГГ")
    private LocalDate dateOfBirth;

    @Pattern(regexp = "[0-9{3}]+(-)+[0-9{2}]+(-)+[0-9{3}]+(-)+[0-9{2}]+(-)+[0-9{2}]", message = "Введите ваш номер в международном формате: 123-45-678-99-00")
    private String phoneNumber;

    @Positive (message = "Баланс не может быть отрицательным")
    @Min(value = 0, message = "Баланс не может быть отрицательным")
    private Double balance = 0.00;

    private Integer discount = 0;
    private Status status = ACTIVE;
    private boolean enabled = true;
}
