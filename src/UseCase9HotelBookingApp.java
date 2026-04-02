import java.util.*;

class InvalidBookingException extends Exception {
    InvalidBookingException(String message) {
        super(message);
    }
}

class RoomInventory {
    private Map<String, Integer> rooms = new HashMap<>();

    RoomInventory() {
        rooms.put("Standard", 2);
        rooms.put("Deluxe", 1);
        rooms.put("Suite", 1);
    }

    void validateAndBook(String roomType) throws InvalidBookingException {
        if (!rooms.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        int available = rooms.get(roomType);

        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for: " + roomType);
        }

        rooms.put(roomType, available - 1);
        System.out.println("Booking successful for room type: " + roomType);
    }

    void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : rooms.keySet()) {
            System.out.println(type + ": " + rooms.get(type));
        }
    }
}

public class UseCase9HotelBookingApp {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();

        String[] bookingRequests = {"Deluxe", "Suite", "Suite", "Premium", "Standard", "Standard", "Standard"};

        for (String request : bookingRequests) {
            try {
                inventory.validateAndBook(request);
            } catch (InvalidBookingException e) {
                System.out.println("Booking failed: " + e.getMessage());
            }
        }

        inventory.displayInventory();
    }
}