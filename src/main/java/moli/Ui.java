package moli;

import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message.
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
     * Prints a horizontal divider line.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Reads and returns the next line of user input.
     *
     * @return The user's input as a trimmed string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints a given message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Closes the Scanner (call this before exiting the program).
     */
    public void close() {
        scanner.close();
    }
}
