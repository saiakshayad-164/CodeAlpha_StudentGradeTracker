import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentGradeTrackerGUI extends JFrame {

    private JTextField marksField;
    private JTextArea resultArea;
    private ArrayList<Integer> marks;
    private JLabel countLabel;

    public StudentGradeTrackerGUI() {
        setTitle("🎓 Student Grade Tracker");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        marks = new ArrayList<>();

        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 247, 250));

        // Title
        JLabel title = new JLabel("Student Grade Tracker");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(245, 247, 250));

        JLabel label = new JLabel("Enter Marks:");
        marksField = new JTextField(10);

        JButton addButton = new JButton("Add");
        JButton calcButton = new JButton("Calculate");
        JButton clearButton = new JButton("Clear");

        // Style Buttons
        addButton.setBackground(new Color(76, 175, 80));
        addButton.setForeground(Color.WHITE);

        calcButton.setBackground(new Color(33, 150, 243));
        calcButton.setForeground(Color.WHITE);

        clearButton.setBackground(new Color(244, 67, 54));
        clearButton.setForeground(Color.WHITE);

        inputPanel.add(label);
        inputPanel.add(marksField);
        inputPanel.add(addButton);
        inputPanel.add(calcButton);
        inputPanel.add(clearButton);

        // Count Label
        countLabel = new JLabel("Students added: 0");
        countLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Result Area
        resultArea = new JTextArea(15, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(resultArea);

        // Add components
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(inputPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(countLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(scroll);

        add(panel);

        // Add Button Action
        addButton.addActionListener(e -> {
            try {
                int mark = Integer.parseInt(marksField.getText());

                if (mark < 0 || mark > 100) {
                    JOptionPane.showMessageDialog(this, "Enter marks between 0-100");
                } else {
                    marks.add(mark);
                    resultArea.append("✔ Added: " + mark + "\n");
                    countLabel.setText("Students added: " + marks.size());
                    marksField.setText("");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!");
            }
        });

        // Calculate Button Action
        calcButton.addActionListener(e -> {
            if (marks.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No marks entered!");
                return;
            }

            int sum = 0;
            int max = marks.get(0);
            int min = marks.get(0);

            for (int m : marks) {
                sum += m;
                if (m > max) max = m;
                if (m < min) min = m;
            }

            double avg = (double) sum / marks.size();

            resultArea.append("\n📊 --- Report ---\n");
            resultArea.append("Marks: " + marks + "\n");
            resultArea.append("Average: " + avg + "\n");
            resultArea.append("Highest: " + max + "\n");
            resultArea.append("Lowest: " + min + "\n");

            resultArea.append("\nGrades:\n");
            for (int i = 0; i < marks.size(); i++) {
                int m = marks.get(i);
                char grade;

                if (m >= 90) grade = 'A';
                else if (m >= 75) grade = 'B';
                else if (m >= 50) grade = 'C';
                else grade = 'F';

                resultArea.append("Student " + (i + 1) + ": " + grade + "\n");
            }
        });

        // Clear Button Action
        clearButton.addActionListener(e -> {
            marks.clear();
            resultArea.setText("");
            countLabel.setText("Students added: 0");
        });
    }

    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        new StudentGradeTrackerGUI().setVisible(true);
    });
}