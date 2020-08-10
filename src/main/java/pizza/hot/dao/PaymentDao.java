package pizza.hot.dao;


import pizza.hot.model.Payment;

import java.util.List;

public interface PaymentDao {
    void savePayment(Payment payment);
    List<Payment> findAll();
    void deletePaymentById(Long id);
}
