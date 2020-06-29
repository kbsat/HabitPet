
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Graphics;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;

public class MainFrame {

	private JFrame frame;
	private JLabel lblLevel;
	private JLabel lblName;
	private JLabel lblPoint;
	private JLabel lblImage;
	private Box verticalBox;
	public Animal RepAnimal;
	public DBConnection dbConnection;
	public User user;
	public Connection conn;

	public MainFrame(DBConnection connection) {
		this.dbConnection = connection;
		this.user = dbConnection.user;
		conn = connection.getConnection();
		try {
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select * from collection where ID=?");
			st.setString(1, user.id);
			ResultSet rs = st.executeQuery();

			// 초기 사용자인지 확인 ( 컬렉션이 없다면
			if (!rs.next()) {
				String firstPetName;
				while (true) {
					firstPetName = JOptionPane.showInputDialog("펫의 이름을 정해주세요");
					if (firstPetName == null || firstPetName.equals("")) {
						JOptionPane.showMessageDialog(null, "다시 입력하세요");
					} else if (firstPetName.getBytes().length >= 20) {
						JOptionPane.showMessageDialog(null, "한글 6글자 이하로 정해주세요");
					} else {
						break;
					}
				}

				Animal firstPet = new Animal(firstPetName, "dog");
				firstPet.SetRep();
				dbConnection.InsertFirstPet(firstPet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		initialize();
	}

	private void initialize() {
		RepAnimal = dbConnection.LoadAnimalData();

		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 705, 410);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblImage = new JLabel("pet Image");
		lblImage.setIcon(new ImageIcon(".\\image\\" + RepAnimal.type + ".gif"));
		lblImage.setBounds(35, 26, 135, 135);
		frame.getContentPane().add(lblImage);

		lblName = new JLabel(RepAnimal.name);
		lblName.setBackground(Color.WHITE);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 17));
		lblName.setBounds(35, 171, 135, 25);
		frame.getContentPane().add(lblName);

		lblLevel = new JLabel("LV : " + RepAnimal.level);
		lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLevel.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 17));
		lblLevel.setBounds(35, 202, 135, 25);
		frame.getContentPane().add(lblLevel);

		lblPoint = new JLabel(user.point + "");
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

		JButton collectionButton = new RoundedButton("컬렉션");
		collectionButton.setForeground(Color.white);
		collectionButton.setBackground(new Color(95, 138, 212));
		collectionButton.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		collectionButton.setBounds(12, 319, 109, 31);
		collectionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Collection collectionFrame = new Collection(dbConnection.con, dbConnection.user);

				collectionChangeChecking changeChecking = new collectionChangeChecking(collectionFrame);
				Thread collectionChangeCheckingThread = new Thread(changeChecking);
				collectionChangeCheckingThread.start();

			}
		});
		frame.getContentPane().add(collectionButton);

		JButton shopButton = new RoundedButton("상점");
		shopButton.setForeground(Color.white);
		shopButton.setBackground(new Color(95, 138, 212));
		shopButton.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		shopButton.setBounds(133, 319, 78, 31);
		shopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Shop shopFrame = new Shop(user, conn);

				shopChangeChecking shopChangeChecking = new shopChangeChecking(shopFrame);
				Thread shopChangeCheckingThread = new Thread(shopChangeChecking);
				shopChangeCheckingThread.start();

			}

		});
		frame.getContentPane().add(shopButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(228, 38, 425, 271);
		frame.getContentPane().add(scrollPane);

		verticalBox = Box.createVerticalBox();
		scrollPane.setViewportView(verticalBox);

		// DB에서 플랜 정보 조회
		try {
			PreparedStatement st = (PreparedStatement) conn
					.prepareStatement("SELECT * FROM plan WHERE ID='" + user.id + "'");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				JCheckBox newCheckBox = new JCheckBox(rs.getString(2));
				if (rs.getInt(3) == 0) {
					newCheckBox.setSelected(false);
				} else {
					newCheckBox.setSelected(true);
					newCheckBox.setEnabled(false);
				}
				newCheckBox.setFont(new Font("굴림", Font.PLAIN, 14));
				newCheckBox.addItemListener(new PlanCheckEvent());
				newCheckBox.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
							DeleteMenu menu = new DeleteMenu(newCheckBox);
							menu.show(e.getComponent(), e.getX(), e.getY());
						}
					}
				});
				verticalBox.add(newCheckBox);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		JButton createButton = new RoundedButton("생성");
		createButton.setForeground(Color.white);
		createButton.setBackground(new Color(140, 224, 94 ));
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newPlan = JOptionPane.showInputDialog("생성할 계획을 적으세요");
				if (newPlan == null || newPlan.equals("")) {
					System.out.println("버튼 생성 취소");
					return;
				}

				dbConnection.InsertPlan(newPlan);

				System.out.println("버튼 생성 완료");
				JCheckBox newCheckBox = new JCheckBox(newPlan);
				newCheckBox.setFont(new Font("굴림", Font.PLAIN, 14));
				newCheckBox.addItemListener(new PlanCheckEvent());
				newCheckBox.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (SwingUtilities.isRightMouseButton(e)) {
							DeleteMenu menu = new DeleteMenu(newCheckBox);
							menu.show(e.getComponent(), e.getX(), e.getY());
						}
					}
				});
				verticalBox.add(newCheckBox);
				frame.getContentPane().revalidate();
			}
		});
		createButton.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		createButton.setBounds(544, 319, 109, 31);
		frame.getContentPane().add(createButton);

		expPanel expPanel = new expPanel();
		expPanel.setBounds(77, 241, 101, 21);
		frame.getContentPane().add(expPanel);

		frame.setVisible(true);
		frame.setResizable(false);
	}

	// 경험치 그래픽표현
	class expPanel extends JPanel {
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(Color.pink);
			g.fillRect(0, 0, RepAnimal.exp, 20);
		}
	}

	// 플랜을 체크했을 때 나오는 이벤트
	class PlanCheckEvent implements ItemListener {

		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				JCheckBox check = (JCheckBox) e.getSource();
				String plan_text = check.getText();
				// 경험치 포인트 추가
				RepAnimal.exp += 20;
				user.point += 100;

				System.out.println("현재 경험치는 " + RepAnimal.exp);

				check.setEnabled(false);
				// 플랜을 done으로 업데이트
				dbConnection.SetPlanDone(plan_text);

				// 플랜으로 인한 경험치와 포인트 갱신
				dbConnection.UpdatePoint(RepAnimal);
				dbConnection.UpdateExp(RepAnimal);
			}
			if (RepAnimal.exp >= 100) {
				RepAnimal.level += 1;
				RepAnimal.exp -= 100;
				lblLevel.setText("LV : " + RepAnimal.level);
				boolean isEvolve = false;
				if (RepAnimal.level <= 10) {
					isEvolve = RepAnimal.Evolve(); // 조건에 맞으면 진화 , 진화를 하면 isEvolve가 참으로 바뀜
				}
				dbConnection.LevelUp(isEvolve, RepAnimal);
				if (isEvolve) {
					lblImage.setIcon(new ImageIcon(".\\image\\" + RepAnimal.type + ".gif"));
				}
			}

			lblPoint.setText(user.point + "");
			frame.getContentPane().repaint();
			frame.getContentPane().revalidate();

		}
	}

	// 딜리트 팝업 메뉴
	class DeleteMenu extends JPopupMenu {
		JMenuItem deleteItem;

		public DeleteMenu(JCheckBox clickedPlan) {
			deleteItem = new JMenuItem("삭제");

			DeleteMenuActionListener deleteAction = new DeleteMenuActionListener(clickedPlan);
			deleteItem.addActionListener(deleteAction);
			add(deleteItem);
		}
	}

	// 딜리트 버튼 클릭 이벤트
	class DeleteMenuActionListener implements ActionListener {
		JCheckBox clickedPlan;

		DeleteMenuActionListener(JCheckBox clickedPlan) {
			this.clickedPlan = clickedPlan;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			verticalBox.remove(clickedPlan);
			verticalBox.revalidate();
			verticalBox.repaint();

			String removedPlanName = clickedPlan.getText();

			dbConnection.DeletePlan(removedPlanName);
		}
	}

	class collectionChangeChecking implements Runnable {
		Collection collectionFrame;

		collectionChangeChecking(Collection collectionFrame) {
			this.collectionFrame = collectionFrame;
		}

		@Override
		public void run() {

			frame.setEnabled(false);// 화면을 사용못하게 중지 ( collection이 종료될 때까지 제어 못함, 모달방식 )
			while (collectionFrame.frame.isVisible()) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			frame.setEnabled(true); // 다시 메인프레임 사용가능하도록 바꾸기
			frame.requestFocus(); // 최소화 되었을 때 다시 스크린 맨 앞으로 띄워주는 것

			// 컬렉션에서 대표동물이 만약 바뀌었다면 메인화면의 요소들을 바꿔준다.
			if (collectionFrame.checking == true && RepAnimal != collectionFrame.repAnimal) {
				RepAnimal = collectionFrame.repAnimal;
				lblImage.setIcon(new ImageIcon(".\\image\\" + RepAnimal.type + ".gif"));
				lblName.setText(RepAnimal.name);
				lblLevel.setText("LV : " + RepAnimal.level);

				frame.getContentPane().revalidate();
				frame.getContentPane().repaint();
			}
		}
	}

	class shopChangeChecking implements Runnable {
		Shop shopFrame;

		shopChangeChecking(Shop shopFrame) {
			this.shopFrame = shopFrame;
		}

		@Override
		public void run() {

			frame.setEnabled(false);// 화면을 사용못하게 중지 ( collection이 종료될 때까지 제어 못함, 모달방식 )
			while (shopFrame.frame.isVisible()) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			frame.setEnabled(true); // 다시 메인프레임 사용가능하도록 바꾸기
			frame.requestFocus(); // 최소화 되었을 때 다시 스크린 맨 앞으로 띄워주는 것

			lblPoint.setText(user.point + "");
			frame.getContentPane().revalidate();
			frame.getContentPane().repaint();
		}
	}
}
