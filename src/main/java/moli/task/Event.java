package moli.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task with a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    // Formatter for output display (e.g., "May 15 2023, 2:00 PM")
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructs a new Event task.
     *
     * @param description The event description.
     * @param from        The start time as a LocalDateTime.
     * @param to          The end time as a LocalDateTime.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event task with a specified completion status.
     *
     * @param description The event description.
     * @param from        The start time as a LocalDateTime.
     * @param to          The end time as a LocalDateTime.
     * @param isDone      The completion status.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return The string with a "[E]" prefix and formatted start/end times.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(OUTPUT_FORMAT) + " to: "
                + to.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Converts the Event into a file-friendly string.
     *
     * @return The file format string.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + from + " | " + to;
    }
}
