package db.interfaces;

import models.Employee;
import models.Student;

import java.util.ArrayList;

public interface IDatabase {
    void connect();

    void close();

    ArrayList<Employee> getEmployees();

    ArrayList<Student> getStudents();
}
