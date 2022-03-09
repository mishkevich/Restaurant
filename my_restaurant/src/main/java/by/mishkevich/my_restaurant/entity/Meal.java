package by.mishkevich.my_restaurant.entity;

import by.mishkevich.my_restaurant.entity.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "meals")
@Validated
public class Meal implements Serializable {

    @Column(name = "meal_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Введите имя блюда - от 2 до 20 символов")
    @Pattern(regexp = "^\\w{2,20}$", message = "Введите имя блюда - от 2 до 20 символов")
    private String name;

    @Column(name = "category")
    @NotBlank
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "price")
    @NotBlank(message = "Впишите сумму в формате х.хх")
    @Pattern(regexp = "^(0|[1-9]\\d*)([.,]\\d+)?\n", message = "Впишите сумму в формате х.хх")
    @Positive (message = "Стоимость не может быть отрицательной")
    @Min(value = 0, message = "Стоимость не может быть отрицательной")
    private Double price;

    @Column(name = "description")
    private String description;

    // Проверить работу enum TODO
    //    @Enumerated
    @Column(name = "enabled")
    private String enabled;

    public Meal(String name, Category category, Double price, String description, String enabled) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.enabled = enabled;
    }

    //    При удалении из корзины, срабатывает ошибка из-за некоректной связи TODO
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "orders_has_meals",
//            joinColumns = @JoinColumn(name = "meal_id"),
//            inverseJoinColumns = @JoinColumn(name = "order_id"))
//    private Collection<Order> ordersMeal;
}
