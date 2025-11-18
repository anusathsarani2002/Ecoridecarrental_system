
public abstract class K2559067_Customer {
    private String nicOrPassport;
    private String name;
    private String contactNumber;
    private String email;

    public K2559067_Customer(String nicOrPassport, String name, String contactNumber, String email) {
        this.nicOrPassport = nicOrPassport;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public abstract boolean isEligibleForDiscount();

    public String getNicOrPassport() { return nicOrPassport; }
    public void setNicOrPassport(String nicOrPassport) { this.nicOrPassport = nicOrPassport; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
