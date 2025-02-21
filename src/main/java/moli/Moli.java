package moli;

import moli.task.Task;
import moli.task.Todo;
import moli.task.Deadline;
import moli.task.Event;

import java.util.ArrayList;
import java.util.Scanner;

public class Moli {
    private static final int MAX_TASKS = 100;
    private static final ArrayList<Task> tasks = Storage.loadTasks(); // Load tasks from file
    private static final String DIVIDER = "____________________________________________________________";

    public static void main(String[] args) {
        final String LOGO = "\n"
                + "                ███╗   ███╗ ██████╗ ██╗     ██╗\n"
                + "                ████╗ ████║██╔═══██╗██║     ██║\n"
                + "                ██╔████╔██║██║   ██║██║     ██║\n"
                + "                ██║╚██╔╝██║██║   ██║██║     ██║\n"
                + "                ██║ ╚═╝ ██║╚██████╔╝███████╗██║\n"
                + "                ╚═╝     ╚═╝ ╚═════╝ ╚══════╝╚═╝";

        System.out.println(LOGO);
        System.out.println(DIVIDER);
        System.out.println("Hello, dear one. I'm Moli.\nI'm here for you💙");
        System.out.println(DIVIDER);

        Scanner scanner = new Scanner(System.in);
        boolean hasQuit = false;
        while (!hasQuit) {
            String input = scanner.nextLine().trim();
            try {
                if ("bye".equalsIgnoreCase(input)) {
                    System.out.println(DIVIDER);
                    System.out.println("Take care of yourself💖\nCome back anytime you need a listening ear.");
                    System.out.println(DIVIDER);
                    hasQuit = true;
                } else if ("list".equalsIgnoreCase(input)) {
                    showTaskList();
                } else if (input.startsWith("mark ")) {
                    markTask(input);
                } else if (input.startsWith("unmark ")) {
                    unmarkTask(input);
                } else if (input.startsWith("delete ")) {
                    deleteTask(input);
                } else if (input.startsWith("todo")) {
                    if (input.length() <= 4) {
                        throw new MoliException("Sorry. A todo task must have a description, I'm here to listen.");
                    }
                    addTask(new Todo(input.substring(5).trim()));
                } else if (input.startsWith("deadline")) {
                    if (!input.matches("^deadline .+ /by .+$")) {
                        throw new MoliException("Sorry, please enter the correct format: 'deadline TASK_DESCRIPTION /by DEADLINE_DATE'");
                    }
                    String[] parts = input.substring(9).split(" /by ", 2);
                    addTask(new Deadline(parts[0].trim(), parts[1].trim()));
                } else if (input.startsWith("event")) {
                    if (!input.matches("^event .+ /from .+ /to .+$")) {
                        throw new MoliException("Sorry, please enter the correct format: 'event TASK_DESCRIPTION /from START_TIME /to END_TIME'");
                    }
                    String[] parts = input.substring(6).split(" /from | /to ", 3);
                    addTask(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                } else {
                    throw new MoliException("Sorry. I didn't understand that command.");
                }
            } catch (MoliException e) {
                System.out.println(DIVIDER);
                System.out.println(e.getMessage());
                System.out.println(DIVIDER);
            }
        }
        Storage.saveTasks(tasks); // Save tasks on exit
        scanner.close();
    }

    /**
     * Adds a task to the task list.
     * Updates task count and prints confirmation message.
     *
     * @param task The task to be added.
     */
    private static void addTask(Task task) {
        if (tasks.size() < MAX_TASKS){
            tasks.add(task);
            System.out.println(DIVIDER);
            System.out.println("Added: " + task + " ✅");
            System.out.println("I'll help you keep them safely, remember to come back and mark it :)");
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(DIVIDER);
        } else {
            System.out.println(DIVIDER);
            System.out.println("Oh no! Sorry I can only remember up to " + MAX_TASKS + " tasks :(");
            System.out.println(DIVIDER);
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param input The user input command.
     */
    private static void deleteTask(String input) {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task removedTask = tasks.remove(index);
                System.out.println(DIVIDER);
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + removedTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println(DIVIDER);
            } else {
                System.out.println(DIVIDER);
                System.out.println("Oops! I can't find that task. Please enter a valid number.");
                System.out.println(DIVIDER);
            }
        } catch (NumberFormatException e) {
            System.out.println(DIVIDER);
            System.out.println("Please enter a valid task number to delete.");
            System.out.println(DIVIDER);
        }
    }

    /**
     * Displays the list of stored tasks.
     */
    private static void showTaskList() {
        System.out.println(DIVIDER);
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.\nWhat would you like to do? I'm always happy to help you record.");
        } else {
            System.out.println("Here’s what you’ve told me so far: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println(DIVIDER);
    }

    /**
     * Marks a task as done.
     *
     * @param input The user input command.
     */
    private static void markTask(String input) {
        try {
            int index = Integer.parseInt(input.substring(5)) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).markAsDone();
                System.out.println(DIVIDER);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(index));
                System.out.println("Well done! I'm so proud of you!");
                System.out.println(DIVIDER);
            } else {
                System.out.println(DIVIDER);
                System.out.println("Oops! I can't find that task. Please enter a valid number.");
                System.out.println(DIVIDER);
            }
        } catch (NumberFormatException e) {
            System.out.println(DIVIDER);
            System.out.println("Please enter a valid task number to mark as done.");
            System.out.println(DIVIDER);
        }
    }

    /**
     * Marks a task as not done.
     *
     * @param input The user input command.
     */
    private static void unmarkTask(String input) {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).markAsNotDone();
                System.out.println(DIVIDER);
                System.out.println("Okay, I've marked this task as not done yet:");
                System.out.println("  " + tasks.get(index));
                System.out.println("But I believe you will finish it very soon!");
                System.out.println(DIVIDER);
            } else {
                System.out.println(DIVIDER);
                System.out.println("Oops! I can't find that task. Please enter a valid number.");
                System.out.println(DIVIDER);
            }
        } catch (NumberFormatException e) {
            System.out.println(DIVIDER);
            System.out.println("Please enter a valid task number to unmark.");
            System.out.println(DIVIDER);
        }
    }

}
