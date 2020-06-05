import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class MainFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
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
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 705, 410);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel PetImage = new JLabel("New label");
		PetImage.setIcon(new ImageIcon("C:\\Users\\kbsat\\OneDrive\\바탕 화면\\수업관련\\2020-3학년1학기\\Java프로그래밍(양재동)\\자바프로젝트\\HabitPet\\jrfarmanimals\\고양이\\cat_1_face1.gif"));
		PetImage.setBounds(35, 26, 135, 135);
		frame.getContentPane().add(PetImage);
		
		JLabel PetLevel_1 = new JLabel("깜찍이");
		PetLevel_1.setHorizontalAlignment(SwingConstants.CENTER);
		PetLevel_1.setFont(new Font("휴먼엑스포", Font.PLAIN, 17));
		PetLevel_1.setBounds(35, 171, 135, 25);
		frame.getContentPane().add(PetLevel_1);
		
		JLabel lblExp = new JLabel("EXP");
		lblExp.setHorizontalAlignment(SwingConstants.CENTER);
		lblExp.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		lblExp.setBounds(35, 237, 45, 31);
		frame.getContentPane().add(lblExp);
		
		JLabel lblPoint = new JLabel("Point");
		lblPoint.setHorizontalAlignment(SwingConstants.CENTER);
		lblPoint.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		lblPoint.setBounds(35, 278, 45, 31);
		frame.getContentPane().add(lblPoint);
		
		JButton btnNewButton = new JButton("컬렉션");
		btnNewButton.setBounds(12, 319, 78, 31);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("상점");
		btnNewButton_1.setBounds(102, 319, 78, 31);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel PetLevel_1_1 = new JLabel("LV : 3");
		PetLevel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		PetLevel_1_1.setFont(new Font("휴먼엑스포", Font.PLAIN, 17));
		PetLevel_1_1.setBounds(35, 202, 135, 25);
		frame.getContentPane().add(PetLevel_1_1);
		
		JLabel lblNewLabel = new JLabel("경험치바");
		lblNewLabel.setBounds(83, 245, 87, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("포인트");
		lblNewLabel_1.setBounds(83, 286, 87, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("ㅇㅇ");
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(228, 38, 425, 271);
		frame.getContentPane().add(scrollPane);
		
		Box verticalBox = Box.createVerticalBox();
		scrollPane.setViewportView(verticalBox);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setFont(new Font("굴림", Font.PLAIN, 14));
		verticalBox.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("New check box");
		chckbxNewCheckBox_1.setFont(new Font("굴림", Font.PLAIN, 14));
		verticalBox.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("New check box");
		chckbxNewCheckBox_2.setFont(new Font("굴림", Font.PLAIN, 14));
		verticalBox.add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("New check box");
		chckbxNewCheckBox_3.setFont(new Font("굴림", Font.PLAIN, 14));
		verticalBox.add(chckbxNewCheckBox_3);
		
		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("New check box");
		chckbxNewCheckBox_4.setFont(new Font("굴림", Font.PLAIN, 14));
		verticalBox.add(chckbxNewCheckBox_4);
		
		JCheckBox chckbxNewCheckBox_5 = new JCheckBox("New check box");
		chckbxNewCheckBox_5.setFont(new Font("굴림", Font.PLAIN, 14));
		verticalBox.add(chckbxNewCheckBox_5);
		
		JCheckBox chckbxNewCheckBox_6 = new JCheckBox("New check box");
		chckbxNewCheckBox_6.setFont(new Font("굴림", Font.PLAIN, 14));
		verticalBox.add(chckbxNewCheckBox_6);
		
		JCheckBox chckbxNewCheckBox_7 = new JCheckBox("New check box");
		chckbxNewCheckBox_7.setFont(new Font("굴림", Font.PLAIN, 14));
		verticalBox.add(chckbxNewCheckBox_7);
		
		JCheckBox chckbxNewCheckBox_8 = new JCheckBox("New check box");
		chckbxNewCheckBox_8.setFont(new Font("굴림", Font.PLAIN, 14));
		verticalBox.add(chckbxNewCheckBox_8);
		
		JCheckBox chckbxNewCheckBox_9 = new JCheckBox("New check box");
		chckbxNewCheckBox_9.setFont(new Font("굴림", Font.PLAIN, 14));
		verticalBox.add(chckbxNewCheckBox_9);
		
		JCheckBox chckbxNewCheckBox_10 = new JCheckBox("New check box");
		chckbxNewCheckBox_10.setFont(new Font("굴림", Font.PLAIN, 14));
		verticalBox.add(chckbxNewCheckBox_10);
		
		JButton btnNewButton_1_1 = new JButton("생성");
		btnNewButton_1_1.setBounds(483, 323, 78, 31);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("삭제");
		btnNewButton_1_2.setBounds(575, 323, 78, 31);
		frame.getContentPane().add(btnNewButton_1_2);
	}
}
