package moli;

import moli.task.Task;
import moli.task.Todo;
import moli.task.Deadline;
import moli.task.Event;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles saving and loading tasks to and from a file.
 * The tasks are stored in a file at the relative path "./data/moli.txt".
 */
public class Storage {
    private static final String FILE_PATH = "./data/moli.txt";

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks to save.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs(); // Create directory if necessary
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Task task : tasks) {
                    writer.write(task.toFileFormat());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads the list of tasks from the file.
     *
     * @return An ArrayList of tasks.
     */
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return tasks;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Parses a line from the file into a Task object.
     * Expected formats:
     * Todo:     T | {1 or 0} | description
     * Deadline: D | {1 or 0} | description | 2023-05-15T14:00
     * Event:    E | {1 or 0} | description | 2023-05-15T14:00 | 2023-05-15T16:00
     *
     * @param line The line to parse.
     * @return A Task object, or null if the line is invalid.
     */
    private static Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (type) {
                case "T":
                    return new Todo(description, isDone);
                case "D": {
                    LocalDateTime by = LocalDateTime.parse(parts[3]);
                    return new Deadline(description, by, isDone);
                }
                case "E": {
                    LocalDateTime from = LocalDateTime.parse(parts[3]);
                    LocalDateTime to = LocalDateTime.parse(parts[4]);
                    return new Event(description, from, to, isDone);
                }
                default:
                    System.out.println("Corrupted task entry: " + line);
                    return null;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date/time: " + e.getMessage());
            System.out.println("Line: " + line);
            return null;
        } catch (Exception e) {
            System.out.println("Error reading task line: " + line);
            return null;
        }
    }
}
