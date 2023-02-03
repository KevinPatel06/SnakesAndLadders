import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

/**
 * JAVADOC
 */
/*
    snakes:
    98 -> -20
    97 -> -21
    95 -> -71
    93 -> -25
    79 -> -60
    64 -> -14
    48 -> -18
    ladders:
    1 -> 37
    4 -> 10
    9 -> 22
    28 -> 56
    21 -> 21
    36 -> 8
    51 -> 16
    71 -> 20
    80 -> 20

     */
public class LadderAndSnake {
    //hardcode board since it never changes
    private int board[][] = {
            {37,0,0,10,0,0,0,0,22,0}, //1-10
            {0,0,0,0,0,0,0,0,0,0}, //11-20
            {21,0,0,0,0,0,0,56,0,0}, //21-30
            {0,0,0,0,0,8,0,0,0,0}, //31-40
            {0,0,0,0,0,0,0,-18,0,0}, //41-50
            {16,0,0,0,0,0,0,0,0,0}, //51-60
            {0,0,0,-14,0,0,0,0,0,0}, //61-70
            {20,0,0,0,0,0,0,0,-14,20}, //71-80
            {0,0,0,0,0,0,0,0,0,0}, //81-90
            {0,0,-25,0,-71,0,-21,-20,0,0}, //91-100
    };
    private int numOfPlayers;
    private Player player1;
    private Player player2;

    public LadderAndSnake() {
        numOfPlayers = 2;
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
    }

    /**
     *
     * @return a random int between 1 and 6 both inclusive
     */
    public int flipDice(){
        Random random = new Random();
        return random.nextInt(6 - 1) + 1;
    }

    /**
     *
     * @return The player that starts
     */
    public Player decideOrder(){
        System.out.println("Now deciding which player will start playing;");
        int attempts = 1;
        boolean flag = true;
        Player player = new Player();
        while(flag){
            int num1 = flipDice();
            System.out.println("Player 1 got a dice value of " + num1);
            int num2 = flipDice();
            System.out.println("Player 2 got a dice value of " + num2);
            if(num1 > num2){
                System.out.println("Reached final decision on order of playing: Player 1 then Player 2. " +
                        "It took a total of " + attempts + " attempt before reaching a decision");
                player.setName("Player 1");
                flag = false;
            }
            else if(num2 > num1){
                System.out.println("Reached final decision on order of playing: Player 2 then Player 1. " +
                        "It took " + attempts + " attempt before reaching a decision");
                player.setName("Player 2");
                flag = false;
            }
            else if(num1 == num2){
                System.out.println("A tie was achieved between Player 1 and Player 2. Attempting to " +
                        "break the tie");
                attempts++;
            }
        }
        return player;
    }
    public void play() {
        int dice = 0;
        boolean flag = true;
        String input = "";
        boolean winner = false;
        Scanner scanner = new Scanner(System.in);

        Player firstPlayer = this.player1;
        Player secondPlayer = this.player2;
        firstPlayer = decideOrder();

        //figure out which player is starting
        if(firstPlayer.equals("Player 1"))
            secondPlayer.setName("Player 2");
        else
            secondPlayer.setName("Player 1");

        //loop while winner is false
        while(!winner) {
            int startersPosition = firstPlayer.getPosition();
            int secondsPosition = secondPlayer.getPosition();

            //while loop to execute until player types a valid option of y or n to rolling the dice
            flag = true;
            while (flag) {
                System.out.print("Roll dice for " + firstPlayer.getName() + "? (y/n)");
                input = scanner.next();
                //trim empty spaces and make lowercase to check for specific string input
                input = input.trim().toLowerCase();

                //if the input is y then will break out of while loop and continue game
                if (input.equals("y"))
                    flag = false;
                //if the input is n then will end the game
                else if (input.equals("n")) {
                    System.out.print("\nEnding the game");
                    System.exit(0);
                }
                //will repeat the while loop until a valid option is entered
                else
                    System.out.println("\nSelect a valid option ");
            }

            //flip dice for starting player
            dice = flipDice();
            System.out.print("\n" + firstPlayer.getName() + " got dice value of: " + dice);

            //add current position to dice roll
            startersPosition += dice;
            //check if space achieved is exactly 100
            if(startersPosition == 100){
                System.out.println(firstPlayer.getName() + " wins!");
                System.exit(0);
            }
            //check if dice roll + position exceeds 100
            if(startersPosition > 100)
                startersPosition = 100 - (100 - startersPosition);

            //respectively divide or modulo to get row and column for the 2d array
            int x = startersPosition / 10;
            int y = startersPosition % 10;

            //prints landing space and travelling space if there is ladder or snake
            if (board[x][y] != 0) {
                System.out.print("; gone to square " + startersPosition + " then to square ");
                startersPosition += board[x][y];
                firstPlayer.setPosition(startersPosition);
                System.out.println(startersPosition);
            }
            //prints landing space when there is no snake or ladder
            else {
                System.out.print("; now in square " + startersPosition + "\n");
                startersPosition += board[x][y];
                firstPlayer.setPosition(startersPosition);
            }
            //checks if first player lands on same square as second player
            if(firstPlayer.getPosition() == secondPlayer.getPosition()){
                System.out.println("Landed in the same square! Kicking second player back to square 0");
                secondPlayer.setPosition(0);
            }

            //Now for second player
            //reinitialize input
            input = "";
            //while loop to execute until player types a valid option of y or n to rolling the dice
            flag = true;
            while (flag) {
                System.out.print("Roll dice for " + secondPlayer.getName() + "? (y/n): ");
                input = scanner.next();
                //trim empty spaces and make lowercase to check for specific string input
                input = input.trim().toLowerCase();

                //if the input is y then will break out of while loop and continue game
                if (input.equals("y"))
                    flag = false;

                    //if the input is n then will end the game
                else if (input.equals("n")) {
                    System.out.print("\nEnding the game");
                    System.exit(0);
                }
                //will repeat the while loop until a valid option is entered
                else
                    System.out.println("\nSelect a valid option ");
            }

            //flip the dice for the second player
            dice = flipDice();
            System.out.print("\n" + secondPlayer.getName() + " got dice value of: " + dice);

            //Determining coordinates of position within the array and applying a snake or ladder if necessary.
            secondsPosition += dice;
            //check if space achieved is exactly 100
            if(secondsPosition == 100){
                System.out.println(secondPlayer.getName() + " wins!");
                System.exit(0);
            }
            //check if dice roll + position exceeds 100
            if(secondsPosition > 100)
                secondsPosition = 100 - (100 - secondsPosition);

            //respectively divide and modulo to attain row and column for array
            x = secondsPosition / 10;
            y = secondsPosition % 10;

            //prints landing space and travelling space if there is ladder or snake
            if (board[x][y] != 0) {
                System.out.print("; gone to square " + secondsPosition + " then to square ");
                secondsPosition += board[x][y];
                secondPlayer.setPosition(secondsPosition);
                System.out.println(secondsPosition);
            }
            //prints landing space when there is no snake or ladder
            else {
                System.out.print("; now in square " + secondsPosition + "\n");
                secondsPosition += board[x][y];
                secondPlayer.setPosition(secondsPosition);
            }
            //checks if second player lands on same square as first player
            if(secondPlayer.getPosition() == firstPlayer.getPosition()){
                System.out.println("Landed in the same square! Kicking first player back to square 0");
                firstPlayer.setPosition(0);
            }

        }


    }
}

