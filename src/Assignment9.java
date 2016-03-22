/**
 * @author Michael Palmer
 * Object-Oriented Design (CSCI 5335 A)
 * March 13, 2016
 * Assignment 9 - Iterator pattern
 */
import java.util.ArrayList;
import java.util.Iterator;

public class Assignment9 {
    public static void main(String[] args) {
        // Create customer list as an array
        Customer[] customerList = new Customer[4];
        customerList[0] = new Customer("John Doe", "Male", 23, 7);
        customerList[1] = new Customer("Jane Doe", "Female", 22, 19);

        // Create employee list as an ArrayList
        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Andrew Smith", "Male", 35, "New Hire"));
        employeeList.add(new Employee("Brittany Jordan", "Female", 42, "Manager"));

        // Output customer list
        System.out.println("CUSTOMER LIST:");
        print(new CustomerIterator(customerList));

        // Output employee list
        System.out.println("\nEMPLOYEE LIST:");
        print(new EmployeeIterator(employeeList));
    }

    /**
     * Iterate through the provided Iterator and output its items
     * @param iterator Iterator to process
     */
    private static void print(Iterator iterator) {
        // Loop through the iterator
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

/**
 * Person class
 */
abstract class Person {
    protected String name, gender;
    protected int age;

    /**
     * Person constructor
     * @param name Name of the person
     * @param gender Person gender (Male|Female)
     * @param age Age of the person
     */
    public Person(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    /**
     * Return the person's name
     * @return Name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Return the person's gender
     * @return Gender of the person
     */
    public String getGender() {
        return gender;
    }

    /**
     * Return the person's age
     * @return Age of the person
     */
    public int getAge() {
        return age;
    }

    /**
     * Return the person information in string format
     * @return String of person information
     */
    public String toString() {
        return String.format("Name: %s, Gender: %s, Age: %d", this.getName(), this.getGender(), this.getAge());
    }
}

/**
 * Customer class
 */
class Customer extends Person {
    private int visits;

    /**
     * Customer constructor
     * @param name Customer name
     * @param gender Customer gender (Male|Female)
     * @param age Customer age
     * @param visits Number of times the customer has visited the store
     */
    public Customer(String name, String gender, int age, int visits) {
        super(name, gender, age);
        this.visits = visits;
    }

    /**
     * Return number of times the customer visited the store
     * @return Visit count
     */
    public int getVisits() {
        return visits;
    }

    /**
     * Return customer information in a string format
     * @return String of customer information
     */
    public String toString() {
        return String.format("%s, Visits: %d", super.toString(), this.getVisits());
    }
}

/**
 * Employee class
 */
class Employee extends Person {
    private String title;

    /**
     * Employee constructor
     * @param name Employee name
     * @param gender Employee gender (Male|Female)
     * @param age Employee age
     * @param title Employee title
     */
    public Employee(String name, String gender, int age, String title) {
        super(name, gender, age);
        this.title = title;
    }

    /**
     * Return the employee's title
     * @return Title of the employee
     */
    public String getTitle() {
        return title;
    }

    /**
     * Return employee information in a string format
     * @return String of employee information
     */
    public String toString() {
        return String.format("%s, Title: %s", super.toString(), this.getTitle());
    }
}

/**
 * Customer Iterator class
 */
class CustomerIterator implements Iterator {
    Customer[] customers;
    int position = 0;

    /**
     * Create a new instance of the customer iterator
     * @param customers Array of Customer objects
     */
    public CustomerIterator(Customer[] customers) {
        this.customers = customers;
    }

    /**
     * Determine if there is another item to iterate through
     * @return true/false
     */
    public boolean hasNext() {
        return (position < customers.length && customers[position] != null);
    }

    /**
     * Go to the next item in the iterator
     * @return Customer object
     */
    public Customer next() {
        return customers[position++];
    }
}

/**
 * Employee Iterator class
 */
class EmployeeIterator implements Iterator {
    ArrayList<Employee> employees;
    int position = 0;

    /**
     * Create a new instance of the employee iterator
     * @param employees ArrayList of Employee objects
     */
    public EmployeeIterator(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    /**
     * Determine if there is another item to iterate through
     * @return true/false
     */
    public boolean hasNext() {
        return position < employees.size();
    }

    /**
     * Go to the next item in the iterator
     * @return Employee object
     */
    public Employee next() {
        return employees.get(position++);
    }
}
