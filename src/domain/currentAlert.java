package domain;

public class currentAlert extends Alert{

    public currentAlert(String severity, double baseline, double reading) {
        super("Current", severity, baseline, reading);
        findSeverity();
    }  

    @Override
    public void findSeverity() {
        double dev = Math.abs(getReading() - getBaseline());

        double stdev = getBaseline() * 0.1;

        if (dev >= 2*stdev) {
            setSeverity("High");
        }
        else if (dev >= stdev) {
            setSeverity("Medium");
        }
        else if (dev >= 0.5*stdev) {
            setSeverity("Low");
        }
        else {
            setSeverity("None");
        }

    }
}
