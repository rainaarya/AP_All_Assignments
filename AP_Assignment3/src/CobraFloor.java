import java.util.ArrayList;

public class CobraFloor extends SnakeFloor {

    public CobraFloor(int location) {
        super(location);
    }

    @Override
    void performFloorAction(Player player, ArrayList<Floor> floors) {
        player.setPoints(-4);
        System.out.println("Player position Floor-" + player.getPosition());
        System.out.println(player.getName() + " has reached a King Cobra Floor");
        System.out.println("Total points: " + player.getPoints());
        System.out.println();

        player.move(-8);
        floors.get(player.getPosition()).performFloorAction(player,floors);



    }
}
