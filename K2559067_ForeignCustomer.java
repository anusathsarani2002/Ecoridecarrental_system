

public class K2559067_ForeignCustomer extends K2559067_Customer {
    private String passportNumber;
    private String country;

    public K2559067_ForeignCustomer(String passportNumber, String country, String name, String contactNumber, String email) {
        super(passportNumber, name, contactNumber, email);
        this.passportNumber = passportNumber;
        this.country = country;
    }

    @Override
    public boolean isEligibleForDiscount() {
        return false;
    }

    public String getPassportNumber() { return passportNumber; }
    public void setPassportNumber(String passportNumber) { this.passportNumber = passportNumber; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
