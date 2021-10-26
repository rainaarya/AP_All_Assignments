import java.util.ArrayList;

public class EmptyFloor extends Floor {

    public EmptyFloor(int location) {
        super(location);
    }

    @Override
    void performFloorAction(Player player, ArrayList<Floor> floors) {
        player.setPoints(1);
        System.out.println("Player position Floor-" + player.getPosition());
        System.out.println(player.getName() + " has reached an Empty Floor");
        System.out.println("Total points: " + player.getPoints());
        System.out.println();
    }
}
