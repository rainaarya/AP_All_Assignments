import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Player player;
    private Dice dice;
    private ArrayList<Floor> floors = new ArrayList<>();
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
        start_game();

        do {
            System.out.print("Hit enter to roll the dice ");
            sc.nextLine();
            player.takeTurn(dice);
            System.out.println("Dice gave " + dice.getFaceValue());
            if (player.getPosition() == 12 && dice.getFaceValue() == 2) {
                System.out.println("Player cannot move");
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

    public void start_game() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Hit enter to roll the dice ");
            sc.nextLine();
            player.takeTurn(dice);
            System.out.println("Dice gave " + dice.getFaceValue());
            if (dice.getFaceValue() == 1) {
                player.move(1);
                points++;
                System.out.println("Player position Floor-" + player.getPosition());
                System.out.println(player.getName() + " has reached an Empty Floor");
                System.out.println("Total points " + points);
                System.out.println();
            }
            if (dice.getFaceValue() != 1) {
                System.out.println("Game cannot start until you get 1");
            }
        } while (dice.getFaceValue() != 1);
    }


}
