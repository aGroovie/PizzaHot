package pizza.hot.enums;

public enum UserRole {

    ADMIN_ROLE("ADMIN_ROLE"),
    CUSTOMER_ROLE("CUSTOMER_ROLE");

    private final String role;

    public String getRole() {
        return role;
    }

    UserRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "role='" + role + '\'' +
                '}';
    }
}
