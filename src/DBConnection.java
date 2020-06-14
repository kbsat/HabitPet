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
	
	public DBConnection(){
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/habitpet?serverTimezone=UTC",
                    "root", "547418");
			st = con.createStatement();
			
		}
		catch(Exception e)
		{
			System.out.println("데이터 베이스 연결 오류: " + e.getMessage());
		}
	}
	
	public void isUserCheck(String id, String password){ //로그인
	try {
        Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/habitpet?serverTimezone=UTC",
            "root", "547418");


        PreparedStatement st = (PreparedStatement) connection
            .prepareStatement("Select ID, password from USER where ID=? AND password=?");

        st.setString(1, id);
        st.setString(2, password);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {

        	JOptionPane.showMessageDialog(null,"로그인 성공");

        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "로그인 실패: 아이디와 패스워드를 확인해주세요.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
	
	public void register(String id, String password){
			String msg = "" + id;
			msg += " \n";
	
          
          try {
              Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/habitpet?serverTimezone=UTC",
                      "root", "547418");

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
                    "root", "547418");

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
	
	public void buy(){
	try {
		Connection connection = (Connection) DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/habitpet?serverTimezone=UTC",
                "root", "547418");

		PreparedStatement st = (PreparedStatement) connection
				.prepareStatement("Select point from USER where point=? ");
		
		ResultSet rs = st.executeQuery();
		int point = rs.getInt("point");

		if(point <500)
		{
			JOptionPane.showMessageDialog(null, "포인트가 부족합니다.");
		}
		else 
		{
			point -=500;
			JOptionPane.showMessageDialog(null, "구매 성공! 컬렉션을 확인해주세요");
			
		}
	}   catch (Exception exception) {
        exception.printStackTrace();
    }
	

}

}
	
