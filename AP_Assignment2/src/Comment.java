import java.util.Date;
import java.util.Scanner;

public class Comment {
    private final String comment;
    private final User user;
    private final Date date_of_upload;

    public Comment(User user) {
        this.user = user;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter comment: ");
        comment = sc.nextLine();
        java.util.Date date = new java.util.Date();
        date_of_upload = date;
    }

    public void showComment() {
        System.out.println(comment + " - " + user.getName());
        System.out.println(date_of_upload);
        System.out.println();
    }
}
