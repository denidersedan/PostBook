package views;

import database.Post;
import database.User;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class HomeView extends JPanel {
    private final JTextField postTextField;
    private final JButton postButton;
    private final JPanel postsPanel;

    public HomeView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // Create Post panel
        JPanel createPostPanel = new JPanel(new GridBagLayout());
        createPostPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)),
                "Create Post",
                TitledBorder.LEFT, // Title alignment
                TitledBorder.TOP,  // Title position
                new Font("Arial", Font.BOLD, 14), // Title font
                new Color(0, 0, 0) // Title color
        ));
        createPostPanel.setBackground(Color.WHITE);

        postTextField = new JTextField(30);
        postTextField.setPreferredSize(new Dimension(200, 30));
        postTextField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        postButton = new JButton("Post");
        postButton.setBackground(new Color(0, 0, 0));
        postButton.setPreferredSize(new Dimension(150, 30));
        postButton.setForeground(Color.WHITE);
        postButton.setFocusPainted(false);
        postButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        createPostPanel.add(postTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        createPostPanel.add(postButton, gbc);

        createPostPanel.setPreferredSize(new Dimension(getWidth(), 2));
        add(createPostPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));

        // Posts panel with GridBagLayout
        postsPanel = new JPanel(new GridBagLayout());
        postsPanel.setBackground(Color.WHITE);
        postsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)),
                "Posts",
                TitledBorder.LEFT, // Title alignment
                TitledBorder.TOP,  // Title position
                new Font("Arial", Font.BOLD, 14), // Title font
                new Color(0, 0, 0) // Title color
        ));

        JScrollPane scrollPane = new JScrollPane(postsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  // Enable vertical scrolling if needed
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  // Disable horizontal scrolling

        scrollPane.setPreferredSize(new Dimension(getWidth(), 550));
        add(scrollPane);
        add(Box.createRigidArea(new Dimension(0, 10)));
    }

    public String getPostText() {
        return postTextField.getText();
    }

    public void clearPostText() {
        postTextField.setText("");
    }

    public void setPostButtonActionListener(ActionListener actionListener) {
        postButton.addActionListener(actionListener);
    }

    public void addPost(String postContent) {
        JLabel postLabel = new JLabel(postContent);
        postLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        postLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel postPanel = new JPanel(new BorderLayout());
        postPanel.add(postLabel, BorderLayout.CENTER);
        postPanel.setBackground(Color.WHITE);
        postPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        postsPanel.add(postPanel);
        postsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        postsPanel.revalidate();
        postsPanel.repaint();
    }

    public void showPostDialog(String title, String message, boolean success) {
        JOptionPane.showMessageDialog(this, message, title, success ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
    }

    public void displayPosts(Map<Post, String> posts) {
        postsPanel.removeAll();  // Clear the existing content

        // GridBagLayout constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Ensure posts take up the full width of the panel
        gbc.gridx = 0;  // All posts will be aligned to the left (gridx = 0)
        gbc.weightx = 1.0;  // Allow posts to expand horizontally

        if (posts.isEmpty()) {
            // If no posts, show a message
            JLabel noPostsLabel = new JLabel("No posts found");
            noPostsLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
            noPostsLabel.setAlignmentX(CENTER_ALIGNMENT);
            noPostsLabel.setForeground(new Color(150, 150, 150));

            postsPanel.add(noPostsLabel, gbc);  // Add the "No posts" label to the panel
        } else {
            // Iterate through the posts and add them to the panel
            int gridy = 0;  // This will be used to place each post below the previous one
            for (Map.Entry<Post, String> entry : posts.entrySet()) {
                Post post = entry.getKey();
                String username = entry.getValue();

                JPanel postPanel = createPostPanel(username, post.getText(), post.getDate());

                // Set the vertical position of each post
                gbc.gridy = gridy++;
                postPanel.setAlignmentY(Component.TOP_ALIGNMENT);

                postsPanel.add(postPanel, gbc);  // Add the post panel to the postsPanel
            }
        }

        postsPanel.revalidate();  // Revalidate to apply the changes
        postsPanel.repaint();     // Repaint to update the UI
    }


    private JPanel createPostPanel(String username, String postText, Date postDate) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));  // Adjust the height as needed
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make the components take full width

        // Username label - Left aligned
        JLabel usernameLabel = new JLabel(username);
        usernameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;  // Allow the username label to take up remaining horizontal space
        gbc.anchor = GridBagConstraints.WEST;  // Align to the left
        panel.add(usernameLabel, gbc);

        // Post text label - Center aligned
        JLabel postTextLabel = new JLabel(postText);
        postTextLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 2.0;  // Make the text take up more space
        gbc.anchor = GridBagConstraints.CENTER;  // Center the post text
        panel.add(postTextLabel, gbc);

        // Post date label - Right aligned
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(postDate);
        JLabel postDateLabel = new JLabel("Posted on: " + formattedDate);
        postDateLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 1.0;  // Allow the date to take up remaining space
        gbc.anchor = GridBagConstraints.EAST;  // Align to the right
        postDateLabel.setForeground(new Color(100, 100, 100));
        panel.add(postDateLabel, gbc);

        return panel;
    }

    public void clearPostsList() {
        postsPanel.removeAll();
        postsPanel.revalidate();
        postsPanel.repaint();
    }
}