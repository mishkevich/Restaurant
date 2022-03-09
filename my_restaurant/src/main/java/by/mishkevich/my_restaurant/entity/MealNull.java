package by.mishkevich.my_restaurant.entity;

import by.mishkevich.my_restaurant.entity.enums.Category;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealNull {

    @NotBlank(message = "Введите имя блюда - от 2 до 20 символов")
    @Pattern(regexp = "^\\w{2,20}$", message = "Введите имя блюда - от 2 до 20 символов")
    private String name;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private Category category;

    @NotBlank(message = "Впишите сумму в формате х.хх")
    @Pattern(regexp = "^(0|[1-9]\\d*)([.,]\\d+)?\n", message = "Впишите сумму в формате х.хх")
    @Positive (message = "Стоимость не может быть отрицательной")
    @Min(value = 0, message = "Стоимость не может быть отрицательной")
    private Double price;

    private String description;

    //    Проверить будет ли работать enum
    //    @Enumerated
    @NotBlank
    private String enabled;
}
