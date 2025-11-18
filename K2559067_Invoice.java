

public class K2559067_Invoice {
    private String invoiceId;
    private double basePrice;
    private double taxAmount;
    private double finalAmount;
    private double extraKmCharge;
    private double discountAmount;
    private double depositHandled;
    private K2559067_Booking booking;

    public K2559067_Invoice(String invoiceId, K2559067_Booking booking) {
        this.invoiceId = invoiceId;
        this.booking = booking;
    }

    public void generateInvoice() {
        basePrice = booking.calculateBasePrice();
        extraKmCharge = booking.calculateExtraKmCharges();
        discountAmount = booking.calculateDiscount();
        taxAmount = booking.calculateTax(basePrice - discountAmount + extraKmCharge);
        depositHandled = booking.getDepositAmount();
        finalAmount = booking.calculateFinalAmount();
    }

    public void displayDetailedInvoice() {
         System.out.println("\n====================================================");
    System.out.println("                     ECO RIDE RENTALS                 ");
    System.out.println("                 ***      INVOICE     ***             ");
    System.out.println("====================================================");

    System.out.printf("%-20s : %s\n", "Invoice ID", invoiceId);
    System.out.printf("%-20s : %s\n", "Booking ID", booking.getBookingId());
    System.out.printf("%-20s : %s (%s)\n", "Car",
            booking.getCar().getModel(),
            booking.getCar().getCarId());

    System.out.println("----------------------------------------------------");
    System.out.printf("%-20s : %s\n", "Customer", booking.getCustomer().getName());
    System.out.printf("%-20s : %s\n", "NIC/Passport", booking.getCustomer().getNicOrPassport());
    System.out.println("----------------------------------------------------");
    System.out.printf("%-20s : %s\n", "Rental Start", booking.getRentalStartDate());
    System.out.printf("%-20s : %d days\n", "Rental Duration", booking.getRentalDays());
    System.out.printf("%-20s : %d km\n", "Total KM Used", booking.getTotalKm());
    System.out.println("----------------------------------------------------");

    System.out.println("                 *** CHARGES SUMMARY ***            ");
    System.out.printf("%-25s LKR %10.2f\n", "Base Price", basePrice);
    System.out.printf("%-25s LKR %10.2f\n", "Extra KM Charge", extraKmCharge);
    System.out.printf("%-25s LKR %10.2f\n", "Discount", discountAmount);
    System.out.printf("%-25s LKR %10.2f\n", "Tax", taxAmount);
    System.out.printf("%-25s LKR %10.2f\n", "Deposit Deducted", depositHandled);

    System.out.println("----------------------------------------------------");
    System.out.printf("%-25s LKR %10.2f\n", "FINAL AMOUNT", finalAmount);
    System.out.println("====================================================");
    System.out.println("           Thank you for choosing EcoRide!          ");
    System.out.println("====================================================\n");
    }

    // getters / setters as required7
}
