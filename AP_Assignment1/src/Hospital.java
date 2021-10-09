import java.util.*;

public class Hospital {

    static private int count = 100000;
    private int hospital_id;
    private String pincode;
    private String name;


    public Hospital(String name, String pincode) {
        this.pincode = pincode;
        this.name = name;
        this.hospital_id = count;
        ++count;
        this.displayDetails();
    }

    public int getHospital_id() {
        return hospital_id;
    }

    public String getPincode() {
        return pincode;
    }

    public String getName() {
        return name;
    }


    public void displayDetails() {
        System.out.println("Hospital Name: " + name);
        System.out.println("PinCode: " + pincode);
        System.out.println("Unique ID: " + hospital_id);
    }

    public void createSlots(ArrayList<Vaccine> vaccines, ArrayList<Slot> slots) {
        Scanner sc = new Scanner(System.in);
        int no_of_slots, day, quantity;
        System.out.print("Enter the Number of slots to be added: ");
        no_of_slots = sc.nextInt();
        while (no_of_slots > 0) {
            System.out.println();
            System.out.print("Enter Day Number: ");
            day = sc.nextInt();
            System.out.print("Enter Quantity: ");
            quantity = sc.nextInt();
            System.out.println("Select Vaccine");

            for (int i = 0; i < vaccines.size(); ++i) {
                System.out.println(i + ". " + vaccines.get(i).getName());
            }
            System.out.print("Enter the Vaccine Number: ");
            int vac_num;
            vac_num = sc.nextInt();

            if (vac_num >= vaccines.size() || vac_num < 0) {
                System.out.println("Invalid Vaccine Number Entered"); // DO more ERROR Handling HERE!!!
                return;
            }


            Slot tempSlot = new Slot(this, day, quantity, vaccines.get(vac_num));
            slots.add(tempSlot);    // the hospital is adding the slot now
            vaccines.get(vac_num).addHospitalWithVaccine(this); //add this hospital in the list of hospitals that the vaccine is available in

            --no_of_slots;
        }

    }

    public void availableSlots(ArrayList<Slot> slots) {
        boolean flag = false;
        for (int i = 0; i < slots.size(); ++i) {
            if (slots.get(i).getHospital().equals(this)) {      //check if slot[i]'s hospital is same as the user entered hospital
                slots.get(i).printSlotHospital();
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("This Hospital Has No Slots Currently!");
        }

    }


}
