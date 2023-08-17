package main.java.keiko;

public class Game {
    private int boardSize;
    private int[][] board;

    private int currentPlayer;

    public Game(int boardSize) {
        this.boardSize = boardSize;
        this.board = new int[boardSize][boardSize];
        this.currentPlayer = 1;
    }
    /**
     * Makes a move on the game board.
     *
     * @param playerId The ID of the player making the move.
     * @param x        The x-coordinate of the move.
     * @param y        The y-coordinate of the move.
     * @return True if the move results in a win, false otherwise.
     * @throws InvalidMoveException If the move is invalid.
     */
    public boolean move(int playerId, int x, int y) throws InvalidMoveException {
        if (x < 0 || x >= boardSize || y < 0 || y >= boardSize || board[x][y] != 0) {
            throw new InvalidMoveException("Invalid move");
        }

        board[x][y] = playerId;

        if (isWin(playerId, x, y)) {
            return true; // Player wins
        }

        currentPlayer = (currentPlayer == 1) ? 2 : 1; // Toggle players
        return false;
    }

    /**
     * Checks if the specified player has won based on their move.
     *
     * @param playerId The ID of the player to check for a win.
     * @param x        The x-coordinate of the player's move.
     * @param y        The y-coordinate of the player's move.
     * @return True if the specified player has won, false otherwise.
     */
    private boolean isWin(int playerId, int x, int y) {
        // Check row
        boolean win = true;
        for (int i = 0; i < boardSize; i++) {
            if (board[x][i] != playerId) {
                win = false;
                break;
            }
        }
        if (win) {
            return true;
        }

        // Check column
        win = true;
        for (int i = 0; i < boardSize; i++) {
            if (board[i][y] != playerId) {
                win = false;
                break;
            }
        }
        if (win) {
            return true;
        }

        // Check diagonals, starting from the top left or from top right
        if (x == y || x + y == boardSize - 1) {
            win = true;
            for (int i = 0; i < boardSize; i++) {
                if (board[i][x == y ? i : boardSize - 1 - i] != playerId) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return true;
            }
        }

        return false; // No win
    }

    public int[][] getBoard() {
        return board;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }


    /**
     * Checks if the game board is full.
     * @return True if the board is full, false otherwise.
     */
    public boolean isBoardFull() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
