package marketplace;

public class Customer extends User {

    public Customer(String userId, String name, String email,
                    String password, String phone) {

        super(userId, name, email, password, phone);
    }

    @Override
    public String getUserType() {
        return "Individual Customer";
    }
}