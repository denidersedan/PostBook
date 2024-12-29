package views;

import controllers.AccountController;
import controllers.FriendsController;
import database.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private final ImageIcon image = new ImageIcon("C:\\Users\\Denisa\\Downloads\\postbook-high-resolution-logo (2).png");
    private final Image scaledImage = image.getImage().getScaledInstance(250, 100, Image.SCALE_SMOOTH);
    private final JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));

    private JTextField searchField = new JTextField(20);
    JTextField postTextField = new JTextField(30);
    JButton postButton = new JButton("Post");

    JMenuBar menuBar = new JMenuBar();
    JMenu homeMenu = new JMenu("Home");
    JMenu friendsMenu = new JMenu("Friends");
    JMenu accountMenu = new JMenu("My Account");
    JMenu searchMenu = new JMenu("Search:");

    public MainView() {
        // Set up the main frame
        setTitle("PostBook");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(219, 239, 237));

        setLayout(new GridBagLayout()); // Use GridBagLayout for precise layout control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding between components

        // Top panel with logo and menu
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setBackground(new Color(219, 239, 237));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        topPanel.add(logoLabel, gbc);

        // Menu bar with search field
        menuBar.add(homeMenu);
        menuBar.add(friendsMenu);
        menuBar.add(accountMenu);
        menuBar.add(searchMenu);
        menuBar.add(searchField);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        topPanel.add(menuBar, gbc);

        // Add the top panel to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(topPanel, gbc);

        // Content section
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // "Create Post" section
        JPanel createPostPanel = new JPanel(new GridBagLayout());
        createPostPanel.setBorder(BorderFactory.createTitledBorder("Create Post"));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        createPostPanel.add(postTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        createPostPanel.add(postButton, gbc);

        createPostPanel.setPreferredSize(new Dimension(getWidth(), 2));  // Set preferred height to 150px
        contentPanel.add(createPostPanel);

//        Box.Filler filler = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(0, Integer.MAX_VALUE));
//        contentPanel.add(filler);  // Add flexible space between "Create Post" and "Posts"

        // "Posts" section
        JPanel postsPanel = new JPanel(new GridBagLayout());
        postsPanel.setBorder(BorderFactory.createTitledBorder("Posts"));

        postsPanel.setPreferredSize(new Dimension(getWidth(), 400));  // Set preferred height to 400px
        contentPanel.add(postsPanel);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(contentPanel, gbc);

        setVisible(true);
    }

    public String getPostText() {
        return postTextField.getText();
    }

    public void setPostButtonActionListener(ActionListener actionListener) {
        postButton.addActionListener(actionListener);
    }

    public String getSearchText() {
        return searchField.getText();
    }

    public void setSearchMenuActionListener(ActionListener actionListener) {
        searchMenu.addActionListener(actionListener);
    }

    public void setAccountMenuActionListener(ActionListener actionListener) {
        accountMenu.addActionListener(actionListener);
    }

    public void setFriendsMenuActionListener(ActionListener actionListener) {
        friendsMenu.addActionListener(actionListener);
    }
}
