package dev.gabriel.reminder.projection;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "Reminders")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class ReminderProjection {
    @Id
    private String id;
    private String name;
    private String description;
    private Long recurrence;
    private Long maxRuns;
    private Long currentRuns;
    private Boolean started;
    private Instant lastRun;
    private String ownerId;

    public static ReminderProjection create(String id,
                                            String name,
                                            String description,
                                            Long recurrence,
                                            Long maxRuns,
                                            Long currentRuns,
                                            Boolean started,
                                            Instant lastRun,
                                            String ownerId
    ) {
        return new ReminderProjection(id, name, description, recurrence, maxRuns, currentRuns, started, lastRun, ownerId);
    }
}
