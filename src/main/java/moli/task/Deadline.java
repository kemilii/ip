package moli.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task with an associated date/time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    // Formatter for output display (e.g., "May 15 2023, 2:00 PM")
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructs a new Deadline task.
     *
     * @param description The task description.
     * @param by          The deadline as a LocalDateTime.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new Deadline task with a specified completion status.
     *
     * @param description The task description.
     * @param by          The deadline as a LocalDateTime.
     * @param isDone      The completion status.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return The string with a "[D]" prefix and formatted deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Converts the Deadline into a file-friendly string.
     *
     * @return The file format string.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
