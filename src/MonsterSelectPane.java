import java.awt.event.MouseEvent;
import acm.graphics.*;

public class MonsterSelectPane extends GraphicsPane {
    private GImage background;
    private GImage monster1;
    private GImage monster2;
    private GImage monster3;

    public MonsterSelectPane(MainApplication mainScreen) {
        this.mainScreen = mainScreen;
    }

    @Override
    public void showContent() {
        addBackground();
        addMonsters();
    }

    private void addBackground() {
        background = new GImage("Train.jpeg", 0, 0);
        background.setSize(mainScreen.getWidth(), mainScreen.getHeight());
        mainScreen.add(background);
        contents.add(background);
    }

    private void addMonsters() {
        monster1 = new GImage("Baby.jpeg", 150, 250);
        monster2 = new GImage("Child.jpeg", 400, 250);
        monster3 = new GImage("Normal.jpeg", 650, 250);
//temp photos
        monster1.scale(0.5);
        monster2.scale(0.5);
        monster3.scale(0.5);

        mainScreen.add(monster1);
        mainScreen.add(monster2);
        mainScreen.add(monster3);

        contents.add(monster1);
        contents.add(monster2);
        contents.add(monster3);
    }

    @Override
    public void hideContent() {
        for (GObject item : contents) {
            mainScreen.remove(item);
        }
        contents.clear();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GObject clicked = mainScreen.getElementAtLocation(e.getX(), e.getY());
        if (clicked == null) return;

        if (clicked == monster1) {
            System.out.println("Monster 1 selected!");
            mainScreen.switchToStoryScreen();
        } 
        else if (clicked == monster2) {
            System.out.println("Monster 2 selected!");
            mainScreen.switchToStoryScreen();
        } 
        else if (clicked == monster3) {
            System.out.println("Monster 3 selected!");
            mainScreen.switchToStoryScreen();
        }
    }
}
