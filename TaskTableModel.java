import javax.swing.table.AbstractTableModel;

public class TaskTableModel extends AbstractTableModel {

     private final TaskManager taskManager;

    public TaskTableModel(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return taskManager.getTasks().size();
    }

    @Override
    public String getColumnName(int column) {
        return switch(column) {
            case 0 -> "Completed";
            case 1 -> "ID";
            case 2 -> "Title";
            default -> "";
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task task = taskManager.getTasks().get(rowIndex);

        return switch(columnIndex) {
            case 0 -> task.isCompleted();
            case 1 -> task.getId();
            case 2 -> task.getTitle();
            default -> null;
        };
    }

}
