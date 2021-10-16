
import java.util.Date;
import java.util.Scanner;

public class Slide implements Material {
    private String topic;
    private int number;
    private String contents[];
    private Date date_of_upload;    //Date object to store date
    private Instructor instructor;

    public Slide(Instructor instructor) {
        this.addSlide(instructor);
    }

    public void addSlide(Instructor instructor) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter topic of slides: ");
        this.topic = sc.nextLine();
        System.out.print("Enter number of slides: ");
        this.number = sc.nextInt();
        contents = new String[number];    //initialise array of strings
        sc.nextLine(); //clear buffer
        System.out.println("Enter content of slides:");
        for (int i = 0; i < number; ++i) {
            System.out.print("Content of slide " + (i + 1) + ": ");
            contents[i] = sc.nextLine();
        }

        java.util.Date date = new java.util.Date();
        date_of_upload = date;
        this.instructor = instructor;
    }


    @Override
    public void show() {
        System.out.println("Title: " + topic);
        for (int i = 0; i < number; ++i) {
            System.out.println("Slide " + (i + 1) + ": " + contents[i]);
        }
        System.out.println("Number of slides: " + number);
        System.out.println("Date of Upload: " + date_of_upload);
        System.out.println("Uploaded by: " + instructor.getName());
        System.out.println();

    }
}
