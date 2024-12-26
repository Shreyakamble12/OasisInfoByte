import java.util.*;

class OnlineExamination {
    private static Map<String, String> users = new HashMap<>(); // Stores username and password
    private static Map<String, String> profiles = new HashMap<>(); // Stores username and profile info
    private static Scanner scanner = new Scanner(System.in);
    private static boolean loggedIn = false;
    private static String currentUser;

    public static void main(String[] args) {
        users.put("admin", "password"); // Default user
        profiles.put("admin", "Default Profile Info");

        while (true) {
            System.out.println("\n=== MCQ System ===");
            if (!loggedIn) {
                System.out.println("1. Login\n2. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> login();
                    case 2 -> {
                        System.out.println("Exiting the system. Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            } else {
                System.out.println("1. Update Profile\n2. Change Password\n3. Attempt Quiz\n4. Logout");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> updateProfile();
                    case 2 -> changePassword();
                    case 3 -> attemptQuiz();
                    case 4 -> logout();
                    default -> System.out.println("Invalid option. Try again.");
                }
            }
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            loggedIn = true;
            currentUser = username;
            System.out.println("Login successful! Welcome, " + username + "!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void updateProfile() {
        System.out.println("Current Profile Info: " + profiles.get(currentUser));
        System.out.print("Enter new profile info: ");
        String newProfile = scanner.nextLine();
        profiles.put(currentUser, newProfile);
        System.out.println("Profile updated successfully.");
    }

    private static void changePassword() {
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();

        if (users.get(currentUser).equals(currentPassword)) {
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            users.put(currentUser, newPassword);
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Incorrect current password.");
        }
    }

    private static void attemptQuiz() {
        String[] questions = {
                "What is the capital of France?\n1. Paris\n2. Rome\n3. Berlin\n4. Madrid",
                "What is 2 + 2?\n1. 3\n2. 4\n3. 5\n4. 6",
                "Which planet is known as the Red Planet?\n1. Earth\n2. Mars\n3. Venus\n4. Jupiter"
        };

        int[] answers = {1, 2, 2};
        int score = 0;

        System.out.println("You have 30 seconds to complete the quiz.");
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < questions.length; i++) {
            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
            if (elapsedTime >= 30) {
                System.out.println("Time's up! Auto-submitting your answers.");
                break;
            }

            System.out.println(questions[i]);
            System.out.print("Your answer: ");
            int userAnswer = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (userAnswer == answers[i]) {
                score++;
            }
        }

        System.out.println("Quiz submitted. Your score: " + score + "/" + questions.length);
    }

    private static void logout() {
        loggedIn = false;
        currentUser = null;
        System.out.println("You have been logged out.");
    }
}
