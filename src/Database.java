
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	 public  Connection connect = null;
	 
	 public Database(){
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			 connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/iot?", "surya", "password");
		 } catch (ClassNotFoundException e) {
			 e.printStackTrace();
		 } catch (SQLException e) {
			e.printStackTrace();
		}
	 }
	 
	 
	 public void disconnect(){
		 try {
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 }


}
