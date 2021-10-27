import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        char ch;
        System.out.print("Do you want to see the Bonus Implementation? (y/n) ");
        ch = sc.next().charAt(0);


        if (ch == 'n' || ch == 'N') {
            Player player = new Player();
            Dice dice = new Dice();
            Game game = new Game(player, dice);
            game.play();
        } else if(ch == 'y' || ch == 'Y'){
            System.out.println("For First Player");
            Player player = new Player();
            System.out.println("For Second Player");
            Player player2 = new Player();
            Dice dice = new Dice();
            Game game = new Game(player, dice);
            game.addPlayer(player2);
            game.play();
        }else{
            System.out.println("Invalid Option Chosen!");
        }

    }
}
