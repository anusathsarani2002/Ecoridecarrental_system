

public class K2559067_Vehicle {
    private String carId;
    private String model;
    private String category;
    private double dailyPrice;
    private String availabilityStatus;
    private K2559067_Package packageDetails;

    public K2559067_Vehicle(String carId, String model, String category, double dailyPrice, String availabilityStatus, K2559067_Package packageDetails) {
        this.carId = carId;
        this.model = model;
        this.category = category;
        this.dailyPrice = dailyPrice;
        this.availabilityStatus = availabilityStatus;
        this.packageDetails = packageDetails;
    }

    public void displayShort() {
        System.out.println(carId + " | " + model + " | " + category + " | LKR " + dailyPrice + " | " + availabilityStatus);
    }

    public void displayFull() {
        displayShort();
        System.out.println("Package: " + packageDetails.getCategoryName() + " FreeKm/day: " + packageDetails.getFreeKmPerDay()
                + " Extra/km: " + packageDetails.getExtraKmCharge() + " Tax: " + (packageDetails.getTaxRate() * 100) + "%");
    }

    public void changeStatus(String status) { this.availabilityStatus = status; }

    public K2559067_Package getPackageDetails() { return packageDetails; }
    public void setPackageDetails(K2559067_Package packageDetails) { this.packageDetails = packageDetails; }

    public String getCarId() { return carId; }
    public void setCarId(String carId) { this.carId = carId; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getDailyPrice() { return dailyPrice; }
    public void setDailyPrice(double dailyPrice) { this.dailyPrice = dailyPrice; }
    public String getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(String availabilityStatus) { this.availabilityStatus = availabilityStatus; }
}
