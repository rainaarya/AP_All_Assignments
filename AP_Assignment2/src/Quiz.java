import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Quiz implements Assessment {
    private String statement;
    private int max_marks;
    private String status;
    private HashMap<Student, Submission> submission_detail = new HashMap<>();

    public Quiz(ArrayList<Student> students) {
        this.create(students);
    }

    public void create(ArrayList<Student> students) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter quiz question: ");
        statement = sc.nextLine();
        max_marks = 1;   //default marks
        for (Student student : students)
            this.submission_detail.put(student, new Submission());
        status = "OPEN";
    }

    public void view() {
        System.out.println("Quiz Question: " + statement);
    }

    public void close() {
        status = "CLOSED";
    }

    public String getStatus() {
        return status;
    }

}
