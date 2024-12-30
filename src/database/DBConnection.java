package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/postbook";
    private static final String password = "password";
    private static final String user = "postgres";

    public static boolean modifyName(String name, String newName) {

        // Correct SQL query for updating username
        String query = "UPDATE users SET name = ? WHERE name = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to database!");

            // Use PreparedStatement to prevent SQL injection
            PreparedStatement pst = connection.prepareStatement(query);

            // Set the parameters for the query
            pst.setString(1, newName); // New username
            pst.setString(2, name);    // Current username

            // Execute the update
            int rowsAffected = pst.executeUpdate();

            // Provide feedback on the update
            if (rowsAffected > 0) {
                System.out.println("Name updated successfully!");
                return true;
            } else {
                System.out.println("No user found with the provided name.");
                return false;
            }
        } catch (Exception e) {
            // Print the stack trace for debugging purposes
            e.printStackTrace();
        }
        return false;
    }
    public static boolean modifyUsername(String username, String newUsername) {

        // Correct SQL query for updating username
        String query = "UPDATE users SET username = ? WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to database!");

            // Use PreparedStatement to prevent SQL injection
            PreparedStatement pst = connection.prepareStatement(query);

            // Set the parameters for the query
            pst.setString(1, newUsername); // New username
            pst.setString(2, username);    // Current username

            // Execute the update
            int rowsAffected = pst.executeUpdate();

            // Provide feedback on the update
            if (rowsAffected > 0) {
                System.out.println("Username updated successfully!");
                return true;
            } else {
                System.out.println("No user found with the provided username.");
                return false;
            }
        } catch (Exception e) {
            // Print the stack trace for debugging purposes
            e.printStackTrace();
        }
        return false;
    }
    public static List<User> getUsers() {
        try(Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to database!");

            Statement statement = connection.createStatement();

            String query = "SELECT * FROM \"users\"";
            ResultSet resultSet = statement.executeQuery(query);

            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("name")
                ));
            }

            return users;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }
    public static List<Post> getFriendsPosts(int loggedInUserId) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to database!");

            // Create the SQL query to select posts from the friends of the logged-in user
            String query = "SELECT p.* FROM \"posts\" p " +
                    "JOIN \"friendships\" f ON (f.user1_id = p.user_id OR f.user2_id = p.user_id) " +
                    "WHERE (f.user1_id = ? OR f.user2_id = ?) AND p.user_id != ? " +
                    "ORDER BY p.post_date DESC";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, loggedInUserId);
                statement.setInt(2, loggedInUserId);
                statement.setInt(3, loggedInUserId);

                ResultSet resultSet = statement.executeQuery();

                List<Post> posts = new ArrayList<>();
                while (resultSet.next()) {
                    Post post = new Post(
                            resultSet.getInt("user_id"),
                            resultSet.getString("post_text"),
                            resultSet.getDate("post_date")
                    );
                    post.setUserById(resultSet.getInt("user_id"));
                    posts.add(post);

                }
                return posts;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }
    public static User getUserById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("name"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static int getUserIdByUsername(String username) {
        String query = "SELECT id FROM users WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if the user is not found
    }
    public static List<User> getFriendsByUserId(int userId) {
        List<User> friends = new ArrayList<>();
        String query = "SELECT u.id, u.username, u.password, u.name " +
                "FROM friendships f " +
                "JOIN users u ON (f.user1_id = u.id OR f.user2_id = u.id) " +
                "WHERE (f.user1_id = ? OR f.user2_id = ?) AND u.id != ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, userId);
            pst.setInt(2, userId);
            pst.setInt(3, userId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                User friend = new User(rs.getString("username"), rs.getString("password"), rs.getString("name"));
                friend.setId(rs.getInt("id")); // Set the id for the friend
                friends.add(friend);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return friends;
    }
    public static void insertUser(String username, String passwordUser, String name) {

        String query = "INSERT INTO users (username, password, name) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to database!");

            PreparedStatement pst = connection.prepareStatement(query);

            pst.setString(1, username);
            pst.setString(2, passwordUser);
            pst.setString(3, name);

            pst.executeUpdate();

            System.out.println("User inserted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean insertFriendship(int userId, String friendUsername) {
        // Fetch the friend's ID from the database based on their username
        int friendId = getUserIdByUsername(friendUsername);
        if (friendId == -1) {
            return false; // Friend's username not found
        }

        // Insert friendship relationship into the database
        String query = "INSERT INTO friendships (user1_id, user2_id) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, userId);
            pst.setInt(2, friendId);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean insertPost(int userId, String postText) {
        // SQL query to insert a new post
        String query = "INSERT INTO posts (user_id, post_text, post_date) VALUES (?, ?, NOW())";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, userId);
            pst.setString(2, postText);

            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
