import java.util.Random;
import java.util.Scanner;
/**
 * Written by: Kevin Patel 40171107
 * COMP 249
 * Assignment # 1 PART I
 * February 6th 2023
 */

/**
 * class to create a snakes and ladders board utilizing 2D array manipulation and create an engine for the game.
 */
public class LadderAndSnake {
    //hardcode board since it never changes
    private int numOfPlayers;
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

    /**
     * default constructor to initialize numOfPlayers
     */
    public LadderAndSnake() {
        numOfPlayers = 2;
    }

    /**
     * method to return a random number from 1-6
     * @return a random int between 1 and 6 both inclusive
     */
    public int flipDice(){
        Random random = new Random();
        return random.nextInt(6 - 1) + 1;
    }

    /**
     * method to decide the order of which player starts first
     * @return The player that starts
     */
    public Player decideOrder(){
        System.out.println("Now deciding which player will start playing;");
        //Number of attempts start at 1
        int attempts = 1;
        boolean flag = true;
        Player player = new Player();

        //while loop loops until a player rolls higher than the other player
        while(flag){
            //randomizes a dice number for both num1 and num2
            int num1 = flipDice();
            System.out.println("Player 1 got a dice value of " + num1);
            int num2 = flipDice();
            System.out.println("Player 2 got a dice value of " + num2);

            //if player 1's number is greater than player 2's then return player 1 as the starting player
            if(num1 > num2){
                System.out.println("Reached final decision on order of playing: Player 1 then Player 2. " +
                        "It took a total of " + attempts + " attempt before reaching a decision");
                player.setName("Player 1");
                flag = false;
            }
            //if player 2's number is greater than player 1's then return player 2 as the starting player
            else if(num2 > num1){
                System.out.println("Reached final decision on order of playing: Player 2 then Player 1. " +
                        "It took " + attempts + " attempt before reaching a decision");
                player.setName("Player 2");
                flag = false;
            }
            //if the two values are equal to each other then increment the number of attempts and repeat the loop
            else if(num1 == num2){
                System.out.println("A tie was achieved between Player 1 and Player 2. Attempting to " +
                        "break the tie");
                attempts++;
            }
        }
        return player;
    }

    /**
     * method to run the core engine of the game Snakes and Ladders
     * @param player1 the first player
     * @param player2 the second player
     */
    public void play(Player player1, Player player2) {
        int dice = 0;
        boolean flag = true;
        String input = "";
        boolean winner = false;
        Scanner scanner = new Scanner(System.in);

        //set first and second player equal to class' players
        Player firstPlayer = player1;
        Player secondPlayer = player2;
        //set first player to be starting player returned from method
        firstPlayer = decideOrder();

        //determines which player is starting based off firstPlayer's value
        if(firstPlayer.getName().equals("Player 1"))
            secondPlayer.setName("Player 2");
        else if(firstPlayer.getName().equals("Player 2"))
            secondPlayer.setName("Player 1");

        //loop until winner is determined
        while(!winner) {
            //get current position of first and second player
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
                startersPosition = 100 - (startersPosition - 100);

            //respectively divide or modulo to get row and column for the 2d array
            int x = (startersPosition - 1) / 10;
            int y = (startersPosition - 1) % 10;

            //prints landing space and travelling space if there is ladder or snake
            if (board[x][y] != 0) {
                System.out.print("; gone to square " + startersPosition + " then to square ");
                startersPosition += board[x][y];
                //set new position
                firstPlayer.setPosition(startersPosition);
                System.out.println(startersPosition);
            }
            //prints landing space when there is no snake or ladder
            else {
                System.out.print("; now in square " + startersPosition + "\n");
                startersPosition += board[x][y];
                firstPlayer.setPosition(startersPosition);
            }
            //win condition when player reaches square 100
            if(startersPosition == 100){
                System.out.println(firstPlayer.getName() + " wins!");
                System.exit(0);
            }
            //checks if first player lands on same square as second player
            if(firstPlayer.getPosition() == secondPlayer.getPosition()){
                System.out.println("Landed in the same square! Kicking second player back to square 0");
                secondPlayer.setPosition(0);
                secondsPosition = 0;
            }

            //Now for second player
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
                secondsPosition = 100 - (secondsPosition - 100);

            //respectively divide and modulo to attain row and column for array
            x = (secondsPosition - 1) / 10;
            y = (secondsPosition - 1) % 10;

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
            //win condition if player is in square 100
            if(secondsPosition == 100){
                System.out.println(firstPlayer.getName() + " wins!");
                System.exit(0);
            }
            //checks if second player lands on same square as first player
            if(secondPlayer.getPosition() == firstPlayer.getPosition()){
                System.out.println("Landed in the same square! Kicking first player back to square 0");
                firstPlayer.setPosition(0);
                startersPosition = 0;
            }
        }
    }
}

