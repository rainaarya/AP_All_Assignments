public class Submission {
    private String status;
    private int marks;
    private String answer;
    private Instructor instructor;

    public Submission() {
        status = "NOT SUBMITTED";
    }

    public void giveSubmission(String ans) {
        answer = ans;
        changeToUngraded();
    }

    public void setMarks(int marks, Instructor instructor) {
        this.marks = marks;
        this.instructor = instructor;
        changeToGraded();

    }

    public void showGrades() {
        System.out.println("Submission: " + answer);
        System.out.println("Marks scored: " + marks);
        System.out.println("Graded by: " + instructor.getName());
        System.out.println();
    }

    public String getSubmission() {
        return answer;
    }

    public String getStatus() {
        return status;
    }

    private void changeToUngraded() {
        status = "UNGRADED";
    }

    private void changeToGraded() {
        status = "GRADED";
    }


}
