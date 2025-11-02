import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static final String URL = "jdbc:postgresql://localhost/gabriel";
    private static final String USER = "gabriel";
    private static final String PASSWORD = "12345";

    public void createUser(String name, String email) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println("User created successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readUser(int id) {
        String sql = "SELECT id, name, email FROM users WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
            } else {
                System.out.println("User not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(int id, String name, String email) {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setInt(3, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("User updated successfully!");
            } else {
                System.out.println("User not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("User deleted successfully!");
            } else {
                System.out.println("User not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> listUsers() {
        String sql = "SELECT id, name, email FROM users";
        List<String> users = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                users.add(
                    rs.getInt("id") + " - " +
                    rs.getString("name") + " (" +
                    rs.getString("email") + ")"
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
