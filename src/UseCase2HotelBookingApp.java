public class UseCase2HotelBookingApp {
    public static void main(String[] args) {
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();
        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;
        System.out.println("Hotel Room Information\n");
        single.displayRoomDetails();
        System.out.println("Available: " + singleAvailability);
        System.out.println();
        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleAvailability);
        System.out.println();
        suite.displayRoomDetails();
        System.out.println("Available: " + suiteAvailability);
        System.out.println("\nApplication terminated.");
    }
}