import java.util.*;

class BookingRequest {
    String guestName;
    String roomType;

    BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

public class UseCase6HotelBookingApp {
    public static void main(String[] args) {
        Queue<BookingRequest> requestQueue = new LinkedList<>();

        requestQueue.add(new BookingRequest("Aashmit", "Deluxe"));
        requestQueue.add(new BookingRequest("Ravi", "Standard"));
        requestQueue.add(new BookingRequest("Priya", "Deluxe"));
        requestQueue.add(new BookingRequest("Kiran", "Suite"));

        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Deluxe", 2);
        inventory.put("Standard", 1);
        inventory.put("Suite", 1);

        Set<String> allocatedRoomIds = new HashSet<>();
        Map<String, Set<String>> roomAllocations = new HashMap<>();

        int roomCounter = 101;

        while (!requestQueue.isEmpty()) {
            BookingRequest request = requestQueue.poll();

            if (inventory.containsKey(request.roomType) && inventory.get(request.roomType) > 0) {
                String roomId;

                do {
                    roomId = request.roomType.substring(0, 1).toUpperCase() + roomCounter++;
                } while (allocatedRoomIds.contains(roomId));

                allocatedRoomIds.add(roomId);

                roomAllocations.putIfAbsent(request.roomType, new HashSet<>());
                roomAllocations.get(request.roomType).add(roomId);

                inventory.put(request.roomType, inventory.get(request.roomType) - 1);

                System.out.println("Booking Confirmed for " + request.guestName + " | Room Type: " + request.roomType + " | Room ID: " + roomId);
            } else {
                System.out.println("Booking Failed for " + request.guestName + " | Room Type: " + request.roomType + " | No Rooms Available");
            }
        }

        System.out.println("\nFinal Allocations:");
        for (String type : roomAllocations.keySet()) {
            System.out.println(type + " -> " + roomAllocations.get(type));
        }
    }
}