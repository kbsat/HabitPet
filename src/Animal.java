
public class Animal {
	String type; // 동물의 이미지 경로
	String name; // 동물의 이름
	int level; // 동물의 레벨
	int exp; //  동물의 경험치 0 ~ 100
	public Animal(String type) {
		this.type = type;
		this.level = 1;
		this.exp = 0;
	}
	
	public void SetName(String name) {
		this.name = name;
	}
	
	
	
}
