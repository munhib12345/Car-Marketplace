package marketplace;

public class Dealership extends User {

    private String dealershipName;
    private String businessAddress;

    public Dealership(String userId, String name, String email,
                      String password, String phone,
                      String dealershipName, String businessAddress) {

        super(userId, name, email, password, phone);

        this.dealershipName = dealershipName;
        this.businessAddress = businessAddress;
    }

    public String getDealershipName() {
        return dealershipName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setDealershipName(String dealershipName) {
        this.dealershipName = dealershipName;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    @Override
    public String getUserType() {
        return "Dealership";
    }

    @Override
    public String toString() {

        return super.toString() +
               "\nDealership Name: " + dealershipName +
               "\nBusiness Address: " + businessAddress;
    }
}