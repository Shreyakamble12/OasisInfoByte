
import java.util.*;

class User {
    String username;
    String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

class Ticket {
    int pnr;
    String passengerName;
    String trainNumber;
    String trainName;
    String classType;
    String from;
    String to;
    String dateOfJourney;

    Ticket(int pnr, String passengerName, String trainNumber, String trainName, String classType, String from, String to, String dateOfJourney) {
        this.pnr = pnr;
        this.passengerName = passengerName;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.from = from;
        this.to = to;
        this.dateOfJourney = dateOfJourney;
    }
}

public class OnlineReservationSystem {

    private static Map<String, User> users = new HashMap<>();
    private static Map<Integer, Ticket> tickets = new HashMap<>();
    private static int nextPnr = 1000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Add a default user for demonstration
        users.put("admin", new User("admin", "password"));

        System.out.println("Welcome to the Online Reservation System");

        while (true) {
            System.out.println("\n1. Login\n2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                if (login(scanner)) {
                    mainMenu(scanner);
                } else {
                    System.out.println("Invalid login credentials.");
                }
            } else if (choice == 2) {
                System.out.println("Thank you for using the Online Reservation System.");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    private static boolean login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        return user != null && user.password.equals(password);
    }

    private static void mainMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n1. Make a Reservation\n2. Cancel a Reservation\n3. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                makeReservation(scanner);
            } else if (choice == 2) {
                cancelReservation(scanner);
            } else if (choice == 3) {
                System.out.println("Logged out successfully.");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.println("\n--- Reservation Form ---");

        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine();

        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();

        System.out.print("Enter train name: ");
        String trainName = scanner.nextLine();

        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();

        System.out.print("Enter from (place): ");
        String from = scanner.nextLine();

        System.out.print("Enter to (destination): ");
        String to = scanner.nextLine();

        System.out.print("Enter date of journey (DD/MM/YYYY): ");
        String dateOfJourney = scanner.nextLine();

        int pnr = nextPnr++;
        Ticket ticket = new Ticket(pnr, passengerName, trainNumber, trainName, classType, from, to, dateOfJourney);
        tickets.put(pnr, ticket);

        System.out.println("\nReservation successful. Your PNR is " + pnr);
    }

    private static void cancelReservation(Scanner scanner) {
        System.out.println("\n--- Cancellation Form ---");

        System.out.print("Enter PNR number: ");
        int pnr = scanner.nextInt();
        scanner.nextLine();

        Ticket ticket = tickets.get(pnr);
        if (ticket != null) {
            System.out.println("\nTicket Details:");
            System.out.println("PNR: " + ticket.pnr);
            System.out.println("Passenger Name: " + ticket.passengerName);
            System.out.println("Train Number: " + ticket.trainNumber);
            System.out.println("Train Name: " + ticket.trainName);
            System.out.println("Class Type: " + ticket.classType);
            System.out.println("From: " + ticket.from);
            System.out.println("To: " + ticket.to);
            System.out.println("Date of Journey: " + ticket.dateOfJourney);

            System.out.print("Do you want to confirm cancellation? (yes/no): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("yes")) {
                tickets.remove(pnr);
                System.out.println("\nTicket cancelled successfully.");
            } else {
                System.out.println("\nCancellation aborted.");
            }
        } else {
            System.out.println("\nInvalid PNR number.");
        }
    }
}
