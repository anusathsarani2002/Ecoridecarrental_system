

public class K2559067_Package {
    private String categoryName;
    private double dailyFee;
    private int freeKmPerDay;
    private double extraKmCharge;
    private double taxRate; // fractional ( 0.10 for 10% ) 

    public K2559067_Package(String categoryName, double dailyFee, int freeKmPerDay, double extraKmCharge, double taxRate) {
        this.categoryName = categoryName;
        this.dailyFee = dailyFee;
        this.freeKmPerDay = freeKmPerDay;
        this.extraKmCharge = extraKmCharge;
        this.taxRate = taxRate;
    }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public double getDailyFee() { return dailyFee; }
    public void setDailyFee(double dailyFee) { this.dailyFee = dailyFee; }
    public int getFreeKmPerDay() { return freeKmPerDay; }
    public void setFreeKmPerDay(int freeKmPerDay) { this.freeKmPerDay = freeKmPerDay; }
    public double getExtraKmCharge() { return extraKmCharge; }
    public void setExtraKmCharge(double extraKmCharge) { this.extraKmCharge = extraKmCharge; }
    public double getTaxRate() { return taxRate; }
    public void setTaxRate(double taxRate) { this.taxRate = taxRate; }
}
