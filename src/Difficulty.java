import java.security.PrivateKey;

public enum Difficulty {
	BABY(80),
	CHILD(50),
	NORMAL(20);
	
	private final int turns;
	private Difficulty(int turns) {
	    this.turns = turns;
	}
	public int getTurns() {
	    return turns;
	}
}
