import java.util.ArrayList;
import java.util.Scanner;

public class Instructor implements User {

    private String name;

    public Instructor(int id) {
        this.name = "I" + id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void addMaterial(ArrayList<Material> materials) {

        Scanner sc = new Scanner(System.in);
        System.out.println("1. Add Lecture Slide\n" +
                "2. Add Lecture Video\n");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine(); //clear buffer

        if (choice == 1) {
            Slide slide = new Slide(this);
            materials.add(slide);
        } else if (choice == 2) {
            Video video = new Video(this);
            if (video.checkFileName()) {
                materials.add(video);   //add video to material if .mp4 is extension of filename
            } else {
                System.out.println("Video Entered does not have the extension .mp4");
            }
        }

    }

    public void showMaterial(ArrayList<Material> materials) {
        for (Material material : materials) {
            material.show();
        }
    }

    public void addComments(ArrayList<Comment> comments) {
        Comment comment = new Comment(this);
        comments.add(comment);
    }

    public void viewComments(ArrayList<Comment> comments) {
        for (Comment comment : comments) {
            comment.showComment();
        }
    }

    public void enter(ArrayList<Material> materials, ArrayList<Comment> comments) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("-----------------------------------");
            System.out.println("Welcome " + name);
            System.out.println("INSTRUCTOR MENU:\n" +
                    "1. Add class material\n" +
                    "2. Add assessments\n" +
                    "3. View lecture materials\n" +
                    "4. View assessments\n" +
                    "5. Grade assessments\n" +
                    "6. Close assessment\n" +
                    "7. View comments\n" +
                    "8. Add comments\n" +
                    "9. Logout\n");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); //clear the buffer

            switch (choice) {
                case 1: {
                    addMaterial(materials);
                    break;
                }
                case 2: {

                    break;
                }
                case 3: {
                    showMaterial(materials);
                    break;
                }

                case 7: {
                    viewComments(comments);
                    break;
                }

                case 8: {
                    addComments(comments);
                    break;
                }
                case 9: {
                    System.out.println("Logging out " + name + "....");
                    break;
                }


            }
        } while (choice != 9);

    }
}
