package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import ui.ViewApp;
import data.Storage;
import data.AlertStorage;
import domain.Alert;
import domain.CurrentAlert;
import domain.VoltageAlert;
import domain.WaterFlowAlert;
import domain.WaterPressureAlert;
import domain.Baseline;
import domain.BaselineStorage;

// controlApp holds all the methods that allow the user to interact with the app
public class ControlApp {

    //Declare view objects, storage objects, and variables for input
    private ViewApp view;
    private Storage store;
    private AlertStorage alertStore;
    private BaselineStorage baselineStore;
    private String enteredUsername;
    private char[] userUsername;
    public char[] getUserUsername() {
        userUsername = enteredUsername.toCharArray();
        return userUsername;
    }
    private char[] enteredPassword;
    private char[] userPassword;
    public char[] getUserPassword() {
        userPassword = enteredPassword;
        return userPassword;
    }

    //Constructor for controlApp
    public ControlApp(ViewApp view, Storage store, AlertStorage alertStore, BaselineStorage baselineStore) {
        this.view = view;
        this.store = store;
        this.alertStore = alertStore;
        this.baselineStore = baselineStore;
    
        //Switch to Sign Up Panel
        view.getSignUpButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.showSignUpPanel();
            }
        });

        //Switch to Login Panel
        view.getReturnButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.showLoginPanel();
            }
        });
        
        //Store Username and Password
        view.getNewEnterButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Username and Password from textfields
                enteredUsername = view.newUsernameInput.getText();
                enteredPassword = view.newPasswordInput.getPassword();

                //Ensure they are not empty
                boolean usernameEmpty = enteredUsername.isEmpty();
                boolean passwordEmpty = enteredPassword.length == 0;

                //If they are empty, do not store
                if (usernameEmpty || passwordEmpty) {
                    return;
                }
                //Store new username and password
                try {
                    //Check if username already exists.
                    //If it does, tell user and do not store
                    if (store.usernameExists(enteredUsername)) {
                        JOptionPane.showMessageDialog(
                            view.getMainFrame(), "Username already exists", "Sign Up Failed", JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    ControlApp.this.store.storeLogin(enteredUsername, enteredPassword);
                    //Show success message and clear textfields
                    JOptionPane.showMessageDialog(
                        view.getMainFrame(), "Sign Up Successful", "Sign Up Successful", JOptionPane.INFORMATION_MESSAGE
                    );
                    view.newUsernameInput.setText("");
                    view.newPasswordInput.setText("");
                } 
                catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        //Sign In to enter alert panel - Default to Senior Panel
        view.getEnterButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Username and password from textfields
                enteredUsername = view.usernameInput.getText();
                enteredPassword = view.passwordInput.getPassword();

                try {
                    //Check if username and password are correct for a given user in the system
                    int userId = store.checkLogin(enteredUsername, enteredPassword);

                    //If there is a match, load the user's alerts and baseline, showing senior panel by default.
                    if (userId != -1) {
                        Baseline baseline = baselineStore.getBaseline(userId);
                        if (baseline == null) {
                            baseline = baselineStore.makeBaseline(userId);
                            baselineStore.storeBaseline(baseline);
                        }
                        view.showSeniorPanel();
                    }
                    //If there is not a match, show error message
                    else {
                        JOptionPane.showMessageDialog(
                            view.getMainFrame(), "Login Failed", "Login Failed", JOptionPane.ERROR_MESSAGE
                        );
                    }
                    
                } catch(IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        //Switch to Family Panel
        view.getToFamilyButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Find the userId of the user that logged in to load the alerts for family panel, then switch to family panel
                    int userId = store.checkLogin(enteredUsername, enteredPassword);
                    view.loadAlerts(alertStore.getAlerts(userId));
                    view.showFamilyPanel();
                }   catch(Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        //Switch back to Senior Panel
        view.getToSeniorButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    view.showSeniorPanel();
                } catch(Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        //Simulate the statuses of the sensors for the senior panel using randomly generated booleans.
        view.getSimulateSenior().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Simulate sensor statuses
                    String type = "";

                    //Randomly select a sensor type to simulate
                    int sensorType = (int)(Math.random()*4) + 1;
                    if (sensorType == 1) {
                        type = "Water Flow Sensor";
                    }
                    else if (sensorType == 2) {
                        type = "Water Pressure Sensor";
                    }
                    else if (sensorType == 3) {
                        type = "Voltage Sensor";
                    }
                    else {
                        type = "Current Sensor";
                    }

                    //If the user has not logged in, do not simulate
                    int userId = store.checkLogin(enteredUsername, enteredPassword);
                    Baseline userBaseline = baselineStore.getBaseline(userId);
                    if (userBaseline == null) {
                        userBaseline = baselineStore.makeBaseline(userId);
                        baselineStore.storeBaseline(userBaseline);
                    }
                    double baseline = 0;
                    double reading = 0;
                    Alert alert;
                    double stdev = 0;
                    double deviation = 0;
                    double deviationDirection = 0;
                    int severityNum = (int)(Math.random()*4);
                    
                    //Water Flow Math
                    if (sensorType == 1) {
                        //Randomly select direction of deviation
                        if (Math.random() < 0.5) {
                            deviationDirection = -1;
                        }
                        else {
                            deviationDirection = 1;
                        }

                        //Get waterflow baseline for the user and find stdev
                        //Only for water flow, stdev has minimum value of 0.2 to ensure there is significant deviation
                        baseline = userBaseline.getWaterFlowBaseline();
                        stdev = baseline * 0.2;
                        if (stdev < 0.2) {
                            stdev = 0.2;
                        }

                        //Find severity based on deviations set for the sensor type - Water Flow
                        if (severityNum == 0) {
                            deviation = Math.random() * 1.49 * stdev;
                        }
                        else if (severityNum == 1) {
                            deviation = (1.5 + Math.random() * 0.99) * stdev;
                        }
                        else if (severityNum == 2) {
                            deviation = (2.5 + Math.random() * 0.99) * stdev;
                        }
                        else {
                            deviation = (3.5 + Math.random()) * stdev;
                        }
                        
                        //Calculate reading and ensure it is not negative
                        reading = baseline + deviationDirection * deviation;
                        if (reading < 0) {
                            reading = 0;
                        }
                        alert = new WaterFlowAlert("Water Flow", baseline, reading);
                    }

                    //Water Pressure Math
                    else if (sensorType == 2) {
                        //Randomly select direction of deviation
                        if (Math.random() < 0.5) {
                            deviationDirection = -1;
                        }
                        else {
                            deviationDirection = 1;
                        }
                        //Get water pressure baseline for the user and find stdev
                        baseline = userBaseline.getWaterPressureBaseline();
                        stdev = baseline * 0.15;

                        //Find severity based on deviations set for the sensor type - Water Pressure
                        if (severityNum == 0) {
                            deviation = Math.random() * 0.99 * stdev;
                        }
                        else if (severityNum == 1) {
                            deviation = (1 + Math.random() * 0.99) * stdev;
                        }
                        else if (severityNum == 2) {
                            deviation = (2 + Math.random() * 0.99) * stdev;
                        }
                        else {
                            deviation = (3 + Math.random()) * stdev;
                        }

                        //Calculate reading and ensure it is not negative
                        reading = baseline + deviationDirection * deviation;
                        if (reading < 0) {
                            reading = 0;
                        }
                        alert = new WaterPressureAlert("Water Pressure", baseline, reading);
                    }
                    
                    //Voltage
                    else if (sensorType == 3) {
                        //Randomly select direction of deviation
                        if (Math.random() < 0.5) {
                            deviationDirection = -1;
                        }
                        else {
                            deviationDirection = 1;
                        }
                        //Get voltage baseline for the user and find stdev
                        baseline = userBaseline.getVoltageBaseline();
                        stdev = baseline * 0.05;

                        //Find severity based on deviations set for the sensor type - Voltage
                        if (severityNum == 0) {
                            deviation = Math.random() * 0.74 * stdev;
                        }
                        else if (severityNum == 1) {
                            deviation = (0.75 + Math.random() * 0.74) * stdev;
                        }
                        else if (severityNum == 2) {
                            deviation = (1.5 + Math.random() * 0.99) * stdev;
                        }
                        else {
                            deviation = (2.5 + Math.random()) * stdev;
                        }

                        //Calculate reading and ensure it is not negative
                        reading = baseline + deviationDirection * deviation;
                        if (reading < 0) {
                            reading = 0;
                        }
                        alert = new VoltageAlert("Voltage", baseline, reading);
                    }

                    //Current
                    else {
                        //Randomly select direction of deviation
                        if (Math.random() < 0.5) {
                            deviationDirection = -1;
                        }
                        else {
                            deviationDirection = 1;
                        }
                        //Get current baseline for the user and find stdev
                        baseline = userBaseline.getCurrentBaseline();
                        stdev = baseline * 0.1;

                        //Find severity based on deviations set for the sensor type - Current
                        if (severityNum == 0) {
                            deviation = Math.random() * 0.49 * stdev;
                        }
                        else if (severityNum == 1) {
                            deviation = (0.5 + Math.random() * 0.49) * stdev;
                        }
                        else if (severityNum == 2) {
                            deviation = (1 + Math.random() * 0.99) * stdev;
                        }
                        else {
                            deviation = (2 + Math.random()) * stdev;
                        }

                        //Calculate reading and ensure it is not negative
                        reading = baseline + deviationDirection * deviation;
                        if (reading < 0) {
                            reading = 0;
                        }
                        alert = new CurrentAlert("Current", baseline, reading);
                    }

                    
                    //Assume all sensors are not normal
                    view.getWaterPressureStatus().setSelected(false);
                    view.getWaterFlowStatus().setSelected(false);
                    view.getVoltageStatus().setSelected(false);
                    view.getCurrentStatus().setSelected(false);

                    //If the sensor type is deemed normal, checkmark the checkbox for that sensor type
                    if (alert.getSeverity().equals("NORMAL")) {
                        if (sensorType == 1) {
                            view.getWaterFlowStatus().setSelected(true);
                        }
                        else if (sensorType == 2) {
                            view.getWaterPressureStatus().setSelected(true);
                        }
                        else if (sensorType == 3) {
                            view.getVoltageStatus().setSelected(true);
                        }
                        else if (sensorType == 4) {
                            view.getCurrentStatus().setSelected(true);
                        }
                    }

                } catch(Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        //Simulate the statuses of the sensors for the family panel using randomly generated booleans.
        view.getSimulateFamily().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String type = "";

                    //Randomly select a sensor type to simulate
                    int sensorType = (int)(Math.random()*4) + 1;
                    if (sensorType == 1) {
                        type = "Water Flow Sensor";
                    }
                    else if (sensorType == 2) {
                        type = "Water Pressure Sensor";
                    }
                    else if (sensorType == 3) {
                        type = "Voltage Sensor";
                    }
                    else {
                        type = "Current Sensor";
                    }
    
                    //If the user has not logged in, do not simulate
                    int userId = store.checkLogin(enteredUsername, enteredPassword);
                    Baseline userBaseline = baselineStore.getBaseline(userId);
                    if (userBaseline == null) {
                        userBaseline = baselineStore.makeBaseline(userId);
                        baselineStore.storeBaseline(userBaseline);
                    }
                    double baseline = 0;
                    double reading = 0;
                    Alert alert;
                    double stdev = 0;
                    double deviation = 0;
                    double deviationDirection = 0;
                    int severityNum = (int)(Math.random()*4);
                    
                    //Water Flow math, same as senior panel
                    //However, it also stores the information as an alert to show in the alert history table
                    if (sensorType == 1) {
                        if (Math.random() < 0.5) {
                            deviationDirection = -1;
                        }
                        else {
                            deviationDirection = 1;
                        }


                        baseline = userBaseline.getWaterFlowBaseline();
                        stdev = baseline * 0.2;
                        if (stdev < 0.2) {
                            stdev = 0.2;
                        }

                        if (severityNum == 0) {
                            deviation = Math.random() * 1.49 * stdev;
                        }
                        else if (severityNum == 1) {
                            deviation = (1.5 + Math.random() * 0.99) * stdev;
                        }
                        else if (severityNum == 2) {
                            deviation = (2.5 + Math.random() * 0.99) * stdev;
                        }
                        else {
                            deviation = (3.5 + Math.random()) * stdev;
                        }

                        reading = baseline + deviationDirection * deviation;
                        if (reading < 0) {
                            reading = 0;
                        }
                        alert = new WaterFlowAlert("Water Flow", baseline, reading);
                    }

                    //Water Pressure math, same as senior panel
                    //However, it also stores the information as an alert to show in the alert history table
                    else if (sensorType == 2) {
                        if (Math.random() < 0.5) {
                            deviationDirection = -1;
                        }
                        else {
                            deviationDirection = 1;
                        }
                        baseline = userBaseline.getWaterPressureBaseline();
                        stdev = baseline * 0.15;

                        if (severityNum == 0) {
                            deviation = Math.random() * 0.99 * stdev;
                        }
                        else if (severityNum == 1) {
                            deviation = (1 + Math.random() * 0.99) * stdev;
                        }
                        else if (severityNum == 2) {
                            deviation = (2 + Math.random() * 0.99) * stdev;
                        }
                        else {
                            deviation = (3 + Math.random()) * stdev;
                        }

                        reading = baseline + deviationDirection * deviation;
                        if (reading < 0) {
                            reading = 0;
                        }
                        alert = new WaterPressureAlert("Water Pressure", baseline, reading);
                    }
                    
                    //Voltage math, same as senior panel
                    //However, it also stores the information as an alert to show in the alert history table
                    else if (sensorType == 3) {
                        if (Math.random() < 0.5) {
                            deviationDirection = -1;
                        }
                        else {
                            deviationDirection = 1;
                        }
                        baseline = userBaseline.getVoltageBaseline();
                        stdev = baseline * 0.05;

                        if (severityNum == 0) {
                            deviation = Math.random() * 0.74 * stdev;
                        }
                        else if (severityNum == 1) {
                            deviation = (0.75 + Math.random() * 0.74) * stdev;
                        }
                        else if (severityNum == 2) {
                            deviation = (1.5 + Math.random() * 0.99) * stdev;
                        }
                        else {
                            deviation = (2.5 + Math.random()) * stdev;
                        }

                        reading = baseline + deviationDirection * deviation;
                        if (reading < 0) {
                            reading = 0;
                        }
                        alert = new VoltageAlert("Voltage", baseline, reading);
                    }

                    //Current math, same as senior panel
                    //However, it also stores the information as an alert to show in the alert history table
                    else {
                        if (Math.random() < 0.5) {
                            deviationDirection = -1;
                        }
                        else {
                            deviationDirection = 1;
                        }
                        baseline = userBaseline.getCurrentBaseline();
                        stdev = baseline * 0.1;

                        if (severityNum == 0) {
                            deviation = Math.random() * 0.49 * stdev;
                        }
                        else if (severityNum == 1) {
                            deviation = (0.5 + Math.random() * 0.49) * stdev;
                        }
                        else if (severityNum == 2) {
                            deviation = (1 + Math.random() * 0.99) * stdev;
                        }
                        else {
                            deviation = (2 + Math.random()) * stdev;
                        }

                        reading = baseline + deviationDirection * deviation;
                        if (reading < 0) {
                            reading = 0;
                        }
                        alert = new CurrentAlert("Current", baseline, reading);
                    }

                    alertStore.storeAlert(userId, alert);
                    view.loadAlerts(alertStore.getAlerts(userId));
                    
                    //If the alert is not normal, show a warning message pop-up with the alert information
                    if (!alert.getSeverity().equals("NORMAL")) {
                        JOptionPane.showMessageDialog(
                            null, 
                            "Sensor: " + alert.getSensor() + "\n"
                            + "Severity: " + alert.getSeverity() + "\n"
                            + "Baseline: " + alert.getBaseline() + "\n"
                            + "Reading: " + alert.getReading(),
                            "alert",
                            JOptionPane.WARNING_MESSAGE
                        );
                    }

                } catch(Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
}
