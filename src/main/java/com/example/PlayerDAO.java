
package com.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {

    private static final String URL = "jdbc:postgresql://localhost:5433/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "test";

    public void createPlayer(String name) {
        String sql = "INSERT INTO players (name, wins, defeats) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setInt(2, 0);
            stmt.setInt(3, 0);
            stmt.executeUpdate();
            System.out.println("Player " + name + " created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Player readPlayer(String name) {
        String sql = "SELECT id, name, wins, defeats FROM players WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Player(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("wins"),
                    rs.getInt("defeats")
                );
            } else {
                System.out.println("Player not found.");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Player increasePlayerWins(String name) {
        Player player = readPlayer(name);
        String sql = "UPDATE players SET wins = ? WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, player.getWins() + 1);
            stmt.setString(2, name);

            player.setWins(player.getWins() + 1);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Player updated successfully!");
                return player;
            } 

            System.out.println("Player not found.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Player increasePlayerDefeats(String name) {
        Player player = readPlayer(name);
        String sql = "UPDATE players SET defeats = ? WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, player.getDefeats() + 1);
            stmt.setString(2, name);

            player.setWins(player.getDefeats() + 1);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Player updated successfully!");
                return player;
            } 

            System.out.println("Player not found.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deletePlayer(int id) {
        String sql = "DELETE FROM players WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Player deleted successfully!");
            } else {
                System.out.println("Player not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> listUsers() {
        String sql = "SELECT id, name FROM players";
        List<String> users = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                users.add(
                    rs.getInt("id") + " - " +
                    rs.getString("name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
