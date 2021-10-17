import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Assignment implements Assessment {
    private String statement;
    private int max_marks;
    private String status;
    private HashMap<Student, Submission> submission_detail = new HashMap<>();

    public Assignment(ArrayList<Student> students) {
        this.create(students);
    }

    public void create(ArrayList<Student> students) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter problem statement: ");
        statement = sc.nextLine();
        System.out.print("Enter Max Marks: ");
        max_marks = sc.nextInt();
        sc.nextLine(); //clear
        for (Student student : students)
            this.submission_detail.put(student, new Submission());
        status = "OPEN";
    }

    public void view() {
        System.out.println("Assignment Name: " + statement + ", Max Marks: " + max_marks);
    }

    public void close() {
        status = "CLOSED";
    }

    public String getStatus(){
        return status;
    }

}
