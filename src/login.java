import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class login {

	private JFrame frame;

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
		frame.setBounds(100, 100, 444, 408);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uB85C\uADF8\uC778");
		lblNewLabel.setFont(new Font("±¼¸²", Font.BOLD, 25));
		lblNewLabel.setBounds(69, 54, 108, 45);
		frame.getContentPane().add(lblNewLabel);
		
		JTextPane txtpnId = new JTextPane();
		txtpnId.setFont(new Font("±¼¸²", Font.PLAIN, 13));
		txtpnId.setText("ID");
		txtpnId.setBounds(69, 140, 192, 24);
		frame.getContentPane().add(txtpnId);
		
		JTextPane txtpnPassword = new JTextPane();
		txtpnPassword.setText("Password");
		txtpnPassword.setFont(new Font("±¼¸²", Font.PLAIN, 13));
		txtpnPassword.setBounds(69, 205, 192, 24);
		frame.getContentPane().add(txtpnPassword);
		
		JButton btnNewButton = new JButton("\uB85C\uADF8\uC778");
		btnNewButton.setBounds(291, 165, 105, 27);
		frame.getContentPane().add(btnNewButton);
	}
}
