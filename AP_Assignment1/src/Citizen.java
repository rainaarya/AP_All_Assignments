import java.util.ArrayList;
import java.util.Scanner;

public class Citizen {
    private String name;
    private int age;
    private String id;
    private String vaccination_status;
    private Vaccine vaccine_taken;      // NEW STUFF ADDED TRYING
    private int doses_taken;
    private int next_dose_date;

    public Citizen(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.vaccination_status = "REGISTERED";
        this.displayDetails();
    }

    public boolean isEligible() {
        return age >= 18;
    }   //age 18 is also valid

    public void checkStatus() {
        if (vaccination_status.equals("REGISTERED")) {
            System.out.println("Citizen REGISTERED");
            return;
        }
        System.out.println(vaccination_status);
        System.out.println("Vaccine Given: " + vaccine_taken.getName());
        System.out.println("Number of Doses Given: " + doses_taken);
        if (vaccination_status.equals("PARTIALLY VACCINATED"))
            System.out.println("Next Dose Due Date: " + next_dose_date);
    }

    public void getVaccinated(ArrayList<Slot> slots, ArrayList<Hospital> hospitals, ArrayList<Vaccine> vaccines, int hospital_id, String vac_name) {
        Scanner sc = new Scanner(System.in);
        if (vaccination_status.equals("REGISTERED")) {
            ArrayList<Integer> temp_slot_id = new ArrayList<Integer>(); // to store eligible slot ids (for error checking)
            int tempflag = 0;
            for (int i = 0; i < slots.size(); ++i) {
                if (vac_name.equals("NULL")) {  //this means case 1, from find via PinCode. All Vaccination slots of the hospital are visible
                    if (slots.get(i).getHospital().getHospital_id() == hospital_id && slots.get(i).getQuantity() > 0) {
                        System.out.println("Slot " + i + "-> Day: " + slots.get(i).getDay() + ", Available Qty: " + slots.get(i).getQuantity() + ", Vaccine: " + slots.get(i).getVaccine().getName());
                        temp_slot_id.add(i);
                        tempflag = 1;
                    }
                } else {  //this means case 2, from find via vaccine name. So via this, if vac_name is passed(not "NULL"), then only the slots of that vaccine are visible
                    if (slots.get(i).getHospital().getHospital_id() == hospital_id && slots.get(i).getQuantity() > 0 && slots.get(i).getVaccine().getName().equals(vac_name)) { //added 'and' condition for vaccine name as well
                        System.out.println("Slot " + i + "-> Day: " + slots.get(i).getDay() + ", Available Qty: " + slots.get(i).getQuantity() + ", Vaccine: " + slots.get(i).getVaccine().getName());
                        temp_slot_id.add(i);
                        tempflag = 1;
                    }
                }
            }
            if (tempflag == 0) {
                System.out.println("No Slots Available");
            } else {
                System.out.print("Choose Slot: ");
                int slot_no = sc.nextInt();
                sc.nextLine(); // eat the buffer
                if (!temp_slot_id.contains(slot_no)) {
                    System.out.println("Slot Number Entered is not in the list shown!");
                    return;
                }

                //if everything is satisfied until now, then vaccinate him/her

                vaccine_taken = slots.get(slot_no).getVaccine();
                doses_taken++;
                slots.get(slot_no).decreaseQty(); //decrease slot quantity

                if (vaccine_taken.getDoses() == doses_taken) {
                    vaccination_status = "FULLY VACCINATED";
                } else {
                    vaccination_status = "PARTIALLY VACCINATED";
                    next_dose_date = slots.get(slot_no).getDay() + vaccine_taken.getGap(); // next date = current day + gap
                }

                System.out.println(name + " vaccinated with " + vaccine_taken.getName());

            }

            //slots.get(i).getVaccine().getName().equals(vac_name)
        } else if (vaccination_status.equals("PARTIALLY VACCINATED")) {

            ArrayList<Integer> temp_slot_id = new ArrayList<Integer>(); // to store eligible slot ids (for error checking)
            int tempflag = 0;
            for (int i = 0; i < slots.size(); ++i) {
                if (vac_name.equals("NULL")) {
                    if (slots.get(i).getHospital().getHospital_id() == hospital_id && slots.get(i).getQuantity() > 0 && slots.get(i).getDay() >= next_dose_date) {  //changed from == to >= as user can take vaccine on or after next dose date
                        System.out.println("Slot " + i + "-> Day: " + slots.get(i).getDay() + ",  Available Qty: " + slots.get(i).getQuantity() + ", Vaccine: " + slots.get(i).getVaccine().getName());
                        temp_slot_id.add(i);
                        tempflag = 1;
                    }
                } else {
                    if (slots.get(i).getHospital().getHospital_id() == hospital_id && slots.get(i).getQuantity() > 0 && slots.get(i).getVaccine().getName().equals(vac_name) && slots.get(i).getDay() >= next_dose_date) {  //changed from == to >= as user can take vaccine on or after next dose date
                        System.out.println("Slot " + i + "-> Day: " + slots.get(i).getDay() + ", Available Qty: " + slots.get(i).getQuantity() + ", Vaccine: " + slots.get(i).getVaccine().getName());
                        temp_slot_id.add(i);
                        tempflag = 1;
                    }

                }
            }
            if (tempflag == 0) {
                System.out.println("No Slots Available");
            } else {
                System.out.print("Choose Slot: ");
                int slot_no = sc.nextInt();
                sc.nextLine(); // eat the buffer
                if (!temp_slot_id.contains(slot_no)) {
                    System.out.println("Slot Number Entered is not in the list shown!");
                    return;
                }

                //if everything is satisfied until now, then vaccinate him/her
                if (!slots.get(slot_no).getVaccine().getName().equals(vaccine_taken.getName())) {
                    System.out.println("You have chosen a different vaccine than the previous vaccine. Vaccine Mixing is not allowed");
                    return;
                }
                //vaccine_taken = slots.get(slot_no).getVaccine();
                doses_taken++;
                slots.get(slot_no).decreaseQty(); //decrease slot quantity

                if (vaccine_taken.getDoses() == doses_taken) {
                    vaccination_status = "FULLY VACCINATED";
                } else {
                    vaccination_status = "PARTIALLY VACCINATED";
                    next_dose_date = slots.get(slot_no).getDay() + vaccine_taken.getGap(); // next date = current day + gap
                }

                System.out.println(name + " vaccinated with " + vaccine_taken.getName());

            }


        } else if (vaccination_status.equals("FULLY VACCINATED")) {
            System.out.println("You are Fully Vaccinated. You are not eligible for any slots.");
        }
    }

    public void bookSlot(ArrayList<Slot> slots, ArrayList<Hospital> hospitals, ArrayList<Vaccine> vaccines) {

        Scanner sc = new Scanner(System.in);
        System.out.println("1. Search by area");
        System.out.println("2. Search by Vaccine");
        System.out.println("3. Exit");
        System.out.print("Enter option: ");
        int choice = sc.nextInt();
        sc.nextLine(); // eat the buffer

        switch (choice) {
            case 1: {
                System.out.print("Enter PinCode: ");
                String pincode = sc.nextLine();
                int flag = 0;
                int hospital_id;
                ArrayList<Integer> temp_hospitals_id = new ArrayList<Integer>(); // to store eligible hospitals ids (for error checking)

                for (int i = 0; i < hospitals.size(); ++i) {
                    if (hospitals.get(i).getPincode().equals(pincode)) {
                        flag = 1;
                        System.out.println(hospitals.get(i).getHospital_id() + " " + hospitals.get(i).getName());
                        temp_hospitals_id.add(hospitals.get(i).getHospital_id());
                    }
                }
                if (flag == 0) {
                    System.out.println("No hospitals exist in the entered PinCode");
                    return;
                } else {
                    System.out.print("Enter Hospital ID: ");
                    hospital_id = sc.nextInt();
                    sc.nextLine(); // eat the buffer
                    if (!temp_hospitals_id.contains(hospital_id)) {
                        System.out.println("Hospital ID entered is not in the list shown!"); //error if entered hospital id is not in list shown
                        return;
                    }

                    getVaccinated(slots, hospitals, vaccines, hospital_id, "NULL");
                    // CODE from here is to check the available slots for the citizen according to eligibility etc


                    /*if (vaccination_status.equals("REGISTERED")) {
                        ArrayList<Integer> temp_slot_id = new ArrayList<Integer>(); // to store eligible slot ids (for error checking)
                        int tempflag = 0;
                        for (int i = 0; i < slots.size(); ++i) {
                            if (slots.get(i).getHospital().getHospital_id() == hospital_id && slots.get(i).getQuantity() > 0) {
                                System.out.println("Slot " + i + "-> Day: " + slots.get(i).getDay() + " ,Available Qty: " + slots.get(i).getQuantity() + " ,Vaccine: " + slots.get(i).getVaccine().getName());
                                temp_slot_id.add(i);
                                tempflag = 1;
                            }
                        }
                        if (tempflag == 0) {
                            System.out.println("No Slots Available");
                        } else {
                            System.out.print("Choose Slot: ");
                            int slot_no = sc.nextInt();
                            sc.nextLine(); // eat the buffer
                            if (!temp_slot_id.contains(slot_no)) {
                                System.out.println("Slot Number Entered is not in the list shown!");
                                return;
                            }

                            //if everything is satisfied until now, then vaccinate him/her

                            vaccine_taken = slots.get(slot_no).getVaccine();
                            doses_taken++;
                            slots.get(slot_no).decreaseQty(); //decrease slot quantity

                            if (vaccine_taken.getDoses() == doses_taken) {
                                vaccination_status = "FULLY VACCINATED";
                            } else {
                                vaccination_status = "PARTIALLY VACCINATED";
                                next_dose_date = slots.get(slot_no).getDay() + vaccine_taken.getGap(); // next date = current day + gap
                            }

                            System.out.println(name + " vaccinated with " + vaccine_taken.getName());

                        }

                    } else if (vaccination_status.equals("PARTIALLY VACCINATED")) {

                        ArrayList<Integer> temp_slot_id = new ArrayList<Integer>(); // to store eligible slot ids (for error checking)
                        int tempflag = 0;
                        for (int i = 0; i < slots.size(); ++i) {
                            if (slots.get(i).getHospital().getHospital_id() == hospital_id && slots.get(i).getQuantity() > 0 && slots.get(i).getDay() == next_dose_date) {
                                System.out.println("Slot " + i + "-> Day: " + slots.get(i).getDay() + " ,Available Qty: " + slots.get(i).getQuantity() + " ,Vaccine: " + slots.get(i).getVaccine().getName());
                                temp_slot_id.add(i);
                                tempflag = 1;
                            }
                        }
                        if (tempflag == 0) {
                            System.out.println("No Slots Available");
                        } else {
                            System.out.print("Choose Slot: ");
                            int slot_no = sc.nextInt();
                            sc.nextLine(); // eat the buffer
                            if (!temp_slot_id.contains(slot_no)) {
                                System.out.println("Slot Number Entered is not in the list shown!");
                                return;
                            }

                            //if everything is satisfied until now, then vaccinate him/her
                            if (!slots.get(slot_no).getVaccine().getName().equals(vaccine_taken.getName())) {
                                System.out.println("You have chosen a different vaccine than the previous vaccine. Vaccine Mixing is not allowed");
                                return;
                            }
                            //vaccine_taken = slots.get(slot_no).getVaccine();
                            doses_taken++;
                            slots.get(slot_no).decreaseQty(); //decrease slot quantity

                            if (vaccine_taken.getDoses() == doses_taken) {
                                vaccination_status = "FULLY VACCINATED";
                            } else {
                                vaccination_status = "PARTIALLY VACCINATED";
                                next_dose_date = slots.get(slot_no).getDay() + vaccine_taken.getGap(); // next date = current day + gap
                            }

                            System.out.println(name + " vaccinated with " + vaccine_taken.getName());

                        }


                    } else if (vaccination_status.equals("FULLY VACCINATED")) {
                        System.out.println("You are Fully Vaccinated. You are not eligible for any slots.");
                    }*/


                }

                break;
            }

            case 2: {
                System.out.print("Enter Vaccine Name: ");
                String vac_name = sc.nextLine();
                int flag = 0;
                int hospital_id;
                ArrayList<Integer> temp_hospitals_id = new ArrayList<Integer>(); // to store eligible hospitals ids (for error checking)

                for (int i = 0; i < vaccines.size(); ++i) {
                    if (vaccines.get(i).getName().equals(vac_name)) { // check if vaccine name entered is present
                        flag = 1;
                        if (vaccines.get(i).no_of_hospitals() == 0) {
                            System.out.println("Vaccine is Registered but currently, no Hospital has any slot");
                            return;
                        }
                        for (Hospital hos : vaccines.get(i).getHospitals()) {
                            System.out.println(hos.getHospital_id() + " " + hos.getName());
                            temp_hospitals_id.add(hos.getHospital_id());
                        }

                        break; //break from for loop if vaccine name is found
                    }
                }
                if (flag == 0) {
                    System.out.println("Vaccine Name does not exist in Database!");
                    return;
                } else {
                    System.out.print("Enter Hospital ID: ");
                    hospital_id = sc.nextInt();
                    sc.nextLine(); // eat the buffer
                    if (!temp_hospitals_id.contains(hospital_id)) {
                        System.out.println("Hospital ID entered is not in the list shown!"); //error if entered hospital id is not in list shown
                        return;
                    }

                    getVaccinated(slots, hospitals, vaccines, hospital_id, vac_name);
                }

                break;  //switch case break
            }
            case 3: {
                System.out.println("Exiting to Menu....");
                return;

            }
            default:{
                System.out.println("Invalid Choice Entered! Exiting to Menu Screen!");
            }

        }
    }

    public void displayDetails() {
        System.out.println("Citizen Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Unique ID: " + id);

        if (!isEligible()) {
            System.out.println("Only above 18 are allowed");
            return;
        }

    }
}
