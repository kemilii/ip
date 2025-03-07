package moli;

import java.util.ArrayList;
import moli.task.Task;

public class Moli {
    private Ui ui;
    private TaskList taskList;
    private boolean hasQuit;

    public Moli() {
        ui = new Ui();
        // Load tasks from file
        ArrayList<Task> loadedTasks = Storage.loadTasks();
        taskList = new TaskList(loadedTasks);
        hasQuit = false;
    }

    /**
     * Starts the chatbot.
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
        // Save tasks on exit
        Storage.saveTasks(taskList.getAllTasks());
        ui.close();
    }

    public static void main(String[] args) {
        new Moli().run();
    }
}
