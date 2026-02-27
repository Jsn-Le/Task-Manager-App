import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public Task addTask(String title) {

        Task t = new Task(nextId, title);
        tasks.add(t);
        nextId++;
        return t;

    }

    public List<Task> getTasks() {

        return new ArrayList<>(tasks);

    }

    private Task findTaskById(int id) {

        for (Task t : tasks) {

            if (t.getId() == id) {
                return t;
            }

        }

        return null;

    }

    public Task getTaskById(int id) {

        return findTaskById(id);

    }

    public Task updateTaskById(int id, String title) {

        Task t = findTaskById(id);
        if (t == null) {
            return null;
        }
        t.setTitle(title);

        return t;

    }

    public Task markTaskCompleted(int id) {

        Task t = findTaskById(id);
        if (t == null) {
            return null;
        }
        t.markCompleted();

        return t;

    }

    public boolean removeTaskById(int id) {

        Task t = findTaskById(id);
        if (t == null) {
            return false;
        }
        tasks.remove(t);

        return true;

    }

    public boolean removeCompletedTasks() {

        return tasks.removeIf(t -> t.isCompleted());

    }

}
