package domain;

public class Baseline {
    //UserId for baseline
    private int userId;
    public int getUserId() {
        return userId;
    }
    //Baselines for each sensor
    private double waterFlowBaseline;
    public double getWaterFlowBaseline() {
        return waterFlowBaseline;
    }
    private double waterPressureBaseline;
    public double getWaterPressureBaseline() {
        return waterPressureBaseline;
    }
    private double currentBaseline;
    public double getCurrentBaseline() {
        return currentBaseline;
    }
    private double voltageBaseline;
    public double getVoltageBaseline() {
        return voltageBaseline;
    }

    //Constructor
    public Baseline(int userId, double waterFlowBaseline, double waterPressureBaseline, double voltageBaseline, double currentBaseline) {
        this.userId = userId;
        this.waterFlowBaseline = waterFlowBaseline;
        this.waterPressureBaseline = waterPressureBaseline;
        this.currentBaseline = currentBaseline;
        this.voltageBaseline = voltageBaseline;
    }
    
}
