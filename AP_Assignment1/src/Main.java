import java.util.*;

public class Main {
    private ArrayList<Vaccine> vaccines = new ArrayList<Vaccine>();
    private ArrayList<Hospital> hospitals = new ArrayList<Hospital>();
    private HashMap<String, Citizen> citizens = new HashMap<String, Citizen>();
    private ArrayList<Slot> slots = new ArrayList<Slot>();

    public void printLines() {
        //System.out.println();
        System.out.println("----------------------------------");
    }

    public void showMenu() {
        System.out.println("\t\t\tMenu:\n1. Add Vaccine\n" +
                "2. Register Hospital\n" +
                "3. Register Citizen\n" +
                "4. Add Slot for Vaccination\n" +
                "5. Book Slot for Vaccination\n" +
                "6. List all slots for a hospital\n" +
                "7. Check Vaccination Status\n" +
                "8. Exit\n");

    }

    public void addVaccine(String name, int doses, int gap) {
        vaccines.add(new Vaccine(name, doses, gap));
    }

    public void addHospital(String name, String pincode) {
        hospitals.add(new Hospital(name, pincode));
    }

    public void addCitizen(String name, int age, String id) {
        if (!(id.matches("[0-9]+") && id.length() == 12)) {
            System.out.println("Invalid Citizen ID entered");
            return;
        }
        Citizen tempCitizen = new Citizen(name, age, id);
        if (!tempCitizen.isEligible())
            return;
        if (citizens.containsKey(id)) {
            System.out.println("Citizen is already Registered");
            return;
        }
        if (tempCitizen.isEligible())
            citizens.put(id, tempCitizen);
    }

    public void addSlots(int hospital_id) {
        if (hospital_id >= hospitals.size() + 100000 || hospital_id < 100000) { // to accommodate 6 digit hospital id
            System.out.println("Invalid Hospital ID Entered");
            return;
        } else {
            hospitals.get(hospital_id - 100000).createSlots(vaccines, slots);
        }
    }

    public void book(String citizen_id) {
        if (!citizens.containsKey(citizen_id)) {
            System.out.println("Invalid Citizen ID or Citizen not Registered");
            return;
        } else {
            citizens.get(citizen_id).bookSlot(slots, hospitals, vaccines);
        }

    }

    public void available(int hospital_id) {
        if (hospital_id >= hospitals.size() + 100000 || hospital_id < 100000) { // to accommodate 6 digit hospital id
            System.out.println("Invalid Hospital ID Entered");
            return;
        } else {
            hospitals.get(hospital_id - 100000).availableSlots(slots);
        }


    }

    public void check(String citizen_id) {
        if (!citizens.containsKey(citizen_id)) {
            System.out.println("Invalid Citizen ID or Citizen not Registered");
            return;
        } else {
            citizens.get(citizen_id).checkStatus();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main COWIN = new Main();
        System.out.println("CoWin Portal Initialised....");

        int choice;
        do {
            COWIN.printLines();
            COWIN.showMenu();
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // eat the buffer

            switch (choice) {

                case 1: {
                    System.out.print("Enter Vaccine Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Number of Doses: ");
                    int doses = sc.nextInt();
                    int gap;
                    if (doses > 1) {
                        System.out.print("Enter Gap Between Doses: ");
                        gap = sc.nextInt();
                    } else {
                        gap = 0;
                    }
                    System.out.println();
                    COWIN.addVaccine(name, doses, gap);
                    break;
                }


                case 2: {
                    System.out.print("Enter Hospital Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter PinCode: ");
                    String pincode = sc.nextLine();
                    System.out.println();
                    COWIN.addHospital(name, pincode);
                    break;
                }

                case 3: {
                    System.out.print("Enter Citizen Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    System.out.print("Enter Unique ID: ");
                    sc.nextLine(); // eat the buffer
                    String id = sc.nextLine();
                    System.out.println();
                    COWIN.addCitizen(name, age, id);
                    break;
                }

                case 4: {
                    System.out.print("Enter Hospital ID: ");
                    int hospital_id = sc.nextInt();
                    COWIN.addSlots(hospital_id);
                    break;
                }

                case 5: {
                    System.out.print("Enter patient Unique ID: ");
                    String citizen_id = sc.nextLine();
                    COWIN.book(citizen_id);
                    break;
                }

                case 6: {
                    System.out.print("Enter Hospital ID: ");
                    int hospital_id = sc.nextInt();
                    sc.nextLine(); //clear the buffer
                    COWIN.available(hospital_id);

                    break;
                }

                case 7: {
                    System.out.print("Enter Patient ID: ");
                    String citizen_id = sc.nextLine();
                    COWIN.check(citizen_id);
                    break;
                }
                case 8: {
                    System.out.println("Exiting the Program!");
                    break;
                }

                default:
                    System.out.println("Invalid Choice! Try again!");
            }


        } while (choice != 8);


    }
}
