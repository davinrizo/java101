import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class AdminInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Add Course");
            System.out.println("2. Enroll Student");
            System.out.println("3. Assign Grade");
            System.out.println("4. Calculate Overall Grade");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                  // Add a new course
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();
                    System.out.print("Enter Course Name: ");
                    String courseName = scanner.nextLine();
                    System.out.print("Enter Maximum Capacity: ");
                    int maxCapacity = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    CourseManagement.addCourse(courseCode, courseName, maxCapacity);
                    System.out.println("Course added successfully.");
                    
                    for(Course element: CourseManagement.getCourses()){
                    System.out.println(element);
                    }
                    break;
                case 2:
                    // Enroll a student in a course
                    System.out.print("Enter Student Name: ");
                    String studentName = scanner.nextLine();

                    System.out.print("Enter Student ID: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();  // Consume the leftover newline

                    Student student = new Student(studentName, studentId);
                    CourseManagement.getStudents().add(student);

                    System.out.print("Enter Course Code to Enroll In: ");
                    String enrollCourseCode = scanner.nextLine();
                    Course enrollCourse = CourseManagement.getCourses().stream()
                            .filter(c -> c.getCourseCode().equals(enrollCourseCode))
                            .findFirst()
                            .orElse(null);

                    if (enrollCourse != null) {
                        CourseManagement.enrollStudent(student, enrollCourse);
                        System.out.println("Student enrolled successfully.");

                        // Print the list of enrolled courses for the student
                        System.out.println("Enrolled Courses for Student " + student.getName() + ":");
                        for (Course course : student.getEnrolledCourses().keySet()) {
                            System.out.println(course);
                        }
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 3:
                    // Assign a grade to a student
                    System.out.print("Enter Student ID: ");
                    int gradeStudentId = scanner.nextInt();
                    scanner.nextLine();  // Consume the leftover newline

                    Student gradeStudent = CourseManagement.getStudents().stream()
                            .filter(s -> s.getId() == gradeStudentId)
                            .findFirst()
                            .orElse(null);

                    if (gradeStudent != null) {
                        System.out.print("Enter Course Code: ");
                        String gradeCourseCode = scanner.nextLine();
                        Course gradeCourse = CourseManagement.getCourses().stream()
                                .filter(c -> c.getCourseCode().equals(gradeCourseCode))
                                .findFirst()
                                .orElse(null);

                        if (gradeCourse != null) {
                            System.out.print("Enter Grade: ");
                            int grade = scanner.nextInt();
                            scanner.nextLine();  // Consume the leftover newline

                            CourseManagement.assignGrade(gradeStudent, gradeCourse, grade);
                            System.out.println("Grade assigned successfully.");

                            // Print the grades for the student
                            System.out.println("Grades for Student " + gradeStudent.getName() + ":");
                            gradeStudent.getEnrolledCourses().forEach((course, assignedGrade) -> {
                                System.out.println("Course: " + course + ", Grade: " + assignedGrade);
                            });
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    // Calculate the overall grade for a student
                    System.out.print("Enter Student ID: ");
                    int calcStudentId = scanner.nextInt();
                    scanner.nextLine();  // Consume the leftover newline

                    Student calcStudent = CourseManagement.getStudents().stream()
                            .filter(s -> s.getId() == calcStudentId)
                            .findFirst()
                            .orElse(null);

                    if (calcStudent != null) {
                        CourseManagement.calculateOverallGrade(calcStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }
}


public class Student {
    // Private instance variables to store student information
    private String name;
    private int id;
    private Map<Course, Integer> enrolledCourses; // Maps courses to their respective grades

    // Constructor to initialize a Student object with name and id
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.enrolledCourses = new HashMap<>(); // Initialize with an empty HashMap for enrolled courses
    }

    // Getter method for student's name
    public String getName() {
        return name;
    }

    // Setter method for student's name
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for student's ID
    public int getId() {
        return id;
    }

    // Setter method for student's ID
    public void setId(int id) {
        this.id = id;
    }

    // Method to enroll the student in a course
    // Adds the course to the student's enrolledCourses map with a null grade initially
    public void enrollInCourse(Course course) {
        this.enrolledCourses.put(course, null);
    }

    // Method to assign a grade to the student for a specific course
    // Updates the student's grade for the course in the enrolledCourses map
    public void assignGrade(Course course, int grade) {
        if (this.enrolledCourses.containsKey(course)) {
            this.enrolledCourses.put(course, grade);
        }
    }

    // Getter method to retrieve the student's enrolled courses and their grades
    public Map<Course, Integer> getEnrolledCourses() {
        return enrolledCourses;
    }
}


public class Course {
    // Private instance variables to store course information
    private String courseCode;
    private String name;
    private int maxCapacity;
    private int enrolledStudents; // Number of students currently enrolled in the course
    private static int totalEnrollment = 0; // Total number of students enrolled across all courses

    // Constructor to initialize a Course object with course code, name, and maximum capacity
    public Course(String courseCode, String name, int maxCapacity) {
        this.courseCode = courseCode;
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = 0; // Initialize with zero enrolled students
    }

    // Getter method for course code
    public String getCourseCode() {
        return courseCode;
    }

    // Getter method for course name
    public String getName() {
        return name;
    }

    // Getter method for maximum capacity of the course
    public int getMaxCapacity() {
        return maxCapacity;
    }

    // Getter method for the number of students currently enrolled in the course
    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    // Static method to retrieve the total number of students enrolled across all courses
    public static int getTotalEnrollment() {
        return totalEnrollment;
    }

    // Method to enroll a student in the course
    // Increments the number of enrolled students if the capacity is not exceeded
    public boolean enrollStudent() {
        if (this.enrolledStudents < this.maxCapacity) {
            this.enrolledStudents++;
            totalEnrollment++;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + ", Name: " + name + ", Capacity: " + maxCapacity + ", Enrolled: " + enrolledStudents;
    }
}


public class CourseManagement {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    public static void addCourse(String courseCode, String name, int maxCapacity) {
        courses.add(new Course(courseCode, name, maxCapacity));
    }

    public static void enrollStudent(Student student, Course course) {
        if (course.enrollStudent()) {
            student.enrollInCourse(course);
        } else {
            System.out.println("Course has reached maximum capacity.");
        }
    }

    public static void assignGrade(Student student, Course course, int grade) {
        student.assignGrade(course, grade);
    }

    public static void calculateOverallGrade(Student student) {
        Map<Course, Integer> grades = student.getEnrolledCourses();
        double sum = 0;
        int count = 0;
        for (Integer grade : grades.values()) {
            if (grade != null) {
                sum += grade;
                count++;
            }
        }
        double average = (count > 0) ? sum / count : 0;
        System.out.println("Overall Grade for Student ID " + student.getId() + ": " + average);
    }

    public static List<Course> getCourses() {
        return courses;
    }

    public static List<Student> getStudents() {
        return students;
    }
}
