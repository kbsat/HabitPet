import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login {

	private JFrame frame;
	private JTextField idtextField;
	private JTextField pstextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 444, 408);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\uB85C\uADF8\uC778");
		lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 25));
		lblNewLabel.setBounds(159, 52, 108, 45);
		frame.getContentPane().add(lblNewLabel);

		JButton loginButton = new JButton("\uB85C\uADF8\uC778");
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String ID = idtextField.getText();
				String password = pstextField.getText();
				try {
					Connection connection = (Connection) DriverManager.getConnection(
							"jdbc:mysql://192.168.200.186:3306/habitpet?serverTimezone=UTC", "root", "dbwm73034146!");

					PreparedStatement st = (PreparedStatement) connection
							.prepareStatement("Select * from USER where ID=? AND password=?");

					st.setString(1, ID);
					st.setString(2, password);
					ResultSet rs = st.executeQuery();
					
					if (rs.next()) {
						int point = Integer.parseInt(rs.getString(3));
						User user = new User(ID,point);
						MainFrame mfr = new MainFrame(connection,user);
						frame.setVisible(false);
						
					} else {
						JOptionPane.showMessageDialog(null, "로그인 실패: 아이디와 패스워드를 확인해주세요.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		loginButton.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		loginButton.setBounds(290, 167, 105, 27);
		frame.getContentPane().add(loginButton);

		idtextField = new JTextField();
		idtextField.setBounds(95, 153, 166, 24);
		frame.getContentPane().add(idtextField);
		idtextField.setColumns(10);

		pstextField = new JTextField();
		pstextField.setColumns(10);
		pstextField.setBounds(95, 202, 166, 24);
		frame.getContentPane().add(pstextField);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(60, 155, 21, 21);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblPw = new JLabel("PW");
		lblPw.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		lblPw.setBounds(57, 205, 35, 18);
		frame.getContentPane().add(lblPw);
		
		JButton btnNewButton = new JButton("회원가입");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Regist rg = new Regist();
			}
		});
		btnNewButton.setBounds(132, 264, 97, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
