package domain;

public class WaterFlowAlert extends Alert{

    //Constructor
    public WaterFlowAlert(String severity, double baseline, double reading) {
        super("Water Flow", severity, baseline, reading);
        findSeverity();
    }
    //Find severity based on the deviation from the baseline, with thresholds at 1.5, 2.5, and 3.5 standard deviations, where stdev is 20% of the baseline.
    @Override
    public void findSeverity() {
        //Calculate the deviation from the baseline
        double dev = Math.abs(getReading() - getBaseline());

        //Assume standard deviation is 20% of the baseline
        double stdev = getBaseline() * 0.2;

        //Correspond each severity with a threshold
        if (dev >= 3.5*stdev) {
            setSeverity("HIGH");
        }
        else if (dev >= 2.5*stdev) {
            setSeverity("MEDIUM");
        }
        else if (dev >= 1.5*stdev) {
            setSeverity("LOW");
        }
        else {
            setSeverity("NORMAL");
        }

    }

}
