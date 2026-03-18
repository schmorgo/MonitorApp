package ui;
import java.awt.*;
import javax.swing.*;

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

        //Signup Buttons
        private JButton newEnterButton = new JButton("Enter");
        public JButton getNewEnterButton() {
            return newEnterButton;
        }

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
        private JCheckBox waterFlowStatus = new JCheckBox();
        private JCheckBox voltageStatus = new JCheckBox();
        private JCheckBox currentStatus = new JCheckBox();

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

        makeLoginPanel();
        makeSignUpPanel();
        makeSeniorPanel();
        makeFamilyPanel();

        mainPanel.add(loginPanel, "Login");
        mainPanel.add(signUpPanel, "SignUp");
        mainPanel.add(seniorPanel, "Senior");
        mainPanel.add(familyPanel, "Family");

        mainFrame.setVisible(true);
    }

    private void makeLoginPanel() {
        // Login Panel Stuff
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginTitle.setFont(new Font("Avenir", Font.BOLD, 24));

        //Username
        usernameInput.setMaximumSize(new Dimension(500, 40));
        usernameInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameLabel.setFont(new Font("Avenir", Font.PLAIN, 16));

        //Password
        passwordInput.setMaximumSize(new Dimension(500, 40));
        passwordInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setFont(new Font("Avenir", Font.PLAIN, 16));

        // Enter
        enterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        enterButton.setFont(new Font("Geneva", Font.BOLD, 14));

        // Sign Up
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpButton.setFont(new Font("Geneva", Font.BOLD, 14));

        // Build
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

    private void makeSignUpPanel() {
        // Sign Up Panel Stuff
        signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.Y_AXIS));
        signUpPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        signUpTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpTitle.setFont(new Font("Avenir", Font.BOLD, 24));

        newUsernameInput.setMaximumSize(new Dimension(500, 40));
        newUsernameInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        newUsernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newUsernameLabel.setFont(new Font("Avenir", Font.PLAIN, 16));

        newPasswordInput.setMaximumSize(new Dimension(500, 40));
        newPasswordInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        newPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        newPasswordLabel.setFont(new Font("Avenir", Font.PLAIN, 16));

        newEnterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newEnterButton.setFont(new Font("Geneva", Font.BOLD, 14));

        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnButton.setFont(new Font("Geneva", Font.BOLD, 14));

        //Build
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

    private void makeSeniorPanel() {
        seniorPanel.setLayout(new BorderLayout());

        seniorTitle.setFont(new Font("Avenir", Font.PLAIN, 24));
        seniorStatusLabel.setFont(new Font("Avenir", Font.PLAIN, 24));

        waterPressureLabel.setFont(new Font("Avenir", Font.PLAIN, 16));
        waterFlowLabel.setFont(new Font("Avenir", Font.PLAIN, 16));
        voltageLabel.setFont(new Font("Avenir", Font.PLAIN, 16));
        currentLabel.setFont(new Font("Avenir", Font.PLAIN, 16));

        waterPressureStatus.setEnabled(false);
        waterFlowStatus.setEnabled(false);
        voltageStatus.setEnabled(false);
        currentStatus.setEnabled(false);

        JPanel topSeniorPanel = new JPanel();
        topSeniorPanel.add(seniorTitle);
        topSeniorPanel.add(seniorStatusLabel);

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

        JPanel seniorLowerButtonsPanel = new JPanel(new BorderLayout());
        seniorLowerButtonsPanel.add(toFamilyButton, BorderLayout.WEST);
        seniorLowerButtonsPanel.add(simulateSenior, BorderLayout.EAST);

        statusPanelHolder.add(statusPanel);
        seniorPanel.add(topSeniorPanel, BorderLayout.NORTH);
        seniorPanel.add(statusPanelHolder, BorderLayout.CENTER);
        seniorPanel.add(seniorLowerButtonsPanel, BorderLayout.SOUTH);
    }

    private void makeFamilyPanel() {
        familyPanel.setLayout(new BorderLayout());

        familyTitle.setFont(new Font("Avenir", Font.PLAIN, 24));
        alertHistoryLabel.setFont(new Font("Avenir", Font.PLAIN, 24));


        JPanel topFamilyPanel = new JPanel();


        JPanel alertHistoryPanel = new JPanel();

        JPanel familyLowerButtonsPanel = new JPanel();
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
