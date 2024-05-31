import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystem extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private List<Student> students; // List to store student records
    private List<Course> courses; // List to store courses
    private Student studentToUpdate; // Variable to store the student being updated
    private Student selectedStudent; // Variable to store the selected student
    private JList<Student> studentList; // Reference to the student list for the assign grade panel
    private JComboBox<Course> courseComboBox; // Reference to the course combo box for the assign grade panel

    public StudentManagementSystem() {
        setTitle("Student Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        students = new ArrayList<>(); // Initialize the student list
        courses = new ArrayList<>(); // Initialize the course list
        populateDummyCourses(); // Populate the courses list with dummy data

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel homePanel = createHomePanel();
        JPanel addStudentPanel = createAddStudentPanel();
        JPanel updateStudentPanel = createUpdateStudentPanel();
        JPanel viewStudentPanel = createViewStudentPanel();
        JPanel enrollStudentPanel = createEnrollStudentPanel();
        JPanel assignGradePanel = createAssignGradePanel();

        cardPanel.add(homePanel, "Home");
        cardPanel.add(addStudentPanel, "AddStudent");
        cardPanel.add(updateStudentPanel, "UpdateStudent");
        cardPanel.add(viewStudentPanel, "ViewStudent");
        cardPanel.add(enrollStudentPanel, "EnrollStudent");
        cardPanel.add(assignGradePanel, "AssignGrade");

        add(cardPanel);
        setJMenuBar(createMenuBar());
    }

    private void populateDummyCourses() {
        courses.add(new Course("CS101", "Computer Science", 30));
        courses.add(new Course("MATH101", "Mathematics", 25));
        courses.add(new Course("PHY101", "Physics", 20));
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to the Student Management System", JLabel.CENTER);
        panel.add(welcomeLabel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createAddStudentPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("ID:"));
        JTextField idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Age:"));
        JTextField ageField = new JTextField();
        panel.add(ageField);

        panel.add(new JLabel("Major:"));
        JTextField majorField = new JTextField();
        panel.add(majorField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    int id = Integer.parseInt(idField.getText());
                    int age = Integer.parseInt(ageField.getText());
                    String major = majorField.getText();

                    // Add the new student to the list
                    students.add(new Student(name, id, age, major));
                    JOptionPane.showMessageDialog(null, "Student added successfully!");

                    // Clear the input fields
                    nameField.setText("");
                    idField.setText("");
                    ageField.setText("");
                    majorField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid input for ID and Age.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(addButton);

        return panel;
    }

    private JPanel createUpdateStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(searchField.getText());
                    studentToUpdate = searchStudentById(id);
                    if (studentToUpdate != null) {
                        // Populate fields with student data for update
                        nameField.setText(studentToUpdate.getName());
                        ageField.setText(String.valueOf(studentToUpdate.getAge()));
                        majorField.setText(studentToUpdate.getMajor());
                        updateButton.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(new JLabel("Enter Student ID:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JPanel updatePanel = new JPanel(new GridLayout(5, 2));
        updatePanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        updatePanel.add(nameField);

        updatePanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        updatePanel.add(ageField);

        updatePanel.add(new JLabel("Major:"));
        majorField = new JTextField();
        updatePanel.add(majorField);

        updateButton = new JButton("Update Student");
        updateButton.setEnabled(false); // Initially disabled
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String major = majorField.getText();

                    // Update the student's information
                    studentToUpdate.setName(name);
                    studentToUpdate.setAge(age);
                    studentToUpdate.setMajor(major);
                    JOptionPane.showMessageDialog(null, "Student updated successfully!");

                    // Clear the input fields
                    nameField.setText("");
                    ageField.setText("");
                    majorField.setText("");
                    updateButton.setEnabled(false);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid input for Age.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        updatePanel.add(updateButton);

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(updatePanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createViewStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"ID", "Name", "Age", "Major"};
        JTable table = new JTable(new Object[][]{}, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTableData(table);
            }
        });
        panel.add(refreshButton, BorderLayout.SOUTH);

        return panel;
    }

    private void updateTableData(JTable table) {
        String[] columnNames = {"ID", "Name", "Age", "Major"};
        Object[][] data = new Object[students.size()][4];
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            data[i][0] = student.getId();
            data[i][1] = student.getName();
            data[i][2] = student.getAge();
            data[i][3] = student.getMajor();
        }
        table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    private JPanel createEnrollStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JComboBox<Course> courseComboBox = new JComboBox<>(courses.toArray(new Course[0]));
        panel.add(new JLabel("Select Course:"), BorderLayout.NORTH);
        panel.add(courseComboBox, BorderLayout.CENTER);

        JButton enrollButton = new JButton("Enroll Student");
        enrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Course selectedCourse = (Course) courseComboBox.getSelectedItem();
                if (selectedCourse != null) {
                    JList<Student> studentList = new JList<>(students.toArray(new Student[0]));
                    int result = JOptionPane.showConfirmDialog(null, new JScrollPane(studentList), "Select Student to Enroll", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        Student selectedStudent = studentList.getSelectedValue();
                        if (selectedStudent != null) {
                            selectedStudent.enrollInCourse(selectedCourse);
                            JOptionPane.showMessageDialog(null, "Student enrolled in course successfully!");
                        } else {
                            JOptionPane.showMessageDialog(null, "No student selected.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No course selected.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(enrollButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createAssignGradePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        studentList = new JList<>(students.toArray(new Student[0]));
        panel.add(new JLabel("Select Student:"), BorderLayout.NORTH);
        panel.add(new JScrollPane(studentList), BorderLayout.CENTER);

        JPanel gradePanel = new JPanel(new GridLayout(3, 2));
        courseComboBox = new JComboBox<>();
        gradePanel.add(new JLabel("Select Course:"));
        gradePanel.add(courseComboBox);

        gradePanel.add(new JLabel("Assign Grade:"));
        JTextField gradeField = new JTextField();
        gradePanel.add(gradeField);

        JButton assignGradeButton = new JButton("Assign Grade");
        assignGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Course selectedCourse = (Course) courseComboBox.getSelectedItem();
                String grade = gradeField.getText();
                if (selectedStudent != null && selectedCourse != null && !grade.isEmpty()) {
                    selectedStudent.assignGrade(selectedCourse, grade);
                    JOptionPane.showMessageDialog(null, "Grade assigned successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a student, course, and enter a grade.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        studentList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedStudent = studentList.getSelectedValue(); // Update the instance variable
                courseComboBox.removeAllItems();
                if (selectedStudent != null) {
                    for (Course course : selectedStudent.getEnrolledCourses()) {
                        courseComboBox.addItem(course);
                    }
                }
            }
        });

        panel.add(gradePanel, BorderLayout.SOUTH);
        panel.add(assignGradeButton, BorderLayout.SOUTH);

        return panel;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu studentMenu = new JMenu("Student");
        JMenuItem addStudentMenuItem = new JMenuItem("Add Student");
        addStudentMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "AddStudent");
            }
        });
        studentMenu.add(addStudentMenuItem);

        JMenuItem updateStudentMenuItem = new JMenuItem("Update Student");
        updateStudentMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "UpdateStudent");
            }
        });
        studentMenu.add(updateStudentMenuItem);

        JMenuItem viewStudentMenuItem = new JMenuItem("View Students");
        viewStudentMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "ViewStudent");
                updateTableData((JTable) ((JScrollPane) ((JPanel) cardPanel.getComponent(3)).getComponent(0)).getViewport().getView());
            }
        });
        studentMenu.add(viewStudentMenuItem);

        JMenuItem enrollStudentMenuItem = new JMenuItem("Enroll Student in Course");
        enrollStudentMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "EnrollStudent");
            }
        });
        studentMenu.add(enrollStudentMenuItem);

        JMenuItem assignGradeMenuItem = new JMenuItem("Assign Grade");
        assignGradeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "AssignGrade");
                // Refresh the student list in the AssignGrade panel
                studentList.setListData(students.toArray(new Student[0]));
            }
        });
        studentMenu.add(assignGradeMenuItem);

        menuBar.add(studentMenu);

        return menuBar;
    }

    private Student searchStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentManagementSystem().setVisible(true);
            }
        });
    }

    // Variables to be used across methods
    private JTextField nameField;
    private JTextField ageField;
    private JTextField majorField;
    private JButton updateButton;
}

