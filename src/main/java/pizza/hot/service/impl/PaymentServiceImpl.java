package pizza.hot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizza.hot.dao.PaymentDao;
import pizza.hot.model.Payment;
import pizza.hot.service.PaymentService;

import java.util.List;
@Service
public class PaymentServiceImpl implements PaymentService {

    PaymentDao paymentDao;
    @Autowired
    public void setPaymentDao(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public void savePayment(Payment payment) {
          paymentDao.savePayment(payment);
    }

    @Override
    public List<Payment> findAll() {
        return  paymentDao.findAll();
    }

    @Override
    public void deletePaymentById(Long id) {
          paymentDao.deletePaymentById(id);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    @Override
    public Payment getPaymentByIdAndCvv(Long id, String cVV) {
        return paymentDao.getPaymentByIdAndCvv(id, cVV);
    }


}
