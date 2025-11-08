import java.security.PrivateKey;

public enum Difficulty {
	BABY(80),
	CHILD(50),
	NORMAL(20);
	
	private final int Turns;
	private Difficulty(int Turns) {
		this.Turns=Turns;
	}
	public int getTurns () {
		return Turns;
	}
}
