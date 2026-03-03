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

            DefaultListModel<Task> model = new DefaultListModel<>();
            JList<Task> list = new JList<>(model);

            JTextField textField = new JTextField(30);
            JButton addButton = new JButton("Add");
            JButton completeButton = new JButton("Complete");

            panel.add(textField);
            panel.add(addButton);
            panel.add(completeButton);

            JScrollPane scrollPane = new JScrollPane(list);
            frame.add(scrollPane, BorderLayout.CENTER);

            TaskManager taskManager = new TaskManager();
            addButton.addActionListener(e -> {

                String title = textField.getText();
                title = title.trim();

                if (title.isEmpty()) {
                    return;
                }

                taskManager.addTask(title);
                
                model.clear();

                for (Task t : taskManager.getTasks()) {
                    model.addElement(t);
                }

                textField.setText("");

            });

            frame.setVisible(true);

        }); 
    }

}