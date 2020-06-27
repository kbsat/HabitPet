public class Animal {
	String type; // 동물의 이미지 경로
	String name; // 동물의 이름
	int level; // 동물의 레벨
	int exp; // 동물의 경험치 0 ~ 100
	int rep; // 대표 동물인지 아닌지 1이면 true 0이면 false

	public Animal(String name, String type) {
		this.name = name;
		this.type = type;
		this.level = 1;
		this.exp = 0;
		this.rep = 0;
	}

	public Animal(String name, String type, int level, int exp, int rep) {
		this.name = name;
		this.type = type;
		this.level = level;
		this.exp = exp;
		this.rep = rep;
	}

	public void SetRep() {
		this.rep = 1;
	}

	public void ResetRep() {
		this.rep = 0;
	}

	public void SetName(String name) {
		this.name = name;
	}

	public boolean Evolve() {
		if (level >= 5 && !type.contains("_1")) {
			type += "_1";
			return true;
		} else if (level >= 10 && type.contains("_1")) {
			type = type.replace("_1", "_2");
			return true;
		}

		return false;
	}

}
