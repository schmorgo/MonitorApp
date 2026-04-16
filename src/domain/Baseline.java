package domain;

public class Baseline {
    private int userId;
    public int getUserId() {
        return userId;
    }
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

    public Baseline(int userId, double waterFlowBaseline, double waterPressureBaseline, double voltageBaseline, double currentBaseline) {
        this.userId = userId;
        this.waterFlowBaseline = waterFlowBaseline;
        this.waterPressureBaseline = waterPressureBaseline;
        this.currentBaseline = currentBaseline;
        this.voltageBaseline = voltageBaseline;
    }
    
}
