package by.mishkevich.my_restaurant.repository;

import by.mishkevich.my_restaurant.entity.Order;
import by.mishkevich.my_restaurant.entity.enums.EatingPlace;
import by.mishkevich.my_restaurant.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByUsername(String username);

    Order findByPaymentId(Long id);

    List<Order> findAllByOrderStatus(OrderStatus orderStatus);

    List<Order> findAllByEatingPlace(EatingPlace eatingPlace);
}
