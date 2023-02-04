/**
 * Written by: Kevin Patel 40171107
 * COMP 249
 * Assignment # 1 PART I
 * February 6th 2023
 */

/**
 * Purpose: To create a player class to hold the position and name of the playing player
 */
public class Player {
    private int position;
    private String name;

    /**
     * default constructor to initialize name and position
     */
    public Player() {
        this.name = "sample";
        this.position = 0;
    }

    /**
     * constructor that takes in a name as a parameter
     * @param name
     */
    public Player(String name){
        this.name = name;
        this.position = 0;
    }

    /**
     * getter for position
     * @return position
     */
    public int getPosition() {
        return position;
    }

    /**
     * setter for position
     * @param position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
