import java.util.LinkedList;
import java.util.Queue

public class UseCase5HotelBookingApp {
    static class Reservation {
        private String guestName;
        private String roomType;
        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }
        public String getGuestName() {
            return guestName;
        }
        public String getRoomType() {
            return roomType;
        }
        public void displayReservation() {
            System.out.println("Guest: " + guestName);
            System.out.println("Requested Room: " + roomType);
        }
    }

    static class BookingRequestQueue {
        private Queue<Reservation> requestQueue;
        public BookingRequestQueue() {
            requestQueue = new LinkedList<>();
        }
        public void addRequest(Reservation reservation) {
            requestQueue.offer(reservation);
            System.out.println(
                    "Booking request added for "
                            + reservation.getGuestName()
                            + " (" + reservation.getRoomType() + ")"
            );
        }
        public Reservation peekNextRequest() {
            return requestQueue.peek();
        }
        public Reservation processNextRequest() {
            return requestQueue.poll();
        }
        public void displayQueue() {
            System.out.println("\nCurrent Booking Queue:\n");
            for (Reservation reservation : requestQueue) {
                reservation.displayReservation();
                System.out.println();
            }
        }
    }
    public static void main(String[] args) {
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Double Room");
        Reservation r3 = new Reservation("Charlie", "Suite Room");
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);
        bookingQueue.displayQueue();
        System.out.println("Next request to process:");
        Reservation next = bookingQueue.peekNextRequest();
        if (next != null) {
            next.displayReservation();
        }
        System.out.println("\nRequests are waiting for allocation.");
    }
}