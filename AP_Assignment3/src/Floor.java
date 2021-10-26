import java.util.ArrayList;

abstract class Floor {
    private final int floor_location;

    public Floor(int location) {
        floor_location = location;
    }

    public int getLocation() {
        return floor_location;
    }

    abstract void performFloorAction(Player player, ArrayList<Floor> floors);

}
