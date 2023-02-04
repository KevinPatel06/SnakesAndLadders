import java.util.Scanner;
/**
 * Written by: Kevin Patel 40171107
 * COMP 249
 * Assignment # 1 PART II
 * February 6th 2023
 */

/**
 * Purpose: main driver class to start snakes and ladders and validate only 2 players are playing
 */
public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LadderAndSnake board = new LadderAndSnake();
        Player player1;
        Player player2;

        //take user input for number of players
        System.out.print("How many players would you like to play with: ");
        int numOfPlayers = scanner.nextInt();

        //if more than 2 players then set it to 2 players
        if (numOfPlayers > 2) {
            System.out.println("Must be 2 players, setting number of players to 2." +
                               "\n setting names to Player 1 and Player 2.");
            numOfPlayers = 2;

        }
        //if less than 2 players then end the game
        else if (numOfPlayers < 2) {
            System.out.println("Error: Cannot execute the game with less than 2 players!" +
                    " Will exit.");
            System.exit(0);
        }
        //initialize both players
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        //starts the game with the two initialized players
        board.play(player1, player2);

    }
}
