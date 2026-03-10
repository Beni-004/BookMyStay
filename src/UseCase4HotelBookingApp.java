import java.util.Arrays;
import java.util.List;

public class UseCase4HotelBookingApp {
    public static void main(String[] args) {
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();
        List<Room> rooms = Arrays.asList(single, doubleRoom, suite);
        RoomInventory inventory = new RoomInventory();
        RoomSearchService searchService = new RoomSearchService(inventory);
        searchService.searchAvailableRooms(rooms);
        System.out.println("Search completed. No system state was modified.");
    }
}