import java.util.Scanner;

public class Player {
    private final String name;
    private int position = -1;

    public Player() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the player name and hit enter: ");
        name = sc.nextLine();
    }

    public void takeTurn(Dice dice) {
        dice.roll();
    }

    public void move(int amount) {
        position += amount;
    }

    public String getName() {
        return name;
    }


    public int getPosition() {
        return position;
    }

}
