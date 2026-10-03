package marketplace;

public abstract class User {

    private String userId;
    private String name;
    private String email;
    private String password;
    private String phone;

    public User(String userId, String name, String email,
                String password, String phone) {

        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    // Getters

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Used later to identify the type of account

    public abstract String getUserType();

    @Override
    public String toString() {

        return "User ID: " + userId +
               "\nName: " + name +
               "\nEmail: " + email +
               "\nPhone: " + phone +
               "\nAccount Type: " + getUserType();
    }
}