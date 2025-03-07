package moli;

import moli.task.Task;
import java.util.ArrayList;

/**
 * TaskList manages the collection of tasks.
 * Provides methods to add, delete, mark, unmark, and retrieve tasks.
 */
public class TaskList {
    private static final int MAX_TASKS = 100;
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with the loaded tasks.
     *
     * @param loadedTasks The tasks loaded from storage; if null, an empty list is used.
     */
    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = (loadedTasks != null) ? loadedTasks : new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     * @throws MoliException If the maximum number of tasks is reached.
     */
    public void addTask(Task task) throws MoliException {
        if (tasks.size() >= MAX_TASKS) {
            throw new MoliException("Oh no! Sorry I can only remember up to " + MAX_TASKS + " tasks :(");
        }
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The 0-based index of the task to delete.
     * @return The removed task.
     * @throws MoliException If the index is invalid.
     */
    public Task deleteTask(int index) throws MoliException {
        if (index < 0 || index >= tasks.size()) {
            throw new MoliException("Oops! I can't find that task. Please enter a valid number.");
        }
        return tasks.remove(index);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The 0-based index of the task to mark.
     * @throws MoliException If the index is invalid.
     */
    public void markTask(int index) throws MoliException {
        if (index < 0 || index >= tasks.size()) {
            throw new MoliException("Oops! I can't find that task. Please enter a valid number.");
        }
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The 0-based index of the task to unmark.
     * @throws MoliException If the index is invalid.
     */
    public void unmarkTask(int index) throws MoliException {
        if (index < 0 || index >= tasks.size()) {
            throw new MoliException("Oops! I can't find that task. Please enter a valid number.");
        }
        tasks.get(index).markAsNotDone();
    }

    /**
     * Returns all tasks in the list.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if there are no tasks, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the number of tasks.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The 0-based index of the task.
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }
}
