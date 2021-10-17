import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Instructor> instructors = new ArrayList<Instructor>();
    private ArrayList<Material> materials = new ArrayList<Material>();
    private ArrayList<Comment> comments = new ArrayList<Comment>();
    private ArrayList<Assessment> assessments = new ArrayList<>();

    public void addStudent(int id) {
        students.add(new Student(id));
    }

    public void addInstructor(int id) {
        instructors.add(new Instructor(id));
    }

    public void enterAsInstructor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Instructors:");
        for (int i = 0; i < instructors.size(); ++i) {
            System.out.println(i + " - I" + i);
        }
        System.out.print("Choose ID: ");
        int id = sc.nextInt();
        instructors.get(id).enter(materials, comments, assessments, students);   //give access to materials & comments to instructor

    }

    public void enterAsStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Students:");
        for (int i = 0; i < students.size(); ++i) {
            System.out.println(i + " - S" + i);
        }
        System.out.print("Choose ID: ");
        int id = sc.nextInt();
        students.get(id).enter(materials, comments,assessments);   //give access to materials & comments to student

    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Main BagPack = new Main();
        BagPack.addStudent(0);
        BagPack.addStudent(1);
        BagPack.addStudent(2);
        BagPack.addInstructor(0);
        BagPack.addInstructor(1);

        int opt;
        do {
            System.out.println("Welcome to Backpack!");
            System.out.println("1. Enter as instructor\n" +
                    "2. Enter as student\n" +
                    "3. Exit\n");
            System.out.print("Enter option: ");
            opt = sc.nextInt();
            sc.nextLine(); //clear buffer
            switch (opt) {
                case 1: {
                    BagPack.enterAsInstructor();
                    break;
                }
                case 2: {
                    BagPack.enterAsStudent();
                    break;
                }
                case 3: {
                    System.out.println("Exiting BackPack....");
                    break;
                }
                default:
                    System.out.println("Invalid Choice! Try Again!");
                    break;
            }


        } while (opt != 3);


    }
}
