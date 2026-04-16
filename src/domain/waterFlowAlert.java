package domain;

public class waterFlowAlert extends Alert{

    public waterFlowAlert(String severity, double baseline, double reading) {
        super("Water Flow", severity, baseline, reading);
    }
    
    @Override
    public void findSeverity() {
        double dev = Math.abs(getReading() - getBaseline());

        double stdev = getBaseline() * 0.2;

        if (dev >= 3.5*stdev) {
            setSeverity("High");
        }
        else if (dev >= 2.5*stdev) {
            setSeverity("Medium");
        }
        else if (dev >= 1.5*stdev) {
            setSeverity("Low");
        }
        else {
            setSeverity("None");
        }

    }

}
