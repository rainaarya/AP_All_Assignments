public class Slot {

    private int day;
    private Vaccine vaccine;
    private int quantity;
    private Hospital hospital;

    public Slot(Hospital hospital, int day, int quantity, Vaccine vaccine) {
        this.hospital = hospital;
        this.day = day;
        this.quantity = quantity;
        this.vaccine = vaccine;
        this.printSlotDetails();
    }

    public void printSlotDetails() {
        System.out.println("Slot added by Hospital " + hospital.getHospital_id() + " for Day: " + day);
        System.out.println("Available Quantity: " + quantity);
        System.out.println("Vaccine Name: " + vaccine.getName());
    }

    public void printSlotHospital() {
        System.out.println("Day: " + day + " Vaccine: " + vaccine.getName() + " Available Qty: " + quantity);

    }

    public void decreaseQty() {
        --quantity;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public int getQuantity() {
        return quantity;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public int getDay() {
        return day;
    }

}
