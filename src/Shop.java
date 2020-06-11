import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Shop {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Shop window = new Shop();
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
	public Shop() {
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
			}
		});
		buyBtn.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		buyBtn.setBounds(227, 299, 105, 27);
		frame.getContentPane().add(buyBtn);
	}
}
