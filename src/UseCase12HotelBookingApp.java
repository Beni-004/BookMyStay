import java.io.*;
import java.util.*;

class Reservation implements Serializable {
    int bookingId;
    String customerName;
    String roomType;

    Reservation(int bookingId, String customerName, String roomType) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.roomType = roomType;
    }

    public String toString() {
        return "BookingID: " + bookingId + ", Name: " + customerName + ", Room: " + roomType;
    }
}

class SystemState implements Serializable {
    List<Reservation> reservations;
    Map<String, Integer> inventory;

    SystemState(List<Reservation> reservations, Map<String, Integer> inventory) {
        this.reservations = reservations;
        this.inventory = inventory;
    }
}

class PersistenceService {
    private static final String FILE_NAME = "hotel_state.dat";

    void save(SystemState state) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            oos.writeObject(state);
            oos.close();
            System.out.println("State saved successfully");
        } catch (Exception e) {
            System.out.println("Error saving state");
        }
    }

    SystemState load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
            SystemState state = (SystemState) ois.readObject();
            ois.close();
            System.out.println("State loaded successfully");
            return state;
        } catch (Exception e) {
            System.out.println("No previous state found, starting fresh");
            return null;
        }
    }
}

public class UseCase12HotelBookingApp {
    public static void main(String[] args) {
        PersistenceService service = new PersistenceService();

        SystemState loadedState = service.load();

        List<Reservation> reservations;
        Map<String, Integer> inventory;

        if (loadedState != null) {
            reservations = loadedState.reservations;
            inventory = loadedState.inventory;
        } else {
            reservations = new ArrayList<>();
            inventory = new HashMap<>();
            inventory.put("Standard", 2);
            inventory.put("Deluxe", 1);
            inventory.put("Suite", 1);
        }

        reservations.add(new Reservation(101, "Amit", "Deluxe"));
        reservations.add(new Reservation(102, "Neha", "Suite"));

        inventory.put("Deluxe", inventory.get("Deluxe") - 1);
        inventory.put("Suite", inventory.get("Suite") - 1);

        System.out.println("\nCurrent Reservations:");
        for (Reservation r : reservations) {
            System.out.println(r);
        }

        System.out.println("\nCurrent Inventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + ": " + inventory.get(key));
        }

        SystemState newState = new SystemState(reservations, inventory);
        service.save(newState);
    }
}