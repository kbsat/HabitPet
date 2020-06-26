import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.mysql.cj.ParseInfo;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import java.awt.Panel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Label;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Insets;

public class Collection {

	private JFrame frame;
	public User user;
	public Animal RepAnimal;
	public Connection conn;
	
	public Collection(Connection conn, User user) {
		this.conn = conn;
		this.user = user;
		initialize();
	}
	
	class PetPanel extends JPanel{
		String petName;
		String petType;
		int petLevel;
		JLabel lblImage;
		JLabel lblName;
		JLabel lblLevel;
		
		PetPanel(Animal animal){
			setLayout(new BorderLayout(0,0));
			setAlignmentX(Component.CENTER_ALIGNMENT);
			petName = animal.name;
			petType = animal.type;
			petLevel = animal.level;
			
			lblImage = new JLabel();
			lblImage.setHorizontalAlignment(JLabel.CENTER);
			lblImage.setIcon(new ImageIcon(".\\image\\" + petType + ".gif"));
			lblLevel = new JLabel("Level "+Integer.toString(petLevel));
			lblName = new JLabel(petName);
			lblName.setBackground(Color.WHITE);
			lblName.setHorizontalAlignment(JLabel.CENTER);
			lblName.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 17));

			add(lblImage,BorderLayout.CENTER);
			add(lblName,BorderLayout.SOUTH);
			setBorder(new LineBorder(Color.black));
			setBackground(Color.white);
			setSize(200,120);
		}
	}
	private void initialize() {		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JPanel collectionPanel = new JPanel();
		collectionPanel.setBackground(Color.white);
		collectionPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		collectionPanel.setBounds(14, 58, 674, 350);
		frame.getContentPane().add(collectionPanel);
		collectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 5));
		
		for (Animal animal : user.collection) {
			PetPanel petPanel = new PetPanel(animal);
			
			if(animal.rep == 1) {
				petPanel.setBorder(new LineBorder(Color.red));
			}
			collectionPanel.add(petPanel);
		}
		
		
		
		JLabel lblNewLabel = new JLabel("컬렉션");
		lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 25));
		lblNewLabel.setBounds(14, 12, 118, 36);
		frame.getContentPane().add(lblNewLabel);
		
		
		frame.setBackground(new Color(169, 169, 169));
		frame.setBounds(100, 100, 720, 456);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
