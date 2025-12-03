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
    
    // Description box (bottom-left)
    private GRect descriptionBox;
    private GLabel descriptionLabel;

    // Monster preview (top-right)
    private GRect monsterPreviewBox;
    private GLabel monsterPreviewLabel;
    private GImage monsterPreviewImage;
    private boolean isStrongForm = false;
    private GImage monsterAboveButtons;
    private GImage babyBattle;
    private GImage childBattle;
    private GImage normalBattle;

    private GLabel turnLabel;
    private int turns; // store the current number of turns

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
        addMonsterAboveButtons();
        updateMonsterAboveButtons();
        addButtons();
        addStatWindow(); 
        addMonsterPreviewImage(); // top-right temp monster
        addDifficultyLabel();
        addDescriptionBox();     // bottom-left description area
        clearPreviews();         // no preview at start
        updateModeDescription(); // describe current mode (TRAIN by default)
    }

    @Override
    public void hideContent() {
        for (GObject item : contents) {
            mainScreen.remove(item);
        }
        contents.clear();
    }

    private void updateMonsterPicture() {
        if (monster == null) return;
        String img = isStrongForm ? monster.getStrongImage() : monster.getNormalImage();
        monsterPreviewImage.setImage(img);
        monsterPreviewImage.setSize(230, 190);
        monsterPreviewImage.setLocation(730, 10);
    }

    private void showNormalMonster() {
        if (monster == null) return;
        monsterPreviewImage.setImage(monster.getNormalImage());
        monsterPreviewImage.setSize(230, 190);
        monsterPreviewImage.setLocation(730, 10);
    }

    private void showSleepingMonster() {
        if (monster == null) return;
        monsterPreviewImage.setImage(monster.getSleepImage());
        monsterPreviewImage.setSize(230, 190);
        monsterPreviewImage.setLocation(730, 10);
    }

    // =============================================================
    // DIFFICULTY LABEL
    // =============================================================
    private void addDifficultyLabel() {
        String text = "Difficulty: " + mainScreen.getDifficulty().toString();
        difficultyLabel = new GLabel(text, 250, 465);
        difficultyLabel.setFont(FontStyle.TITLE_FONT);
        difficultyLabel.setColor(Color.WHITE);

        contents.add(difficultyLabel);
        mainScreen.add(difficultyLabel);
    }

    private void addMonsterAboveButtons() {
        if (monster == null) return;
        monsterAboveButtons = new GImage(monster.getNormalImage());
        monsterAboveButtons.setSize(230, 190); // adjust as needed
        monsterAboveButtons.setLocation(500, 150); // adjust x/y to be above buttons

        contents.add(monsterAboveButtons);
        mainScreen.add(monsterAboveButtons);
    }
    private void updateDescriptionWithTurns(String actionText) {
        int remaining = mainScreen.getTurnsRemaining();
        updateDescription(actionText + "  |  Turns remaining: " + remaining);

        if (remaining <= 0) {
            System.out.println("Turns exhausted! Switching to battle...");
            mainScreen.switchToBattleScreen();
        }
    }


    private void updateMonsterAboveButtons() {
        if (monsterAboveButtons == null || monster == null) return;

        switch(button) {
            case TRAIN:
                monsterAboveButtons.setImage(monster.getNormalImage());
                break;
            case REST:
                monsterAboveButtons.setImage(monster.getSleepImage());
                break;
            case BATTLE:
                monsterAboveButtons.setImage(monster.getBattleImage()); // ready to fight
                break;
        }

        monsterAboveButtons.setSize(230, 190); // keep consistent
        monsterAboveButtons.setLocation(250, 150);
    }

    private void addDescriptionBox() {
        // Long gray rectangle in bottom-left of TrainingPane
        descriptionBox = new GRect(600, 80);
        descriptionBox.setFilled(true);
        descriptionBox.setFillColor(new Color(91, 87, 75));
        descriptionBox.setLocation(40, 470);
        descriptionBox.setLineWidth(0);
        descriptionBox.setColor(new Color(0, 0, 0, 0));

        descriptionLabel = new GLabel("", descriptionBox.getX() + 10, descriptionBox.getY() + 25);
        descriptionLabel.setFont(FontStyle.DESC_FONT);
        descriptionLabel.setColor(Color.WHITE);

        contents.add(descriptionBox);
        contents.add(descriptionLabel);
        mainScreen.add(descriptionBox);
        mainScreen.add(descriptionLabel);

        updateDescriptionWithTurns("Training menu: use the arrows to select TRAIN, REST, or BATTLE.");
    }

    private void updateDescription(String text) {
        if (descriptionLabel != null) {
            descriptionLabel.setLabel(text);
        }
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
    
    private void showBattleButtons() {
    	babyBattle=new GImage("baby.jpeg");
    	babyBattle.setLocation(230, 270);
    	babyBattle.setSize(75, 50);
    	childBattle=new GImage("child.jpeg");
    	childBattle.setLocation(330, 270);
    	childBattle.setSize(75, 50);
    	normalBattle=new GImage("normal.jpeg");
    	normalBattle.setLocation(430, 270);
    	normalBattle.setSize(75, 50);
    	
    	mainScreen.add(babyBattle);
    	contents.add(babyBattle);
    	mainScreen.add(childBattle);
    	contents.add(childBattle);
    	mainScreen.add(normalBattle);
    	contents.add(normalBattle);
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
        strengthLabel = new GLabel("", 790, 300);
        speedLabel = new GLabel("", 790, 340);
        defenseLabel = new GLabel("", 790, 380);
        fatigueLabel = new GLabel("", 790, 420);

        strengthLabel.setFont(FontStyle.STAT_FONT);
        speedLabel.setFont(FontStyle.STAT_FONT);
        defenseLabel.setFont(FontStyle.STAT_FONT);
        fatigueLabel.setFont(FontStyle.STAT_FONT);


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

        strengthPreview.setFont(FontStyle.DESC_FONT);
        speedPreview.setFont(FontStyle.DESC_FONT);
        defensePreview.setFont(FontStyle.DESC_FONT);
        fatiguePreview.setFont(FontStyle.DESC_FONT);


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
        updateMonsterPreview();
    }

    private void addMonsterPreviewImage() {
        monsterPreviewImage = new GImage(monster.getNormalImage());
        monsterPreviewImage.setSize(230, 190); 
        monsterPreviewImage.setLocation(730, 10);

        contents.add(monsterPreviewImage);
        mainScreen.add(monsterPreviewImage);
       
        strengthLabel.setFont(FontStyle.STAT_FONT);
        speedLabel.setFont(FontStyle.STAT_FONT);
        defenseLabel.setFont(FontStyle.STAT_FONT);
        fatigueLabel.setFont(FontStyle.STAT_FONT);

    }

    private void updateMonsterPreview() {
        if (monsterPreviewLabel == null || monster == null) return;

        String text =
                "Monster: " + monster.getMonsterType() +
                "  STR " + monster.getStrength() +
                "  AGI " + monster.getSpeed() +
                "  DEF " + monster.getDefense() +
                "  HP " + monster.getHealth() +
                "  FAT " + monster.getFatigue();

        monsterPreviewLabel.setLabel(text);
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
        isStrongForm = true;
        updateMonsterPicture();

        int oldS = monster.getStrength();
        int oldF = monster.getFatigue();

        Difficulty d = mainScreen.getDifficulty();

        if (d == Difficulty.BABY) {
            monster.setStrength(oldS + 2);
            monster.setFatigue(oldF + 1);
        } else {
            monster.setStrength(oldS + 1);
            monster.setFatigue(oldF + 1);
        }
        decrementTurns();
        updateDescriptionWithTurns("Your monster trained Strength!"); 

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
        } else{
            monster.setSpeed(oldA + 1);
            monster.setFatigue(oldF + 1);
        } 
        decrementTurns();
        updateDescriptionWithTurns("Your monster trained Agility!");

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
        } else {
            monster.setDefense(oldD + 1);
            monster.setFatigue(oldF + 1);
        } 
        decrementTurns();
        updateDescriptionWithTurns("Your monster trained Defense!");

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
        descriptionLabel.setFont(FontStyle.DESC_FONT);

        clearPreviews();

        int fatigueLost = oldF - newF;
        decrementTurns();
        updateDescriptionWithTurns("Resting: -" + fatigueLost + " Fatigue. Your monster takes a break to recover.");
        updateMonsterPreview();
    }

    
    // =============================================================
    // MODE DESCRIPTION
    // =============================================================
    private void updateModeDescription() {
        if (descriptionLabel == null) return;

        switch (button) {
            case TRAIN:
                updateDescription("Training menu: Click Train button to start training your monster.");
                break;
            case REST:
                updateDescription("Rest: to lower your monster's fatigue.");
                break;
            case BATTLE:
                updateDescription("Battle: Click combat button to move toward a battle using your current stats.");
                break;
            case STRENGTH:
                updateDescription("Strength training: increases Strength, but adds Fatigue.");
                break;
            case AGILITY:
                updateDescription("Agility training: increases Agility, but adds Fatigue.");
                break;
            case DEFENSE:
                updateDescription("Defense training: increases Defense, but adds Fatigue.");
                break;
            case BACK:
                updateDescription("Back: return to the main training menu.");
                break;
        }
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
                	if (monster.getFatigue()>9){
                		updateDescription("Your monster is too tired!");
                		break;
                	}
                    showNormalMonster();
                    button = TrainingButton.STRENGTH;
                    selectButton.setImage(button.toString());
                    switchBackgroundToTrain();
                    showStrengthPreview();
                    break;

                case STRENGTH:
                	if (monster.getFatigue()>9){
                		updateDescription("Your monster is too tired!");
                		break;
                	}
                    switchBackgroundToTrain();
                    trainStrength();
                    showStrengthPreview();
                    break;

                case AGILITY:
                	if (monster.getFatigue()>9){
                		updateDescription("Your monster is too tired!");
                		break;
                	}
                    switchBackgroundToTrain();
                    trainAgility();
                    showAgilityPreview();
                    break;

                case DEFENSE:
                	if (monster.getFatigue()>9){
                		updateDescription("Your monster is too tired!");
                		break;
                	}
                    switchBackgroundToTrain();
                    trainDefense();
                    showDefensePreview();
                    break;

                case REST:
                    switchBackgroundToRest();
                    showSleepingMonster();
                    RestMonster();
                    break;

                case BATTLE:
                	if (monster.getFatigue()>7){
                		updateDescription("Your monster is too tired!");
                		break;
                	}
                	else
                		monsterPreviewImage.setVisible(false);
                    	switchBackgroundToBattle();
                    	clearPreviews();
                    	showBattleButtons();
                    break;

                case BACK:
                    button = TrainingButton.TRAIN;
                    selectButton.setImage(button.toString());
                    switchBackgroundToTrain();
                    clearPreviews();
                    break;
            }

            selectButton.setSize(selectButtonSize.getWidth(), selectButtonSize.getHeight());

        }
        
        if (clicked==babyBattle) {

        	mainScreen.setBattleDifficulty(BattleDifficulty.BABY);
        	mainScreen.switchToBattleScreen();
        }
        if (clicked==childBattle) {

        	mainScreen.setBattleDifficulty(BattleDifficulty.CHILD);
        	mainScreen.switchToBattleScreen();
        }
        if (clicked==normalBattle) {

        	mainScreen.setBattleDifficulty(BattleDifficulty.NORMAL);
        	mainScreen.switchToBattleScreen();
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
        selectButton.setSize(selectButtonSize.getWidth(), selectButtonSize.getHeight());
        updateModeDescription();

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
            case REST: button = TrainingButton.TRAIN; break;
            case BATTLE: button = TrainingButton.REST; break;

            case STRENGTH: button = TrainingButton.BACK; break;
            case AGILITY: button = TrainingButton.STRENGTH; break;
            case DEFENSE: button = TrainingButton.AGILITY; break;
            case BACK: button = TrainingButton.DEFENSE; break;
        }

        selectButton.setImage(button.toString());
        selectButton.setSize(selectButtonSize.getWidth(), selectButtonSize.getHeight());
        updateModeDescription();

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

    // =============================================================
    // TURNS DECREMENT LOGIC (NEW)
    // =============================================================
    private void decrementTurns() {
        int remaining = mainScreen.getTurnsRemaining();
        remaining--; // use one turn
        mainScreen.setTurnsRemaining(remaining);

        System.out.println("Turns remaining: " + remaining);

        if (remaining <= 0) {
            System.out.println("Turns exhausted! Switching to battle...");
            mainScreen.setBattleDifficulty(BattleDifficulty.BOSS);

        	System.out.println("(train)set bat dif to: "+BattleDifficulty.BOSS);
            mainScreen.switchToBattleScreen();
        }
    }

}
