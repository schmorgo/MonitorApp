package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import ui.viewApp;
import data.storage;
import data.alertStorage;
import domain.Alert;
import domain.currentAlert;
import domain.voltageAlert;
import domain.waterFlowAlert;
import domain.waterPressureAlert;
import domain.Baseline;
import domain.BaselineStorage;

// controlApp holds all the methods that allow the user to interact with the app
public class controlApp {

    //Declare view objects, storage objects, and variables for input
    private viewApp view;
    private storage store;
    private alertStorage alertStore;
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
    public controlApp(viewApp view, storage store, alertStorage alertStore, BaselineStorage baselineStore) {
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
                    controlApp.this.store.storeLogin(enteredUsername, enteredPassword);
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

        //Simulate the statuses of the sensors for the senior panel usin randomly generated booleans.
        view.getSimulateSenior().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Assume that the sensors are false and therefore not on.
                    boolean[] statusBoolean = {false, false, false, false};
                    
                    //Randomly generate booleans for each sensor type. 
                    for (int i = 0; i < 4; i++) {
                        int tempInt = (int)(Math.random()*2);
                        if (tempInt == 0) {
                            statusBoolean[i] = false;
                        }
                        else if (tempInt == 1) {
                            statusBoolean[i] = true;
                        }
                    }
                
                    //Change the checkboxes that correspond to each sensor type with the generated booleans
                    view.getWaterPressureStatus().setSelected(statusBoolean[0]);
                    view.getWaterFlowStatus().setSelected(statusBoolean[1]);
                    view.getVoltageStatus().setSelected(statusBoolean[2]);
                    view.getCurrentStatus().setSelected(statusBoolean[3]);

                } catch(Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        view.getSimulateFamily().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String type = "";
                    String sensor;

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

                    int sensorNum = (int)(Math.random()*3) + 1;
                    sensor = type + " " + sensorNum;
    
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
                    
                    //Water Flow
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
                        alert = new waterFlowAlert("", baseline, reading);
                    }

                    //Water Pressure
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
                        alert = new waterPressureAlert("", baseline, reading);
                    }
                    
                    //Voltage
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
                        alert = new voltageAlert("", baseline, reading);
                    }

                    //Current
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
                        alert = new currentAlert("", baseline, reading);
                    }

                    alertStore.storeAlert(userId, alert);
                    view.loadAlerts(alertStore.getAlerts(userId));
                    
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
