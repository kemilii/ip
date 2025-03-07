package moli;

import java.util.ArrayList;
import moli.task.Task;

/**
 * The main class for the Moli chatbot.
 * Loads tasks from storage, starts the chatbot loop, and saves tasks upon exit.
 */
public class Moli {
    private Ui ui;
    private TaskList taskList;
    private boolean hasQuit;

    /**
     * Constructs a Moli instance.
     * Loads tasks from the storage file and initializes the user interface.
     */
    public Moli() {
        ui = new Ui();
        ArrayList<Task> loadedTasks = Storage.loadTasks();
        taskList = new TaskList(loadedTasks);
        hasQuit = false;
    }

    /**
     * Starts the chatbot loop.
     * Reads user commands and executes corresponding actions until the user exits.
     */
    public void run() {
        ui.showWelcome();
        while (!hasQuit) {
            String input = ui.readCommand();
            try {
                hasQuit = Parser.handleCommand(input, taskList, ui);
            } catch (MoliException e) {
                ui.showLine();
                ui.showMessage(e.getMessage());
                ui.showLine();
            }
        }
        Storage.saveTasks(taskList.getAllTasks());
        ui.close();
    }

    /**
     * Main method to launch the chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Moli().run();
    }
}
