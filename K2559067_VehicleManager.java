import java.util.ArrayList;
import java.util.List;

public class K2559067_VehicleManager {
    private List<K2559067_Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(K2559067_Vehicle v) { vehicles.add(v); }
    public void updateVehicle(K2559067_Vehicle updated) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getCarId().equalsIgnoreCase(updated.getCarId())) {
                vehicles.set(i, updated);
                return;
            }
        }
    }
    public void removeVehicle(String id) { vehicles.removeIf(v -> v.getCarId().equalsIgnoreCase(id)); }

    public List<K2559067_Vehicle> browseAvailableCars() {
        List<K2559067_Vehicle> available = new ArrayList<>();
        for (K2559067_Vehicle v : vehicles) if ("Available".equalsIgnoreCase(v.getAvailabilityStatus())) available.add(v);
        return available;
    }

    public void changeStatus(String id, String status) {
        K2559067_Vehicle v = findVehicle(id);
        if (v != null) v.changeStatus(status);
    }

    public List<K2559067_Vehicle> getVehicles() { return vehicles; }
    public void setVehicles(List<K2559067_Vehicle> vehicles) { this.vehicles = vehicles; }

    public K2559067_Vehicle findVehicle(String id) {
        for (K2559067_Vehicle v : vehicles) if (v.getCarId().equalsIgnoreCase(id)) return v;
        return null;
    }
}
