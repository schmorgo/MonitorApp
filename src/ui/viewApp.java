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

    //Caregiver/Family Alerts Panel
    private JPanel familyPanel = new JPanel();
    private JLabel familyTitle = new JLabel("Family");

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

    }

    private void makeFamilyPanel() {

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
