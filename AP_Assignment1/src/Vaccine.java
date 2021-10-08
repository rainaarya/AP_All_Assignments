import java.util.ArrayList;

public class Vaccine {
    private String name;
    private int doses;
    private int gap;
    private ArrayList<Hospital> hospitalsContainingVaccine = new ArrayList<Hospital>();

    public Vaccine(String name, int doses, int gap) {
        this.name = name;
        this.doses = doses;
        this.gap = gap;
        this.displayDetails();
    }

    public void displayDetails() {
        System.out.println("Vaccine Name: " + name);
        System.out.println("Number of Doses: " + doses);
        System.out.println("Gap between Doses: " + gap);
    }

    public String getName() {

        return name;
    }

    public int getDoses() {
        return doses;
    }

    public int getGap() {
        return gap;
    }

    public int no_of_hospitals(){
        return hospitalsContainingVaccine.size();
    }

    public void addHospitalWithVaccine(Hospital hospital){
        if(!hospitalsContainingVaccine.contains(hospital)){
            hospitalsContainingVaccine.add(hospital);
        }
    }

    public ArrayList<Hospital> getHospitals(){
        return hospitalsContainingVaccine;
    }


}
