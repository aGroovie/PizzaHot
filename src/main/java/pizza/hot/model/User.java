package pizza.hot.model;

import pizza.hot.enums.UserRole;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", unique = true)
    @NotNull
    @Size(min=5,max=10, message = "Username must be between {min} and {max}")
    private String username;

    @Column(name = "user_pw")
    @NotNull
    @Size(min=6,max=16, message ="Password must be between {min} and {max}" )
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
@Enumerated(EnumType.STRING)
    private UserRole userRole;


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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
