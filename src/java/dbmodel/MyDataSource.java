package dbmodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDataSource {

	private static String className="com.mysql.jdbc.Driver";
	
	
	
	static{
		try{
			Class.forName(className);
		}catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		Connection connection=null;
		try{
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/lubricate?user=root&password=root&useUnicode=true&characterEncoding=utf-8" );
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return  connection;
	}
	
	
	
	
}
