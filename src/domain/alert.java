package domain;
import java.time.*;

public abstract class Alert {
    //Baseline
    private double baseline;
    public double getBaseline() {
        return baseline;
    }

    //Reading
    private double reading;
    public double getReading() {
        return reading;
    }

    //Time
    private LocalDateTime time;
    public LocalDateTime getTime() {
        return time;
    }

    //Sensor
    private String sensor;
    public String getSensor() {
        return sensor;
    }

    //Severity
    private String severity;
    public String getSeverity() {
        return severity;
    }
    //Set severity to the severity level based on find severity in the daughter classes
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    //Placeholder findSeverity for daughter classes to override
    public abstract void findSeverity();

    //Constructor
    public Alert(String sensor, String severity, double baseline, double reading) {
        this.time = LocalDateTime.now();
        this.sensor = sensor;
        this.severity = severity;
        this.baseline = baseline;
        this.reading = reading;
    }

}
