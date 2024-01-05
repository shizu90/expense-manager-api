package dev.gabriel.reminder.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.gabriel.shared.projection.AggregateRootProjection;
import dev.gabriel.user.projection.UserProjection;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "reminders")
@Getter
@Setter
public class ReminderProjection extends AggregateRootProjection {
    @Id
    private String id;
    private String name;
    private String description;
    private Long recurrence;
    @Column(name = "max_runs")
    private Long maxRuns;
    @Column(name = "current_runs")
    private Long currentRuns;
    @Column(name = "is_running")
    private Boolean isRunning;
    @Column(name = "last_run")
    private Instant lastRun;
    @ManyToOne
    @JsonIgnore
    private UserProjection owner;

    public ReminderProjection(String id, String name, String description, Long recurrence, Long maxRuns, Long currentRuns, Boolean isRunning, Instant lastRun, UserProjection user) {
        super(Instant.now(), Instant.now(), false);
        this.id = id;
        this.name = name;
        this.description = description;
        this.recurrence = recurrence;
        this.maxRuns = maxRuns;
        this.currentRuns = currentRuns;
        this.isRunning = isRunning;
        this.lastRun = lastRun;
        this.owner = user;
    }
}
