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

public class Storage {
    private static final String FILE_PATH = "./data/moli.txt";

    /**
     * Saves the task list to a file.
     *
     * @param tasks The list of tasks to save.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs(); // Create directory if it doesn't exist

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Task task : tasks) {
                    // Each task has a custom toFileFormat() method
                    writer.write(task.toFileFormat());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads the task list from a file.
     *
     * @return The list of tasks.
     */
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks; // Return empty list if file doesn't exist
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

    private static Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (type) {
                case "T":
                    // T | {1 or 0} | description
                    return new Todo(description, isDone);

                case "D": {
                    // D | {1 or 0} | description | 2023-05-15T14:00
                    LocalDateTime by = LocalDateTime.parse(parts[3]);
                    return new Deadline(description, by, isDone);
                }

                case "E": {
                    // E | {1 or 0} | description | 2023-05-15T14:00 | 2023-05-15T16:00
                    LocalDateTime from = LocalDateTime.parse(parts[3]);
                    LocalDateTime to   = LocalDateTime.parse(parts[4]);
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
