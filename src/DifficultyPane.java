import java.awt.event.MouseEvent;
import acm.graphics.*;

public class DifficultyPane extends GraphicsPane {
	
    private GImage babyBtn;
    private GImage childBtn;
    private GImage normalBtn;

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
        babyBtn = new GImage("baby.png", 250, 200);
        contents.add(babyBtn);
        mainScreen.add(babyBtn);

        // Child
        childBtn = new GImage("child.png", 450, 200);
        contents.add(childBtn);
        mainScreen.add(childBtn);

        // Normal
        normalBtn = new GImage("normal.png", 650, 200);
        contents.add(normalBtn);
        mainScreen.add(normalBtn);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GObject clicked = mainScreen.getElementAtLocation(e.getX(), e.getY());
        if(clicked == null) return;

        if(clicked == babyBtn) {
            mainScreen.setDifficulty(Difficulty.BABY);
            System.out.println("Difficulty set → BABY");
        }
        else if(clicked == childBtn) {
            mainScreen.setDifficulty(Difficulty.CHILD);
            System.out.println("Difficulty set → CHILD");
        }
        else if(clicked == normalBtn) {
            mainScreen.setDifficulty(Difficulty.NORMAL);
            System.out.println("Difficulty set → NORMAL");
        }
    }
}
