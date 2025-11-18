import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class K2559067_Booking {
    private String bookingId;
    private LocalDate bookingDate;
    private LocalDate rentalStartDate;
    private int rentalDays;
    private int totalKm;
    private double depositAmount;
    private String status;
    private boolean isDiscountApplied;
    private K2559067_Customer customer;
    private K2559067_Vehicle car;

    // cached amounts
    private double basePrice;
    private double extraKmCharge;
    private double discountAmount;
    private double taxAmount;
    private double finalAmount;

    public K2559067_Booking(String bookingId, LocalDate bookingDate, LocalDate rentalStartDate, int rentalDays, int totalKm,
                   double depositAmount, String status, boolean isDiscountApplied, K2559067_Customer customer, K2559067_Vehicle car) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.rentalStartDate = rentalStartDate;
        this.rentalDays = rentalDays;
        this.totalKm = totalKm;
        this.depositAmount = depositAmount;
        this.status = status;
        this.isDiscountApplied = isDiscountApplied;
        this.customer = customer;
        this.car = car;
    }

    public double calculateBasePrice() {
        basePrice = car.getPackageDetails().getDailyFee() * rentalDays;
        return basePrice;
    }

    public double calculateExtraKmCharges() {
        int freeKmTotal = car.getPackageDetails().getFreeKmPerDay() * rentalDays;
        int extra = Math.max(0, totalKm - freeKmTotal);
        extraKmCharge = extra * car.getPackageDetails().getExtraKmCharge();
        return extraKmCharge;
    }

    public double calculateDiscount() {
        // 7 or more days - 10% discount on base price
        if (rentalDays >= 7) {
            discountAmount = 0.10 * calculateBasePrice();
            isDiscountApplied = true;
        } else {
            discountAmount = 0;
            isDiscountApplied = false;
        }
        return discountAmount;
    }

    public double calculateTax(double amount) {
        taxAmount = amount * car.getPackageDetails().getTaxRate();
        return taxAmount;
    }

    public double calculateFinalAmount() {
        double base = calculateBasePrice();
        double extra = calculateExtraKmCharges();
        double discount = calculateDiscount();
        double taxable = base - discount + extra;
        double tax = calculateTax(taxable);
        finalAmount = taxable + tax - depositAmount; // deposit deducted (refundable 5000)
        if (finalAmount < 0) finalAmount = 0;
        return finalAmount;
    }

    public int calculateRentalPeriod() {
        return rentalDays;
    }

    public boolean checkCancellationEligibility() {
        
        LocalDate today = LocalDate.now();
        long daysSinceBooking = ChronoUnit.DAYS.between(bookingDate, today);
        return daysSinceBooking <= 2; 
    }

    public void displayDetailedInvoice() {
        System.out.println("\n--- Booking Details ---");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Booking Date: " + bookingDate);
        System.out.println("Rental Start Date: " + rentalStartDate);
        System.out.println("Rental Days: " + rentalDays);
        System.out.println("Customer: " + customer.getName() + " | " + customer.getNicOrPassport());
        System.out.println("Vehicle: " + car.getCarId() + " | " + car.getModel());
        double finalAmt = calculateFinalAmount();
        System.out.printf("Base Price: LKR %.2f\n", basePrice);
        System.out.printf("Extra Km Charge: LKR %.2f\n", extraKmCharge);
        System.out.printf("Discount: LKR %.2f\n", discountAmount);
        System.out.printf("Tax: LKR %.2f\n", taxAmount);
        System.out.printf("Deposit Handled: LKR %.2f\n", depositAmount);
        System.out.printf("Final Amount (payable after deposit): LKR %.2f\n", finalAmt);
    }

    // getters and setters

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }

    public LocalDate getRentalStartDate() { return rentalStartDate; }
    public void setRentalStartDate(LocalDate rentalStartDate) { this.rentalStartDate = rentalStartDate; }

    public int getRentalDays() { return rentalDays; }
    public void setRentalDays(int rentalDays) { this.rentalDays = rentalDays; }

    public int getTotalKm() { return totalKm; }
    public void setTotalKm(int totalKm) { this.totalKm = totalKm; }

    public double getDepositAmount() { return depositAmount; }
    public void setDepositAmount(double depositAmount) { this.depositAmount = depositAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public boolean isDiscountApplied() { return isDiscountApplied; }
    public void setDiscountApplied(boolean discountApplied) { isDiscountApplied = discountApplied; }

    public K2559067_Customer getCustomer() { return customer; }
    public void setCustomer(K2559067_Customer customer) { this.customer = customer; }

    public K2559067_Vehicle getCar() { return car; }
    public void setCar(K2559067_Vehicle car) { this.car = car; }
}
