import java.awt.event.MouseEvent;
import acm.graphics.*;

public class DifficultyPane extends GraphicsPane {
	
    private GImage babyButton;
    private GImage childButton;
    private GImage normalButton;

    public DifficultyPane(MainApplication mainApplication) {
        this.mainScreen = mainApplication;
    }

    @Override
    public void showContent() {
        addOptions();
    }

    @Override
    public void hideContent() {
        for(GObject item : contents) {
            mainScreen.remove(item);
        }
        contents.clear();
    }

    private void addOptions() {
        // Baby
        babyButton = new GImage("baby.png", 100, 30);
        babyButton.scale(0.5);
        contents.add(babyButton);
        mainScreen.add(babyButton);

        // Child
        childButton = new GImage("child.png", 100, 200);
        childButton.scale(0.5);
        contents.add(childButton);
        mainScreen.add(childButton);

        // Normal
        normalButton = new GImage("normal.png", 100, 400);
        normalButton.scale(0.5);
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
