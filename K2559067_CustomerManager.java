

import java.util.ArrayList;
import java.util.List;

public class K2559067_CustomerManager {
    private List<K2559067_Customer> customers = new ArrayList<>();

    public void addCustomer(K2559067_Customer c) { customers.add(c); }

    public K2559067_Customer findCustomer(String id) {
        for (K2559067_Customer c : customers) {
            if (c.getNicOrPassport().equalsIgnoreCase(id)) return c;
        }
        return null;
    }

    public List<K2559067_Customer> listCustomers() { return customers; }

    public void updateCustomer(K2559067_Customer updated) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getNicOrPassport().equalsIgnoreCase(updated.getNicOrPassport())) {
                customers.set(i, updated);
                return;
            }
        }
    }

    public boolean removeCustomer(String id) {
        return customers.removeIf(c -> c.getNicOrPassport().equalsIgnoreCase(id));
    }
}
