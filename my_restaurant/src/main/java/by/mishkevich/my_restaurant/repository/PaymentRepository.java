package by.mishkevich.my_restaurant.repository;

import by.mishkevich.my_restaurant.entity.Payment;
import by.mishkevich.my_restaurant.entity.enums.Method;
import by.mishkevich.my_restaurant.entity.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository <Payment, Long> {

    List<Payment> findAllByPaymentStatus(PaymentStatus paymentStatus);

    List<Payment> findAllByMethod (Method method);
}
