package pizza.hot.dao;


import pizza.hot.model.Payment;
import pizza.hot.model.User;

import java.util.List;

public interface PaymentDao {
    void savePayment(Payment payment);
    List<Payment> findAll();
    void deletePaymentById(Long id);
    public Payment getPaymentById(Long id);
   Payment getPaymentByIdAndCvv(Long id, String cVV);
}
