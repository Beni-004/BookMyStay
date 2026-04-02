import java.util.*;

class Service {
    String name;
    double cost;

    Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

public class UseCase7HotelBookingApp {
    public static void main(String[] args) {
        Map<String, List<Service>> reservationServices = new HashMap<>();

        String res1 = "RES101";
        String res2 = "RES102";

        List<Service> services1 = new ArrayList<>();
        services1.add(new Service("Breakfast", 200));
        services1.add(new Service("WiFi", 100));

        List<Service> services2 = new ArrayList<>();
        services2.add(new Service("Spa", 500));
        services2.add(new Service("Airport Pickup", 300));

        reservationServices.put(res1, services1);
        reservationServices.put(res2, services2);

        for (String resId : reservationServices.keySet()) {
            List<Service> services = reservationServices.get(resId);
            double total = 0;

            System.out.println("Reservation ID: " + resId);
            for (Service s : services) {
                System.out.println("Service: " + s.name + " | Cost: " + s.cost);
                total += s.cost;
            }
            System.out.println("Total Add-On Cost: " + total);
            System.out.println();
        }
    }
}