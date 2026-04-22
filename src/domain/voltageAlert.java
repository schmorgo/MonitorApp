package domain;

public class VoltageAlert extends Alert{

    //Constructor
    public VoltageAlert(String severity, double baseline, double reading) {
        super("Voltage", severity, baseline, reading);
        findSeverity();
    }
    
    //Find severity based on the deviation from the baseline, with thresholds at 0.75, 1.5, and 2.5 standard deviations, where stdev is 5% of the baseline.
    @Override
    public void findSeverity() {
        //Calculate the deviation from the baseline
        double dev = Math.abs(getReading() - getBaseline());

        //Assume standard deviation is 5% of the baseline
        double stdev = getBaseline() * 0.05;

        //Correspond each severity with a threshold
        if (dev >= 2.5*stdev) {
            setSeverity("HIGH");
        }
        else if (dev >= 1.5*stdev) {
            setSeverity("MEDIUM");
        }
        else if (dev >= 0.75*stdev) {
            setSeverity("LOW");
        }
        else {
            setSeverity("NORMAL");
        }

    }

}
