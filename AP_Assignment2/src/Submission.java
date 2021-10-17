public class Submission {
    private String status;
    private int marks;
    private String answer;
    private String grader_name;

    public Submission() {
        status = "NOT SUBMITTED";
    }

    public void giveSubmission(String ans) {
        answer = ans;
        changeToUngraded();
    }

    public void setMarks(int marks, String instructor_name) {
        this.marks = marks;
        this.grader_name = instructor_name;
        changeToGraded();

    }

    public void showGrades() {
        System.out.println("Submission: " + answer);
        System.out.println("Marks scored: " + marks);
        System.out.println("Graded by: " + grader_name);
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