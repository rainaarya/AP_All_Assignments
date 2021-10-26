import java.util.ArrayList;

public class ElevatorFloor extends LadderFloor {

    public ElevatorFloor(int location) {
        super(location);
    }

    @Override
    void performFloorAction(Player player, Game game, ArrayList<Floor> floors) {
        game.setPoints(4);
        System.out.println("Player position Floor-" + player.getPosition());
        System.out.println(player.getName() + " has reached an Elevator Floor");
        System.out.println("Total points: " + game.getPoints());
        System.out.println();

        player.move(8);
        floors.get(player.getPosition()).performFloorAction(player, game, floors);


    }
}
