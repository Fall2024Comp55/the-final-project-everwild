
public class Monster {
    private int fatigue, strength, speed, defense, health;
    private MonsterType monsterType;
    private String imagePath;

    public Monster(int fatigue, int strength, int speed, int defense, int health, MonsterType monsterType) {
        super();
        this.fatigue = fatigue;
        this.strength = strength;
        this.speed = speed;
        this.defense = defense;
        this.health = health;
        this.monsterType = monsterType;
    }

    public int getFatigue() { return fatigue; }
    public void setFatigue(int fatigue) { this.fatigue = fatigue; }

    public int getStrength() { return strength; }
    public void setStrength(int strength) { this.strength = strength; }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }

    public int getDefense() { return defense; }
    public void setDefense(int defense) { this.defense = defense; }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }

    public MonsterType getMonsterType() { return monsterType; }
    public void setMonsterType(MonsterType monsterType) { this.monsterType = monsterType; }

    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public String getImagePath() { return imagePath; }

    public String getNormalImage() {
        switch (monsterType) {
            case BILLABONG: return "Billabong.png";
            case SOCKGUY:   return "sockNormal.png";
            case CLAYGUY:   return "CuteMonster.png";
            case SECRET: 	return "redbong.png";
        }
        return "";
    }

    public String getStrongImage() {
        switch (monsterType) {
            case BILLABONG: return "Buffbong.png";
            case SOCKGUY:   return "sockBuff.png";
            case CLAYGUY:   return "StrongerCuteMonster.png";
            case SECRET: 	return "redbong.png";
        }
        return "";
    }

    public String getSleepImage() {
        switch (monsterType) {
            case BILLABONG: return "TiredBillabong.png";
            case SOCKGUY:   return "sockSleep.png";
            case CLAYGUY:   return "SleepCuteMonster.png";
            case SECRET: 	return "redbong.png";
        }
        return "";
    }
    public String getBattleImage() {
        switch (monsterType) {
            case BILLABONG: return "BuffBong.png";
            case SOCKGUY:   return "sockCombat.png";
            case CLAYGUY:   return "BattleCuteMonster.png";
            case SECRET: 	return "redbong.png";
        }
        return "";
    }
    public String getCritImage() {
    	switch(monsterType) {
    		case BILLABONG: return "BuffBongCrit.png";
    		case SOCKGUY: return "sockCrit.png";
    		case CLAYGUY: return "BattleCuteMonsterCrit.png";
            case SECRET: 	return "redbong.png";
    	}
    	return "";
    }
}
