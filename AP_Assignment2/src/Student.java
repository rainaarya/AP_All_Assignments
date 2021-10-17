import java.util.ArrayList;
import java.util.Scanner;

public class Student implements User {
    private String name;

    public Student(int id) {
        this.name = "S" + id;
    }

    @Override
    public String getName() {
        return this.name;
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

    public void viewAssessments(ArrayList<Assessment> assessments) {
        for (int i = 0; i < assessments.size(); ++i) {
            System.out.print("ID: " + i + " ");
            assessments.get(i).view();
        }
    }

    public void enter(ArrayList<Material> materials, ArrayList<Comment> comments, ArrayList<Assessment> assessments) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("-----------------------------------");
            System.out.println("Welcome " + name);
            System.out.println("STUDENT MENU\n" +
                    "1. View lecture materials\n" +
                    "2. View assessments\n" +
                    "3. Submit assessment\n" +
                    "4. View grades\n" +
                    "5. View comments\n" +
                    "6. Add comments\n" +
                    "7. Logout\n");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); //clear the buffer

            switch (choice) {
                case 1: {
                    showMaterial(materials);
                    break;
                }
                case 2: {
                    viewAssessments(assessments);
                    break;
                }

                case 5: {
                    viewComments(comments);
                    break;
                }

                case 6: {
                    addComments(comments);
                    break;
                }
                case 7: {
                    System.out.println("Logging out " + name + "....");
                    break;
                }


            }
        } while (choice != 7);

    }
}
