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
            JButton deleteButton = new JButton("Delete");
            JButton clearCompletedButton = new JButton("Clear Completed");
            completeButton.setEnabled(false);
            deleteButton.setEnabled(false);

            panel.add(textField);
            panel.add(addButton);
            panel.add(completeButton);
            panel.add(deleteButton);
            panel.add(clearCompletedButton);

            TaskManager taskManager = new TaskManager();
            TaskTableModel tableModel = new TaskTableModel(taskManager);
            JTable table = new JTable(tableModel);
            
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);
            
            table.getSelectionModel().addListSelectionListener(e -> {
                if (table.getSelectedRow() != -1) {
                    completeButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                } else {
                    completeButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                }
            });

            completeButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();
                
                if (selectedRow == -1) {
                    return;
                }

                Task task = taskManager.getTasks().get(selectedRow);
                int id = task.getId();

                taskManager.markTaskCompleted(id);
                tableModel.fireTableDataChanged();
            });

            deleteButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {
                    return;
                }

                Task task = taskManager.getTasks().get(selectedRow);
                int id = task.getId();

                taskManager.removeTaskById(id);
                tableModel.fireTableDataChanged();
            });

            clearCompletedButton.addActionListener(e -> {
                taskManager.removeCompletedTasks();
                tableModel.fireTableDataChanged();
            });

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