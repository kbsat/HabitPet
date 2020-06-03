import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Regist {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Regist window = new Regist();
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
	public Regist() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 395, 365);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("회원가입");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 19));
		lblNewLabel.setBounds(150, 0, 80, 56);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(12, 85, 72, 40);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("PW");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(12, 135, 72, 40);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("PW 확인");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(22, 185, 72, 40);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		textField = new JTextField();
		textField.setBounds(117, 95, 150, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(117, 145, 150, 21);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(117, 195, 150, 21);
		frame.getContentPane().add(textField_2);
		
		JButton btnNewButton = new JButton("중복확인");
		btnNewButton.setBounds(279, 94, 90, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("가입");
		btnNewButton_1.setBounds(150, 273, 90, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
}
