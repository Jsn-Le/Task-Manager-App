public class Task {

    private final int id;
    private String title;
    private boolean completed;

    public Task (int id, String title) {
        this.id = id;
        this.title = title;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void markCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        String status;
        
        if (completed) {
            status = "[x]";   
        } else {
            status = "[]";
        }
        return status + " " + id + " - " + title;
    }

}
