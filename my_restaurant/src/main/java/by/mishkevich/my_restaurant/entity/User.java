package by.mishkevich.my_restaurant.entity;


import by.mishkevich.my_restaurant.entity.enums.Role;
import by.mishkevich.my_restaurant.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Validated
public class User implements Serializable {

    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    @NotBlank(message = "Введите имя пользователя - от 2 до 20 символов")
    @Pattern(regexp = "^\\w{2,20}$", message = "Введите имя пользователя - от 2 до 20 символов")
    private String username;

    @Column(name = "email")
    @NotBlank(message = "Введите ваш e-mail")
    @Email(message = "Введите ваш e-mail")
    @Pattern(regexp = "\\\\w+([\\\\.-]?\\\\w+)*@\\\\w+([\\\\.-]?\\\\w+)*\\\\.\\\\w{2,4}", message = "Введите ваш e-mail")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "Пожалуйста введите пароль, от 4 до 10 символов")
    @Pattern(regexp = "^\\w{4,10}$", message = "Пожалуйста введите пароль, от 4 до 10 символов")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "date_of_birth")
    @Past(message = "Введите корректную дату рождения в формате ДД.ММ.ГГГГ")
    @Pattern(regexp = "(0?[1-9]|[12][0-9]|3[01]).(0?[1-9]|1[012]).((19|20)\\d\\d)", message = "Введите дату рождения в формате ДД.ММ.ГГГГ")
    private LocalDate dateOfBirth;

    @Column(name = "phone_number")
    @Pattern(regexp = "[0-9{3}]+(-)+[0-9{2}]+(-)+[0-9{3}]+(-)+[0-9{2}]+(-)+[0-9{2}]", message = "Введите ваш номер в международном формате: 123-45-678-99-00")
    private String phoneNumber;

    @Column(name = "balance")
    @Positive(message = "Баланс не может быть отрицательным")
    @Min(value = 0, message = "Баланс не может быть отрицательным")
    private Double balance;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(mappedBy = "usersOrder", fetch = FetchType.LAZY)
    private Collection<Order> ordersUser;

    public User(String username, String email, String password, Role role, String firstName, String surname, LocalDate dateOfBirth, String phoneNumber, Double balance, Integer discount, Status status, boolean enabled) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.discount = discount;
        this.status = status;
        this.enabled = enabled;
    }
}

