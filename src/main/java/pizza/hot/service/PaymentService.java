package pizza.hot.service;

import pizza.hot.model.Payment;

import java.util.List;

public interface PaymentService {
    void savePayment(Payment payment);
    List<Payment> findAll();
    void deletePaymentById(Long id);
    public Payment getPaymentById(Long id);
    Payment getPaymentByIdAndCvv(Long id, String cVV);
}
