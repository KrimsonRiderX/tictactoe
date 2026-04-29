import java.util.Random;
import java.util.Scanner;

public class tictactoe {

    static boolean isHumanTurn;
    static char    humanSymbol;
    static char    computerSymbol;
    static char[][] board = new char[3][3];
    static Scanner  sc = new Scanner(System.in);

    public static void main(String[] Args) {
        tossAndAssignSymbols();
        displayTossResults();
        initializeBoard();
        printBoard();

        while (true) {
            if (isHumanTurn) humanMove();
            else             computerMove();

            printBoard();

            char winner = checkWinner();
            if (winner != ' ') {
                System.out.println(winner == humanSymbol ? "You win!" : "Computer wins!");
                break;
            }
            if (isDraw()) { System.out.println("Draw!"); break; }

            isHumanTurn = !isHumanTurn;
        }
    }

    // ── Move handling ────────────────────────────────

    static void humanMove() {
        int slot;
        while (true) {
            slot = getUserSlot();
            if (slot < 1 || slot > 9)
                System.out.println("Enter 1-9.");
            else if (!isValidMove(slot))
                System.out.println("Slot taken.");
            else break;
        }
        makeMove(slot, humanSymbol);
    }

    static void computerMove() {
        int move = findBestMove(computerSymbol);   // win
        if (move == -1) move = findBestMove(humanSymbol);  // block
        if (move == -1 && isValidMove(5)) move = 5;          // centre
        if (move == -1) {
            int[] corners = {1,3,7,9};
            for (int c : corners)
                if (isValidMove(c)) { move = c; break; }
        }
        if (move == -1)
            for (int i = 1; i <= 9; i++)
                if (isValidMove(i)) { move = i; break; }
        makeMove(move, computerSymbol);
    }

    static int findBestMove(char symbol) {
        for (int i = 1; i <= 9; i++) {
            if (isValidMove(i)) {
                makeMove(i, symbol);
                boolean wins = checkWinner() == symbol;
                undoMove(i);
                if (wins) return i;
            }
        }
        return -1;
    }

    static void makeMove(int slot, char symbol) {
        board[getRowNo(slot)][getColumnNo(slot)] = symbol;
    }

    static void undoMove(int slot) {
        board[getRowNo(slot)][getColumnNo(slot)] = '-';
    }

    // ── Validation ───────────────────────────────────

    static boolean isValidMove(int slot) {
        if (slot < 1 || slot > 9) return false;
        return board[getRowNo(slot)][getColumnNo(slot)] == '-';
    }

    static int getRowNo(int slot)    { return (slot - 1) / 3; }
    static int getColumnNo(int slot) { return (slot - 1) % 3; }

    // ── Win / Draw ───────────────────────────────────

    static char checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return board[i][0];
            if (board[0][i] != '-' && board[0][i] == board[1][i] && board[1][i] == board[2][i])
                return board[0][i];
        }
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return board[0][0];
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0])
            return board[0][2];
        return ' ';
    }

    static boolean isDraw() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (board[r][c] == '-') return false;
        return true;
    }

    // ── I/O ──────────────────────────────────────────

    static int getUserSlot() {
        System.out.println("Enter slot (1-9):");
        return sc.nextInt();
    }

    static void tossAndAssignSymbols() {
        if (new Random().nextBoolean()) {
            humanSymbol = 'X'; computerSymbol = 'O'; isHumanTurn = true;
        } else {
            humanSymbol = 'O'; computerSymbol = 'X'; isHumanTurn = false;
        }
    }

    static void displayTossResults() {
        System.out.println(humanSymbol == 'X'
            ? "You won the toss! You are X."
            : "You lost the toss. CPU is X.");
    }

    static void initializeBoard() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                board[r][c] = '-';
    }

    static void printBoard() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                System.out.print(board[r][c]);
                if (c < 2) System.out.print(" | ");
            }
            System.out.println();
            if (r < 2) System.out.println("---+---+---");
        }


    }

}