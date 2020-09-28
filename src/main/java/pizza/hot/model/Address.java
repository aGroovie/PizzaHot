package pizza.hot.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Entity
@Table(name ="user_address")
@EntityListeners(AuditingEntityListener.class)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;


    @NotNull
    @Column(name = "address_city")
    @Size(min = 3, max = 20, message = "City must be between {min} and {max}")
    private String city;

    @Column(name = "address_street")
    @NotNull
    @Size(min = 3, max = 30, message = "Street must be between {min} and {max}")
    private String street;

    @Column(name = "address_house_number")
    @NotNull
    @Digits(integer = 3, fraction = 0,message = "Please, enter the valid house number")
    private int houseNumber;

    @Column(name = "address_apartment_number")
    @NotNull
    @Digits(integer = 3, fraction = 0)
    private int apartmentNumber;

    @Column(name = "address_phone_number")   // Custom validator with phone number to do
    @NotNull
    private String phoneNumber;

    @Column(name ="address_zip") //Custom validator for zip
    @NotNull
    private int zip;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
