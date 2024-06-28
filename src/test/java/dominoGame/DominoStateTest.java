package dominoGame;

import game.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DominoStateTest {
    private DominoState state;

    @BeforeEach
    void setUp() {
        state = new DominoState();
    }

    @Test
    void testInitialState() {
        assertEquals(State.Player.PLAYER_1, state.getNextPlayer());
        assertEquals(State.Status.IN_PROGRESS, state.getStatus());
    }

    @Test
    void testLegalMove() {
        assertTrue(state.isLegalMove("1 1 1 2"));
        state.makeMove("1 1 1 2");
        assertTrue(state.isLegalMove("2 1 3 1"));
    }

    @Test
    void testIllegalMove() {
        state.makeMove("1 1 1 2");
        state.makeMove("2 1 3 1");
        assertFalse(state.isLegalMove("1 1 1 3"));
        assertFalse(state.isLegalMove("1 8 2 8"));
        assertFalse(state.isLegalMove("1 3 1 5"));
        assertFalse(state.isLegalMove("9 1 9 2"));
        assertFalse(state.isLegalMove("0 1 0 2"));
        assertFalse(state.isLegalMove("1 1 1"));
    }

    @Test
    void testGetStatus() {
        assertEquals(State.Status.IN_PROGRESS, state.getStatus());
        state.makeMove("1 1 1 2");
        assertEquals(State.Status.IN_PROGRESS, state.getStatus());
        state.makeMove("2 1 3 1");
        assertEquals(State.Status.IN_PROGRESS, state.getStatus());
    }

    @Test
    void testFullyOccupiedBoard() {
        var state = new DominoState();
        for (int i = 1; i <= 8; i += 2) {
            for (int j = 1; j <= 8; j++) {
                if (j < 8) {
                    state.makeMove(i + " " + j + " " + i + " " + (j + 1)); // Player 1
                    //System.out.println(state);
                    if (j + 2 < 9 || i + 2 < 9) { // Ensure not to make the last move for Player 2
                        state.makeMove((i + 1) + " " + j + " " + (i + 1) + " " + (j + 1)); // Player 2
                        //System.out.println(state);
                    }
                }
            }
        }

        assertTrue(state.isGameOver());
        assertEquals(State.Status.PLAYER_1_WINS, state.getStatus());
    }

    @Test
    void testPlayer1Wins() {
        state.makeMove("1 1 1 2");
        state.makeMove("2 1 3 1");
        state.makeMove("1 3 1 4");
        state.makeMove("2 3 3 3");
        state.makeMove("1 5 1 6");
        state.makeMove("2 5 3 5");
        state.makeMove("1 7 1 8");
        state.makeMove("2 7 3 7");
        state.makeMove("2 2 2 3");
        state.makeMove("3 2 4 2");
        state.makeMove("2 4 2 5");
        state.makeMove("3 4 4 4");
        state.makeMove("2 6 2 7");
        state.makeMove("3 6 4 6");
        state.makeMove("3 8 4 8");
        state.makeMove("4 1 5 1");
        state.makeMove("4 2 4 3");
        state.makeMove("5 3 6 3");
        state.makeMove("4 5 4 6");
        state.makeMove("5 5 6 5");
        state.makeMove("4 7 4 8");
        state.makeMove("5 7 6 7");
        state.makeMove("5 1 5 2");
        state.makeMove("6 1 7 1");
        state.makeMove("5 4 5 5");
        state.makeMove("6 4 7 4");
        state.makeMove("5 6 5 7");
        state.makeMove("6 6 7 6");
        state.makeMove("6 2 6 3");
        state.makeMove("7 2 8 2");
        state.makeMove("6 5 6 6");
        state.makeMove("7 5 8 5");
        state.makeMove("6 8 7 8");
        state.makeMove("7 7 8 7");
        state.makeMove("7 3 8 3");
        state.makeMove("8 1 8 2");
        state.makeMove("7 4 8 4"); // Player 1
        //System.out.println(state);

        assertTrue(state.isGameOver());
        assertEquals(State.Status.PLAYER_1_WINS, state.getStatus());
    }

    @Test
    void testPlayer2Wins() {
        state.makeMove("1 1 1 2");
        state.makeMove("2 2 3 2");
        state.makeMove("4 1 4 2");
        state.makeMove("5 2 6 2");
        state.makeMove("7 2 7 1");
        state.makeMove("8 4 7 4");
        state.makeMove("8 3 8 2");
        state.makeMove("6 3 5 3");
        state.makeMove("5 4 5 5");
        state.makeMove("1 4 2 4");
        state.makeMove("5 6 5 7");
        state.makeMove("3 4 4 4");
        state.makeMove("6 8 6 7");
        state.makeMove("6 5 7 5");
        state.makeMove("1 6 1 7");
        state.makeMove("7 7 8 7");
        state.makeMove("2 6 2 7");
        state.makeMove("7 6 8 6");
        state.makeMove("3 5 3 6");
        state.makeMove("3 7 4 7");
        state.makeMove("4 5 4 6");
        //System.out.println(state);
        state.makeMove("2 8 3 8"); // Player 2
        //System.out.println(state);

        assertTrue(state.isGameOver());
        assertEquals(State.Status.PLAYER_2_WINS, state.getStatus());
    }

    @Test
    void testGameOver2() {
        var state = new DominoState();
        String[] moves = {
                "1 1 1 2", "2 1 2 2", // Player 1, Player 2
                "1 3 1 4", "2 3 2 4",
                "1 5 1 6", "2 5 2 6",
                "1 7 1 8", "2 7 2 8",
                "3 1 3 2", "4 1 4 2",
                "3 3 3 4", "4 3 4 4",
                "3 5 3 6", "4 5 4 6",
                "3 7 3 8", "4 7 4 8",
                "5 1 5 2", "6 1 6 2",
                "5 3 5 4", "6 3 6 4",
                "5 5 5 6", "6 5 6 6",
                "5 7 5 8", "6 7 6 8",
                "7 1 7 2", "8 1 8 2",
                "7 3 7 4", "8 3 8 4",
                "7 5 7 6", "8 5 8 6",
                "7 7 7 8" // (Player 2 cannot move now)
        };

        for (String move : moves) {
            state.makeMove(move);
            //System.out.println(state);
        }

        assertTrue(state.isGameOver());
        assertEquals(State.Status.PLAYER_1_WINS, state.getStatus());
    }

    @Test
    void testPlayer1Wins2() {
        state.makeMove("1 1 1 2");
        state.makeMove("1 3 2 3");
        state.makeMove("1 4 1 5");
        state.makeMove("2 4 3 4");
        state.makeMove("1 6 1 7");
        state.makeMove("2 1 3 1");
        state.makeMove("2 2 2 3");
        state.makeMove("3 2 4 2");
        state.makeMove("2 5 2 6");
        state.makeMove("3 5 4 5");
        state.makeMove("2 7 2 8");
        state.makeMove("3 6 4 6");
        state.makeMove("3 1 3 2");
        state.makeMove("4 1 5 1");
        state.makeMove("3 3 3 4");
        state.makeMove("4 3 5 3");
        state.makeMove("3 7 3 8");
        state.makeMove("4 7 5 7");
        state.makeMove("4 2 4 3");
        state.makeMove("5 2 6 2");
        state.makeMove("4 4 4 5");
        state.makeMove("5 4 6 4");
        state.makeMove("4 6 4 7");
        state.makeMove("5 6 6 6");
        state.makeMove("4 8 5 8");
        state.makeMove("5 1 6 1");
        state.makeMove("5 5 5 6");
        state.makeMove("6 5 7 5");
        state.makeMove("5 7 5 8");
        state.makeMove("6 7 7 7");
        state.makeMove("6 3 6 4");
        state.makeMove("7 3 8 3");
        state.makeMove("6 6 6 7");
        state.makeMove("7 6 8 6");
        state.makeMove("7 1 7 2");
        state.makeMove("8 1 8 2");
        state.makeMove("7 4 7 5");
        state.makeMove("8 4 8 5");
        state.makeMove("7 8 8 8");

        assertTrue(state.isGameOver());
        assertEquals(State.Status.PLAYER_1_WINS, state.getStatus());
    }

    @Test
    void testPlayer2Wins2() {
        state.makeMove("1 1 1 2");
        state.makeMove("2 1 3 1");
        state.makeMove("1 3 1 4");
        state.makeMove("2 2 3 2");
        state.makeMove("1 5 1 6");
        state.makeMove("2 3 3 3");
        state.makeMove("1 7 1 8");
        state.makeMove("2 4 3 4");
        state.makeMove("2 5 3 5");
        state.makeMove("4 1 5 1");
        state.makeMove("2 6 3 6");
        state.makeMove("4 2 5 2");
        state.makeMove("2 7 3 7");
        state.makeMove("4 3 5 3");
        state.makeMove("2 8 3 8");
        state.makeMove("4 4 5 4");
        state.makeMove("4 5 5 5");
        state.makeMove("6 1 7 1");
        state.makeMove("4 6 5 6");
        state.makeMove("6 2 7 2");
        state.makeMove("4 7 5 7");
        state.makeMove("6 3 7 3");
        state.makeMove("4 8 5 8");
        state.makeMove("6 4 7 4");
        state.makeMove("6 5 7 5");
        state.makeMove("8 1 8 2");
        state.makeMove("6 6 7 6");
        state.makeMove("8 3 8 4");
        state.makeMove("6 7 7 7");
        state.makeMove("8 5 8 6");
        state.makeMove("6 8 7 8");
        state.makeMove("8 7 8 8");

        assertTrue(state.isGameOver());
        assertEquals(State.Status.PLAYER_2_WINS, state.getStatus());
    }

    @Test
    void testPlayer2Wins3() {
        state.makeMove("1 1 1 2");
        state.makeMove("1 3 2 3");
        state.makeMove("1 4 1 5");
        state.makeMove("2 4 3 4");
        state.makeMove("1 6 1 7");
        state.makeMove("2 1 3 1");
        state.makeMove("2 2 2 3");
        state.makeMove("3 2 4 2");
        state.makeMove("2 5 2 6");
        state.makeMove("3 5 4 5");
        state.makeMove("2 7 2 8");
        state.makeMove("3 6 4 6");
        state.makeMove("3 1 3 2");
        state.makeMove("4 1 5 1");
        state.makeMove("3 3 3 4");
        state.makeMove("4 3 5 3");
        state.makeMove("3 7 3 8");
        state.makeMove("4 7 5 7");
        state.makeMove("4 2 4 3");
        state.makeMove("5 2 6 2");
        state.makeMove("4 4 4 5");
        state.makeMove("5 4 6 4");
        state.makeMove("4 6 4 7");
        state.makeMove("5 6 6 6");
        state.makeMove("4 8 5 8");
        state.makeMove("5 1 6 1");
        state.makeMove("5 5 5 6");
        state.makeMove("6 5 7 5");
        state.makeMove("5 7 5 8");
        state.makeMove("6 7 7 7");
        state.makeMove("6 3 6 4");
        state.makeMove("7 3 8 3");
        state.makeMove("6 6 6 7");
        state.makeMove("7 6 8 6");
        state.makeMove("7 1 7 2");
        state.makeMove("8 1 8 2");
        state.makeMove("7 4 7 5");
        state.makeMove("8 4 8 5");
        state.makeMove("7 8 8 8");
        state.makeMove("7 7 8 7");

        assertTrue(state.isGameOver());
        assertEquals(State.Status.PLAYER_2_WINS, state.getStatus());
    }
}
