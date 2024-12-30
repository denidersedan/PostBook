package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LogInView extends JFrame {
    private final JLabel title;
    private final JLabel usernameLabel;
    private final JLabel passwordLabel;
    private final JLabel message;

    private final JTextField usernameField;
    private final JPasswordField passwordField;

    private final JButton logInButton;
    private final JButton signUpButton;

    public LogInView() {
        // Frame configuration
        setTitle("PostBook - Log In");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Title label
        title = new JLabel("Welcome back to PostBook!", SwingConstants.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        title.setForeground(new Color(0, 0, 0));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Username and password labels and fields
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 30));

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 30));

        // Buttons
        logInButton = new JButton("Log In");
        logInButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        logInButton.setBackground(new Color(0, 0, 0));
        logInButton.setForeground(Color.WHITE);
        logInButton.setFocusPainted(false);

        signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        signUpButton.setBackground(new Color(0, 0, 0));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusPainted(false);

        // Message label
        message = createErrorMessage("Invalid username or password!");

        // Input panel
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
        inputPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(passwordField, gbc);

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

        // Adding components to frame
        add(title, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(message, BorderLayout.SOUTH);
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

    public String getPassword() {
        return String.valueOf(passwordField.getPassword());
    }

    public void setMessageVisible(boolean visible) {
        message.setVisible(visible);
    }

    public void setLogInButtonActionListener(ActionListener actionListener) {
        logInButton.addActionListener(actionListener);
    }

    public void setSignUpButtonActionListener(ActionListener actionListener) {
        signUpButton.addActionListener(actionListener);
    }
}