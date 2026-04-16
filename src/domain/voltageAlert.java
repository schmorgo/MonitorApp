package domain;

public class voltageAlert extends Alert{

    public voltageAlert(String severity, double baseline, double reading) {
        super("Voltage", severity, baseline, reading);
    }
    
    @Override
    public void findSeverity() {
        double dev = Math.abs(getReading() - getBaseline());

        double stdev = getBaseline() * 0.05;

        if (dev >= 2.5*stdev) {
            setSeverity("High");
        }
        else if (dev >= 1.5*stdev) {
            setSeverity("Medium");
        }
        else if (dev >= 0.75*stdev) {
            setSeverity("Low");
        }
        else {
            setSeverity("None");
        }

    }

}
