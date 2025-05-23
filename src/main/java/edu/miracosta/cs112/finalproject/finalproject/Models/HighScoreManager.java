package edu.miracosta.cs112.finalproject.finalproject.Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HighScoreManager {

    private static final String DB_URL = "jdbc:sqlite:HighScores.db";
    private static final String TABLE_NAME = "HighScores";

    public HighScoreManager() {
        try {
            Class.forName("org.sqlite.JDBC");  // Load SQLite JDBC driver
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load SQLite JDBC driver: " + e.getMessage());
        }
        createTableIfNotExists();
    }

    /**
     * Creates the high score table if it doesn't already exist.
     * Now includes both player name and score.
     */
    private void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "score INTEGER NOT NULL" +
                ");";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            System.err.println("Error creating high score table: " + e.getMessage());
        }
    }

    /**
     * Saves a new high score with a player name if it's higher than the current high score.
     */
    public void saveScore(String name, int score) {
        int currentHigh = getHighScore();

        if (score > currentHigh) {
            String insertSQL = "INSERT INTO " + TABLE_NAME + " (name, score) VALUES (?, ?);";

            try (Connection conn = DriverManager.getConnection(DB_URL);
                 PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

                pstmt.setString(1, name);
                pstmt.setInt(2, score);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error inserting high score: " + e.getMessage());
            }
        }
    }

    /**
     * Gets the highest score from the table.
     */
    public int getHighScore() {
        String querySQL = "SELECT MAX(score) AS max_score FROM " + TABLE_NAME + ";";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(querySQL)) {

            if (rs.next()) {
                return rs.getInt("max_score");
            }

        } catch (SQLException e) {
            System.err.println("Error reading high score: " + e.getMessage());
        }

        return 0;
    }

    /**
     * Returns a list of top high scores (name + score), sorted descending.
     */
    public List<ScoreEntry> getAllHighScores() {
        List<ScoreEntry> scores = new ArrayList<>();
        String querySQL = "SELECT name, score FROM " + TABLE_NAME + " ORDER BY score DESC LIMIT 10;";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(querySQL)) {

            while (rs.next()) {
                String name = rs.getString("name");
                int score = rs.getInt("score");
                scores.add(new ScoreEntry(name, score));
            }

        } catch (SQLException e) {
            System.err.println("Error reading high scores: " + e.getMessage());
        }

        return scores;
    }

    /**
     * Inner class to represent a high score entry with player name and score.
     */
    public static class ScoreEntry {
        public final String name;
        public final int score;

        public ScoreEntry(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return name + " - " + score;
        }
    }
}
