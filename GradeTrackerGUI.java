import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

class Student {
    private String name;
    private String id;
    private String email;
    private Map<String, Integer> marks;

    public Student(String name, String id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.marks = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getMarks() {
        return marks;
    }

    public void addMark(String subject, int mark) {
        marks.put(subject, mark);
    }
}

public class GradeTrackerGUI {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JTextArea marksDisplay;
    private Map<String, Student> students;
    private JTextField studentField, subjectField, marksField;
    private Student currentStudent;

    public GradeTrackerGUI() {
        frame = new JFrame("Grade Tracker");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        students = new HashMap<>();
        students.put("yash", new Student("Yash", "009", "yashkakkar@gmail.com"));
        students.put("shree", new Student("Shree", "001", "shree@gmail.com"));

        createLoginPanel();
        createTeacherPanel();

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void createLoginPanel() {
        JPanel loginPanel = new JPanel(null);
        loginPanel.setBackground(Color.DARK_GRAY);

        JLabel titleLabel = new JLabel("GRADE TRACKER", SwingConstants.CENTER);
        titleLabel.setBounds(250, 50, 300, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(250, 120, 100, 25);
        emailLabel.setForeground(Color.WHITE);
        JTextField emailField = new JTextField();
        emailField.setBounds(350, 120, 200, 25);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(250, 170, 100, 25);
        roleLabel.setForeground(Color.WHITE);
        String[] roles = {"Teacher", "Student"};
        JComboBox<String> roleBox = new JComboBox<>(roles);
        roleBox.setBounds(350, 170, 200, 25);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(350, 220, 100, 30);
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String role = (String) roleBox.getSelectedItem();

            if (role.equals("Teacher")) {
                cardLayout.show(mainPanel, "Teacher");
            } else {
                JOptionPane.showMessageDialog(frame, "Student Dashboard is under construction!");
            }
        });

        loginPanel.add(titleLabel);
        loginPanel.add(emailLabel);
        loginPanel.add(emailField);
        loginPanel.add(roleLabel);
        loginPanel.add(roleBox);
        loginPanel.add(loginButton);
        mainPanel.add(loginPanel, "Login");
    }

    private void createTeacherPanel() {
        JPanel teacherPanel = new JPanel(null);
        teacherPanel.setBackground(Color.DARK_GRAY);

        JLabel titleLabel = new JLabel("Teacher Dashboard", SwingConstants.CENTER);
        titleLabel.setBounds(250, 20, 300, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);

        JLabel studentLabel = new JLabel("Student Name:");
        studentLabel.setBounds(50, 80, 100, 25);
        studentLabel.setForeground(Color.WHITE);
        studentField = new JTextField();
        studentField.setBounds(160, 80, 150, 25);

        JLabel subjectLabel = new JLabel("Subject:");
        subjectLabel.setBounds(50, 120, 100, 25);
        subjectLabel.setForeground(Color.WHITE);
        subjectField = new JTextField();
        subjectField.setBounds(160, 120, 150, 25);

        JLabel marksLabel = new JLabel("Marks:");
        marksLabel.setBounds(50, 160, 100, 25);
        marksLabel.setForeground(Color.WHITE);
        marksField = new JTextField();
        marksField.setBounds(160, 160, 150, 25);

        JButton addButton = new JButton("Add Marks");
        addButton.setBounds(330, 140, 120, 30);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentName = studentField.getText().trim().toLowerCase();
                String subject = subjectField.getText().trim();
                String marksText = marksField.getText().trim();

                if (!students.containsKey(studentName)) {
                    JOptionPane.showMessageDialog(frame, "Student not found!");
                    return;
                }

                currentStudent = students.get(studentName);

                try {
                    int marks = Integer.parseInt(marksText);
                    currentStudent.addMark(subject, marks);
                    updateMarksDisplay(currentStudent);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Enter valid marks!");
                }
            }
        });

        marksDisplay = new JTextArea();
        marksDisplay.setBounds(50, 210, 400, 150);
        marksDisplay.setEditable(false);
        marksDisplay.setBackground(Color.LIGHT_GRAY);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(50, 380, 100, 30);
        logoutButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));

        teacherPanel.add(titleLabel);
        teacherPanel.add(studentLabel);
        teacherPanel.add(studentField);
        teacherPanel.add(subjectLabel);
        teacherPanel.add(subjectField);
        teacherPanel.add(marksLabel);
        teacherPanel.add(marksField);
        teacherPanel.add(addButton);
        teacherPanel.add(marksDisplay);
        teacherPanel.add(logoutButton);
        mainPanel.add(teacherPanel, "Teacher");
    }

    private void updateMarksDisplay(Student student) {
        StringBuilder displayText = new StringBuilder("Marks for " + student.getName() + ":\n");

        int totalMarks = 0, count = 0;
        for (Map.Entry<String, Integer> entry : student.getMarks().entrySet()) {
            displayText.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            totalMarks += entry.getValue();
            count++;
        }

        if (count > 0) {
            double avg = (double) totalMarks / count;
            displayText.append("\nAverage Marks: ").append(avg);
        }

        marksDisplay.setText(displayText.toString());
    }

    public static void main(String[] args) {
        new GradeTrackerGUI();
    }
}
