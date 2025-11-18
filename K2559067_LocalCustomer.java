

public class K2559067_LocalCustomer extends K2559067_Customer {
    private String nic;

    public K2559067_LocalCustomer(String nic, String name, String contactNumber, String email) {
        super(nic, name, contactNumber, email);
        this.nic = nic;
    }

    @Override
    public boolean isEligibleForDiscount() {
        //  eligibility may be determined in booking by rental days
        return false;
    }

    public String getNic() { return nic; }
    public void setNic(String nic) { this.nic = nic; }
}

