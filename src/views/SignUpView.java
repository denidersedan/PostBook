package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SignUpView extends JFrame {

    private final JLabel title;
    private final JLabel fullNameLabel;
    private final JLabel usernameLabel;
    private final JLabel passwordLabel;
    private final JLabel confirmPasswordLabel;
    private final JLabel messageFullName;
    private final JLabel messageUsername;
    private final JLabel messagePassword;
    private final JLabel messageExistingPassword;

    private JTextField fullNameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    private final JButton logInButton;
    private final JButton signUpButton;

    public SignUpView() {
        // Title Label
        title = new JLabel("Welcome to PostBook!", SwingConstants.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        title.setForeground(new Color(0, 0, 0));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Input Labels
        fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        confirmPasswordLabel = new JLabel("Confirm password:");
        confirmPasswordLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

        // Error Messages
        messageFullName = createErrorMessage("Full Name not entered!");
        messageUsername = createErrorMessage("Username already taken or not entered!");
        messagePassword = createErrorMessage("Passwords do not match!");
        messageExistingPassword = createErrorMessage("Either or both passwords not entered!");

        // Input Fields
        fullNameField = new JTextField();
        fullNameField.setPreferredSize(new Dimension(200, 30));
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 30));
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 30));
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setPreferredSize(new Dimension(200, 30));

        // Buttons
        logInButton = new JButton("Log In");
        logInButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        logInButton.setBackground(new Color(0, 0, 0));
        logInButton.setForeground(Color.WHITE);
        logInButton.setFocusPainted(false);

        signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusPainted(false);
        signUpButton.setBackground(new Color(0, 0, 0));

        // Layout Setup
        setTitle("PostBook - Sign Up");
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(fullNameLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(fullNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(confirmPasswordLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(confirmPasswordField, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(logInButton);
        buttonPanel.add(signUpButton);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new GridBagLayout());
        messagePanel.setBackground(Color.WHITE);
        messagePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        messagePanel.add(messageFullName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        messagePanel.add(messageUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        messagePanel.add(messagePassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        messagePanel.add(messageExistingPassword, gbc);

        add(title, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(messagePanel, BorderLayout.SOUTH);
    }

    private JLabel createErrorMessage(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.RED);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVisible(false);
        return label;
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword1() {
        return String.valueOf(passwordField.getPassword());
    }

    public String getPassword2() {
        return String.valueOf(confirmPasswordField.getPassword());
    }

    public String getFullName() {
        return String.valueOf(fullNameField.getText());
    }

    public void setMessageUsernameVisible() {
        messageUsername.setVisible(true);
    }

    public void setMessagePasswordVisible() {
        messagePassword.setVisible(true);
    }

    public void setMessageFullNameVisible() {
        messageFullName.setVisible(true);
    }

    public void setMessageExistingPasswordVisible() {
        messageExistingPassword.setVisible(true);
    }

    public void setSignUpButtonActionListener(ActionListener actionListener) {
        signUpButton.addActionListener(actionListener);
    }

    public void setLogInButtonActionListener(ActionListener actionListener) {
        logInButton.addActionListener(actionListener);
    }
}
