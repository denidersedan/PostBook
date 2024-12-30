package views;

import controllers.AccountController;
import controllers.FriendsController;
import controllers.HomeController;
import controllers.SearchController;
import database.User;
import models.HomeModel;
import models.SearchModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private final ImageIcon image = new ImageIcon("C:\\Users\\Denisa\\Downloads\\postbook-high-resolution-logo (3).png");
    private final Image scaledImage = image.getImage().getScaledInstance(250, 100, Image.SCALE_SMOOTH);
    private final JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
    private CardLayout cardLayout;
    private JPanel contentPanel;

    private JTextField searchField = new JTextField(20);
    private JTextField postTextField = new JTextField(30);
    private JButton postButton = new JButton("Post");

    private JMenuBar menuBar = new JMenuBar();
    private JMenu homeMenu = new JMenu("Home");
    private JMenu friendsMenu = new JMenu("Friends");
    private JMenu accountMenu = new JMenu("My Account");
    private JLabel searchMenu = new JLabel("Search:");

    public MainView(User account) {
        // Set up the main frame
        setTitle("PostBook");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Top panel with logo and menu
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(200, 200, 200)));
        topPanel.setBackground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        topPanel.add(logoLabel, gbc);

        // Menu bar with search field
        menuBar.setBackground(Color.WHITE);
        menuBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        menuBar.add(homeMenu);
        menuBar.add(friendsMenu);
        menuBar.add(accountMenu);
        menuBar.add(searchMenu);

        searchField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        searchField.setPreferredSize(new Dimension(200, 30));
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
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        HomeView homeView = new HomeView();
        HomeController homeController = new HomeController(account, homeView);

        AccountView accountView = new AccountView();
        AccountController accountController = new AccountController(account, accountView);

        FriendsView friendsView = new FriendsView();
        FriendsController friendsController = new FriendsController(account, friendsView);

        SearchView searchView = new SearchView();
        SearchModel searchModel = new SearchModel();
        SearchController searchController = new SearchController(searchModel, searchView, searchField);

        contentPanel.add(homeView, "Home");
        contentPanel.add(accountView, "Account");
        contentPanel.add(friendsView, "Friends");
        contentPanel.add(searchView, "Search");

        contentPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(contentPanel, gbc);

        setVisible(true);
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public JMenu getHomeMenu() {
        return homeMenu;
    }

    public JMenu getAccountMenu() {
        return accountMenu;
    }

    public JMenu getFriendsMenu() {
        return friendsMenu;
    }

    public void setContentPanelMouseListener(MouseAdapter mouseAdapter) {
        contentPanel.addMouseListener(mouseAdapter);
    }

    public void setHomeMenuListener(MenuListener menuListener) {
        homeMenu.addMenuListener(menuListener);
    }

    public void setSearchFieldActionListener(ActionListener actionListener) {
        searchField.addActionListener(actionListener);
    }

    public void setAccountMenuListener(MenuListener menuListener) {
        accountMenu.addMenuListener(menuListener);
    }

    public void setFriendsMenuListener(MenuListener menuListener) {
        friendsMenu.addMenuListener(menuListener);
    }
}
