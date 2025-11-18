
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class K2559067_BookingManager {
    private List<K2559067_Booking> bookings = new ArrayList<>();

    public void addBooking(K2559067_Booking b) { bookings.add(b); }
    public K2559067_Booking searchById(String id) {
        for (K2559067_Booking b : bookings) if (b.getBookingId().equalsIgnoreCase(id)) return b;
        return null;
    }

    public List<K2559067_Booking> searchByCustomer(K2559067_Customer c) {
        List<K2559067_Booking> out = new ArrayList<>();
        for (K2559067_Booking b : bookings) if (b.getCustomer().getNicOrPassport().equalsIgnoreCase(c.getNicOrPassport())) out.add(b);
        return out;
    }

    public List<K2559067_Booking> viewByRentalDate(LocalDate date) {
        List<K2559067_Booking> out = new ArrayList<>();
        for (K2559067_Booking b : bookings) if (b.getRentalStartDate().equals(date)) out.add(b);
        return out;
    }

    public boolean cancelBooking(String id) {
        K2559067_Booking b = searchById(id);
        if (b == null) return false;
        return bookings.remove(b);
    }

    public List<K2559067_Booking> getBookings() { return bookings; }
    public void setBookings(List<K2559067_Booking> bookings) { this.bookings = bookings; }
    public void updateBooking(K2559067_Booking updated) {
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getBookingId().equalsIgnoreCase(updated.getBookingId())) {
                bookings.set(i, updated);
                return;
            }
        }
    }
}
