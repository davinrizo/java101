import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    while (running) {
      System.out.println("\nStudent Management System");
      System.out.println("1. Add new student");
      System.out.println("2. Update student information");
      System.out.println("3. View student details");
      System.out.println("4. Exit");
      System.out.print("Choose an option: ");

      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
        StudentOperations.addStudent(scanner);
        break;
        case 2:
        StudentOperations.updateStudent(scanner);
        break;
        case 3:
        StudentOperations.viewStudentDetails(scanner);
        break;
        case 4:
        running = false;
        System.out.println("Exiting System...");
        break;
        default:
        System.out.println("Invalid option, please enter 1, 2, 3, or 4.");
        break;
      }
    }

    scanner.close();
  }
}

public class StudentOperations {
    private static List<Student> students = new ArrayList<>();
    private static int totalStudents = 0;

    public static void addStudent(Scanner scanner) {
        System.out.println("Enter Student Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Student ID:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Student Age:");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Student Grade:");
        double grade = Double.parseDouble(scanner.nextLine());

        students.add(new Student(id, name, age, grade));
        totalStudents++;
        System.out.println("Student successfully added! ");
    }

    public static void updateStudent(Scanner scanner) {
        System.out.println("Enter Student ID to update:");
        int id = Integer.parseInt(scanner.nextLine());
        for (Student student : students) {
            if (student.getId() == id) {
                System.out.println("Enter new name:");
                student.setName(scanner.nextLine());
                System.out.println("Enter new age:");
                student.setAge(Integer.parseInt(scanner.nextLine()));
                System.out.println("Enter new Grade:");
                student.setGrade(Double.parseDouble(scanner.nextLine()));
                System.out.println("Student updated successfully!");
                return;
            }
        }
        System.out.println("Student ID not found.");
    }

    public static void viewStudentDetails(Scanner scanner) {
        System.out.println("Enter Student ID to view:");
        int id = Integer.parseInt(scanner.nextLine());
        for (Student student : students) {
            if (student.getId() == id) {
                System.out.println("Name: " + student.getName());
                System.out.println("Age: " + student.getAge());
                System.out.println("Grade: " + student.getGrade());
                return;
            }
        }
        System.out.println("Student ID not found.");
    }
}

public class Student {
    private String name;
    private int id;
    private int age;
    private double grade;

  public Student(int id, String name,int age, double grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}


