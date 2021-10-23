import java.util.ArrayList;

abstract class Floor {
    private int floor_location;

    public void setFloor_location(int location) {
        floor_location = location;
    }

    public int getLocation() {
        return floor_location;
    }

    abstract void performFloorAction(Player player, Game game, ArrayList<Floor> floors);

}
