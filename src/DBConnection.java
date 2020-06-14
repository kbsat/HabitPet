import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DBConnection {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public Connection getConnection() {
		return con;
	}
	public DBConnection(){
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/habitpet?serverTimezone=UTC",
                    "root", "dbwm73034146!");
			st = con.createStatement();
			
		}
		catch(Exception e)
		{
			System.out.println("데이터 베이스 연결 오류: " + e.getMessage());
		}
	}
	
	public User isUserCheck(String id, String password){ //로그인
	try {
        Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/habitpet?serverTimezone=UTC",
            "root", "dbwm73034146!");


        PreparedStatement st = (PreparedStatement) connection
            .prepareStatement("Select * from USER where ID=? AND password=?");

        st.setString(1, id);
        st.setString(2, password);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
        	String id_db = rs.getString(1); 
        	int point = rs.getInt(3);
        	User user = new User(id_db,point);
        	return user;
        	

        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "로그인 실패: 아이디와 패스워드를 확인해주세요.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
	return null;
}
	
	public void register(String id, String password){
			String msg = "" + id;
			msg += " \n";
	
          
          try {
              Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/habitpet?serverTimezone=UTC",
                      "root", "dbwm73034146!");

              String query = "INSERT INTO USER values('" + id + "','" + password +"','"+ 0 +"')";

              Statement sta = connection.createStatement();;
              int x = sta.executeUpdate(query);
              if (x == 0) {
                  JOptionPane.showMessageDialog(null, "회원가입 실패"); 
              }
              else
              {
      
                  JOptionPane.showMessageDialog(null,
                  		"환영합니다, " + msg + "님 회원 가입이 완료되었습니다");
              }
              
              connection.close();
              
          }   catch (Exception exception) {
              exception.printStackTrace();
          }
      }
	
	public void dupCheck(String id)
	{
		try {
			Connection connection = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/habitpet?serverTimezone=UTC",
                    "root", "dbwm73034146!");

			PreparedStatement st = (PreparedStatement) connection
					.prepareStatement("Select ID from USER where ID=? ");
			
			st.setString(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {

				JOptionPane.showMessageDialog(null, "아이디 중복: 사용할 수 없는 아이디 입니다."); 
			} else {
				JOptionPane.showMessageDialog(null, "사용 가능한 아이디 입니다.");
			}
			
			

		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
	


}
	
