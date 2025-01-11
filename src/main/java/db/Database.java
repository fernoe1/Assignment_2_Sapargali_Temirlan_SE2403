package db;

import db.interfaces.IDatabase;
import models.Employee;
import models.Student;

import java.sql.*;
import java.util.ArrayList;

public class Database implements IDatabase {
    private final String port;
    private final String host;
    private final String user;
    private final String password;
    private final String dbName;

    private Connection con = null;
    private PreparedStatement st = null;
    private ResultSet rs = null;

    public Database(String port, String host, String user, String password, String dbName) {
        this.port = port;
        this.host = host;
        this.user = user;
        this.password = password;
        this.dbName = dbName;
    }

    public static String getUrl(String port, String host, String dbName) {

        return "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
    }

    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(getUrl(port, host, dbName), user, password);
            System.out.println("Connection successful.");
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class loading error: " + e.getMessage());
        }
    }

    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            String sql = "SELECT name, surname, position, salary FROM employees";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String position = rs.getString("position");
                double salary = rs.getDouble("salary");

                Employee employee = new Employee(name, surname, position, salary);
                employees.add(employee);
            }
            st.close();
            rs.close();

            return employees;
        } catch(SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }

        return null;
    }

    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT name, surname, gpa FROM students";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                double gpa = rs.getDouble("gpa");

                Student student = new Student(name, surname, gpa);
                students.add(student);
            }
            st.close();
            rs.close();

            return students;
        } catch(SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }

        return null;
    }

    public void close() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("SQL error: " + e.getMessage());
            }
        }
    }
}
