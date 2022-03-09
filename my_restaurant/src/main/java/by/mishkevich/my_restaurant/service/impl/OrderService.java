package by.mishkevich.my_restaurant.service.impl;

import by.mishkevich.my_restaurant.entity.Order;
import by.mishkevich.my_restaurant.entity.User;
import by.mishkevich.my_restaurant.entity.enums.EatingPlace;
import by.mishkevich.my_restaurant.entity.enums.OrderStatus;
import by.mishkevich.my_restaurant.exeptions.UserNotFoundException;
import by.mishkevich.my_restaurant.repository.OrderRepository;
import by.mishkevich.my_restaurant.repository.UserRepository;
import by.mishkevich.my_restaurant.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService implements IService <Order, Long> {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> orderId = orderRepository.findById(id);
        if (orderId.isEmpty()) {
            throw new UserNotFoundException("There is no order with ID = " + id + " in database");
        }
        return orderId.get();
    }

    @Override
    public void create(Order entity) {
        orderRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public Order findByUsername(String username) {
        return orderRepository.findByUsername(username);
    }

    public Order findByPaymentId (Long id) {
        return orderRepository.findByPaymentId(id);
    }

    public List<Order> findAllByOrderStatus(OrderStatus orderStatus) {
        return orderRepository.findAllByOrderStatus(orderStatus);
    }

    public List<Order> findAllByEatingPlace(EatingPlace eatingPlace) {
        return orderRepository.findAllByEatingPlace(eatingPlace);
    }
}
