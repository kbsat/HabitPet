import java.util.ArrayList;

public class User {
	public String id;
	public int point;
	public ArrayList<Animal> collection = new ArrayList<Animal>();
	public ArrayList<Plan> plans = new ArrayList<Plan>();
	
	User(String id,int point){
		this.id = id;
		this.point = point;
	}
	
}

