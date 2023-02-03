public class Player {
    private int position;
    private String name;

    public Player() {
        this.name = "sample";
        this.position = 0;
    }
    public Player(String name){
        this.name = name;
        this.position = 0;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
