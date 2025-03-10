package views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FriendsView extends JPanel {
    private final JPanel friendsPanel;
    private final JButton addFriendButton;

    public FriendsView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Friends List");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel);

        friendsPanel = new JPanel();
        friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
        friendsPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(friendsPanel);
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

        addFriendButton = new JButton("Add Friend");
        addFriendButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        addFriendButton.setBackground(new Color(0, 0, 0));
        addFriendButton.setForeground(Color.WHITE);
        addFriendButton.setFocusPainted(false);
        addFriendButton.setAlignmentX(CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0, 10)));
        add(addFriendButton);
    }

    public void setAddFriendButtonActionListener(ActionListener actionListener) {
        addFriendButton.addActionListener(actionListener);
    }

    public void addFriend(String name, String username) {
        JLabel friendName = new JLabel(name);
        JLabel friendUsername = new JLabel(username);

        JPanel friendPanel = createFriendPanel(friendName, friendUsername);
        friendPanel.setBackground(new Color(200, 200,   200));

        friendsPanel.add(friendPanel);
        friendsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        friendsPanel.revalidate();
        friendsPanel.repaint();
    }

    private JPanel createFriendPanel(JLabel nameLabel, JLabel usernameLabel) {
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

    public void clearFriendsList() {
        friendsPanel.removeAll();
        friendsPanel.revalidate();
        friendsPanel.repaint();
    }

}
