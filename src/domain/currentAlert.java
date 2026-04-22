package domain;

public class CurrentAlert extends Alert{

    //Constructor
    public CurrentAlert(String severity, double baseline, double reading) {
        super("Current", severity, baseline, reading);
        findSeverity();
    }  
    //Find severity based on the deviation from the baseline, with thresholds at 0.5, 1, and 2 standard deviations, where stdev is 10% of the baseline.
    @Override
    public void findSeverity() {
        //Calculate the deviation from the baseline
        double dev = Math.abs(getReading() - getBaseline());

        //Assume standard deviation is 10% of the baseline
        double stdev = getBaseline() * 0.1;

        //Correspond each severity with a threshold
        if (dev >= 2*stdev) {
            setSeverity("HIGH");
        }
        else if (dev >= stdev) {
            setSeverity("MEDIUM");
        }
        else if (dev >= 0.5*stdev) {
            setSeverity("LOW");
        }
        else {
            setSeverity("NORMAL");
        }

    }
}
