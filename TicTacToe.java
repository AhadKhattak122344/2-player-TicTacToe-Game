
//Assignment:  Project1: Tic-Tac-Toe 🔴

//TicTacToe

//Name: Ahad Khattak

//File Created on June 23, 2024

import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
    private static final char EMPTY = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static char[][] board;
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void initBoard() {
        board = new char[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    public static void printBoard() {
        System.out.println("  1 2 3");
        System.out.println(" -------");
        for (int row = 0; row < 3; row++) {
            System.out.print((row + 1) + "|");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + "|");
            }
            System.out.println("\n -------");
        }
    }

    public static char checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != EMPTY) {
                return board[i][0];
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != EMPTY) {
                return board[0][i];
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != EMPTY) {
            return board[0][0];
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != EMPTY) {
            return board[0][2];
        }
        boolean isFull = true;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == EMPTY) {
                    isFull = false;
                    break;
                }
            }
            if (!isFull) {
                break;
            }
        }
        if (isFull) {
            return 'T';
        }
        return EMPTY;
    }

    public static void yourTurn() {
        int row, col;
        do {
            System.out.print("Your move (row[1-3] column[1-3]): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
        } while (!isValidMove(row, col));
        board[row][col] = PLAYER_X;
    }

    public static void machineTurn() {
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!isValidMove(row, col));
        board[row][col] = PLAYER_O;
        System.out.println("Computer's move: Row " + (row + 1) + ", Column " + (col + 1));
    }

    public static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            System.out.println("Invalid move. Row and column must be between 1 and 3.");
            return false;
        }
        if (board[row][col] != EMPTY) {
            System.out.println("Invalid move. Cell already occupied.");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Tic-Tac-Toe!");
        initBoard();
        printBoard();

        char winner = EMPTY;
        char currentPlayer = PLAYER_X;

        while (winner == EMPTY) {
            if (currentPlayer == PLAYER_X) {
                yourTurn();
                currentPlayer = PLAYER_O;
            } else {
                machineTurn();
                currentPlayer = PLAYER_X;
            }
            printBoard();
            winner = checkWinner();
        }

        if (winner == 'T') {
            System.out.println("It's a tie!");
        } else {
            System.out.println("Player '" + winner + "' wins!");
        }

        scanner.close();
    }
}

