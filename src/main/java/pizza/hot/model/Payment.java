package pizza.hot.model;


import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Component
@Entity
@Table(name ="payment_info")
@EntityListeners(AuditingEntityListener.class)
public class Payment implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;


    @NotNull
    @Column(name = "payment_clientname")
    @Size(min = 2, message = "Name cannot be less than 2 characters!")
    private String name;

    @NotNull
    @Column(name = "payment_city")
    private String city;

    @NotNull
    @Column(name = "payment_street")
    private String street;

    @NotNull
    @Column(name = "payment_state")
    private String state;

    @NotNull
    @Column(name = "payment_zip")
    @Size(min = 5, max = 5)
    private String zip;

    @NotNull
    @Column(name = "payment_phone")
    @Size(min=11)
    private String phone;


    @Column(name = "payment_date")
    private String date;

   @CreditCardNumber(message = "Please, enter a valid credit card number")
    @Column(name = "payment_cc")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message = "Must be formatted MM/YY")
    @Column(name = "payment_ccexp")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    @Column(name = "payment_vv")
    private String ccCVV;



}
