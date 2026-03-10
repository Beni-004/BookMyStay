public class UseCase3HotelBookingApp {
    public static void main(String[] args) {
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();
        RoomInventory inventory = new RoomInventory();
        System.out.println("Hotel Room Information\n");
        single.displayRoomDetails();
        System.out.println("Available: " + inventory.getAvailability(single.getRoomType()));
        System.out.println();
        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + inventory.getAvailability(doubleRoom.getRoomType()));
        System.out.println();
        suite.displayRoomDetails();
        System.out.println("Available: " + inventory.getAvailability(suite.getRoomType()));
        System.out.println("\n--- Inventory Snapshot ---");
        inventory.displayInventory();
        System.out.println("\nBooking a Single Room...");
        inventory.decreaseAvailability("Single Room");
        System.out.println("\nUpdated Inventory:");
        inventory.displayInventory();
    }
}