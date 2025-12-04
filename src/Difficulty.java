import java.security.PrivateKey;

public enum Difficulty {
	BABY(30),
	CHILD(20),
	NORMAL(10);
	
	private final int turns;
	private Difficulty(int turns) {
	    this.turns = turns;
	}
	public int getTurns() {
	    return turns;
	}
}
