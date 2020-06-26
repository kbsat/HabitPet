import java.awt.EventQueue;

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
	private JLabel lblPoint;
	private JLabel lblImage;
	private Box verticalBox;
	public User user;
	public Animal RepAnimal;
	public Connection conn;

	

	/**
	 * Create the application.
	 */
	public MainFrame(Connection connection, User user) {
		
		this.conn = connection;
		this.user = user;
		try {
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("Select * from collection where ID=?");
			st.setString(1, user.id);
			ResultSet rs = st.executeQuery();

			// 초기 사용자인지 확인 ( 컬렉션이 없다면
			if (!rs.next()) {
				String firstPetName;
				while (true) {
					firstPetName = JOptionPane.showInputDialog("강아지의 이름을 정해주세요");
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

				String query = "INSERT INTO collection values('" + user.id + "','" + firstPet.type + "','"
						+ firstPet.name + "','" + firstPet.level + "','" + firstPet.exp + "','" + firstPet.rep + "')";
				Statement sta = conn.createStatement();
				sta.execute(query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		PreparedStatement st;
		try {
			st = (PreparedStatement) conn.prepareStatement("Select * from collection where id=?");
			st.setString(1, user.id);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String animalType = rs.getString(2);
				String animalName = rs.getString(3);
				int animalLevel = rs.getInt(4);
				int animalExp = rs.getInt(5);
				int animalRep = rs.getInt(6);

				Animal animal = new Animal(animalName, animalType, animalLevel, animalExp, animalRep);
				user.collection.add(animal);
				if (animalRep == 1) {
					RepAnimal = animal;
				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 705, 410);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblImage = new JLabel("pet Image");
		lblImage.setIcon(new ImageIcon(".\\image\\" + RepAnimal.type + ".gif"));
		lblImage.setBounds(35, 26, 135, 135);
		frame.getContentPane().add(lblImage);

		JLabel lblName = new JLabel(RepAnimal.name);
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

		JButton btnNewButton = new JButton("컬렉션");
		btnNewButton.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		btnNewButton.setBounds(12, 319, 78, 31);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("상점");
		btnNewButton_1.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		btnNewButton_1.setBounds(102, 319, 78, 31);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Shop shop = new Shop(user);
				
			}
			
		});
		frame.getContentPane().add(btnNewButton_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(228, 38, 425, 271);
		frame.getContentPane().add(scrollPane);

		verticalBox = Box.createVerticalBox();
		scrollPane.setViewportView(verticalBox);

		// DB에서 플랜 정보 조회
		try {
			st = (PreparedStatement) conn.prepareStatement("SELECT * FROM plan WHERE ID='" + user.id + "'");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				JCheckBox newCheckBox = new JCheckBox(rs.getString(2));
				if(rs.getInt(3) == 0) {
					newCheckBox.setSelected(false);
				}
				else {
					newCheckBox.setSelected(true);
					newCheckBox.setEnabled(false);
				}
				newCheckBox.setFont(new Font("굴림", Font.PLAIN, 14));
				newCheckBox.addItemListener(new PlanCheckEvent());
				newCheckBox.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e) {
						if(SwingUtilities.isRightMouseButton(e)&& e.getClickCount() == 1) {
							DeleteMenu menu = new DeleteMenu(newCheckBox,verticalBox);
							menu.show(e.getComponent(),e.getX(),e.getY());
						}
					}
				});
				verticalBox.add(newCheckBox);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		JButton btnNewButton_1_1 = new JButton("생성");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newPlan = JOptionPane.showInputDialog("생성할 계획을 적으세요");
				if (newPlan == null || newPlan.equals("")) {
					System.out.println("버튼 생성 취소");
					return;
				}

				try {
					String query = "INSERT INTO plan values('" + user.id + "','" + newPlan + "', 0)";
					Statement sta;
					sta = conn.createStatement();
					sta.execute(query);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				System.out.println("버튼 생성 완료");
				JCheckBox newCheckBox = new JCheckBox(newPlan);
				newCheckBox.setFont(new Font("굴림", Font.PLAIN, 14));
				newCheckBox.addItemListener(new PlanCheckEvent());
				newCheckBox.addMouseListener(new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e) {
						if(SwingUtilities.isRightMouseButton(e)) {
							DeleteMenu menu = new DeleteMenu(newCheckBox,verticalBox);
							menu.show(e.getComponent(),e.getX(),e.getY());
						}
					}
				});
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

		frame.setVisible(true);
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
				try {
					// 플랜을 done으로 업데이트
					String query = "UPDATE plan SET done = 1 Where (ID = '" + user.id
							+ "' AND plan_text = '" + plan_text + "' )";
					Statement sta;
					sta = conn.createStatement();
					sta.execute(query);
					
					// 플랜으로 인한 경험치와 포인트 갱신
					query = "UPDATE collection SET exp = " + RepAnimal.exp + " Where (ID = '" + user.id
							+ "' AND rep = 1 )";
					sta = conn.createStatement();
					sta.execute(query);

					query = "UPDATE user SET point = " + user.point + " Where (ID = '" + user.id + "')";
					sta = conn.createStatement();
					sta.execute(query);
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (RepAnimal.exp >= 100) {
				RepAnimal.level += 1;
				RepAnimal.exp -= 100;
				lblLevel.setText("LV : " + RepAnimal.level);
				boolean isEvolve =false;
				if(RepAnimal.level <= 10) {
					isEvolve = RepAnimal.Evolve(); // 조건에 맞으면 진화 , 진화를 하면 isEvolve가 참으로 바뀜
				}
				try {
					String query = "UPDATE collection SET level = " + RepAnimal.level + ", exp = "+RepAnimal.exp +" Where (ID = '" + user.id
							+ "' AND rep = 1 )";
					Statement sta;
					sta = conn.createStatement();
					sta.execute(query);
					
					// 만약 진화가 되었다면 collection의 타입을 바꾸어주고 표시되는 이미지를 교체해준다.
					if(isEvolve) {
						query = "UPDATE collection SET type = '" + RepAnimal.type + "' Where ( ID = '" + user.id + "' AND rep = 1)";
						sta = conn.createStatement();
						sta.execute(query);
						lblImage.setIcon(new ImageIcon(".\\image\\" + RepAnimal.type + ".gif"));
						
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			lblPoint.setText(user.point + "");
			frame.getContentPane().repaint();
			frame.getContentPane().revalidate();

		}
	}

	class DeleteMenu extends JPopupMenu {
		JMenuItem deleteItem;
		public DeleteMenu(JCheckBox clickedPlan,Box planBox) {
			deleteItem = new JMenuItem("삭제");
			deleteItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(SwingUtilities.isLeftMouseButton(e)&& e.getClickCount() == 1) {
						planBox.remove(clickedPlan);
						planBox.revalidate();
					}
				}
			});
			add(deleteItem);
		}
	}
}
