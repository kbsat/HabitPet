
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class DBConnection {
	Connection con;
	User user;
	PreparedStatement preparedStatement;
	Statement statement;

	public Connection getConnection() {
		return con;
	}

	public DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/habitpet?serverTimezone=UTC", "root",
					"547418");
			statement = con.createStatement();
		} catch (Exception e) {
			System.out.println("데이터 베이스 연결 오류: " + e.getMessage());
		}
	}

	public User isUserCheck(String id, String password) { // 로그인
		try {

			preparedStatement = (PreparedStatement) con
					.prepareStatement("Select * from USER where ID=? AND password=?");

			preparedStatement.setString(1, id);
			preparedStatement.setString(2, password);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String id_db = rs.getString(1);
				int point = rs.getInt(3);
				user = new User(id_db, point);
				return user;

			} else {
				JOptionPane.showMessageDialog(null, "로그인 실패: 아이디와 패스워드를 확인해주세요.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void register(String id, String password) {
		String msg = "" + id;
		msg += " \n";

		try {
			String query = "INSERT INTO USER values('" + id + "','" + password + "','" + 0 + "')";
			int x = statement.executeUpdate(query);
			if (x == 0) {
				JOptionPane.showMessageDialog(null, "회원가입 실패");
			} else {

				JOptionPane.showMessageDialog(null, "환영합니다, " + msg + "님 회원 가입이 완료되었습니다");
			}

			con.close();

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void dupCheck(String id) {
		try {

			preparedStatement = (PreparedStatement) con.prepareStatement("Select ID from USER where ID=? ");

			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "아이디 중복: 사용할 수 없는 아이디 입니다.");
			} else {
				JOptionPane.showMessageDialog(null, "사용 가능한 아이디 입니다.");
			}

		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	// 첫 동물 생성 쿼리
	public void InsertFirstPet(Animal firstPet) {
		try {
			String query = "INSERT INTO collection values('" + user.id + "','" + firstPet.type + "','" + firstPet.name
					+ "','" + firstPet.level + "','" + firstPet.exp + "','" + firstPet.rep + "')";
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Animal LoadAnimalData() {
		Animal RepAnimal = null;
		try {
			preparedStatement = (PreparedStatement) con.prepareStatement("Select * from collection where id=?");
			preparedStatement.setString(1, user.id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String animalType = rs.getString(2);
				String animalName = rs.getString(3);
				int animalLevel = rs.getInt(4);
				int animalExp = rs.getInt(5);
				int animalRep = rs.getInt(6);

				// DB에서 동물들을 꺼내 user.collection에 담음
				Animal animal = new Animal(animalName, animalType, animalLevel, animalExp, animalRep);
				user.collection.add(animal);
				if (animalRep == 1) {
					RepAnimal = animal;
				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return RepAnimal;
	}

	public void InsertPlan(String newPlan) {
		try {
			String query = "INSERT INTO plan values('" + user.id + "','" + newPlan + "', 0)";
			statement = con.createStatement();
			statement.execute(query);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void SetPlanDone(String plan_text) {

		try {
			String query = "UPDATE plan SET done = 1 Where (ID = '" + user.id + "' AND plan_text = '" + plan_text
					+ "' )";
			statement = con.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void UpdatePoint(Animal RepAnimal) {
		try {
			String query = "UPDATE collection SET exp = " + RepAnimal.exp + " Where (ID = '" + user.id
					+ "' AND rep = 1 )";
			statement = con.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void UpdateExp(Animal RepAnimal) {

		try {
			String query = "UPDATE collection SET exp = " + RepAnimal.exp + " Where (ID = '" + user.id
					+ "' AND rep = 1 )";
			statement = con.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void LevelUp(boolean isEvolve,Animal RepAnimal) {
		try {
			String query = "UPDATE collection SET level = " + RepAnimal.level + ", exp = " + RepAnimal.exp
					+ " Where (ID = '" + user.id + "' AND rep = 1 )";
			statement.execute(query);

			// 만약 진화가 되었다면 collection의 타입을 바꾸어주고 표시되는 이미지를 교체해준다.
			if (isEvolve) {
				query = "UPDATE collection SET type = '" + RepAnimal.type + "' Where ( ID = '" + user.id
						+ "' AND rep = 1)";
				statement.execute(query);

			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void DeletePlan(String removedPlanName) {
		try {
			String query = "delete from plan where (id = '" + user.id + "' and plan_text = '" + removedPlanName
					+ "');";
			statement.execute(query);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
}
