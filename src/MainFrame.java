import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MainFrame {

	private JFrame frame;
	private JLabel lblLevel;
	private JLabel lblPoint;

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
	static int lv = 1;
	static int num = 1;
	static int exp = 0;
	static int point = 0;

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 705, 410);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		String dog = "dog";
		JLabel lblImage = new JLabel("New label");
		lblImage.setIcon(new ImageIcon(
				".\\image\\"+dog+".gif"));
		lblImage.setBounds(35, 26, 135, 135);
		frame.getContentPane().add(lblImage);

		JLabel lblName = new JLabel("깜찍이");
		lblName.setBackground(Color.WHITE);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 17));
		lblName.setBounds(35, 171, 135, 25);
		frame.getContentPane().add(lblName);
		
		lblLevel = new JLabel("LV : "+lv);
		lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLevel.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 17));
		lblLevel.setBounds(35, 202, 135, 25);
		frame.getContentPane().add(lblLevel);

		lblPoint = new JLabel(point+"");
		lblPoint.setBounds(83, 286, 87, 15);
		frame.getContentPane().add(lblPoint);
		
		JLabel lblExp = new JLabel("EXP");
		lblExp.setHorizontalAlignment(SwingConstants.CENTER);
		lblExp.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblExp.setBounds(35, 237, 45, 31);
		frame.getContentPane().add(lblExp);

		JLabel lblPointName = new JLabel("포인트");
		lblPointName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPointName.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblPointName.setBounds(35, 278, 45, 31);
		frame.getContentPane().add(lblPointName);

		JButton btnNewButton = new JButton("컬렉션");
		btnNewButton.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		btnNewButton.setBounds(12, 319, 78, 31);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("상점");
		btnNewButton_1.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		btnNewButton_1.setBounds(102, 319, 78, 31);
		frame.getContentPane().add(btnNewButton_1);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(228, 38, 425, 271);
		frame.getContentPane().add(scrollPane);

		Box verticalBox = Box.createVerticalBox();
		scrollPane.setViewportView(verticalBox);

		// 이거 이용해서 DB에서 뽑을 예정
		JCheckBox[] CheckBoxArr = new JCheckBox[num];
		for (int i = 0; i < num; i++) {
			CheckBoxArr[i] = new JCheckBox("헬로");
			CheckBoxArr[i].setFont(new Font("굴림", Font.PLAIN, 14));
			CheckBoxArr[i].addItemListener(new PlanCheckEvent());
			verticalBox.add(CheckBoxArr[i]);

		}

		JButton btnNewButton_1_1 = new JButton("생성");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newPlan = JOptionPane.showInputDialog("생성할 계획을 적으세요");
				if (newPlan == null || newPlan.equals("")) {
					System.out.println("버튼 생성 취소");
					return;
				}

				System.out.println("버튼 생성 완료");
				JCheckBox newCheckBox = new JCheckBox(newPlan);
				newCheckBox.setFont(new Font("굴림", Font.PLAIN, 14));
				newCheckBox.addItemListener(new PlanCheckEvent());
				verticalBox.add(newCheckBox);
				frame.getContentPane().revalidate();
			}
		});
		btnNewButton_1_1.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		btnNewButton_1_1.setBounds(483, 323, 78, 31);
		frame.getContentPane().add(btnNewButton_1_1);

		JButton btnNewButton_1_2 = new JButton("삭제");
		btnNewButton_1_2.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		btnNewButton_1_2.setBounds(575, 323, 78, 31);
		frame.getContentPane().add(btnNewButton_1_2);
		
		expPanel expPanel = new expPanel();
		expPanel.setBounds(77, 241, 101, 21);
		frame.getContentPane().add(expPanel);
	}
	
	// 경험치 그래픽표현
	class expPanel extends JPanel{
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(Color.black);
			g.fillRect(0,0,exp,20);
		}
	}

	// 플랜을 체크했을 때 나오는 이벤트
	class PlanCheckEvent implements ItemListener {

		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				exp += 20;
				point += 100;
				System.out.println("현재 경험치는 " + exp);
				
			} else if (e.getStateChange() == ItemEvent.DESELECTED) {
				exp -= 20;
				point -= 100;
				System.out.println("현재 경험치는 " + exp);
			}
			if(exp >= 100) {
				lv += 1;
				exp -= 100;
				lblLevel.setText("LV : "+lv);
			}
			
			lblPoint.setText(point+"");
			frame.getContentPane().repaint();
			frame.getContentPane().revalidate();
		}
	}

}
