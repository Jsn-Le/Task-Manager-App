import java.awt.BorderLayout;
import javax.swing.*;

public class Main {

    public static void main(String[] args) { 
        SwingUtilities.invokeLater(() -> {
        
            JFrame frame = new JFrame("Task Manager");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 800);

            JPanel panel = new JPanel();
            frame.add(panel, BorderLayout.NORTH);

            JTextField textField = new JTextField(30);
            JButton addButton = new JButton("Add");
            JButton completeButton = new JButton("Complete");

            panel.add(textField);
            panel.add(addButton);
            panel.add(completeButton);

            TaskManager taskManager = new TaskManager();
            TaskTableModel tableModel = new TaskTableModel(taskManager);
            JTable table = new JTable(tableModel);
            
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);
            
            addButton.addActionListener(e -> {

                String title = textField.getText();
                title = title.trim();

                if (title.isEmpty()) {
                    return;
                }

                taskManager.addTask(title);
                tableModel.fireTableDataChanged();

                textField.setText("");

            });

            frame.setVisible(true);

        }); 
    }

}