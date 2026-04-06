package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrawingDAO {

	private Connection connect() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return DriverManager.getConnection("jdbc:sqlite:drawing.db");
	}
	
	public void initDB() {
	    try (Connection conn = connect()) {
	        conn.createStatement().execute(
	            "CREATE TABLE IF NOT EXISTS drawings (" +
	            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
	            "content TEXT)"
	        );
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void save(String data) {
		try (Connection conn = connect()) {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO drawings(content) VALUES(?)");
			ps.setString(1, data);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getLastDrawing() {
		try (Connection conn = connect()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT content FROM drawings ORDER BY id DESC LIMIT 1");

			if (rs.next()) {
				return rs.getString("content");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public List<Integer> getAllDrawingIds() {
	    List<Integer> list = new ArrayList<>();

	    try (Connection conn = connect()) {
	        ResultSet rs = conn.createStatement().executeQuery(
	            "SELECT id FROM drawings ORDER BY id DESC"
	        );

	        while (rs.next()) {
	            list.add(rs.getInt("id"));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	public String getDrawingById(int id) {
	    try (Connection conn = connect()) {
	        PreparedStatement ps = conn.prepareStatement(
	            "SELECT content FROM drawings WHERE id = ?"
	        );
	        ps.setInt(1, id);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            return rs.getString("content");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null;
	}
}