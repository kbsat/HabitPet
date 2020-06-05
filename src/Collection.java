import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.ImageIcon;
import java.awt.Panel;
import java.awt.Label;

public class Collection {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Collection window = new Collection();
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
	public Collection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("컬렉션");
		lblNewLabel.setFont(new Font("나눔스퀘어", Font.PLAIN, 25));
		lblNewLabel.setBounds(14, 12, 118, 36);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setBounds(14, 60, 135, 135);
		frame.getContentPane().add(label);
		label.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\HabitPet\\jrfarmanimals\\앵무새\\parrot_face1.gif"));
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(14, 60, 674, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(14, 395, 674, 2);
		frame.getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(14, 228, 674, 2);
		frame.getContentPane().add(separator_2);
		
		JPanel nameTag1 = new JPanel();
		nameTag1.setBounds(182, 192, 135, 24);
		frame.getContentPane().add(nameTag1);
		
		JLabel label_2 = new JLabel("Lv.");
		nameTag1.add(label_2);
		label_2.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		label_2.setForeground(Color.BLACK);
		label_2.setBackground(new Color(211, 211, 211));
		
		JLabel lblBulldog = new JLabel("name");
		nameTag1.add(lblBulldog);
		lblBulldog.setFont(new Font("빙그레체", Font.PLAIN, 15));
		lblBulldog.setBackground(new Color(205, 133, 63));
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(182, 60, 135, 135);
		frame.getContentPane().add(label_1);
		label_1.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\HabitPet\\jrfarmanimals\\불독\\bulldog_1.gif"));
		
		JPanel nameTag3 = new JPanel();
		nameTag3.setBounds(533, 192, 135, 24);
		frame.getContentPane().add(nameTag3);
		
		JLabel label_3 = new JLabel("Lv.");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		label_3.setBackground(new Color(211, 211, 211));
		nameTag3.add(label_3);
		
		JLabel lblName = new JLabel("name");
		lblName.setFont(new Font("빙그레체", Font.PLAIN, 15));
		lblName.setBackground(Color.WHITE);
		nameTag3.add(lblName);
		
		JPanel nameTag2 = new JPanel();
		nameTag2.setBounds(362, 192, 135, 24);
		frame.getContentPane().add(nameTag2);
		
		JLabel label_5 = new JLabel("Lv.");
		label_5.setForeground(Color.BLACK);
		label_5.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		label_5.setBackground(new Color(211, 211, 211));
		nameTag2.add(label_5);
		
		JLabel lblName_1 = new JLabel("name");
		lblName_1.setFont(new Font("빙그레체", Font.PLAIN, 15));
		lblName_1.setBackground(Color.WHITE);
		nameTag2.add(lblName_1);
		
		JPanel nameTag0 = new JPanel();
		nameTag0.setBounds(14, 192, 134, 24);
		frame.getContentPane().add(nameTag0);
		
		JLabel label_7 = new JLabel("Lv.");
		label_7.setForeground(Color.BLACK);
		label_7.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		label_7.setBackground(new Color(211, 211, 211));
		nameTag0.add(label_7);
		
		JLabel lblName_2 = new JLabel("name");
		lblName_2.setFont(new Font("빙그레체", Font.PLAIN, 15));
		lblName_2.setBackground(new Color(205, 133, 63));
		nameTag0.add(lblName_2);
		frame.setBackground(new Color(169, 169, 169));
		frame.setBounds(100, 100, 720, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
