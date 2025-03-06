package moli;

import moli.task.Deadline;
import moli.task.Event;
import moli.task.Task;
import moli.task.Todo;

public class Parser {

    /**
     * Processes the user input and executes the corresponding command.
     *
     * @param input The full user command.
     * @param tasks The TaskList managing tasks.
     * @param ui    The Ui for displaying messages.
     * @return True if the user typed "bye" (indicating exit), false otherwise.
     * @throws MoliException If an invalid command or format is detected.
     */
    public static boolean handleCommand(String input, TaskList tasks, Ui ui) throws MoliException {
        if ("bye".equalsIgnoreCase(input)) {
            ui.showLine();
            ui.showMessage("Take care of yourself💖\nCome back anytime you need a listening ear.");
            ui.showLine();
            return true; // signals to exit

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

        } else {
            throw new MoliException("Sorry. I didn't understand that command.");
        }
    }

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

    private static void addDeadline(String input, TaskList tasks, Ui ui) throws MoliException {
        if (!input.matches("^deadline .+ /by .+$")) {
            throw new MoliException("Sorry, please enter the correct format: 'deadline TASK_DESCRIPTION /by DEADLINE_DATE'");
        }
        String[] parts = input.substring(9).split(" /by ", 2);
        Deadline deadline = new Deadline(parts[0].trim(), parts[1].trim());
        tasks.addTask(deadline);
        ui.showLine();
        ui.showMessage("Added: " + deadline + " ✅");
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }

    private static void addEvent(String input, TaskList tasks, Ui ui) throws MoliException {
        if (!input.matches("^event .+ /from .+ /to .+$")) {
            throw new MoliException("Sorry, please enter the correct format: 'event TASK_DESCRIPTION /from START_TIME /to END_TIME'");
        }
        String[] parts = input.substring(6).split(" /from | /to ", 3);
        Event event = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        tasks.addTask(event);
        ui.showLine();
        ui.showMessage("Added: " + event + " ✅");
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }
}
