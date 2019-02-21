package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
			try {
				String dbURL = "jdbc:mysql://localhost:3306/AJAX?serverTimezone=UTC&useSSL=false";		//getConnection 인자값 세팅을 위한, 변수 만들기
				String dbID = "root";
				String dbPassword = "admin";
				Class.forName("com.mysql.jdbc.Driver");					//드라이버 로드  ***********************************
				conn = DriverManager.getConnection(dbURL, dbID, dbPassword);	//getConnection으로 연결 ***********************************
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	public ArrayList<User> search(String userName){
		String SQL = "SELECT * FROM USER WHERE userName LIKE ?";
		ArrayList<User> userList = new ArrayList<User>();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUserName(rs.getString(1));
				user.setUserAge(rs.getString(2));
				user.setUserGender(rs.getString(3));
				user.setUserEmail(rs.getString(4));
				userList.add(user);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(userList);
		return userList;
	}
	
}
