package pizza.hot.model;

import pizza.hot.validator.UserNameConstraint;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {



    public static final String ROLE_ADMIN = "ADMIN_ROLE";
    public static final String ROLE_CUSTOMER = "CUSTOMER_ROLE";




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    @NotNull
    @Size(min=5,max=10, message = "Username must be between {min} and {max}")
   // @UserNameConstraint
    private String username;

    @Column(name = "user_pw")
    @NotNull
    @Size(min=6,max=1000, message ="Password must be between {min} and {max}" )
    private String password;

    @Column(name = "user_fname")
    @NotNull
    @Size(min = 2, max = 16 , message = "First name must be between {min} and {max}")
    private String firstName;

    @Column(name = "user_sname")
    @NotNull
    @Size(min = 2, max = 16, message = "Second name must be between {min} and {max}")
    private String secondName;

    @Column(name = "user_email")
    @NotNull
    @Email
    private String email;


    @Column(name = "user_role")
    private String userRole;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Payment> payments = new HashSet<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Address> addresses = new HashSet<>();

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Order order;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
