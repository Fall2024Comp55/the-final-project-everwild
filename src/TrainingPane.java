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
        clearPreviews();
    }

    @Override
    public void hideContent() {
        for (GObject item : contents) {
            mainScreen.remove(item);
        }
        contents.clear();
    }

    // =============================================================
    // BACKGROUND (ORIGINAL SIZE)
    // =============================================================
    private void addBackground() {
        background = new GImage("Train.jpeg");
        background.setSize(mainScreen.getWidth() - 15, mainScreen.getHeight() - 30);
        contents.add(background);
        mainScreen.add(background);
    }

    private void switchBackgroundToTrain() {
        background.setImage("Train.jpeg");
        background.setSize(mainScreen.getWidth() - 15, mainScreen.getHeight() - 30);
    }

    private void switchBackgroundToRest() {
        background.setImage("RestBackground.jpeg");
        background.setSize(mainScreen.getWidth() - 15, mainScreen.getHeight() - 30);
    }

    private void switchBackgroundToBattle() {
        background.setImage("BattleBackground.jpeg");
        background.setSize(mainScreen.getWidth() - 15, mainScreen.getHeight() - 30);
    }

    // =============================================================
    // DIFFICULTY LABEL
    // =============================================================
    private void addDifficultyLabel() {
        difficultyLabel = new GLabel("Difficulty: " + mainScreen.getDifficulty(), 330, 450);
        difficultyLabel.setFont("Arial-18");
        difficultyLabel.setColor(Color.WHITE);
        contents.add(difficultyLabel);
        mainScreen.add(difficultyLabel);
    }

    // =============================================================
    // BUTTONS
    // =============================================================
    private void addButtons() {
        leftButton = new GImage("leftarrow.png");
        leftButton.setSize(50, 50);

        rightButton = new GImage("rightarrow.png");
        rightButton.setSize(50, 50);

        selectButton = new GImage(button.toString());
        selectButton.setSize(selectButtonSize.getWidth(), selectButtonSize.getHeight());
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
    // STAT WINDOW + PREVIEW LABELS
    // =============================================================
    private void addStatWindow() {

        GRect statBox = new GRect(200, 300);
        statBox.setFilled(true);
        statBox.setFillColor(new Color(91, 87, 75));
        statBox.setLocation(777, 240);
        statBox.setLineWidth(0);

        contents.add(statBox);
        mainScreen.add(statBox);

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
    // PREVIEW LOGIC
    // =============================================================
    private void clearPreviews() {
        strengthPreview.setLabel("");
        speedPreview.setLabel("");
        defensePreview.setLabel("");
        fatiguePreview.setLabel("");
    }

    private int getStatGain() {
        Difficulty d = mainScreen.getDifficulty();
        return (d == Difficulty.BABY) ? 2 : 1;
    }

    private int getFatigueGain() {
        Difficulty d = mainScreen.getDifficulty();
        return (d == Difficulty.NORMAL) ? 2 : 1;
    }

    private void showStrengthPreview() {
        clearPreviews();
        strengthPreview.setLabel("+" + getStatGain());
        fatiguePreview.setLabel("+" + getFatigueGain());
    }

    private void showAgilityPreview() {
        clearPreviews();
        speedPreview.setLabel("+" + getStatGain());
        fatiguePreview.setLabel("+" + getFatigueGain());
    }

    private void showDefensePreview() {
        clearPreviews();
        defensePreview.setLabel("+" + getStatGain());
        fatiguePreview.setLabel("+" + getFatigueGain());
    }

    // =============================================================
    // STAT ANIMATION
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
    // TRAINING METHODS
    // =============================================================
    private void trainStrength() {
        int old = monster.getStrength();
        int fatOld = monster.getFatigue();

        monster.setStrength(old + getStatGain());
        monster.setFatigue(fatOld + getFatigueGain());

        animateStatIncrease(strengthLabel, old, monster.getStrength());
        animateStatIncrease(fatigueLabel, fatOld, monster.getFatigue());

        clearPreviews();
    }

    private void trainAgility() {
        int old = monster.getSpeed();
        int fatOld = monster.getFatigue();

        monster.setSpeed(old + getStatGain());
        monster.setFatigue(fatOld + getFatigueGain());

        animateStatIncrease(speedLabel, old, monster.getSpeed());
        animateStatIncrease(fatigueLabel, fatOld, monster.getFatigue());

        clearPreviews();
    }

    private void trainDefense() {
        int old = monster.getDefense();
        int fatOld = monster.getFatigue();

        monster.setDefense(old + getStatGain());
        monster.setFatigue(fatOld + getFatigueGain());

        animateStatIncrease(defenseLabel, old, monster.getDefense());
        animateStatIncrease(fatigueLabel, fatOld, monster.getFatigue());

        clearPreviews();
    }

    // =============================================================
    // REST
    // =============================================================
    private void RestMonster() {
        int old = monster.getFatigue();
        int newFat = Math.max(0, old - 2);

        monster.setFatigue(newFat);
        animateStatIncrease(fatigueLabel, old, newFat);

        clearPreviews();
    }

    // =============================================================
    // MAIN BUTTON CLICK
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
                default:
                    button = TrainingButton.TRAIN;
                    selectButton.setImage(button.toString());
                    switchBackgroundToTrain();
                    clearPreviews();
                    break;
            }

            selectButton.setSize(selectButtonSize.getWidth(), selectButtonSize.getHeight());
        }
    }

    // =============================================================
    // BUTTON CYCLING (NEW ORDER)
    // =============================================================
    private void changeButtonOptionRight() {

        switch (button) {
            case TRAIN: button = TrainingButton.STRENGTH; break;
            case STRENGTH: button = TrainingButton.AGILITY; break;
            case AGILITY: button = TrainingButton.DEFENSE; break;
            case DEFENSE: button = TrainingButton.REST; break;
            case REST: button = TrainingButton.BATTLE; break;
            case BATTLE: button = TrainingButton.TRAIN; break;
            case BACK: button = TrainingButton.TRAIN; break;
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

    private void changeButtonOptionLeft() {

        switch (button) {
            case TRAIN: button = TrainingButton.BATTLE; break;
            case BATTLE: button = TrainingButton.REST; break;
            case REST: button = TrainingButton.DEFENSE; break;
            case DEFENSE: button = TrainingButton.AGILITY; break;
            case AGILITY: button = TrainingButton.STRENGTH; break;
            case STRENGTH: button = TrainingButton.TRAIN; break;
            case BACK: button = TrainingButton.TRAIN; break;
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
        // Future
    }
}
