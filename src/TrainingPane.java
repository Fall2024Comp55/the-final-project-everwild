import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GDimension;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;

public class TrainingPane extends GraphicsPane {

    private MainApplication mainScreen;
    private Monster monster; 

    private GImage leftButton;
    private GImage rightButton;
    private GImage selectButton;
    private GImage background;

    private GLabel strengthLabel;
    private GLabel speedLabel;
    private GLabel defenseLabel;
    private GLabel fatigueLabel;
    private GLabel difficultyLabel;

    // Preview labels (only used for selected stat)
    private GLabel strengthPreview;
    private GLabel speedPreview;
    private GLabel defensePreview;
    private GLabel fatiguePreview;

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
        addStatWindow();
        addDifficultyLabel();
        clearPreviews();        // ← no preview at start
    }

    @Override
    public void hideContent() {
        for (GObject item : contents) {
            mainScreen.remove(item);
        }
        contents.clear();
    }


    // =============================================================
    // DIFFICULTY LABEL
    // =============================================================
    private void addDifficultyLabel() {
        String text = "Difficulty: " + mainScreen.getDifficulty().toString();
        difficultyLabel = new GLabel(text, 330, 450);
        difficultyLabel.setFont("Arial-18");
        difficultyLabel.setColor(Color.WHITE);

        contents.add(difficultyLabel);
        mainScreen.add(difficultyLabel);
    }


    // =============================================================
    // BUTTONS
    // =============================================================
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


    // =============================================================
    // BACKGROUND
    // =============================================================
    private void addBackground() {
        background = new GImage("Train.jpeg");
        background.setSize(mainScreen.getWidth() - 15, mainScreen.getHeight() - 30);

        contents.add(background);
        mainScreen.add(background);
    }

    private void switchBackgroundToRest() {
        background.setImage("RestBackground.jpeg");
        background.setSize(mainScreen.getWidth() - 15, mainScreen.getHeight() - 30);
    }

    private void switchBackgroundToTrain() {
        background.setImage("Train.jpeg");
        background.setSize(mainScreen.getWidth() - 15, mainScreen.getHeight() - 30);
    }

    private void switchBackgroundToBattle() {
        background.setImage("BattleBackground.jpeg");
        background.setSize(mainScreen.getWidth() - 15, mainScreen.getHeight() - 30);
    }


    // =============================================================
    // STAT WINDOW + PREVIEW LABELS
    // =============================================================
    private void addStatWindow() {

        GRect statBox = new GRect(260, 300);
        statBox.setFilled(true);
        statBox.setFillColor(Color.LIGHT_GRAY);
        statBox.setLocation(740, 240);

        contents.add(statBox);
        mainScreen.add(statBox);

        // Real stat labels
        strengthLabel = new GLabel("", 790, 300);
        speedLabel = new GLabel("", 790, 340);
        defenseLabel = new GLabel("", 790, 380);
        fatigueLabel = new GLabel("", 790, 420);

        strengthLabel.setFont("Arial-18");
        speedLabel.setFont("Arial-18");
        defenseLabel.setFont("Arial-18");
        fatigueLabel.setFont("Arial-18");

        mainScreen.add(strengthLabel);
        mainScreen.add(speedLabel);
        mainScreen.add(defenseLabel);
        mainScreen.add(fatigueLabel);

        // PREVIEW LABELS — start empty
        Color previewColor = new Color(120, 120, 120);

        strengthPreview = new GLabel("", 950, 300);
        speedPreview = new GLabel("", 950, 340);
        defensePreview = new GLabel("", 950, 380);
        fatiguePreview = new GLabel("", 950, 420);

        strengthPreview.setColor(previewColor);
        speedPreview.setColor(previewColor);
        defensePreview.setColor(previewColor);
        fatiguePreview.setColor(previewColor);

        strengthPreview.setFont("Arial-16");
        speedPreview.setFont("Arial-16");
        defensePreview.setFont("Arial-16");
        fatiguePreview.setFont("Arial-16");

        mainScreen.add(strengthPreview);
        mainScreen.add(speedPreview);
        mainScreen.add(defensePreview);
        mainScreen.add(fatiguePreview);

        updateStatDisplay();
    }


    private void updateStatDisplay() {
        strengthLabel.setLabel("Strength: " + monster.getStrength());
        speedLabel.setLabel("Agility: " + monster.getSpeed());
        defenseLabel.setLabel("Defense: " + monster.getDefense());
        fatigueLabel.setLabel("Fatigue: " + monster.getFatigue());
    }


    // =============================================================
    // PREVIEW HELPERS
    // =============================================================
    private void clearPreviews() {
        strengthPreview.setLabel("");
        speedPreview.setLabel("");
        defensePreview.setLabel("");
        fatiguePreview.setLabel("");
    }

    private void showStrengthPreview() {
        clearPreviews();

        Difficulty d = mainScreen.getDifficulty();
        int gain = (d == Difficulty.BABY) ? 2 :
                   (d == Difficulty.CHILD) ? 1 : 1;

        strengthPreview.setLabel("+" + gain);
        // fatigue also increases
        int fatGain = (d == Difficulty.NORMAL) ? 2 : 1;
        fatiguePreview.setLabel("+" + fatGain);
    }

    private void showAgilityPreview() {
        clearPreviews();

        Difficulty d = mainScreen.getDifficulty();
        int gain = (d == Difficulty.BABY) ? 2 :
                   (d == Difficulty.CHILD) ? 1 : 1;

        speedPreview.setLabel("+" + gain);

        int fatGain = (d == Difficulty.NORMAL) ? 2 : 1;
        fatiguePreview.setLabel("+" + fatGain);
    }

    private void showDefensePreview() {
        clearPreviews();

        Difficulty d = mainScreen.getDifficulty();
        int gain = (d == Difficulty.BABY) ? 2 :
                   (d == Difficulty.CHILD) ? 1 : 1;

        defensePreview.setLabel("+" + gain);

        int fatGain = (d == Difficulty.NORMAL) ? 2 : 1;
        fatiguePreview.setLabel("+" + fatGain);
    }


    // =============================================================
    // ANIMATION
    // =============================================================
    private void animateStatIncrease(GLabel label, int oldVal, int newVal) {
        int step = (newVal > oldVal) ? 1 : -1;

        while (oldVal != newVal) {
            oldVal += step;
            label.setLabel(label.getLabel().split(":")[0] + ": " + oldVal);
            mainScreen.pause(120);
        }
    }


    // =============================================================
    // TRAINING METHODS WITH ANIMATION
    // =============================================================
    private void trainStrength() {

        int oldS = monster.getStrength();
        int oldF = monster.getFatigue();

        Difficulty d = mainScreen.getDifficulty();

        if (d == Difficulty.BABY) {
            monster.setStrength(oldS + 2);
            monster.setFatigue(oldF + 1);
        } else if (d == Difficulty.CHILD) {
            monster.setStrength(oldS + 1);
            monster.setFatigue(oldF + 1);
        } else {
            if (Math.random() < 0.75) monster.setStrength(oldS + 1);
            monster.setFatigue(oldF + 2);
        }

        animateStatIncrease(strengthLabel, oldS, monster.getStrength());
        animateStatIncrease(fatigueLabel, oldF, monster.getFatigue());
        clearPreviews();
    }

    private void trainAgility() {

        int oldA = monster.getSpeed();
        int oldF = monster.getFatigue();

        Difficulty d = mainScreen.getDifficulty();

        if (d == Difficulty.BABY) {
            monster.setSpeed(oldA + 2);
            monster.setFatigue(oldF + 1);
        } else if (d == Difficulty.CHILD) {
            monster.setSpeed(oldA + 1);
            monster.setFatigue(oldF + 1);
        } else {
            if (Math.random() < 0.75) monster.setSpeed(oldA + 1);
            monster.setFatigue(oldF + 2);
        }

        animateStatIncrease(speedLabel, oldA, monster.getSpeed());
        animateStatIncrease(fatigueLabel, oldF, monster.getFatigue());
        clearPreviews();
    }

    private void trainDefense() {

        int oldD = monster.getDefense();
        int oldF = monster.getFatigue();

        Difficulty d = mainScreen.getDifficulty();

        if (d == Difficulty.BABY) {
            monster.setDefense(oldD + 2);
            monster.setFatigue(oldF + 1);
        } else if (d == Difficulty.CHILD) {
            monster.setDefense(oldD + 1);
            monster.setFatigue(oldF + 1);
        } else {
            if (Math.random() < 0.75) monster.setDefense(oldD + 1);
            monster.setFatigue(oldF + 2);
        }

        animateStatIncrease(defenseLabel, oldD, monster.getDefense());
        animateStatIncrease(fatigueLabel, oldF, monster.getFatigue());
        clearPreviews();
    }


    // =============================================================
    // REST
    // =============================================================
    private void RestMonster() {

        int oldF = monster.getFatigue();
        int newF = oldF - 2;
        if (newF < 0) newF = 0;

        monster.setFatigue(newF);
        animateStatIncrease(fatigueLabel, oldF, newF);

        clearPreviews();
    }


    // =============================================================
    // MOUSE CLICK HANDLING
    // =============================================================
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
                    switchBackgroundToTrain();
                    clearPreviews();
                    break;

                case STRENGTH:
                    switchBackgroundToTrain();
                    trainStrength();
                    break;

                case AGILITY:
                    switchBackgroundToTrain();
                    trainAgility();
                    break;

                case DEFENSE:
                    switchBackgroundToTrain();
                    trainDefense();
                    break;

                case REST:
                    switchBackgroundToRest();
                    RestMonster();
                    break;

                case BATTLE:
                    switchBackgroundToBattle();
                    BattleDifficultySelect();
                    clearPreviews();
                    break;

                case BACK:
                    button = TrainingButton.TRAIN;
                    selectButton.setImage(button.toString());
                    switchBackgroundToTrain();
                    clearPreviews();
                    break;
            }

            selectButton.setSize(selectButtonSize);
        }
    }


    // =============================================================
    // CYCLING BUTTONS (LEFT/RIGHT)
    // =============================================================
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

        // backgrounds
        if (button == TrainingButton.REST) switchBackgroundToRest();
        else if (button == TrainingButton.BATTLE) switchBackgroundToBattle();
        else switchBackgroundToTrain();

        // previews
        if (button == TrainingButton.STRENGTH) showStrengthPreview();
        else if (button == TrainingButton.AGILITY) showAgilityPreview();
        else if (button == TrainingButton.DEFENSE) showDefensePreview();
        else clearPreviews();
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

        if (button == TrainingButton.REST) switchBackgroundToRest();
        else if (button == TrainingButton.BATTLE) switchBackgroundToBattle();
        else switchBackgroundToTrain();

        if (button == TrainingButton.STRENGTH) showStrengthPreview();
        else if (button == TrainingButton.AGILITY) showAgilityPreview();
        else if (button == TrainingButton.DEFENSE) showDefensePreview();
        else clearPreviews();
    }


    private void BattleDifficultySelect() {
        // TODO for future
    }

}
