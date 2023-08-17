package main.java.keiko;

import java.util.Scanner;

public class TicTacToeApp {
    public static void main(String[] args) throws RuntimeException {
        int boardSize = 3;
        Game game = new Game(boardSize);

        System.out.println("Welcome to Tic-Tac-Toe!");
        displayBoard(game.getBoard());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Player " + game.getCurrentPlayer() + ", enter your move (row column):");
            int row = getPlayerMove("Row: ");
            int column = getPlayerMove("Column: ");

            try {
                boolean playerWins = game.move(game.getCurrentPlayer(), row, column);

                displayBoard(game.getBoard());

                if (playerWins) {
                    System.out.println("Player " + game.getCurrentPlayer() + " wins!");
                    break;
                } else if (game.isBoardFull()) {
                    System.out.println("It's a draw!");
                    break;
                }
            } catch (InvalidMoveException e) {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }

    private static int getPlayerMove(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextInt();
    }

    private static void displayBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char mark = '-';
                if (board[i][j] == 1) {
                    mark = 'X';
                } else if (board[i][j] == 2) {
                    mark = 'O';
                }
                System.out.print(" " + mark + " ");
                if (j < board[i].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < board.length - 1) {
                System.out.println("-----------");
            }
        }
    }
}
