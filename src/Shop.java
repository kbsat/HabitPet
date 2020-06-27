import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

public class Shop {
	public Connection conn;
	private JFrame frame;
	private User user;
	
	
	/**
	 * Create the application.
	 */
	public Shop(User user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 592, 433);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(".\\image\\egg.png"));
		lblNewLabel.setBounds(207, 60, 160, 197);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblpoint = new JLabel("가 필요합니다. 구매하시겠습니까?");
		lblpoint.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		lblpoint.setBounds(227, 253, 282, 28);
		frame.getContentPane().add(lblpoint);
		
		JLabel lblNewLabel_1 = new JLabel("500point");
		lblNewLabel_1.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 15));
		lblNewLabel_1.setBounds(151, 258, 83, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("알은");
		lblNewLabel_2.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(112, 258, 62, 18);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton buyBtn = new JButton("구매하기");
		buyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DBConnection connection = new DBConnection();
				buy(user);
				System.out.println("현재 남은 포인트는 "+ user.point);
			}
		});
		buyBtn.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		buyBtn.setBounds(227, 299, 105, 27);
		frame.getContentPane().add(buyBtn);
		frame.setVisible(true);
	}
	
	
		//public enum Type {dog,cat,wolf,deer,raccoon}
 
      
	
	public void buy(User user){

		
	try {
		
		if(user.point <500)
		{
			JOptionPane.showMessageDialog(null, "포인트가 부족합니다.");
		}
		else 
		{	

			/*포인트 차감
			user.point-=500;
			String query = "UPDATE user SET point  = " + user.point + " Where (ID = '" + user.id + "')";
			Statement sta = conn.createStatement();
			sta.execute(query); */

			
		ArrayList<String> Types = new ArrayList<String>();

		String[] Type = { "dog", "cat", "wolf", "deer", "raccon"};


			for (int i = 0; i < Type.length; i++){
				Types.add(Type[i]);
		    }


	        double randomValue = Math.random();
	        
	        int ran = (int)(randomValue * Type.length) ;

	        String get_Type = (String) Types.get(ran);
	       
		
		JOptionPane.showMessageDialog(null, get_Type+ "가(이) 태어났습니다!");// 그림도 같이 보이게
		
		String newPetName;
		while (true) {
			newPetName = JOptionPane.showInputDialog("펫의 이름을 정해주세요");
			if (newPetName == null || newPetName.equals("")) {
				JOptionPane.showMessageDialog(null, "다시 입력하세요");
			} else if (newPetName.getBytes().length >= 20) {
				JOptionPane.showMessageDialog(null, "한글 6글자 이하로 정해주세요");
			} else {
				break;
			}
		}

		Animal newPet = new Animal(newPetName,get_Type);
		
		user.collection.add(newPet);
		
		 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/habitpet?serverTimezone=UTC",
                 "root", "547418");

	
		String query = "INSERT INTO collection values('" + user.id + "','" + newPet.type + "','"
				+ newPet.name + "','" + newPet.level + "','" + newPet.exp + "','" + newPet.rep + "')";
		
		 Statement sta = conn.createStatement();
         int x = sta.executeUpdate(query);
         if (x == 0) {
             JOptionPane.showMessageDialog(null, "실패"); 
         }
         else
         {
 
             JOptionPane.showMessageDialog(null,
             		"구매 성공! 컬렉션을 확인해주세요");
         }
         
  
		} 
        
	}  catch (Exception exception) {
        exception.printStackTrace();
    }
	
	}
}

