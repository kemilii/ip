package moli.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    // We'll only store a DateTimeFormatter for output
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    // Overloaded constructor for loading from file
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        // Example: "May 15 2023, 2:00 PM"
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileFormat() {
        // Example: "D | 0 | return book | 2023-05-15T14:00"
        return "D | " + (isDone ? "1" : "0") + " | "
                + description + " | " + by; // or by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}