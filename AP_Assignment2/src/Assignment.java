import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Assignment implements Assessment {
    private final String statement;
    private final int max_marks;
    private String status;
    private final LinkedHashMap<Student, Submission> submission_detail = new LinkedHashMap<>();

    public Assignment(ArrayList<Student> students) {
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

    @Override
    public void submit(Student student) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter filename of Assignment: ");
        String filename = sc.nextLine();
        if (!filename.endsWith(".zip")) {
            System.out.println("Assignment does not have the extension .zip");
        } else {
            submission_detail.get(student).giveSubmission(filename);
        }

    }

    @Override
    public void getGraded(Student student) {
        submission_detail.get(student).showGrades();
    }

    @Override
    public void getUngraded(Student student) {
        System.out.println("Submission: " + submission_detail.get(student).getSubmission());
    }

    @Override
    public void grade(Instructor instructor) {
        Scanner sc = new Scanner(System.in);
        int flag = 0;
        int id;
        for (Student student : submission_detail.keySet()) {
            if (getStatus().equals("OPEN")) {
                if (getStudentSubmissionStatus(student).equals("UNGRADED")) {
                    ++flag;
                    if (flag == 1) {
                        System.out.println("Choose ID from these Ungraded Submissions: ");
                    }
                    System.out.println(student.getName().substring(1) + ". " + student.getName());  // check if better implementation is possible
                }
            }

        }
        if (flag == 0) {
            System.out.println("Nothing is present to grade!");
        } else {
            System.out.print("Enter ID ");
            id = sc.nextInt();
            sc.nextLine(); //clear the buffer
            Student student = null;
            for (Student temp_student : submission_detail.keySet()) {
                if (temp_student.getMe("S" + id)) {
                    student = temp_student;
                    break;
                }
            }
            //markSubmission(student, instructor.getName());
            int marks;
            System.out.println("Submission Details ");
            System.out.println("Submission: " + submission_detail.get(student).getSubmission());
            System.out.println("Max Marks: " + max_marks);
            System.out.print("Enter Marks Scored: ");
            marks = sc.nextInt();
            sc.nextLine(); //clear the buffer
            submission_detail.get(student).setMarks(marks, instructor);


        }
    }


    @Override
    public String getStudentSubmissionStatus(Student student) {
        return submission_detail.get(student).getStatus();
    }

    @Override
    public void view() {
        System.out.println("Assignment Name: " + statement + ", Max Marks: " + max_marks);
    }

    @Override
    public void close() {
        status = "CLOSED";
    }

    @Override
    public String getStatus() {
        return status;
    }

}
