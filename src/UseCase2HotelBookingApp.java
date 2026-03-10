public class UseCase2HotelBookingApp {
    static abstract class Room {
        private int beds;
        private int size;
        private double price;
        public Room(int beds, int size, double price) {
            this.beds = beds;
            this.size = size;
            this.price = price;
        }
        public int getBeds() {
            return beds;
        }
        public int getSize() {
            return size;
        }
        public double getPrice() {
            return price;
        }
        public abstract String getRoomType();
        public void displayRoomDetails() {
            System.out.println("Room Type: " + getRoomType());
            System.out.println("Beds: " + beds);
            System.out.println("Size: " + size + " sq ft");
            System.out.println("Price: $" + price);
        }
    }
    static class SingleRoom extends Room {
        public SingleRoom() {
            super(1, 200, 100);
        }
        @Override
        public String getRoomType() {
            return "Single Room";
        }
    }
    static class DoubleRoom extends Room {
        public DoubleRoom() {
            super(2, 400, 200);
        }
        @Override
        public String getRoomType(){
            return "Double room";
        }
    }
    static class SuiteRoom extends Room {
        public SuiteRoom(){
            super(3, 750, 5000);
        }
        @Override
        public String getRoomType(){
            return "Suite room";
        }
    }

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