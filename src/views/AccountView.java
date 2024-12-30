package views;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class AccountView extends JPanel {
    private JLabel fullNameLabel;
    private JLabel usernameLabel;
    private JPasswordField passwordField;
    private JButton togglePasswordButton;
    private JButton modifyUsernameButton;
    private JButton modifyFullNameButton;
    private JButton logOutButton;

    public AccountView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        // Labels and Password Field
        fullNameLabel = new JLabel("John Doe");
        usernameLabel = new JLabel("johndoe");
        JLabel passwordLabel = new JLabel("Password: ");

        passwordField = new JPasswordField();
        passwordField.setEchoChar('•');
        passwordField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        passwordField.setForeground(new Color(75, 37, 100));
        passwordField.setEditable(false);

        // Buttons
        togglePasswordButton = new JButton("Show");
        togglePasswordButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        togglePasswordButton.setBackground(new Color(0, 0, 0));
        togglePasswordButton.setForeground(Color.WHITE);
        togglePasswordButton.setFocusPainted(false);

        modifyUsernameButton = new JButton("Modify Username");
        modifyUsernameButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        modifyUsernameButton.setBackground(new Color(0, 0, 0));
        modifyUsernameButton.setForeground(Color.WHITE);
        modifyUsernameButton.setFocusPainted(false);

        modifyFullNameButton = new JButton("Modify Full Name");
        modifyFullNameButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        modifyFullNameButton.setBackground(new Color(0, 0, 0));
        modifyFullNameButton.setForeground(Color.WHITE);
        modifyFullNameButton.setFocusPainted(false);

        logOutButton = new JButton("Log Out");
        logOutButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        logOutButton.setBackground(new Color(50, 50, 50));
        logOutButton.setForeground(Color.WHITE);
        logOutButton.setFocusPainted(false);

        // Panels for organized layout
        JPanel passwordPanel = createSubPanel("Password", passwordField, togglePasswordButton);
        JPanel usernamePanel = createSubPanel("Username", usernameLabel, modifyUsernameButton);
        JPanel fullNamePanel = createSubPanel("Full name", fullNameLabel, modifyFullNameButton);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(245, 245, 245));
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        contentPanel.add(fullNamePanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(usernamePanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(passwordPanel);

        // Adding components to the main panel
        this.add(contentPanel);
        this.add(Box.createRigidArea(new Dimension(0, 15)));
        this.add(logOutButton);
    }

    private JPanel createSubPanel(String name, JComponent field, JButton button) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)),
                name,
                TitledBorder.LEFT, // Title alignment
                TitledBorder.TOP,  // Title position
                new Font("Arial", Font.BOLD, 14), // Title font
                new Color(0, 0, 0) // Title color
        ));
        panel.setBackground(Color.WHITE);

        button.setBackground(new Color(0, 0, 0));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        GridBagConstraints gbc = new GridBagConstraints();

        if (field != null) {
            field.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
            field.setPreferredSize(new Dimension(200, 30));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            panel.add(field, gbc);
        }

        if (button != null) {
            button.setPreferredSize(new Dimension(150, 30));
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            panel.add(button, gbc);
        }
        //panel.setPreferredSize(new Dimension(getWidth(), 2));
        return panel;
    }

    public void setFullNameLabel(String text) {
        fullNameLabel.setText(text);
    }

    public void setUsernameLabel(String text) {
        usernameLabel.setText(text);
    }

    public void setPasswordField(String text) {
        passwordField.setText(text);
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setTogglePasswordActionListener(ActionListener actionListener) {
        togglePasswordButton.addActionListener(actionListener);
    }

    public void setModifyUsernameActionListener(ActionListener actionListener) {
        modifyUsernameButton.addActionListener(actionListener);
    }

    public void setModifyFullNameActionListener(ActionListener actionListener) {
        modifyFullNameButton.addActionListener(actionListener);
    }

    public void setLogOutActionListener(ActionListener actionListener) {
        logOutButton.addActionListener(actionListener);
    }

    public JButton getTogglePasswordButton() {
        return togglePasswordButton;
    }
}
