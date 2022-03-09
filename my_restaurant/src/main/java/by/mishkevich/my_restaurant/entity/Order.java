package by.mishkevich.my_restaurant.entity;

import by.mishkevich.my_restaurant.entity.enums.EatingPlace;
import by.mishkevich.my_restaurant.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "username", updatable = false,insertable = false)
    private String username;

    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @Column(name = "meal_name")
    private String mealName;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "total_cost")
    private Double totalCost;

    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "eating_place")
    @Enumerated(EnumType.STRING)
    private EatingPlace eatingPlace;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn (name = "username")
    private User usersOrder;

    @OneToOne(optional = true, mappedBy = "orderPayment")
    private Payment paymentOrder;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "orders_has_meals",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private Collection <Meal> mealsOrder;

}
