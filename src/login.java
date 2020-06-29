import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login {

	private JFrame frame;
	private JTextField idtextField;
	private JPasswordField pstextField;

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

		JButton loginButton = new RoundedButton("로그인");
		loginButton.setForeground(Color.white);
		loginButton.setBackground(new Color(95, 138, 212));
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DBConnection connection = new DBConnection();
				String id = idtextField.getText();
				String password = pstextField.getText();

				User user = connection.isUserCheck(id, password);
				new MainFrame(connection);
				frame.setVisible(false); // 로그인 후 로그인 윈도우 사라지게

			}
		});
		loginButton.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		loginButton.setBounds(290, 167, 105, 27);
		frame.getContentPane().add(loginButton);

		idtextField = new JTextField();
		idtextField.setBounds(95, 153, 166, 24);
		frame.getContentPane().add(idtextField);
		idtextField.setColumns(10);

		pstextField = new JPasswordField();
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

		JButton btnNewButton = new RoundedButton("회원가입");
		btnNewButton.setForeground(Color.white);
		btnNewButton.setBackground(new Color(95, 138, 212));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Regist rg = new Regist();
			}
		});
		btnNewButton.setBounds(132, 264, 97, 23);
		JLabel logo = new JLabel();
		logo.setBounds(97, 29, 245, 92);
		frame.getContentPane().add(logo);
		logo.setIcon(new ImageIcon(".\\image\\habitpetLogo.png"));

		frame.getContentPane().add(btnNewButton);
		frame.setResizable(false);
	}

	

}

