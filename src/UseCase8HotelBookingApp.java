import java.util.*;

class Reservation {
    int bookingId;
    String customerName;
    String roomType;
    int nights;
    double pricePerNight;

    Reservation(int bookingId, String customerName, String roomType, int nights, double pricePerNight) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.roomType = roomType;
        this.nights = nights;
        this.pricePerNight = pricePerNight;
    }

    double getTotalCost() {
        return nights * pricePerNight;
    }

    public String toString() {
        return "BookingID: " + bookingId + ", Name: " + customerName + ", Room: " + roomType + ", Nights: " + nights + ", Total: " + getTotalCost();
    }
}

class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    void addReservation(Reservation r) {
        history.add(r);
    }

    List<Reservation> getAllReservations() {
        return history;
    }
}

class BookingReportService {
    void generateReport(List<Reservation> reservations) {
        int totalBookings = reservations.size();
        double totalRevenue = 0;

        for (Reservation r : reservations) {
            totalRevenue += r.getTotalCost();
        }

        System.out.println("\n--- Booking Summary Report ---");
        System.out.println("Total Bookings: " + totalBookings);
        System.out.println("Total Revenue: " + totalRevenue);
    }
}

public class UseCase8HotelBookingApp {
    public static void main(String[] args) {
        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        Reservation r1 = new Reservation(101, "Amit", "Deluxe", 2, 2000);
        Reservation r2 = new Reservation(102, "Neha", "Suite", 3, 3500);
        Reservation r3 = new Reservation(103, "Rahul", "Standard", 1, 1500);

        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        System.out.println("--- Booking History ---");
        for (Reservation r : history.getAllReservations()) {
            System.out.println(r);
        }

        reportService.generateReport(history.getAllReservations());
    }
}