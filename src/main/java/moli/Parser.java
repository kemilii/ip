package moli;

import moli.task.Deadline;
import moli.task.Event;
import moli.task.Task;
import moli.task.Todo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The Parser class interprets user commands and executes corresponding actions.
 * It interacts with the TaskList and Ui to perform operations on tasks.
 */
public class Parser {
    // Expected input format: "yyyy-MM-dd HHmm" (e.g., "2023-05-15 1400")
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Processes the user command.
     *
     * @param input The full user command.
     * @param tasks The TaskList instance.
     * @param ui    The Ui instance for messages.
     * @return true if the command is "bye" (exit), false otherwise.
     * @throws MoliException if the command is unrecognized or improperly formatted.
     */
    public static boolean handleCommand(String input, TaskList tasks, Ui ui)
            throws MoliException {
        if ("bye".equalsIgnoreCase(input)) {
            ui.showLine();
            ui.showMessage("Take care of yourself!\nCome back anytime you need a listening ear.");
            ui.showLine();
            return true;
        } else if ("list".equalsIgnoreCase(input)) {
            showTaskList(tasks, ui);
            return false;
        } else if (input.startsWith("mark ")) {
            markTask(input, tasks, ui);
            return false;
        } else if (input.startsWith("unmark ")) {
            unmarkTask(input, tasks, ui);
            return false;
        } else if (input.startsWith("delete ")) {
            deleteTask(input, tasks, ui);
            return false;
        } else if (input.startsWith("todo")) {
            addTodo(input, tasks, ui);
            return false;
        } else if (input.startsWith("deadline")) {
            addDeadline(input, tasks, ui);
            return false;
        } else if (input.startsWith("event")) {
            addEvent(input, tasks, ui);
            return false;
        } else if (input.startsWith("find ")) {
            findTask(input, tasks, ui);
            return false;
        } else {
            throw new MoliException("Sorry. I didn't understand that command.");
        }
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The TaskList instance.
     * @param ui    The Ui instance.
     */
    private static void showTaskList(TaskList tasks, Ui ui) {
        ui.showLine();
        if (tasks.isEmpty()) {
            ui.showMessage("Your task list is empty.\nWhat would you like to do? I'm always happy to help you record.");
        } else {
            ui.showMessage("Here’s what you’ve told me so far:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + tasks.getTask(i));
            }
        }
        ui.showLine();
    }

    /**
     * Marks a task as done.
     *
     * @param input The user command.
     * @param tasks The TaskList instance.
     * @param ui    The Ui instance.
     * @throws MoliException if the task number is invalid.
     */
    private static void markTask(String input, TaskList tasks, Ui ui) throws MoliException {
        try {
            int index = Integer.parseInt(input.substring(5)) - 1;
            tasks.markTask(index);
            ui.showLine();
            ui.showMessage("Nice! I've marked this task as done:");
            ui.showMessage("  " + tasks.getTask(index));
            ui.showMessage("Well done! I'm so proud of you!");
            ui.showLine();
        } catch (NumberFormatException e) {
            throw new MoliException("Please enter a valid task number to mark as done.");
        }
    }

    /**
     * Marks a task as not done.
     *
     * @param input The user command.
     * @param tasks The TaskList instance.
     * @param ui    The Ui instance.
     * @throws MoliException if the task number is invalid.
     */
    private static void unmarkTask(String input, TaskList tasks, Ui ui) throws MoliException {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            tasks.unmarkTask(index);
            ui.showLine();
            ui.showMessage("Okay, I've marked this task as not done yet:");
            ui.showMessage("  " + tasks.getTask(index));
            ui.showMessage("But I believe you will finish it very soon!");
            ui.showLine();
        } catch (NumberFormatException e) {
            throw new MoliException("Please enter a valid task number to unmark.");
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param input The user command.
     * @param tasks The TaskList instance.
     * @param ui    The Ui instance.
     * @throws MoliException if the task number is invalid.
     */
    private static void deleteTask(String input, TaskList tasks, Ui ui) throws MoliException {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            Task removedTask = tasks.deleteTask(index);
            ui.showLine();
            ui.showMessage("Noted. I've removed this task:");
            ui.showMessage("  " + removedTask);
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            ui.showLine();
        } catch (NumberFormatException e) {
            throw new MoliException("Please enter a valid task number to delete.");
        }
    }

    /**
     * Finds and displays tasks containing the given keyword.
     *
     * @param input The user command.
     * @param tasks The TaskList instance.
     * @param ui    The Ui instance.
     * @throws MoliException if the search keyword is missing.
     */
    private static void findTask(String input, TaskList tasks, Ui ui) throws MoliException {
        if (input.length() <= 5) {
            throw new MoliException("Please provide a keyword to search for.");
        }
        String keyword = input.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new MoliException("The search keyword cannot be empty.");
        }

        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        ui.showLine();
        if (matchingTasks.isEmpty()) {
            ui.showMessage("No matching tasks found.");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + matchingTasks.get(i));
            }
        }
        ui.showLine();
    }

    /**
     * Adds a new Todo task.
     *
     * @param input The user command.
     * @param tasks The TaskList instance.
     * @param ui    The Ui instance.
     * @throws MoliException if the Todo description is missing.
     */
    private static void addTodo(String input, TaskList tasks, Ui ui) throws MoliException {
        if (input.length() <= 4) {
            throw new MoliException("Sorry. A todo task must have a description, I'm here to listen.");
        }
        Todo todo = new Todo(input.substring(5).trim());
        tasks.addTask(todo);
        ui.showLine();
        ui.showMessage("Added: " + todo + " ✅");
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }

    /**
     * Adds a new Deadline task.
     *
     * @param input The user command.
     * @param tasks The TaskList instance.
     * @param ui    The Ui instance.
     * @throws MoliException if the format is incorrect or the date/time is invalid.
     */
    private static void addDeadline(String input, TaskList tasks, Ui ui) throws MoliException {
        if (!input.matches("^deadline .+ /by .+$")) {
            throw new MoliException("Sorry, please enter the correct format:\n"
                    + "deadline TASK_DESCRIPTION /by yyyy-MM-dd HHmm\n"
                    + "e.g., deadline return book /by 2023-05-15 1400");
        }
        String[] parts = input.substring(9).split(" /by ", 2);
        String description = parts[0].trim();
        String dateTimeStr = parts[1].trim();
        try {
            LocalDateTime by = LocalDateTime.parse(dateTimeStr, INPUT_FORMAT);
            Deadline deadline = new Deadline(description, by);
            tasks.addTask(deadline);
            ui.showLine();
            ui.showMessage("Added: " + deadline + " ✅");
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            ui.showLine();
        } catch (DateTimeParseException e) {
            throw new MoliException("Invalid date/time format.\n"
                    + "Please use 'yyyy-MM-dd HHmm' e.g., 2023-05-15 1400");
        }
    }

    /**
     * Adds a new Event task.
     *
     * @param input The user command.
     * @param tasks The TaskList instance.
     * @param ui    The Ui instance.
     * @throws MoliException if the format is incorrect or the date/time is invalid.
     */
    private static void addEvent(String input, TaskList tasks, Ui ui) throws MoliException {
        if (!input.matches("^event .+ /from .+ /to .+$")) {
            throw new MoliException("Sorry, please enter the correct format:\n"
                    + "event TASK_DESCRIPTION /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm\n"
                    + "e.g., event project meeting /from 2023-05-15 1400 /to 2023-05-15 1600");
        }
        String[] parts = input.substring(6).split(" /from | /to ", 3);
        String description = parts[0].trim();
        String fromStr = parts[1].trim();
        String toStr = parts[2].trim();
        try {
            LocalDateTime from = LocalDateTime.parse(fromStr, INPUT_FORMAT);
            LocalDateTime to = LocalDateTime.parse(toStr, INPUT_FORMAT);
            Event event = new Event(description, from, to);
            tasks.addTask(event);
            ui.showLine();
            ui.showMessage("Added: " + event + " ✅");
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            ui.showLine();
        } catch (DateTimeParseException e) {
            throw new MoliException("Invalid date/time format.\n"
                    + "Please use 'yyyy-MM-dd HHmm' e.g., 2023-05-15 1400");
        }
    }
}
