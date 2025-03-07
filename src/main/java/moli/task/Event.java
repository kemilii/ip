package moli.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        // Example: "May 15 2023, 2:00 PM"
        return "[E]" + super.toString() + " (from: "
                + from.format(OUTPUT_FORMAT)
                + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileFormat() {
        // Example: "E | 0 | project meeting | 2023-05-15T14:00 | 2023-05-15T16:00"
        return "E | " + (isDone ? "1" : "0") + " | "
                + description + " | " + from + " | " + to;
    }
}