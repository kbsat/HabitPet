
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

public class Regist {

	private JFrame frame;
	private JTextField RidTextField;
	private JTextField RpwTextField;
	private JTextField RpwCheck;

	/**
	 * Launch the application.
	 */
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
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 395, 365);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("회원가입");
		lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 19));
		lblNewLabel.setBounds(150, 12, 80, 56);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(12, 85, 72, 40);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("PW");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(12, 135, 72, 40);
		frame.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("PW 확인");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(22, 185, 72, 40);
		frame.getContentPane().add(lblNewLabel_1_2);

		RidTextField = new JTextField();
		RidTextField.setBounds(117, 95, 150, 21);
		frame.getContentPane().add(RidTextField);
		RidTextField.setColumns(10);

		RpwTextField = new JTextField();
		RpwTextField.setColumns(10);
		RpwTextField.setBounds(117, 145, 150, 21);
		frame.getContentPane().add(RpwTextField);

		RpwCheck = new JTextField();
		RpwCheck.setColumns(10);
		RpwCheck.setBounds(117, 195, 150, 21);
		frame.getContentPane().add(RpwCheck);

		JButton duplicationCheck = new RoundedButton("중복확인");
		duplicationCheck.setForeground(Color.white);
		duplicationCheck.setBackground(Color.gray);
		JButton registerButton = new RoundedButton("가입");
		registerButton.setForeground(Color.white);
		registerButton.setBackground(Color.gray);
		registerButton.setEnabled(false);
		duplicationCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBConnection connection = new DBConnection();
				String id = RidTextField.getText();
				
				connection.dupCheck(id);
				registerButton.setEnabled(true);
			}
		});
		duplicationCheck.setFont(new Font("돋움", Font.PLAIN, 15));
		duplicationCheck.setBounds(279, 94, 90, 23);
		frame.getContentPane().add(duplicationCheck);

		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				
				DBConnection connection = new DBConnection();
				String id = RidTextField.getText();
                String password = RpwTextField.getText();
                String pwCheck = RpwCheck.getText();

	                if(!password.equals(pwCheck)) 
	                {
	                	JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
	                	return;      
	                }
	                
	            connection.register(id, password);
	                
	          
	            }

		});
		registerButton.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		registerButton.setBounds(150, 273, 90, 23);
		frame.getContentPane().add(registerButton);
		
		JTextPane txtpnId = new JTextPane();
		txtpnId.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		txtpnId.setText("ID 중복 여부를 확인해주세요");
		txtpnId.setBounds(109, 237, 184, 24);
		frame.getContentPane().add(txtpnId);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
