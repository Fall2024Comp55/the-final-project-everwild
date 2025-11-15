import java.awt.event.MouseEvent;
import acm.graphics.GDimension;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import java.awt.Color;

public class TrainingPane extends GraphicsPane {

    private MainApplication mainScreen;
    private Monster monster;  // << THE MONSTER SELECTED BY PLAYER

    private GImage leftButton;
    private GImage rightButton;
    private GImage selectButton;
    private GImage background;
    private GImage statWindow;

    private GLabel strengthLabel;
    private GLabel speedLabel;
    private GLabel defenseLabel;
    private GLabel fatigueLabel;
    private GLabel difficultyLabel;

    private GDimension selectButtonSize = new GDimension(120, 50);
    private TrainingButton button;

   
    public TrainingPane(MainApplication mainScreen, Monster monster) {
        this.mainScreen = mainScreen;
        this.monster = monster;
        this.button = TrainingButton.TRAIN;
    }


    @Override
    public void showContent() {
        addBackground();
        addButtons();
        addStatWindow();   // loads stats from monster
        addDifficultyLabel();
        addBorders();
    }

    @Override
    public void hideContent() {
        for (GObject item : contents) {
            mainScreen.remove(item);
        }
        contents.clear();
    }
    private void addDifficultyLabel() {
        String text = "Difficulty: " + mainScreen.getDifficulty().toString();
        difficultyLabel = new GLabel(text, 330, 450);
        difficultyLabel.setFont("Arial-18");
        difficultyLabel.setColor(Color.WHITE);

        contents.add(difficultyLabel);
        mainScreen.add(difficultyLabel);
    }


    private void addButtons() {
        leftButton = new GImage("leftarrow.jpeg");
        leftButton.setSize(50, 50);

        rightButton = new GImage("rightarrow.jpeg");
        rightButton.setSize(50, 50);

        selectButton = new GImage(button.toString());
        selectButton.setSize(selectButtonSize);
        selectButton.setLocation(330, 370);

        leftButton.setLocation(selectButton.getX() - leftButton.getWidth() - 10, selectButton.getY());
        rightButton.setLocation(selectButton.getX() + selectButton.getWidth() + 10, selectButton.getY());

        contents.add(leftButton);
        contents.add(rightButton);
        contents.add(selectButton);

        mainScreen.add(leftButton);
        mainScreen.add(rightButton);
        mainScreen.add(selectButton);
    }

    private void addBackground() {
        background = new GImage("Train.jpeg");
        background.setSize(mainScreen.getWidth() - 15, mainScreen.getHeight() - 30);

        contents.add(background);
        mainScreen.add(background);
    }


   
    private void addStatWindow() {

        // Smaller, lower-right box
        GRect statBox = new GRect(200, 280);
        statBox.setFilled(true);
        statBox.setFillColor(Color.LIGHT_GRAY);

        // move it into your bottom-right UI rectangle
        statBox.setLocation(770, 260);

        contents.add(statBox);
        mainScreen.add(statBox);

        // Position labels slightly inside the box
        strengthLabel = new GLabel("", 790, 300);
        speedLabel = new GLabel("", 790, 340);
        defenseLabel = new GLabel("", 790, 380);
        fatigueLabel = new GLabel("", 790, 420);

        strengthLabel.setFont("Arial-18");
        speedLabel.setFont("Arial-18");
        defenseLabel.setFont("Arial-18");
        fatigueLabel.setFont("Arial-18");

        contents.add(strengthLabel);
        contents.add(speedLabel);
        contents.add(defenseLabel);
        contents.add(fatigueLabel);

        mainScreen.add(strengthLabel);
        mainScreen.add(speedLabel);
        mainScreen.add(defenseLabel);
        mainScreen.add(fatigueLabel);

        updateStatDisplay();
    }
    
    private void addBorders() {
    	
    }
    private void updateStatDisplay() {
        strengthLabel.setLabel("Strength: " + monster.getStrength());
        speedLabel.setLabel("Agility: " + monster.getSpeed());
        defenseLabel.setLabel("Defense: " + monster.getDefense());
        fatigueLabel.setLabel("Fatigue: " + monster.getFatigue());
    }
    private void endTurn() {
        mainScreen.setTurnsRemaining(mainScreen.getTurnsRemaining()-1);
        mainScreen.switchToTurnsScreen();
	}


    private void RestMonster() {
        int f = monster.getFatigue();
        f -= 2;
        if (f < 0) f = 0;
        monster.setFatigue(f);
        updateStatDisplay();
        endTurn();
    }

    private void trainStrength() {
        Difficulty diff = mainScreen.getDifficulty();

        switch (diff) {

            case BABY:
                monster.setStrength(monster.getStrength() + 2);
                monster.setFatigue(monster.getFatigue() + 1);
                break;

            case CHILD:
                monster.setStrength(monster.getStrength() + 1);
                monster.setFatigue(monster.getFatigue() + 1);
                break;

            case NORMAL:
                if (Math.random() < 0.75) {        // 50% chance
                    monster.setStrength(monster.getStrength() + 1);
                }
                monster.setFatigue(monster.getFatigue() + 2);
                break;
        }

        updateStatDisplay();
        endTurn();
    }

    private void trainAgility() {
        Difficulty diff = mainScreen.getDifficulty();

        switch (diff) {

            case BABY:
                monster.setSpeed(monster.getSpeed() + 2);
                monster.setFatigue(monster.getFatigue() + 1);
                break;

            case CHILD:
                monster.setSpeed(monster.getSpeed() + 1);
                monster.setFatigue(monster.getFatigue() + 1);
                break;

            case NORMAL:
                if (Math.random() < 0.75) {
                    monster.setSpeed(monster.getSpeed() + 1);
                }
                monster.setFatigue(monster.getFatigue() + 2);
                break;
        }

        updateStatDisplay();
        endTurn();
    }

    private void trainDefense() {
        Difficulty diff = mainScreen.getDifficulty();

        switch (diff) {

            case BABY:
                monster.setDefense(monster.getDefense() + 2);
                monster.setFatigue(monster.getFatigue() + 1);
                break;

            case CHILD:
                monster.setDefense(monster.getDefense() + 1);
                monster.setFatigue(monster.getFatigue() + 1);
                break;

            case NORMAL:
                if (Math.random() < 0.75) {
                    monster.setDefense(monster.getDefense() + 1);
                }
                monster.setFatigue(monster.getFatigue() + 2);
                break;
        }

        updateStatDisplay();
        endTurn();
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        GObject clicked = mainScreen.getElementAtLocation(e.getX(), e.getY());
        if (clicked == null) return;

        if (clicked == leftButton) changeButtonOptionLeft();
        if (clicked == rightButton) changeButtonOptionRight();

        if (clicked == selectButton) {
            switch (button) {

                case TRAIN:
                    button = TrainingButton.STRENGTH;
                    selectButton.setImage(button.toString());
                    break;

                case STRENGTH:
                    trainStrength();
                    break;

                case AGILITY:
                    trainAgility();
                    break;

                case DEFENSE:
                    trainDefense();
                    break;

                case REST:
                    RestMonster();
                    break;

                case BATTLE:
                    BattleDifficultySelect();
                    break;

                case BACK:
                    button = TrainingButton.TRAIN;
                    selectButton.setImage(button.toString());
                    break;
            }

            selectButton.setSize(selectButtonSize);
        }
    }


    private void changeButtonOptionRight() {
        switch (button) {
            case TRAIN: button = TrainingButton.REST; break;
            case REST: button = TrainingButton.BATTLE; break;
            case BATTLE: button = TrainingButton.TRAIN; break;

            case STRENGTH: button = TrainingButton.AGILITY; break;
            case AGILITY: button = TrainingButton.DEFENSE; break;
            case DEFENSE: button = TrainingButton.BACK; break;
            case BACK: button = TrainingButton.STRENGTH; break;
        }
        selectButton.setImage(button.toString());
        selectButton.setSize(selectButtonSize);
    }

    private void changeButtonOptionLeft() {
        switch (button) {
            case TRAIN: button = TrainingButton.BATTLE; break;
            case BATTLE: button = TrainingButton.REST; break;
            case REST: button = TrainingButton.TRAIN; break;

            case STRENGTH: button = TrainingButton.BACK; break;
            case BACK: button = TrainingButton.DEFENSE; break;
            case DEFENSE: button = TrainingButton.AGILITY; break;
            case AGILITY: button = TrainingButton.STRENGTH; break;
        }
        selectButton.setImage(button.toString());
        selectButton.setSize(selectButtonSize);
    }

    private void BattleDifficultySelect() {
        // TODO: implement later
    }

}
