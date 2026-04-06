package logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBLogger implements LoggerStrategy {

    private static DBLogger instance;

    private DBLogger() {}

    public static DBLogger getInstance() {
        if (instance == null) {
            instance = new DBLogger();
        }
        return instance;
    }

    private Connection connect() throws Exception {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection("jdbc:sqlite:drawing.db");
    }

    @Override
    public void log(String message) {
        try (Connection conn = connect()) {

            conn.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS logs (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "message TEXT" +
                ")"
            );

            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO logs(message) VALUES(?)"
            );

            ps.setString(1, message);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}