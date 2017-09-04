import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BazaSqlLite {

	
	public static final String DRIVER = "org.splite.JDBC";
	public static final String DB_URL = "jdbc:sqlite:myGameListDB.sqlite";
	private Statement stat;
	


	Connection conn;

	public BazaSqlLite() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.err.println("Brak sterownika JDBC");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DB_URL);
			stat = conn.createStatement();
		} catch (SQLException e) {
			System.err.println("Problem z otwarciem Polaczenia");
			e.printStackTrace();
		}
	}

	public void dodawanieRekordu(String tytul, String opis) {

		try {
			String dodaj = "INSERT INTO Games(title, type) VALUES (?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(dodaj);
			preparedStatement.setString(1, tytul);
			preparedStatement.setString(2, opis);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}
	}

	public ArrayList<games> pobierzNaprawe(int gotowe) {

		ArrayList<games> opis = new ArrayList<>();
		try {
			String zapytanie = "SELECT * FROM Games WHERE gotowe=? ";
			PreparedStatement preparedStatement = conn.prepareStatement(zapytanie);
			preparedStatement.setInt(1, gotowe);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String i = rs.getString(1);
				String s = rs.getString(2);
				opis.add(new games(1,i, s));

			}
			conn.close();
		} catch (Exception e) {

		}
		return opis;
	}
	
	public ArrayList<games> getGame() {

		ArrayList<games> description = new ArrayList<>();
		try {
			String zapytanie = "select * from Games";
			Statement stat1 = conn.createStatement();
			ResultSet rs = stat1.executeQuery(zapytanie);
			while (rs.next()) {
				int int0 = rs.getInt("id");
				String i = rs.getString("title");
				String s = rs.getString("type");
				description.add(new games(int0, i, s));
			//	System.out.println("id="+int0+" title="+i+" typ="+s);

			}
			//System.out.println("tu jestem");
			//conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Problem z pobraniem gier");
		}
		return description;
	}
	
public void deleteSelectedGame(String name) {
try {
	String query = "Delete from Games where title = ?";
	PreparedStatement prepareStatement = conn.prepareStatement(query);
	prepareStatement.setString(1, name);
	prepareStatement.executeUpdate();
	
}
catch(Exception e) {
	e.printStackTrace();
}

}



public games getSelectedGame(String name) {
	try {
		String query = "select * from Games where  title = ?";
		PreparedStatement prepareStatement1 = conn.prepareStatement(query);
		prepareStatement1.setString(1, name);
		ResultSet rs = prepareStatement1.executeQuery();
		return new games(rs.getInt("id"), rs.getString("title"), rs.getString("type"));
	}
	catch(Exception e) {
		e.printStackTrace();
		return null;
	}
}

public void editRow(String title1, String description, String name) {
	try {
		String query = "Update Games SET  title = ?, type = ? where title = ?";
		PreparedStatement s = conn.prepareStatement(query);
		s.setString(1, title1);
		s.setString(2, description);
		s.setString(3, name);
		s.executeUpdate();	
		Statement ss = conn.createStatement();
		ResultSet rs = ss.executeQuery("select * from Games");
		while(rs.next()) {
			System.out.println(rs.getString("title")+" "+rs.getString("type"));
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}

}
