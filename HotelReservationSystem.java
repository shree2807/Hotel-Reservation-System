import java.util.ArrayList;
import java.util.Scanner;

class Reservation {
    int reservationId;
    String guestName;
    int roomNumber;
    String contactNumber;

    public Reservation(int reservationId, String guestName, int roomNumber, String contactNumber) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId + " | Guest: " + guestName + " | Room: " + roomNumber + " | Contact: " + contactNumber;
    }
}

public class HotelReservationSystem {
    private static final ArrayList<Reservation> reservations = new ArrayList<>();
    private static int nextReservationId = 1;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nHOTEL RESERVATION SYSTEM");
            System.out.println("1. Reserve a Room");
            System.out.println("2. View Reservations");
            System.out.println("3. Update Reservation");
            System.out.println("4. Delete Reservation");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    reserveRoom(scanner);
                    break;
                case 2:
                    viewReservations();
                    break;
                case 3:
                    updateReservation(scanner);
                    break;
                case 4:
                    deleteReservation(scanner);
                    break;
                case 0:
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void reserveRoom(Scanner scanner) {
        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Enter contact number: ");
        String contactNumber = scanner.next();

        reservations.add(new Reservation(nextReservationId++, guestName, roomNumber, contactNumber));
        System.out.println("Room reserved successfully!");
    }

    private static void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }
        System.out.println("\nCurrent Reservations:");
        for (Reservation res : reservations) {
            System.out.println(res);
        }
    }

    private static void updateReservation(Scanner scanner) {
        System.out.print("Enter reservation ID to update: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Reservation res : reservations) {
            if (res.reservationId == reservationId) {
                System.out.print("Enter new guest name: ");
                res.guestName = scanner.nextLine();
                System.out.print("Enter new room number: ");
                res.roomNumber = scanner.nextInt();
                System.out.print("Enter new contact number: ");
                res.contactNumber = scanner.next();
                System.out.println("Reservation updated successfully!");
                return;
            }
        }
        System.out.println("Reservation not found.");
    }

    private static void deleteReservation(Scanner scanner) {
        System.out.print("Enter reservation ID to delete: ");
        int reservationId = scanner.nextInt();

        for (Reservation res : reservations) {
            if (res.reservationId == reservationId) {
                reservations.remove(res);
                System.out.println("Reservation deleted successfully!");
                return;
            }
        }
        System.out.println("Reservation not found.");
    }
}
