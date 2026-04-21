public class tictactoe {
    static char[][] board = new char[3][3];

    public static void main (String[] Args){
        initializeboard();
        printboard();
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
