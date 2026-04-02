import java.util.*;

class Reservation {
    int bookingId;
    String customerName;
    String roomType;
    String roomId;
    boolean isActive;

    Reservation(int bookingId, String customerName, String roomType, String roomId) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.roomType = roomType;
        this.roomId = roomId;
        this.isActive = true;
    }

    public String toString() {
        return "BookingID: " + bookingId + ", Name: " + customerName + ", RoomType: " + roomType + ", RoomID: " + roomId + ", Status: " + (isActive ? "Active" : "Cancelled");
    }
}

class RoomInventory {
    private Map<String, Integer> rooms = new HashMap<>();

    RoomInventory() {
        rooms.put("Standard", 2);
        rooms.put("Deluxe", 1);
        rooms.put("Suite", 1);
    }

    boolean allocateRoom(String type) {
        if (!rooms.containsKey(type) || rooms.get(type) <= 0) {
            return false;
        }
        rooms.put(type, rooms.get(type) - 1);
        return true;
    }

    void releaseRoom(String type) {
        rooms.put(type, rooms.get(type) + 1);
    }

    void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String key : rooms.keySet()) {
            System.out.println(key + ": " + rooms.get(key));
        }
    }
}

class BookingService {
    private Map<Integer, Reservation> reservations = new HashMap<>();
    private Stack<String> rollbackStack = new Stack<>();
    private RoomInventory inventory;

    BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    void addReservation(Reservation r) {
        reservations.put(r.bookingId, r);
    }

    void cancelBooking(int bookingId) {
        if (!reservations.containsKey(bookingId)) {
            System.out.println("Cancellation failed: Booking does not exist");
            return;
        }

        Reservation r = reservations.get(bookingId);

        if (!r.isActive) {
            System.out.println("Cancellation failed: Booking already cancelled");
            return;
        }

        rollbackStack.push(r.roomId);
        inventory.releaseRoom(r.roomType);
        r.isActive = false;

        System.out.println("Booking cancelled successfully: " + bookingId);
    }

    void displayReservations() {
        System.out.println("\nAll Reservations:");
        for (Reservation r : reservations.values()) {
            System.out.println(r);
        }
    }

    void displayRollbackStack() {
        System.out.println("\nRollback Stack:");
        while (!rollbackStack.isEmpty()) {
            System.out.println(rollbackStack.pop());
        }
    }
}

public class UseCase10HotelBookingApp {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(inventory);

        Reservation r1 = new Reservation(101, "Amit", "Deluxe", "D1");
        Reservation r2 = new Reservation(102, "Neha", "Suite", "S1");

        service.addReservation(r1);
        service.addReservation(r2);

        inventory.allocateRoom("Deluxe");
        inventory.allocateRoom("Suite");

        service.cancelBooking(101);
        service.cancelBooking(101);
        service.cancelBooking(999);

        service.displayReservations();
        inventory.displayInventory();
        service.displayRollbackStack();
    }
}