package domain;

public class waterPressureAlert extends Alert{
    
    //Constructor
    public waterPressureAlert(String severity, double baseline, double reading) {
        super("Water Pressure", severity, baseline, reading);
        findSeverity();
    }

    //Find severity based on the deviation from the baseline, with thresholds at 1, 2, and 3 standard deviations, where stdev is 15% of the baseline.
    @Override
    public void findSeverity() {
        //Calculate the deviation from the baseline
        double dev = Math.abs(getReading() - getBaseline());

        //Assume standard deviation is 15% of the baseline
        double stdev = getBaseline() * 0.15;

        //Correspond each severity with a threshold
        if (dev >= 3*stdev) {
            setSeverity("HIGH");
        }
        else if (dev >= 2*stdev) {
            setSeverity("MEDIUM");
        }
        else if (dev >= stdev) {
            setSeverity("LOW");
        }
        else {
            setSeverity("NORMAL");
        }

    }

}


