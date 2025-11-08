import java.awt.event.MouseEvent;
import acm.graphics.*;

public class DifficultyPane extends GraphicsPane {
	private GImage background;
    private GImage babyButton;
    private GImage childButton;
    private GImage normalButton;

    public DifficultyPane(MainApplication mainApplication) {
        this.mainScreen = mainApplication;
    }

    @Override
    public void showContent() {
    	addBackground();
        addOptions();
    }

    @Override
    public void hideContent() {
        for(GObject item : contents) {
            mainScreen.remove(item);
        }
        contents.clear();
    }
    private void addBackground() {
        background = new GImage("Difficulty background.jpeg", 0, 0);
        background.setSize(mainScreen.getWidth(), mainScreen.getHeight());

        contents.add(background);
        mainScreen.add(background);
    }

    private void addOptions() {
        // Baby
        babyButton = new GImage("Baby.jpeg", 100, 110);
        babyButton.scale(0.4);
        contents.add(babyButton);
        mainScreen.add(babyButton);

        // Child
        childButton = new GImage("Child.jpeg", 100, 250);
        childButton.scale(0.4);
        contents.add(childButton);
        mainScreen.add(childButton);

        // Normal
        normalButton = new GImage("Normal.jpeg", 100, 400);
        normalButton.scale(0.4);
        contents.add(normalButton);
        mainScreen.add(normalButton);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GObject clicked = mainScreen.getElementAtLocation(e.getX(), e.getY());
        if(clicked == null) return;

        if(clicked == babyButton) {
            mainScreen.setDifficulty(Difficulty.BABY);
            System.out.println("BABY");
            mainScreen.switchToStoryScreen();
        }
        else if(clicked == childButton) {
            mainScreen.setDifficulty(Difficulty.CHILD);
            System.out.println("CHILD");
            mainScreen.switchToStoryScreen();
        }
        else if(clicked == normalButton) {
            mainScreen.setDifficulty(Difficulty.NORMAL);
            System.out.println("NORMAL");
            mainScreen.switchToStoryScreen();
        }
        
    }
}
