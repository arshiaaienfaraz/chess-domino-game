package dominoGame;

import game.BasicState;

/**
 * Represents the states of a domino game played on a chessboard.
 */
public class DominoState implements BasicState<String> {

    private static final int SIZE = 8;
    private final boolean[][] board;
    private Player currentPlayer;

    /**
     * Initializes a new game state with an empty board and Player 1 to start.
     */
    public DominoState() {
        this.board = new boolean[SIZE][SIZE];
        this.currentPlayer = Player.PLAYER_1;
    }

    /**
     * Checks if a move is legal.
     *
     * @param move the move to check in the format {@code "x1 y1 x2 y2"}
     * @return {@code true} if the move is legal, {@code false} otherwise
     */
    @Override
    public boolean isLegalMove(String move) {
        String[] parts = move.split(" ");
        if (parts.length != 4) {
            return false;
        }
        try {
            int x1 = Integer.parseInt(parts[0]) - 1;
            int y1 = Integer.parseInt(parts[1]) - 1;
            int x2 = Integer.parseInt(parts[2]) - 1;
            int y2 = Integer.parseInt(parts[3]) - 1;

            if (isOutOfBounds(x1, y1) || isOutOfBounds(x2, y2)) {
                return false;
            }
            if (board[x1][y1] || board[x2][y2]) {
                return false;
            }
            if (!isValidDominoPlacement(x1, y1, x2, y2)) {
                return false;
            }
            return (currentPlayer == Player.PLAYER_1 && x1 == x2)
                    || (currentPlayer == Player.PLAYER_2 && y1 == y2);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= SIZE || y < 0 || y >= SIZE;
    }

    private boolean isValidDominoPlacement(int x1, int y1, int x2, int y2) {
        return (Math.abs(x1 - x2) == 1 && y1 == y2)
                || (x1 == x2 && Math.abs(y1 - y2) == 1);
    }

    /**
     * Makes a move on the board.
     *
     * @param move the move to make in the format {@code "x1 y1 x2 y2"}
     */
    @Override
    public void makeMove(String move) {
        String[] parts = move.split(" ");
        int x1 = Integer.parseInt(parts[0]) - 1;
        int y1 = Integer.parseInt(parts[1]) - 1;
        int x2 = Integer.parseInt(parts[2]) - 1;
        int y2 = Integer.parseInt(parts[3]) - 1;

        board[x1][y1] = true;
        board[x2][y2] = true;
        currentPlayer = currentPlayer.opponent();
    }

    /**
     * Gets the next player.
     *
     * @return the next player
     */
    @Override
    public Player getNextPlayer() {
        return currentPlayer;
    }

    /**
     * Checks if the game is over.
     *
     * @return {@code true} if the game is over, {@code false} otherwise
     */
    @Override
    public boolean isGameOver() {
        return !hasLegalMoves(currentPlayer)
                && !hasLegalMoves(currentPlayer.opponent());
    }

    private boolean hasLegalMoves(Player player) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!board[i][j]) {
                    if (player == Player.PLAYER_1) {
                        if ((i + 1 < SIZE
                                && !board[i + 1][j]
                                && isLegalMove((i + 1) + " " + (j + 1) + " " + (i + 2) + " " + (j + 1)))
                                || (i - 1 >= 0
                                && !board[i - 1][j]
                                && isLegalMove((i + 1) + " " + (j + 1) + " " + i + " " + (j + 1)))) {
                            return true;
                        }
                    } else {
                        if ((j + 1 < SIZE
                                && !board[i][j + 1]
                                && isLegalMove((i + 1) + " " + (j + 1) + " " + (i + 1) + " " + (j + 2)))
                                || (j - 1 >= 0
                                && !board[i][j - 1]
                                && isLegalMove((i + 1) + " " + (j + 1) + " " + (i + 1) + " " + j))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Gets the current status of the game.
     *
     * @return the current status
     */
    @Override
    public Status getStatus() {
        if (!isGameOver()) {
            return Status.IN_PROGRESS;
        }
        return currentPlayer.opponent() ==
                Player.PLAYER_1 ? Status.PLAYER_1_WINS : Status.PLAYER_2_WINS;
    }

    /**
     * Returns a string representation of the board.
     *
     * @return the board as a string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("  1 2 3 4 5 6 7 8\n");
        sb.append(" +----------------\n");
        for (int i = 0; i < SIZE; i++) {
            sb.append(i + 1).append("|");
            for (int j = 0; j < SIZE; j++) {
                sb.append(board[i][j] ? "X " : ". ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
