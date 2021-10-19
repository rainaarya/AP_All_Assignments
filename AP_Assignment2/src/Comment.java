import java.util.Date;
import java.util.Scanner;

public class Comment {
    private String comment;
    private User user;
    private Date date_of_upload;

    public Comment(User user) {
        this.user = user;
        this.getComment();
    }

    public void getComment() {
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
