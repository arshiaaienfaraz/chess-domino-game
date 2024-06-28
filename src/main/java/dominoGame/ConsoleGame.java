package dominoGame;

import game.console.BasicGame;

import java.util.Scanner;

/**
 * Conducts a domino game on the console.
 */
public class ConsoleGame {

    /**
     * The entry point of the application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        var state = new DominoState();
        var game = new BasicGame<>(state, ConsoleGame::parseMove);
        System.out.println("Welcome to the Chess Domino Game!");
        System.out.println("Player 1: Horizontal placement");
        System.out.println("Player 2: Vertical placement");
        System.out.println("Enter moves as 'x1 y1 x2 y2' (without quotes), where x and y range from 1 to 8.");
        game.start();
        System.out.println("Game over! " + state.getStatus());
    }

    private static String parseMove(String input) {
        while (!input.matches("\\d \\d \\d \\d")) {
            System.out.println("Invalid input format. Please enter as 'x1 y1 x2 y2'.");
            input = new Scanner(System.in).nextLine();
        }
        return input.trim();
    }
}
