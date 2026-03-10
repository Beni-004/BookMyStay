import java.util.List;

class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void searchAvailableRooms(List<Room> rooms) {

        System.out.println("Available Rooms:\n");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getRoomType());

            // Validation: show only available rooms
            if (available > 0) {

                room.displayRoomDetails();
                System.out.println("Available: " + available);
                System.out.println();
            }
        }
    }
}