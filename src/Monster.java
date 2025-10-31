
public class Monster {
	private int fatigue,strength,speed,defense,health;
	private MonsterType monsterType;
	
	
	public Monster(int fatigue, int strength, int speed, int defense, int health, MonsterType monsterType) {
		super();
		this.fatigue = fatigue;
		this.strength = strength;
		this.speed = speed;
		this.defense = defense;
		this.health = health;
		this.monsterType = monsterType;
	}
	public int getFatigue() {
		return fatigue;
	}
	public void setFatigue(int fatigue) {
		this.fatigue = fatigue;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public MonsterType getMonsterType() {
		return monsterType;
	}
	public void setMonsterType(MonsterType monsterType) {
		this.monsterType = monsterType;
	}
	
	
	
}
