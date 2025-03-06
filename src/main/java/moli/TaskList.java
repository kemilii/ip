package moli;

import moli.task.Task;
import java.util.ArrayList;

public class TaskList {
    private static final int MAX_TASKS = 100;
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> loadedTasks) {
        // Use loaded tasks if available, otherwise empty list
        this.tasks = loadedTasks != null ? loadedTasks : new ArrayList<>();
    }

    /**
     * Adds a new task to the list if under MAX_TASKS.
     *
     * @param task The task to add.
     * @throws MoliException If max tasks reached.
     */
    public void addTask(Task task) throws MoliException {
        if (tasks.size() >= MAX_TASKS) {
            throw new MoliException("Oh no! Sorry I can only remember up to " + MAX_TASKS + " tasks :(");
        }
        tasks.add(task);
    }

    /**
     * Deletes the task at the given index.
     *
     * @param index Index of task to delete (0-based).
     * @return The removed task.
     * @throws MoliException If index is out of range.
     */
    public Task deleteTask(int index) throws MoliException {
        if (index < 0 || index >= tasks.size()) {
            throw new MoliException("Oops! I can't find that task. Please enter a valid number.");
        }
        return tasks.remove(index);
    }

    /**
     * Marks the task at the given index as done.
     */
    public void markTask(int index) throws MoliException {
        if (index < 0 || index >= tasks.size()) {
            throw new MoliException("Oops! I can't find that task. Please enter a valid number.");
        }
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the given index as not done.
     */
    public void unmarkTask(int index) throws MoliException {
        if (index < 0 || index >= tasks.size()) {
            throw new MoliException("Oops! I can't find that task. Please enter a valid number.");
        }
        tasks.get(index).markAsNotDone();
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }
}
