package pizza.hot.model;


import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

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
    @Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$",message = "zip code must be 5 numbers length!")
    private String zip;

    @NotNull
    @Column(name = "payment_phone")
    @Pattern(regexp = "^\\d{11}$",message = "Phone number must be 11 numbers length!")
    private String phone;


    @Column(name = "payment_date")
    private String date;

  @CreditCardNumber(message = "Please, enter a valid credit card number")
    @Column(name = "payment_card")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message = "Must be formatted MM/YY")
    @Column(name = "payment_ccexp")
    private String ccExpiration;

   @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    @Column(name = "payment_cvv")
    private String ccCVV;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;



    public  Payment(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpiration() {
        return ccExpiration;
    }

    public void setCcExpiration(String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public String getCcCVV() {
        return ccCVV;
    }

    public void setCcCVV(String ccCVV) {
        this.ccCVV = ccCVV;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
