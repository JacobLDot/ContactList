public class Mastery extends Person {
    private double mastery;

    public Mastery (String firstName, String lastName, String phoneNumber, double mastery) {
        super(firstName, lastName, phoneNumber);
        this.mastery = mastery;
    }

    public double getMastery() {
        return mastery;
    }

    public String toString() {
        return super.toString() + " Mastery: " + mastery;
    }
}
