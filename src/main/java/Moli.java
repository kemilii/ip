import java.util.Scanner;

public class Moli {
    private static final int MAX_TASKS = 100;
    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    final static String DIVIDER = "____________________________________________________________";

    /**
     * Main method that starts the chatbot.
     */
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
        boolean quit = false;
        while (!quit) {
            String input = scanner.nextLine().trim();

            if ("bye".equalsIgnoreCase(input)) {
                System.out.println(DIVIDER);
                System.out.println("Take care of yourself💖\nCome back anytime you need a listening ear.");
                System.out.println(DIVIDER);
                quit = true;
            }else if ("list".equalsIgnoreCase(input)) {
                displayTasks();
            } else if (input.startsWith("mark ")) {
                markTask(input);
            } else if (input.startsWith("unmark ")) {
                unmarkTask(input);
            } else {
                Task task = new Task(input);
                addTask(task);
            }
        }
        scanner.close();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task    The task to be added.
     */
    private static void addTask(Task task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println(DIVIDER);
            System.out.println("Added: " + task + " ✅");
            System.out.println("I'll help you keep them safely, remember to come back and mark it :)");
            System.out.println(DIVIDER);
        } else {
            System.out.println(DIVIDER);
            System.out.println("Oh no! Sorry I can only remember up to " + MAX_TASKS + " tasks:(");
            System.out.println(DIVIDER);
        }
    }

    /**
     * Displays the list of stored tasks.
     */
    private static void displayTasks() {
        System.out.println(DIVIDER);
        if (taskCount == 0) {
            System.out.println("Your task list is empty.\n What would you like to do? I'm always happy to help you record.");
        } else {
            System.out.println("Here’s what you’ve told me so far: ");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
        System.out.println(DIVIDER);
    }

    /**
     * Marks a task as done.
     *
     * @param input The user input command (e.g., "mark 2").
     */
    private static void markTask(String input) {
        try {
            int index = Integer.parseInt(input.substring(5)) - 1;
            if (index >= 0 && index < taskCount) {
                tasks[index].markAsDone();
                System.out.println(DIVIDER);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[index]);
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
     * @param input The user input command (e.g., "unmark 2").
     */
    private static void unmarkTask(String input) {
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (index >= 0 && index < taskCount) {
                tasks[index].markAsNotDone();
                System.out.println(DIVIDER);
                System.out.println("Okay, I've marked this task as not done yet:");
                System.out.println("  " + tasks[index]);
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
