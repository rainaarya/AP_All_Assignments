import java.util.ArrayList;

public class SnakeFloor extends Floor {

    public SnakeFloor(int location) {
        super(location);
    }

    @Override
    void performFloorAction(Player player, ArrayList<Floor> floors) {
        player.setPoints(-2);
        System.out.println("Player position Floor-" + player.getPosition());
        System.out.println(player.getName() + " has reached a Normal Snake Floor");
        System.out.println("Total points: " + player.getPoints());
        System.out.println();

        player.move(-4);
        floors.get(player.getPosition()).performFloorAction(player, floors);


    }
}
