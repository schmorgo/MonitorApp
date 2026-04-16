package domain;
import java.time.*;

public abstract class Alert {
    private double baseline;
    public double getBaseline() {
        return baseline;
    }

    private double reading;
    public double getReading() {
        return reading;
    }

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
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public abstract void findSeverity();

    public Alert(String sensor, String severity, double baseline, double reading) {
        this.time = LocalDateTime.now();
        this.sensor = sensor;
        this.severity = severity;
        this.baseline = baseline;
        this.reading = reading;
    }

}
