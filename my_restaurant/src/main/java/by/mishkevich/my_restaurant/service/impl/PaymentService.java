package by.mishkevich.my_restaurant.service.impl;

import by.mishkevich.my_restaurant.entity.Payment;
import by.mishkevich.my_restaurant.entity.enums.Method;
import by.mishkevich.my_restaurant.entity.enums.PaymentStatus;
import by.mishkevich.my_restaurant.repository.MealRepository;
import by.mishkevich.my_restaurant.repository.PaymentRepository;
import by.mishkevich.my_restaurant.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentService implements IService <Payment, Long> {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment findById(Long id) {
        Optional<Payment> paymentId = paymentRepository.findById(id);
        //        if (userId.isEmpty()) {
//            throw new NoSuchRestaurantException("There is no order with ID = " + id + " in database");
//        }// TODO
        return paymentId.get();
    }

    @Override
    public void create(Payment entity) { paymentRepository.save(entity); }

    @Override
    public void delete(Long id) { paymentRepository.deleteById(id);
    }

    public List<Payment> findAllByPaymentStatus(PaymentStatus paymentStatus){
        return paymentRepository.findAllByPaymentStatus(paymentStatus);
    }

    public List<Payment> findAllByMethod (Method method) {
        return paymentRepository.findAllByMethod(method);
    }

}
