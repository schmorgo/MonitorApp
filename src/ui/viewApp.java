package ui;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;

public class viewApp {

    //Frame
    private JFrame mainFrame = new JFrame("App");
    public JFrame getMainFrame() {
        return mainFrame;
    }

    //CardLayout
    CardLayout cardLayout = new CardLayout();
    JPanel mainPanel = new JPanel(cardLayout);

    //Login Panel
    private JPanel loginPanel = new JPanel();
    private JLabel loginTitle = new JLabel("Login");

        //Login Username
        private JLabel usernameLabel = new JLabel("Username:");
        public JTextField usernameInput = new JTextField();

        //Login Password
        private JLabel passwordLabel = new JLabel("Password:");
        public JPasswordField passwordInput = new JPasswordField();
    
        //Login Buttons
        private JButton enterButton = new JButton("Enter");
        public JButton getEnterButton() {
            return enterButton;
        }
        
        //Sign Up Button
        private JButton signUpButton = new JButton("Sign Up");
        public JButton getSignUpButton() {
            return signUpButton;
        }

    //Signup Panel
    private JPanel signUpPanel = new JPanel();
    private JLabel signUpTitle = new JLabel("Sign Up");

        //SignUp New Username
        private JLabel newUsernameLabel = new JLabel("New Username:");
        public JTextField newUsernameInput = new JTextField();

        //SignUp New Password
        private JLabel newPasswordLabel = new JLabel("New Password:");
        public JPasswordField newPasswordInput = new JPasswordField();

        //Enter New Account Button
        private JButton newEnterButton = new JButton("Enter");
        public JButton getNewEnterButton() {
            return newEnterButton;
        }

        //Return to Login Panel Button
        private JButton returnButton = new JButton("Return to Login");
        public JButton getReturnButton() {
            return returnButton;
        }

    //Senior Alerts Panel
    private JPanel seniorPanel = new JPanel();
    private JLabel seniorTitle = new JLabel("Senior");

        //Senior Status Labels
        private JLabel seniorStatusLabel = new JLabel("Status");
        private JLabel waterPressureLabel = new JLabel("Water Pressure Status");
        private JLabel waterFlowLabel = new JLabel("Water Flow Status");
        private JLabel voltageLabel = new JLabel("Voltage Status");
        private JLabel currentLabel = new JLabel("Amperes Status");

        //Senior Status Statuses
        private JCheckBox waterPressureStatus = new JCheckBox();
        public JCheckBox getWaterPressureStatus() {
            return waterPressureStatus;
        }
        private JCheckBox waterFlowStatus = new JCheckBox();
        public JCheckBox getWaterFlowStatus() {
            return waterFlowStatus;
        }
        private JCheckBox voltageStatus = new JCheckBox();
        public JCheckBox getVoltageStatus() {
            return voltageStatus;
        }
        private JCheckBox currentStatus = new JCheckBox();
        public JCheckBox getCurrentStatus() {
            return currentStatus;
        }

        //Switch to Family Button
        private JButton toFamilyButton = new JButton("Open Family Panel");
        public JButton getToFamilyButton() {
            return toFamilyButton;
        }

        //Simulate Senior Panel
        private JButton simulateSenior = new JButton("Simulate");
        public JButton getSimulateSenior() {
            return simulateSenior;
        }

    //Caregiver/Family Alerts Panel
    private JPanel familyPanel = new JPanel();
    private JLabel familyTitle = new JLabel("Family");

        //Family History
        private JLabel alertHistoryLabel = new JLabel("Alert History");
        private String[] columns = {"Time", "Sensor", "Severity"};
        private JTable alertHistory = new JTable();
        JScrollPane scrollAlerts = new JScrollPane(alertHistory);

        //Switch to Senior Button
        private JButton toSeniorButton = new JButton("Open Senior Panel");
        public JButton getToSeniorButton() {
            return toSeniorButton;
        }

        //Simulate Family Panel
        private JButton simulateFamily = new JButton("Simulate");
        public JButton getSimulateFamily() {
            return simulateFamily;
        }

    public viewApp() {
        // Main Frame
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(550, 750);
        mainFrame.add(mainPanel);
        mainFrame.setLocationRelativeTo(null);

        //Make the panels
        makeLoginPanel();
        makeSignUpPanel();
        makeSeniorPanel();
        makeFamilyPanel();

        //Add the panels to the main panel
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(signUpPanel, "SignUp");
        mainPanel.add(seniorPanel, "Senior");
        mainPanel.add(familyPanel, "Family");

        //Show frame
        mainFrame.setVisible(true);
    }

    private void makeLoginPanel() {
        // Build Login 
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginTitle.setFont(new Font("Avenir", Font.BOLD, 24));

        //Build Username Text Field
        usernameInput.setMaximumSize(new Dimension(500, 40));
        usernameInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameLabel.setFont(new Font("Avenir", Font.PLAIN, 16));

        //Build Password password field
        passwordInput.setMaximumSize(new Dimension(500, 40));
        passwordInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setFont(new Font("Avenir", Font.PLAIN, 16));

        // Build Enter Button
        enterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        enterButton.setFont(new Font("Geneva", Font.BOLD, 14));

        // Build Sign Up Button
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpButton.setFont(new Font("Geneva", Font.BOLD, 14));

        // Build the Login Panel
        loginPanel.add(Box.createVerticalGlue());
        loginPanel.add(loginTitle);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameInput);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordInput);
        loginPanel.add(enterButton);
        loginPanel.add(signUpButton);
        loginPanel.add(Box.createVerticalGlue());
        loginPanel.add(Box.createVerticalGlue());
        loginPanel.add(Box.createVerticalGlue());
    }

    //Build the Sign Up Panel
    private void makeSignUpPanel() {
        // Build Sign Up Panel
        signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.Y_AXIS));
        signUpPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        signUpTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpTitle.setFont(new Font("Avenir", Font.BOLD, 24));

        //Build New Username Text Field
        newUsernameInput.setMaximumSize(new Dimension(500, 40));
        newUsernameInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        newUsernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newUsernameLabel.setFont(new Font("Avenir", Font.PLAIN, 16));

        //Build New Password password field
        newPasswordInput.setMaximumSize(new Dimension(500, 40));
        newPasswordInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        newPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newPasswordLabel.setFont(new Font("Avenir", Font.PLAIN, 16));

        // Build Enter New Account Button
        newEnterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newEnterButton.setFont(new Font("Geneva", Font.BOLD, 14));

        // Build Return to Login Button
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnButton.setFont(new Font("Geneva", Font.BOLD, 14));

        // Build the Sign Up Panel
        signUpPanel.add(Box.createVerticalGlue());
        signUpPanel.add(signUpTitle);
        signUpPanel.add(newUsernameLabel);
        signUpPanel.add(newUsernameInput);
        signUpPanel.add(newPasswordLabel);
        signUpPanel.add(newPasswordInput);
        signUpPanel.add(newEnterButton);
        signUpPanel.add(returnButton);
        signUpPanel.add(Box.createVerticalGlue());
        signUpPanel.add(Box.createVerticalGlue());
        signUpPanel.add(Box.createVerticalGlue());
    }

    //Build the Senior Panel
    private void makeSeniorPanel() {
        seniorPanel.setLayout(new BorderLayout());

        //Build Senior Panel
        seniorTitle.setFont(new Font("Avenir", Font.PLAIN, 24));
        seniorStatusLabel.setFont(new Font("Avenir", Font.PLAIN, 24));

        //Build Senior Status Labels and Statuses
        waterPressureLabel.setFont(new Font("Avenir", Font.PLAIN, 16));
        waterFlowLabel.setFont(new Font("Avenir", Font.PLAIN, 16));
        voltageLabel.setFont(new Font("Avenir", Font.PLAIN, 16));
        currentLabel.setFont(new Font("Avenir", Font.PLAIN, 16));

        //Set the checkboxes to be unclickable because users should not be able to edit the status of sensors
        waterPressureStatus.setEnabled(false);
        waterFlowStatus.setEnabled(false);
        voltageStatus.setEnabled(false);
        currentStatus.setEnabled(false);

        //Build the top part of the senior panel with title and the label
        JPanel topSeniorPanel = new JPanel();
        topSeniorPanel.add(seniorTitle);
        topSeniorPanel.add(seniorStatusLabel);

        //Build the middle part of the senior panel with the sensor status labels and their checkbox statuses
        JPanel statusPanel = new JPanel(new GridLayout(4, 2, 20, 10));
        JPanel statusPanelHolder = new JPanel();
        statusPanel.add(waterPressureLabel);
        statusPanel.add(waterPressureStatus);
        statusPanel.add(waterFlowLabel);
        statusPanel.add(waterFlowStatus);
        statusPanel.add(voltageLabel);
        statusPanel.add(voltageStatus);
        statusPanel.add(currentLabel);
        statusPanel.add(currentStatus);

        //Build the bottom part of the senior panel with the buttons to switch to family panel and simulate the sensor statuses
        JPanel seniorLowerButtonsPanel = new JPanel(new BorderLayout());
        seniorLowerButtonsPanel.add(toFamilyButton, BorderLayout.WEST);
        seniorLowerButtonsPanel.add(simulateSenior, BorderLayout.EAST);

        //Add the three sub-panels to the senior panel
        statusPanelHolder.add(statusPanel);
        seniorPanel.add(topSeniorPanel, BorderLayout.NORTH);
        seniorPanel.add(statusPanelHolder, BorderLayout.CENTER);
        seniorPanel.add(seniorLowerButtonsPanel, BorderLayout.SOUTH);
    }

    //Build the Family Panel
    private void makeFamilyPanel() {
        //Build the family panel
        familyPanel.setLayout(new BorderLayout());
        familyTitle.setFont(new Font("Avenir", Font.PLAIN, 24));
        alertHistoryLabel.setFont(new Font("Avenir", Font.PLAIN, 24));

        JPanel topFamilyPanel = new JPanel();
        topFamilyPanel.add(familyTitle);
        topFamilyPanel.add(alertHistoryLabel);
        
        JPanel alertHistoryPanel = new JPanel(new BorderLayout());
        alertHistoryPanel.add(scrollAlerts, BorderLayout.CENTER);

        JPanel familyLowerButtonsPanel = new JPanel(new BorderLayout());
        familyLowerButtonsPanel.add(toSeniorButton, BorderLayout.WEST);
        familyLowerButtonsPanel.add(simulateFamily, BorderLayout.EAST);

        familyPanel.add(topFamilyPanel, BorderLayout.NORTH);
        familyPanel.add(alertHistoryPanel, BorderLayout.CENTER);
        familyPanel.add(familyLowerButtonsPanel, BorderLayout.SOUTH);
    }

    public void loadAlerts(ArrayList<String[]> alertInfo) {
        String[][] info = new String[alertInfo.size()][3];

        for (int i = 0; i < alertInfo.size(); i++) {
            info[i] = alertInfo.get(i);
        }

        alertHistory.setModel(new DefaultTableModel(info, columns));

    }

    public void showLoginPanel() {
        cardLayout.show(mainPanel, "Login");
    }
    public void showSignUpPanel() {
        cardLayout.show(mainPanel, "SignUp");
    }
    public void showSeniorPanel() {
        cardLayout.show(mainPanel, "Senior");
    }
    public void showFamilyPanel() {
        cardLayout.show(mainPanel, "Family");
    }
}
