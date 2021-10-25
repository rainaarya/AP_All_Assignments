import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private final Player player;
    private final Dice dice;
    private final ArrayList<Floor> floors = new ArrayList<>();
    private int points;

    public Game(Player player, Dice dice) {
        this.player = player;
        this.dice = dice;
        for (int i = 0; i < 14; ++i) {
            if (i == 2) {
                floors.add(new ElevatorFloor(i));
            } else if (i == 5) {
                floors.add(new SnakeFloor(i));
            } else if (i == 8) {
                floors.add(new LadderFloor(i));
            } else if (i == 11) {
                floors.add(new CobraFloor(i));
            } else {
                floors.add(new EmptyFloor(i));
            }
        }
        System.out.println("The game setup is ready!");
    }

    public void play() {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Hit enter to roll the dice ");
            sc.nextLine();
            player.takeTurn(dice);
            System.out.println("Dice gave " + dice.getFaceValue());
            if (player.getPosition() == -1 && dice.getFaceValue() != 1) {
                System.out.println("Game cannot start until you get 1\n");
            } else if (player.getPosition() == 12 && dice.getFaceValue() == 2) {
                System.out.println("Player cannot move\n");
            } else {
                player.move(dice.getFaceValue());
                floors.get(player.getPosition()).performFloorAction(player, this, floors);
            }


        } while (player.getPosition() != 13);

        System.out.println("Game Over!");
        System.out.println(player.getName() + " has accumulated " + points + " points");
        System.out.println("---------------------------------------------------------------");

    }

    public void setPoints(int points) {
        this.points += points;
    }

    public int getPoints() {
        return points;
    }

}
