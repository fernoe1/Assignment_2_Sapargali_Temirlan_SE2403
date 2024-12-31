package models;

public class Student extends Person {
    private double gpa;

    public Student(String name, String surname, double gpa) {
        super(name, surname);
        setGpa(gpa);
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public String getPosition() {
        return "Student";
    }

    @Override
    public String toString() {
        return getPosition() + ": " + super.toString();
    }

    @Override
    public double getPaymentAmount(){
        if (gpa >= 3.67) {
            return 54205;
        }
        else if (gpa >= 2.33) {
            return 47135;
        } else {
            return 0;
        }
    }

    @Override
    public int compareTo(Person o) {
        if (this.getPaymentAmount() > o.getPaymentAmount()) {
            return 1;
        } else if (this.getPaymentAmount() < o.getPaymentAmount()) {
            return -1;
        } else {
            return 0;
        }
    }
}
