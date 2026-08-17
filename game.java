import java.util.Scanner;

public class game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // a 3 x 3 grid for the game
        char[][] board = new char[3][3];

        // entering ' ' to every position
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = ' ';
            }
        }

        // there will be two players 'X' or 'O', who will play their move one by one
        char player = 'X';
        // this will identify, after each move, that if any player won or game draw
        boolean gameOver = false;

        // this is to track that if every box of 3 x 3 grid is occupied or not, if not then continue the loop and if yes then break the loop
        // after printing that the game i draw. because if someone will won then the loop will terminate automatically
        int total_move = 0;


        while (!gameOver) {

            printBoard(board);  // printing the grid every time after user/player plays a move

            // which player want to play their next move to which place:
            System.out.println("Player " + player + " enter: ");
            int row = sc.nextInt();
            int col = sc.nextInt();

            // if player entered a wrong position then it would print a message and then will give another chance to same player to
            // re-enter the position
            if (row > 2 || col > 2) {
                System.out.println("You have entered wrong co-ordinates. Please re enter it!");
                continue;
            }

            // this will make sure that player will only be able to enter it's value on the empty position
            if (board[row][col] == ' ') {
                board[row][col] = player;
                // this will check that if player won the game or not
                gameOver = haveWon(board, player);
                // if player won the match, then it will print a statement and will break the loop
                if (gameOver) {
                    System.out.println(player + " won the game!");
                    break;
                }
//                if (player == 'X') {
//                    player = 'O';
//                }   else {
//                    player = 'X';
//                }
                // will pass the game to next player
                player = (player == 'X') ? 'O' : 'X';
            }   else {
                System.out.println("Invalid move. Try again please!");
            }
            total_move++;
            // if players have played their game but no one won then this will execute
            if (total_move == 9) {
                System.out.println("Game draw. No one won or lose the game.");
                gameOver = true;
            }
        }
        printBoard(board);

    }

    // haveWon function will check if the respective player won or not
    private static boolean haveWon(char[][] board, char player) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boolean wonOrNot = helper(board, player, i, j);
                if (wonOrNot) {
                    return true;
                }
            }
        }

        return false;
    }

    // helper function is used by haveWon function
    private static boolean helper(char[][] board, char player, int row, int col) {
        boolean tOf = true;

        // will check the row
        for (int i = 0; i < 3; i++) {
            if (board[row][i] != player) {
                tOf = false;
            }
        }

        // it will check the col
        for (int i = 0; i < col; i++) {
            if (board[i][col] != player) {
                tOf = false;
            }
        }

        // it will check the diagonal
        if ((row == 0 && (col == 0 || col == 2)) || (row == 2 && (col == 0 || col == 2)) || (row == 1 && col == 1)) {
            if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
                return true;
            }   else if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
                return true;
            }
        }
        return tOf;
    }

    // printing function to print the grid or board
    private static void printBoard(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (col != 2) {
                    System.out.print(board[row][col] + " | ");
                }   else {
                    System.out.print(board[row][col]);
                }
            }
            System.out.println();
        }
    }
}
