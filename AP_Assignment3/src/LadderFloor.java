import java.util.ArrayList;

public class LadderFloor extends Floor {

    public LadderFloor(int location) {
        setFloor_location(location);
    }

    @Override
    void performFloorAction(Player player, Game game, ArrayList<Floor> floors) {
        game.setPoints(2);
        System.out.println("Player position Floor-" + player.getPosition());
        System.out.println(player.getName() + " has reached a Ladder Floor");
        System.out.println("Total points: " + game.getPoints());
        System.out.println();

        player.move(4);
        floors.get(player.getPosition()).performFloorAction(player, game, floors);


    }
}
