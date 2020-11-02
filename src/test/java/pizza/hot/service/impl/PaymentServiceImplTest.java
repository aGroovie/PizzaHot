package pizza.hot.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pizza.hot.dao.PaymentDao;
import pizza.hot.model.Payment;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceImplTest {


    @Mock
    PaymentDao paymentDao;

    @InjectMocks
    PaymentServiceImpl paymentServiceImpl = new PaymentServiceImpl();

    Payment payment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        payment = new Payment();
        payment.setCcCVV("322");
        payment.setCcNumber("432832842388233");
        payment.setCcExpiration("12/22");
        payment.setId(22L);
        payment.setState("new york");
        payment.setName("Bobby Lashley");
        payment.setCity("New York");
        payment.setStreet("144 Ortchard street");
        payment.setZip("10002");
        payment.setPhone("21236742000");
    }


    @Test
    void savePayment_savesCorrectDate() {
        paymentServiceImpl.savePayment(payment);
        String curDate = "02-11-2020";
        assertEquals(payment.getDate(), curDate);
    }

    @Test
    void findAll_returnsCorrect() {
        Mockito.when(paymentServiceImpl.findAll()).thenReturn(Arrays.asList(new Payment(), new Payment(), payment));
        Assert.assertEquals(3, paymentServiceImpl.findAll().size());
    }

}