public class Main {

    public static void main(String[] args) {

        Player player = new Player();
        Dice dice = new Dice();
        Game game = new Game(player, dice);
        game.play();

    }
}
