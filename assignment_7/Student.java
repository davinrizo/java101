import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Student {
    private String name;
    private int id;
    private int age;
    private String major;
    private Map<Course, String> grades; // Map to store grades for courses

    public Student(String name, int id, int age, String major) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.major = major;
        this.grades = new HashMap<>(); // Initialize the grades map
    }

    // Getters and setters
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return String.format("%s (ID: %d)", name, id);
    }

    public void enrollInCourse(Course course) {
        this.grades.put(course, null);
    }

    public void assignGrade(Course course, String grade) {
        this.grades.put(course, grade);
    }

    public Set<Course> getEnrolledCourses() {
        return grades.keySet();
    }

    public String getGrade(Course course) {
        return grades.get(course);
    }
}

