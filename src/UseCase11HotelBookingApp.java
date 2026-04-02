import java.util.*;

class BookingRequest {
    int requestId;
    String customerName;
    String roomType;

    BookingRequest(int requestId, String customerName, String roomType) {
        this.requestId = requestId;
        this.customerName = customerName;
        this.roomType = roomType;
    }
}

class RoomInventory {
    private Map<String, Integer> rooms = new HashMap<>();

    RoomInventory() {
        rooms.put("Standard", 2);
        rooms.put("Deluxe", 1);
        rooms.put("Suite", 1);
    }

    public synchronized boolean allocateRoom(String type) {
        if (!rooms.containsKey(type) || rooms.get(type) <= 0) {
            return false;
        }
        int available = rooms.get(type);
        rooms.put(type, available - 1);
        return true;
    }

    void displayInventory() {
        System.out.println("\nFinal Inventory:");
        for (String key : rooms.keySet()) {
            System.out.println(key + ": " + rooms.get(key));
        }
    }
}

class BookingProcessor implements Runnable {
    private Queue<BookingRequest> queue;
    private RoomInventory inventory;

    BookingProcessor(Queue<BookingRequest> queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {
        while (true) {
            BookingRequest request;

            synchronized (queue) {
                if (queue.isEmpty()) {
                    break;
                }
                request = queue.poll();
            }

            boolean success = inventory.allocateRoom(request.roomType);

            if (success) {
                System.out.println(Thread.currentThread().getName() + " processed booking for " + request.customerName + " (" + request.roomType + ")");
            } else {
                System.out.println(Thread.currentThread().getName() + " failed booking for " + request.customerName + " (" + request.roomType + ")");
            }
        }
    }
}

public class UseCase11HotelBookingApp {
    public static void main(String[] args) {
        Queue<BookingRequest> queue = new LinkedList<>();
        RoomInventory inventory = new RoomInventory();

        queue.add(new BookingRequest(1, "Amit", "Deluxe"));
        queue.add(new BookingRequest(2, "Neha", "Suite"));
        queue.add(new BookingRequest(3, "Rahul", "Suite"));
        queue.add(new BookingRequest(4, "Sneha", "Standard"));
        queue.add(new BookingRequest(5, "Karan", "Standard"));
        queue.add(new BookingRequest(6, "Pooja", "Standard"));

        Thread t1 = new Thread(new BookingProcessor(queue, inventory), "Thread-1");
        Thread t2 = new Thread(new BookingProcessor(queue, inventory), "Thread-2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        inventory.displayInventory();
    }
}