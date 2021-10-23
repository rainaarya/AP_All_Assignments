import java.util.ArrayList;

public class CobraFloor extends Floor {

    public CobraFloor(int location) {
        setFloor_location(location);
    }

    @Override
    void performFloorAction(Player player, Game game, ArrayList<Floor> floors) {
        game.setPoints(-4);
        System.out.println("Player position Floor-" + player.getPosition());
        System.out.println(player.getName() + " has reached a King Cobra Floor");
        System.out.println("Total points: " + game.getPoints());
        System.out.println();

        player.move(-8);
        floors.get(player.getPosition()).performFloorAction(player, game, floors);



    }
}
