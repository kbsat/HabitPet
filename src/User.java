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

/*public class User {
	private String id;
    private String password;
    private String point;

    public String getID() {
        return id;
    }
    public void setID(String id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPoint() {
        return point;
    }
    public void setPoint(String point) {
        this.point = point;
    } */