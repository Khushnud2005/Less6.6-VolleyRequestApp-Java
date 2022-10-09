package uz.example.less66_volleyrequestapp_java.model;

public class Employee {
    private int id;
    private String name;
    private int salary;
    private int age;

    public Employee(String name, int salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }
    public Employee(int id, String name, int salary, int age) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String get_name() {
        return name;
    }

    public int get_salary() {
        return salary;
    }

    public int get_age() {
        return age;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public void set_salary(int salary) {
        this.salary = salary;
    }

    public void set_age(int age) {
        this.age = age;
    }
}
