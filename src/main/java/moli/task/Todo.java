package moli.task;

/**
 * Represents a Todo task.
 * A Todo task does not have any associated date/time.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo with the specified description.
     *
     * @param description The task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new Todo with the specified description and completion status.
     *
     * @param description The task description.
     * @param isDone      The completion status.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the Todo.
     *
     * @return The string prefixed with "[T]".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the Todo into a file-friendly string.
     *
     * @return The file format string.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
