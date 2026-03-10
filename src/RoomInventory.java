import java.util.HashMap;
import java.util.Map;
class RoomInventory {
    private HashMap<String, Integer> availability;
    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single Room", 5);
        availability.put("Double Room", 3);
        availability.put("Suite Room", 2);
    }
    public int getAvailability(String roomType) {
        return availability.getOrDefault(roomType, 0);
    }
    public void updateAvailability(String roomType, int newCount) {
        availability.put(roomType, newCount);
    }
    public void decreaseAvailability(String roomType) {
        int current = getAvailability(roomType);
        if (current > 0) {
            availability.put(roomType, current - 1);
        } else {
            System.out.println(roomType + " is fully booked.");
        }
    }
    public void displayInventory() {
        System.out.println("Current Room Inventory:");
        for (Map.Entry<String, Integer> entry : availability.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}