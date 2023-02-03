import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LadderAndSnake board = new LadderAndSnake();
        Player player1;
        Player player2;

        System.out.print("How many players would you like to play with: ");
        int numOfPlayers = scanner.nextInt();

        if (numOfPlayers > 2) {
            System.out.println("Must be 2 players, setting number of players to 2." +
                               "\n setting names to Player 1 and Player 2.");
            numOfPlayers = 2;

        }
        else if (numOfPlayers < 2) {
            System.out.println("Error: Cannot execute the game with less than 2 players!" +
                    " Will exit.");
            System.exit(0);
        }
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        board.play(player1, player2);

    }
}
