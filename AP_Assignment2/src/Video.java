
import java.util.Date;
import java.util.Scanner;

public class Video implements Material {
    private String topic;
    private String filename;
    private Date date_of_upload;    //Date object to store date
    private Instructor instructor;

    public Video(Instructor instructor) {
        this.addVideo(instructor);
    }

    public void addVideo(Instructor instructor) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter topic of video: ");
        this.topic = sc.nextLine();
        System.out.print("Enter filename of video: ");
        this.filename = sc.nextLine();
        // sc.nextLine(); //clear buffer
        java.util.Date date = new java.util.Date();
        date_of_upload = date;
        this.instructor = instructor;
    }

    public boolean checkFileName() {
        return filename.endsWith(".mp4");
    }


    @Override
    public void show() {
        System.out.println("Title of video: " + topic);
        System.out.println("Video File: " + filename);
        System.out.println("Date of Upload: " + date_of_upload);
        System.out.println("Uploaded by: " + instructor.getName());
        System.out.println();
    }
}
