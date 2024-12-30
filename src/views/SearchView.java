package views;

import database.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchView extends JPanel {
    private final JPanel resultsPanel;

    public SearchView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        // Results panel
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(245, 245, 245));
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        contentPanel.add(scrollPane);

        add(contentPanel);
    }

    public void displayResults(List<User> users) {
        resultsPanel.removeAll();

        if (users.isEmpty()) {
            JLabel noResultsLabel = new JLabel("No users found.");
            noResultsLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            noResultsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            resultsPanel.add(noResultsLabel);
        } else {
            for (User user : users) {
                JPanel userPanel = createUserPanel(user);
                resultsPanel.add(userPanel);
                resultsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }

    private JPanel createUserPanel(User user) {
        JLabel nameLabel = new JLabel(user.getName());
        JLabel usernameLabel = new JLabel(user.getUsername());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        nameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);

        usernameLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        usernameLabel.setAlignmentX(CENTER_ALIGNMENT);
        usernameLabel.setForeground(new Color(100, 100, 100));

        panel.add(nameLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(usernameLabel);

        return panel;
    }

    public void clearSearchResults() {
        resultsPanel.removeAll();
        resultsPanel.revalidate();
        resultsPanel.repaint();
    }
}
