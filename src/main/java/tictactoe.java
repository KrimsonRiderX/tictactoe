import java.util.Random;

public class tictactoe {

    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSybmol;


    static char[][] board = new char[3][3];

    public static void main (String[] Args){
        tossAndAssignSymobols();
        displayTossResults();
        initializeboard();
        printboard();
    }

    static void tossAndAssignSymobols(){
        Random rand = new Random();

        if (rand.nextInt()%2 == 0){
            humanSymbol = 'X';
            computerSybmol = 'O';
        }
        else {
            humanSymbol = 'O';
            computerSybmol = 'X';
        }
    }

    static void displayTossResults(){

        if (humanSymbol == 'X'){
            System.out.println("you won the toss move first");
        }
        else {
            System.out.println("you lost the toss computer move first");
        }
    }

    static void initializeboard(){
        for(int row = 0; row<3 ; row++){
            for (int column = 0; column<3 ; column++){
                board[row][column] = '-';
            }
        }
    }

    static void printboard() {
        System.out.println("xxxxxxxxxxxxxxxxxxxxxx");

        for(int row = 0; row<3 ; row++){
            for (int column = 0; column<3 ; column++){
                System.out.print(board[row][column]);
            }
            System.out.println(" ");
        }
        System.out.println("xxxxxxxxxxxxxxxxxxxxxx");
    }
}
