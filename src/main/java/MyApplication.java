import db.Database;

import models.Person;
import models.interfaces.Payable;

import java.util.ArrayList;
import java.util.Collections;

public class MyApplication {
    public static void main(String[] args) {
        Database db = new Database("1987", "localhost", "postgres", "130924", "simpledb");
        db.connect();
        ArrayList<Person> people = new ArrayList<>();

        people.addAll(db.getEmployees());
        people.addAll(db.getStudents());
        db.close();
        Collections.sort(people);
        printData(people);
    }

    public static void printData(Iterable<? extends Payable> payables) {
        for (Payable payable : payables) {
            System.out.println(payable + " earns " + payable.getPaymentAmount() + " tenge.");
        }
    }
}