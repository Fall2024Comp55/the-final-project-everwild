import java.security.PrivateKey;

public enum Difficulty {
    BABY(30, "Thirty.jpeg"),
    CHILD(20, "Twenty.jpeg"),
    NORMAL(10, "Ten.jpeg");

    private final int turns;
    private final String backgroundImage;

    private Difficulty(int turns, String backgroundImage) {
        this.turns = turns;
        this.backgroundImage = backgroundImage;
    }

    public int getTurns() {
        return turns;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }
}
