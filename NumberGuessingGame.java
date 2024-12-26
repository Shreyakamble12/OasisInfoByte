//Task 2
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0;
        boolean playAgain = true;

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1; 
            int maxAttempts = 7; 
            int attempts = 0;

            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("I've chosen a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess Number: ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess == numberToGuess) {
                    System.out.println("Congrats! You guessed the number in " + attempts + " attempts.");
                    score++;
                    break;
                } else if (guess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (attempts == maxAttempts) {
                System.out.println("Sorry, you ran out of attempts. The number was " + numberToGuess);
            }

            System.out.println("Your current score: " + score);
            System.out.print("Do you want to play again? (y/n): ");
            String playAgainInput = scanner.next();
            playAgain = playAgainInput.equalsIgnoreCase("y");
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}