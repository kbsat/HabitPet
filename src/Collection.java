
import java.awt.FlowLayout;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;


public class Collection {

	public JFrame frame;
	private User user;
	private Connection conn;
	private PetPanel repPetPanel;
	public Animal repAnimal;
	public boolean checking = false; // rep 변경이 되었는지 안되었는지 확인
	public Collection(Connection conn, User user) {
		this.conn = conn;
		this.user = user;
		initialize();
	}

	class PetPanel extends JPanel {
		Animal animal;
		int petLevel;
		JLabel lblImage;
		JLabel lblName;
		JLabel lblLevel;

		PetPanel(Animal animal) {
			this.animal = animal;
			setLayout(new BorderLayout(0, 0));
			setAlignmentX(Component.CENTER_ALIGNMENT);

			lblImage = new JLabel();
			lblImage.setHorizontalAlignment(JLabel.CENTER);
			lblImage.setIcon(new ImageIcon(".\\image\\" + animal.type + ".gif"));
			lblLevel = new JLabel("Level " + Integer.toString(animal.level));
			lblName = new JLabel(animal.name);
			lblName.setBackground(Color.WHITE);
			lblName.setHorizontalAlignment(JLabel.CENTER);
			lblName.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 17));

			add(lblImage, BorderLayout.CENTER);
			add(lblName, BorderLayout.SOUTH);
			setBorder(new LineBorder(Color.black));
			setBackground(Color.white);
			setSize(200, 120);
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(14, 58, 674, 350);
		
		JPanel collectionPanel = new JPanel();
		collectionPanel.setBackground(Color.white);
		collectionPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		collectionPanel.setBounds(14, 58, 674, 350);
		//frame.getContentPane().add(collectionPanel);
		collectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 5));
		collectionPanel.setLayout(new WrapLayout(FlowLayout.LEFT,25,5));
		scrollPane.setViewportView(collectionPanel);
		frame.getContentPane().add(scrollPane);

		for (Animal animal : user.collection) {
			PetPanel petPanel = new PetPanel(animal);
			petPanel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (SwingUtilities.isRightMouseButton(e)) {
						PopupMenu menu = new PopupMenu(petPanel);
						menu.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			});
			if (animal.rep == 1) {
				petPanel.setBorder(BorderFactory.createLineBorder(Color.red,3));
				repPetPanel = petPanel;
			}
			collectionPanel.add(petPanel);
		}

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 25));
		lblNewLabel.setBounds(14, 0, 219, 61);
		frame.getContentPane().add(lblNewLabel);
		
	    lblNewLabel.setIcon(new ImageIcon(".\\image\\collectionLogo.png"));

		frame.setBackground(new Color(169, 169, 169));
		frame.setBounds(100, 100, 720, 456);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) { 
                    frame.setVisible(false);
                    System.out.println("frame.setvisible을 false로");
            }
    });
	}

	// 팝업 메뉴
	class PopupMenu extends JPopupMenu {
		JMenuItem representItem;
		PetPanel petPanel;

		public PopupMenu(PetPanel petPanel) {
			this.petPanel = petPanel;
			representItem = new JMenuItem("대표 동물 설정");
			
			MenuActionListener menuActionListener = new MenuActionListener(petPanel);
			representItem.addActionListener(menuActionListener);
			
			add(representItem);
		}
	}

	// 딜리트 버튼 클릭 이벤트
	class MenuActionListener implements ActionListener {
		JCheckBox clickedPlan;
		PetPanel petPanel;

		MenuActionListener(PetPanel petPanel) {
			this.petPanel = petPanel;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String clickedText = e.getActionCommand();
			
			switch(clickedText) {
			case "대표 동물 설정":
				System.out.println("대표 동물 설정 클릭");
				petPanel.setBorder(BorderFactory.createLineBorder(Color.red,3));
				petPanel.animal.SetRep();
				repPetPanel.setBorder(new LineBorder(Color.black));
				repPetPanel.animal.ResetRep();
				repPetPanel = petPanel;
				
				// Main에 넘겨주기 위해 rep를 표시 위함 
				checking = true;
				repAnimal = repPetPanel.animal;
				
				try {
					// rep 변경
					String query = "UPDATE collection SET rep = 0 Where (ID = '" + user.id
							+ "' AND rep = 1 )";
					Statement sta;
					sta = conn.createStatement();
					sta.execute(query);
					
					query = "UPDATE collection SET rep = 1 Where (ID = '" + user.id
							+ "' AND name = '"+repPetPanel.animal.name +"' )";
					sta = conn.createStatement();
					sta.execute(query);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				break;
			}
		}
	}
}
