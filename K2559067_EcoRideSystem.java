import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;



public class K2559067_EcoRideSystem {
    private K2559067_CustomerManager K2559067_customerManager = new K2559067_CustomerManager();
    private K2559067_VehicleManager K2559067_vehicleManager = new K2559067_VehicleManager();
    private K2559067_BookingManager K2559067_bookingManager = new K2559067_BookingManager();
    private Scanner scanner = new Scanner(System.in);
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    String booking_Id = "B" + (int)(Math.random() * 100000);  

    public static void main(String[] args) {
        new K2559067_EcoRideSystem().startSystem();
    }

    public void startSystem() {
        seedSampleData(); // optional demo data
        boolean running = true;
        while (running) {
            showMainMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> handleCustomerRegistration();
                case "2" -> handleVehicleManagement();
                case "3" -> handleCarBrowsing();
                case "4" -> handleNewBooking();
                case "5" -> handleBookingSearch();
                case "6" -> handleViewBookingsByDate();
                case "7" -> handleInvoiceGeneration();
                case "8" -> handleCancelBooking();
                case "9" -> listAllCustomers();
                case "10" -> listAllVehicles();
                case "0" -> {
                    quitSystem();
                    running = false;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    public void showMainMenu() {
        System.out.println("\n=== EcoRide Car Rental System ===");
        System.out.println("1. Register Customer");
        System.out.println("2. Add / Update / Remove Vehicle");
        System.out.println("3. Browse Available Cars");
        System.out.println("4. Make New Booking");
        System.out.println("5. Search Booking by ID");
        System.out.println("6. View Bookings by Rental Date");
        System.out.println("7. Generate Invoice for Booking");
        System.out.println("8. Cancel Booking");
        System.out.println("9. List All Customers");
        System.out.println("10. List All Vehicles");
        System.out.println("0. Quit");
        System.out.print("Select an option: ");
    }

    private void handleCustomerRegistration() {
        System.out.println("\n--- Register Customer ---");
        System.out.print("Is customer local or foreign? (L/F): ");
        String type = scanner.nextLine().trim().toUpperCase();
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Contact Number: ");
        String contact = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        if ("L".equals(type)) {
            System.out.print("NIC: ");
            String nic = scanner.nextLine().trim();
            K2559067_LocalCustomer c = new K2559067_LocalCustomer(nic, name, contact, email);
            K2559067_customerManager.addCustomer(c);
            System.out.println("Local customer registered with NIC: " + nic);
        } else {
            System.out.print("Passport Number: ");
            String passport = scanner.nextLine().trim();
            System.out.print("Country: ");
            String country = scanner.nextLine().trim();
            K2559067_ForeignCustomer c = new K2559067_ForeignCustomer(passport, country, name, contact, email);
            K2559067_customerManager.addCustomer(c);
            System.out.println("Foreign customer registered with Passport: " + passport);
        }
    }

    private void handleVehicleManagement() {
        System.out.println("\n--- Vehicle Management ---");
        System.out.println("1. Add Vehicle");
        System.out.println("2. Update Vehicle");
        System.out.println("3. Remove Vehicle");
        System.out.print("Choice: ");
        String c = scanner.nextLine().trim();
        switch (c) {
            case "1" -> addVehicle();
            case "2" -> updateVehicle();
            case "3" -> removeVehicle();
            default -> System.out.println("Invalid choice.");
        }
    }

    private void addVehicle() {
        System.out.print("Car ID (or leave blank to auto-generate): ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) id = booking_Id.toString();
        System.out.print("Model: ");
        String model = scanner.nextLine().trim();
        System.out.print("Category (Compact/Hybrid/Electric/Luxury): ");
        String category = scanner.nextLine().trim();
        System.out.print("Daily Price: ");
        double daily = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Availability (Available/Reserved/Under Maintenance): ");
        String status = scanner.nextLine().trim();

        // package details
        System.out.print("Package Daily Fee: ");
        double pkgDaily = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Free Km per Day: ");
        int freeKm = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Extra Km Charge (per km): ");
        double extraCharge = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Tax Rate (as percentage, e.g., 10 for 10%): ");
        double tax = Double.parseDouble(scanner.nextLine().trim());

        K2559067_Package pkg = new K2559067_Package(category, pkgDaily, freeKm, extraCharge, tax / 100.0);
        K2559067_Vehicle v = new K2559067_Vehicle(id, model, category, daily, status, pkg);
        K2559067_vehicleManager.addVehicle(v);
        System.out.println("Vehicle added.");
    }

    private void updateVehicle() {
        System.out.print("Enter Car ID to update: ");
        String id = scanner.nextLine().trim();
        K2559067_Vehicle v = K2559067_vehicleManager.findVehicle(id);
        if (v == null) {
            System.out.println("Vehicle not found.");
            return;
        }
        System.out.print("New Model (enter to skip): ");
        String model = scanner.nextLine().trim();
        if (!model.isEmpty()) v.setModel(model);
        System.out.print("New Availability (enter to skip): ");
        String status = scanner.nextLine().trim();
        if (!status.isEmpty()) v.setAvailabilityStatus(status);
        System.out.println("Vehicle updated.");
    }

    private void removeVehicle() {
        System.out.print("Enter Car ID to remove: ");
        String id = scanner.nextLine().trim();
        K2559067_vehicleManager.removeVehicle(id);
        System.out.println("If existed, vehicle removed.");
    }

    private void handleCarBrowsing() {
        System.out.println("\n--- Available Cars ---");
        List<K2559067_Vehicle> list = K2559067_vehicleManager.browseAvailableCars();
        if (list.isEmpty()) {
            System.out.println("No available cars.");
            return;
        }
        list.forEach(K2559067_Vehicle::displayShort);
    }

    private void handleNewBooking() {
        System.out.println("\n--- New Booking ---");
        System.out.print("Customer NIC/Passport: ");
        String custId = scanner.nextLine().trim();
        K2559067_Customer customer = K2559067_customerManager.findCustomer(custId);
        if (customer == null) {
            System.out.println("Customer not found. Please register first.");
            return;
        }

        System.out.print("Vehicle ID: ");
        String vid = scanner.nextLine().trim();
        K2559067_Vehicle vehicle = K2559067_vehicleManager.findVehicle(vid);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }
        if (!"Available".equalsIgnoreCase(vehicle.getAvailabilityStatus())) {
            System.out.println("Vehicle not available for booking.");
            return;
        }

        System.out.print("Rental start date (yyyy-MM-dd): ");
        LocalDate start = LocalDate.parse(scanner.nextLine().trim(), dtf);
        System.out.print("Number of rental days: ");
        int days = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Expected total km during rental (integer): ");
        int totalKm = Integer.parseInt(scanner.nextLine().trim());

        // business rule: must schedule at least 3 days prior
        LocalDate today = LocalDate.now();
        if (start.isBefore(today.plusDays(3))) {
            System.out.println("Booking must be scheduled at least 3 days in advance.");
            return;
        }

        K2559067_Booking booking = new K2559067_Booking(booking_Id.toString(), today, start, days, totalKm, 5000.0, "Active", false, customer, vehicle);
        booking.calculateFinalAmount(); // pre-calc
        K2559067_bookingManager.addBooking(booking);
        vehicle.changeStatus("Reserved");
        System.out.println("Booking created with ID: " + booking.getBookingId());
    }

    private void handleBookingSearch() {
        System.out.print("Enter Booking ID: ");
        String id = scanner.nextLine().trim();
        K2559067_Booking b = K2559067_bookingManager.searchById(id);
        if (b == null) {
            System.out.println("Booking not found.");
            return;
        }
        b.displayDetailedInvoice(); // prints booking + invoice summary
    }

    private void handleViewBookingsByDate() {
        System.out.print("Enter rental date to view (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine().trim(), dtf);
        List<K2559067_Booking> list = K2559067_bookingManager.viewByRentalDate(date);
        if (list.isEmpty()) {
            System.out.println("No bookings found for that date.");
            return;
        }
        list.forEach(b -> System.out.println(b.getBookingId() + " - " + b.getCustomer().getName() + " - " + b.getCar().getModel()));
    }

    private void handleInvoiceGeneration() {
        System.out.print("Enter Booking ID to generate invoice: ");
        String id = scanner.nextLine().trim();
        K2559067_Booking b = K2559067_bookingManager.searchById(id);
        if (b == null) {
            System.out.println("Booking not found.");
            return;
        }
        K2559067_Invoice inv = new K2559067_Invoice(booking_Id.toString(), b);
        inv.generateInvoice();
        inv.displayDetailedInvoice();
    }

    private void handleCancelBooking() {
        System.out.print("Enter Booking ID to cancel: ");
        String id = scanner.nextLine().trim();
        K2559067_Booking b = K2559067_bookingManager.searchById(id);
        if (b == null) {
            System.out.println("Booking not found.");
            return;
        }
        if (!b.checkCancellationEligibility()) {
            System.out.println("Booking cannot be cancelled (passed cancellation window).");
            return;
        }
        boolean ok = K2559067_bookingManager.cancelBooking(id);
        if (ok) {
            b.getCar().changeStatus("Available");
            System.out.println("Booking cancelled.");
        } else {
            System.out.println("Could not cancel booking.");
        }
    }

    private void listAllCustomers() {
        System.out.println("\n--- Customers ---");
        K2559067_customerManager.listCustomers().forEach(c ->
                System.out.println(c.getNicOrPassport() + " | " + c.getName() + " | " + c.getContactNumber())
        );
    }

    private void listAllVehicles() {
        System.out.println("\n--- Vehicles ---");
        K2559067_vehicleManager.getVehicles().forEach(K2559067_Vehicle::displayShort);
    }

    private void quitSystem() {
        System.out.println("Exiting system. Goodbye!");
    }

    // small demo data for convenience
    private void seedSampleData() {
        // packages as in assignment
        K2559067_Package compact = new K2559067_Package("Compact", 5000, 100, 50, 0.10);
        K2559067_Package hybrid = new K2559067_Package("Hybrid", 7500, 150, 60, 0.12);
        K2559067_Package electric = new K2559067_Package("Electric", 10000, 200, 40, 0.08);
        K2559067_Package luxury = new K2559067_Package("Luxury", 15000, 250, 75, 0.15);

        K2559067_Vehicle v1 = new K2559067_Vehicle("C001", "Toyota Aqua", "Compact Petrol", 5000, "Available", compact);
        K2559067_Vehicle v2 = new K2559067_Vehicle("C002", "Nissan Leaf", "Electric", 10000, "Available", electric);
        K2559067_Vehicle v3 = new K2559067_Vehicle("C003", "BMW X5", "Luxury SUV", 15000, "Available", luxury);
        K2559067_Vehicle v4 = new K2559067_Vehicle("C004", "Prado", "Luxury SUV", 15000, "Available", luxury);

        K2559067_vehicleManager.addVehicle(v1);
        K2559067_vehicleManager.addVehicle(v2);
        K2559067_vehicleManager.addVehicle(v3);
        K2559067_vehicleManager.addVehicle(v4);

        K2559067_LocalCustomer lc = new K2559067_LocalCustomer("987654321V", "Ravishka", "0711234567", "ravi@example.com");
        K2559067_customerManager.addCustomer(lc);
    }
}
