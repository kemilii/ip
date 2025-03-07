package moli;

import java.util.Scanner;

/**
 * The Ui class handles all user interactions,
 * including displaying messages and reading user input.
 */
public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private final Scanner scanner;

    /**
     * Constructs a new Ui instance.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        final String LOGO = "\n"
                + "                ███╗   ███╗ ██████╗ ██╗     ██╗\n"
                + "                ████╗ ████║██╔═══██╗██║     ██║\n"
                + "                ██╔████╔██║██║   ██║██║     ██║\n"
                + "                ██║╚██╔╝██║██║   ██║██║     ██║\n"
                + "                ██║ ╚═╝ ██║╚██████╔╝███████╗██║\n"
                + "                ╚═╝     ╚═╝ ╚═════╝ ╚══════╝╚═╝";
        System.out.println(LOGO);
        showLine();
        System.out.println("Hello, dear one. I'm Moli.\nI'm here for you💙");
        showLine();
    }

    /**
     * Displays a horizontal divider.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Reads a line of user input.
     *
     * @return The trimmed user input.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Closes the Ui and releases resources.
     */
    public void close() {
        scanner.close();
    }
}
