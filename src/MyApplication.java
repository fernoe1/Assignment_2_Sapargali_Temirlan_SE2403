import models.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MyApplication {
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();

        String employeeFile = "C:\\Users\\Berik\\IdeaProjects\\Assignment_2_Sapargali_Temirlan_SE2403\\src\\resources\\Employed.txt";
        String studentFile = "C:\\Users\\Berik\\IdeaProjects\\Assignment_2_Sapargali_Temirlan_SE2403\\src\\resources\\Students.txt";
        readEmployees(employeeFile, people);
        readStudents(studentFile, people);

        Collections.sort(people);
        ArrayList<Payable> payables = new ArrayList<>(people);
        printData(payables);
    }

    public static void readEmployees(String file, ArrayList<Person> people) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length > 4) {
                    String position = "";
                    for (int i = 2; i < parts.length - 1; i++) {
                        position += " " + parts[i];
                    }
                    position = position.trim();
                    Employee employee = new Employee(parts[0], parts[1], position, Double.parseDouble(parts[parts.length-1]));
                    people.add(employee);
                } else {
                    Employee employee = new Employee(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]));
                    people.add(employee);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void readStudents(String file, ArrayList<Person> people) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                Student student = new Student(parts[0], parts[1], Double.parseDouble(parts[2]));
                people.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printData(Iterable <Payable> payables) {
        for (Payable payable : payables) {
            System.out.println(payable + " earns " + payable.getPaymentAmount() + " tenge");
        }
    }
}
