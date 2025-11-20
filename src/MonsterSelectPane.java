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
        background = new GImage("monsterselectscreen.jpeg", 0, 0);
        background.setSize(mainScreen.getWidth()-15, mainScreen.getHeight()-30);
        mainScreen.add(background);
        contents.add(background);
    }

    private void addMonsters() {
        monster1 = new GImage("Baby.jpeg", 150, 250);
        monster2 = new GImage("Child.jpeg", 400, 250);
        monster3 = new GImage("CuteMonster.png", 650, 250);

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

        Monster selectedMonster = null;

        if (clicked == monster1) {
            System.out.println("Monster 1 selected!");
            selectedMonster = createMonster(MonsterType.BILLABONG);
        } 
        else if (clicked == monster2) {
            System.out.println("Monster 2 selected!");
            selectedMonster = createMonster(MonsterType.SOCKGUY);
        } 
        else if (clicked == monster3) {
            System.out.println("Monster 3 selected!");
            selectedMonster = createMonster(MonsterType.CLAYGUY);
        }

        if (selectedMonster != null) {
            mainScreen.setMonster(selectedMonster);  
            mainScreen.switchToTurnsScreen();       
        }

    }
    private Monster createMonster(MonsterType type) {
        switch (type) {
            case BILLABONG:
                return new Monster(
                    0,   // fatigue
                    5,   // strength
                    4,   // speed
                    6,   // defense
                    20,  // health
                    type
                );

            case SOCKGUY:
                return new Monster(
                    0,
                    3,
                    7,
                    3,
                    18,
                    type
                );

            case CLAYGUY:
                return new Monster(
                    0,
                    6,
                    3,
                    5,
                    22,
                    type
                );
        }
        return null;
    }


}
