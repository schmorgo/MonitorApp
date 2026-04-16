package domain;

public class waterPressureAlert extends Alert{
    
    public waterPressureAlert(String severity, double baseline, double reading) {
        super("Water Pressure", severity, baseline, reading);
    }

    @Override
    public void findSeverity() {
        double dev = Math.abs(getReading() - getBaseline());

        double stdev = getBaseline() * 0.15;

        if (dev >= 3*stdev) {
            setSeverity("High");
        }
        else if (dev >= 2*stdev) {
            setSeverity("Medium");
        }
        else if (dev >= stdev) {
            setSeverity("Low");
        }
        else {
            setSeverity("None");
        }

    }

}


