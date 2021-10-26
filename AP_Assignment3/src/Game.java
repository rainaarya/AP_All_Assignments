import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private final Player player;
    private Player player2;   //for bonus
    private final Dice dice;
    private final ArrayList<Floor> floors = new ArrayList<>();
    //private int points;

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
        player2 = null; //for bonus
        System.out.println("The game setup is ready!\n");
    }

    //for bonus

    public void addPlayer(Player player) {
        player2 = player;
    }

    public void play() {
        Scanner sc = new Scanner(System.in);

        do {
            if (player.getPosition() != 13) {
                if (player2 != null) {
                    System.out.print("Player 1 - " + player.getName() + ": ");     //for bonus
                }

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
                    floors.get(player.getPosition()).performFloorAction(player, floors);
                }
            }


            if (player2 != null) {
                if (player2.getPosition() != 13) {
                    System.out.print("Player 2 - " + player2.getName() + ": ");     //for bonus
                    System.out.print("Hit enter to roll the dice ");
                    sc.nextLine();
                    player2.takeTurn(dice);
                    System.out.println("Dice gave " + dice.getFaceValue());
                    if (player2.getPosition() == -1 && dice.getFaceValue() != 1) {
                        System.out.println("Game cannot start until you get 1\n");
                    } else if (player2.getPosition() == 12 && dice.getFaceValue() == 2) {
                        System.out.println("Player cannot move\n");
                    } else {
                        player2.move(dice.getFaceValue());
                        floors.get(player2.getPosition()).performFloorAction(player2, floors);
                    }
                }
            }


        } while ((player2 == null) ? (player.getPosition() != 13) : (player.getPosition() != 13 || player2.getPosition() != 13)); //for bonus

        if (player2 == null) {
            System.out.println("Game Over!");
            System.out.println(player.getName() + " has accumulated " + player.getPoints() + " points");
            System.out.println("---------------------------------------------------------------");
        } else {
            System.out.println("Game Over!");
            System.out.println(player.getName() + " has accumulated " + player.getPoints() + " points");
            System.out.println(player2.getName() + " has accumulated " + player2.getPoints() + " points");
            if (player.getPoints() > player2.getPoints()) {
                System.out.println("The Winner is " + player.getName() + " !!!");
            } else if (player.getPoints() < player2.getPoints()) {
                System.out.println("The Winner is " + player2.getName() + " !!!");
            } else {
                System.out.println("Both have the same points! It's a Tie!");
            }
            System.out.println("---------------------------------------------------------------");
        }

    }


}
