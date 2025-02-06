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
        boolean hasQuit = false;
        while (!hasQuit) {
            String input = scanner.nextLine().trim();

            if ("bye".equalsIgnoreCase(input)) {
                System.out.println(DIVIDER);
                System.out.println("Take care of yourself💖\nCome back anytime you need a listening ear.");
                System.out.println(DIVIDER);
                hasQuit = true;
            }else if ("list".equalsIgnoreCase(input)) {
                showTaskList();
            } else if (input.startsWith("mark ")) {
                markTask(input);
            } else if (input.startsWith("unmark ")) {
                unmarkTask(input);
            }  else if (input.startsWith("todo ")) {
                addTask(new Todo(input.substring(5)));
            } else if (input.startsWith("deadline ")) {
                if (!input.matches("^deadline .+ /by .+$")) {
                    System.out.println(DIVIDER);
                    System.out.println("Sorry, please enter the correct format: 'deadline TASK_DESCRIPTION /by YOUR_DEADLINE'");
                    System.out.println(DIVIDER);
                } else {
                    String[] parts = input.substring(9).split(" /by ", 2);
                    Deadline deadline = new Deadline(parts[0], parts[1]);
                    addTask(deadline);
                }
            } else if (input.startsWith("event ")) {
                if (!input.matches("^event .+ /from .+ /to .+$")) {
                    System.out.println(DIVIDER);
                    System.out.println("Sorry, please enter the correct format: 'event TASK_DESCRIPTION /from START_TIME /to END_TIME'");
                    System.out.println(DIVIDER);
                } else {
                    String[] parts = input.substring(6).split(" /from | /to ", 3);
                    addTask(new Event(parts[0], parts[1], parts[2]));
                }
            }
            else{
                Task task = new Task(input);
                addTask(task);
            }
        }
        scanner.close();
    }

    /**
     * Adds a task to the task list.
     * Updates task count and prints confirmation message.
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
            System.out.println("Now you have " + taskCount + " tasks in the list.");
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
    private static void showTaskList() {
        System.out.println(DIVIDER);
        if (taskCount == 0) {
            System.out.println("Your task list is empty.\nWhat would you like to do? I'm always happy to help you record.");
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
