package domain;
import java.time.*;

public class Alert {
    private LocalDateTime time;
    public LocalDateTime getTime() {
        return time;
    }

    private String sensor;
    public String getSensor() {
        return sensor;
    }

    private String severity;
    public String getSeverity() {
        return severity;
    }

    public Alert(String sensor, String severity) {
        this.time = LocalDateTime.now();
        this.sensor = sensor;
        this.severity = severity;
    }
}
