public interface Assessment {
    public void view();

    public void close();

    public String getStatus();

    public String getStudentSubmissionStatus(Student student);

    public void submit(Student student);

    public void grade(Instructor instructor);

    public void getGraded(Student student);

    public void getUngraded(Student student);

}
