import java.util.Scanner;

public class Moli {
    private static final int MAX_TASKS = 100;
    private static final String[] tasks = new String[MAX_TASKS];
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
            } else {
                addTask(input);
            }
        }
        scanner.close();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task    The task to be added.
     */
    private static void addTask(String task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println(DIVIDER);
            System.out.println("Added: " + task + " ✅");
            System.out.println("I'm listening, tell me more.");
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
}
