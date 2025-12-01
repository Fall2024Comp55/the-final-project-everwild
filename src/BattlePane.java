import java.awt.Color;
import java.awt.event.MouseEvent;
import acm.graphics.*;

public class BattlePane extends GraphicsPane {
	private GImage playerImg;
	private GImage enemyImg;
	private GImage monsterPreviewImage;

	private Monster playerMonster;
	private Monster enemyMonster;
	
    private GLabel strengthLabel;
    private GLabel speedLabel;
    private GLabel defenseLabel;
    private GLabel fatigueLabel;
    private GLabel difficultyLabel;
    private GLabel typeLabel;
    private GLabel HealthLabel;
    private BattleDifficulty battleDifficulty;
	private boolean battleStarted = false;


    private GImage background;
    private GImage battleButton;

    // Description box (bottom-left)
    private GRect descriptionBox;
    private GLabel descriptionLabel;

    // Monster preview (top-right)
    private GRect monsterPreviewBox;
    private GLabel monsterPreviewLabel;

    public BattlePane(MainApplication mainScreen) {
        this.mainScreen = mainScreen;

        // Your selected monster from game
        this.playerMonster = mainScreen.getMonster();

        // Built-in enemy monster (you can tune stats later)
        
        
        if (this.battleDifficulty==battleDifficulty.BABY) {
        	this.enemyMonster = new Monster(
                    0,   // fatigue
                    10,   // strength
                    10,   // agility
                    10,  // defense
                    20,  // health
                    MonsterType.ENEMY  
                );
        }
        
        if (this.battleDifficulty==battleDifficulty.CHILD) {
        	this.enemyMonster = new Monster(
                    0,   // fatigue
                    15,   // strength
                    15,   // agility
                    15,  // defense
                    30,  // health
                    MonsterType.ENEMY  
                );
        }
        
        if (this.battleDifficulty==battleDifficulty.NORMAL) {
        	this.enemyMonster = new Monster(
                    0,   // fatigue
                    20,   // strength
                    20,   // agility
                    20,  // defense
                    40,  // health
                    MonsterType.ENEMY  
                );
        }
        
        if (this.battleDifficulty==battleDifficulty.BOSS) {
        	this.enemyMonster = new Monster(
                    0,   // fatigue
                    30,   // strength
                    30,   // agility
                    30,  // defense
                    50,  // health
                    MonsterType.ENEMY  
                );
        }
    }

    public void setBattleDifficulty(BattleDifficulty e){
    	battleDifficulty=e;
    }

    @Override
    public void showContent() {
        System.out.println("SHOW BATTLE PANE");

        background = new GImage("BattleBackground.jpeg");
        background.setLocation(0, 0);
        background.setSize(mainScreen.getWidth(), mainScreen.getHeight());

        battleButton = new GImage("Battle Button.jpeg");
        battleButton.setSize(150, 60); 
        battleButton.setLocation(500, 400);

        contents.add(background);
        contents.add(battleButton);

        mainScreen.add(background);
        mainScreen.add(battleButton);
        
        addBattleMonsters();

        addMonsterPreview();
        addDescriptionBox();
        battleButton.setVisible(false);

        updateDescription("Battle mode: your monster will fight using its current stats. Click your monster to start!");
    }

    @Override
    public void hideContent() {
        for (GObject g : contents) {
            mainScreen.remove(g);
        }
        contents.clear();
    }
    
    private void addBattleMonsters() {
        // Player monster on LEFT
        playerImg = new GImage(playerMonster.getBattleImage()); 
        playerImg.setSize(220, 200);
        playerImg.setLocation(80, 260);

        // Enemy monster on RIGHT (static built-in enemy)
        enemyImg = new GImage("ENEMY.png");
        enemyImg.setSize(220, 200);
        enemyImg.setLocation(530, 260);

        contents.add(playerImg);
        contents.add(enemyImg);

        mainScreen.add(playerImg);
        mainScreen.add(enemyImg);
    }

    // =============================================================
    // DESCRIPTION BOX
    // =============================================================
    private void addDescriptionBox() {
        descriptionBox = new GRect(600, 80);
        descriptionBox.setFilled(true);
        descriptionBox.setFillColor(new Color(91, 87, 75));
        descriptionBox.setLocation(40, 470);
        descriptionBox.setLineWidth(0);
        descriptionBox.setColor(new Color(0, 0, 0, 0));

        descriptionLabel = new GLabel("", descriptionBox.getX() + 10, descriptionBox.getY() + 25);
        descriptionLabel.setFont("Arial-16");
        descriptionLabel.setColor(Color.WHITE);

        contents.add(descriptionBox);
        contents.add(descriptionLabel);
        mainScreen.add(descriptionBox);
        mainScreen.add(descriptionLabel);
    }

    private void updateDescription(String text) {
        if (descriptionLabel != null) {
            descriptionLabel.setLabel(text);
        }
    }

    // =============================================================
    // MONSTER PREVIEW (TOP-RIGHT)
    // =============================================================
    private void addMonsterPreview() {
        monsterPreviewBox = new GRect(215, 320); // taller to fit all stats
        monsterPreviewBox.setFilled(true);
        monsterPreviewBox.setFillColor(new Color(0, 0, 0, 120));
        monsterPreviewBox.setLocation(777, 220);
        monsterPreviewBox.setLineWidth(0);

        // Monster image
        monsterPreviewImage = new GImage(playerMonster.getBattleImage());
        monsterPreviewImage.setSize(230, 190);
        monsterPreviewImage.setLocation(730, 10);

        // Stats labels positions
        int startX = 790;
        int startY = 300;
        int lineHeight = 20;

        typeLabel     = new GLabel("", startX, startY);
        strengthLabel = new GLabel("", startX, startY + 1*lineHeight);
        speedLabel    = new GLabel("", startX, startY + 2*lineHeight);
        defenseLabel  = new GLabel("", startX, startY + 3*lineHeight);
        HealthLabel   = new GLabel("", startX, startY + 4*lineHeight);
        fatigueLabel  = new GLabel("", startX, startY + 5*lineHeight);

        typeLabel.setColor(Color.WHITE);
        strengthLabel.setColor(Color.WHITE);
        speedLabel.setColor(Color.WHITE);
        defenseLabel.setColor(Color.WHITE);
        HealthLabel.setColor(Color.WHITE);
        fatigueLabel.setColor(Color.WHITE);

        // Add to contents and main screen
        contents.add(monsterPreviewBox);
        contents.add(monsterPreviewImage);
        contents.add(typeLabel);
        contents.add(strengthLabel);
        contents.add(speedLabel);
        contents.add(defenseLabel);
        contents.add(HealthLabel);
        contents.add(fatigueLabel);

        mainScreen.add(monsterPreviewBox);
        mainScreen.add(monsterPreviewImage);
        mainScreen.add(typeLabel);
        mainScreen.add(strengthLabel);
        mainScreen.add(speedLabel);
        mainScreen.add(defenseLabel);
        mainScreen.add(HealthLabel);
        mainScreen.add(fatigueLabel);

        updateMonsterPreview();
    }




    private void updateMonsterPreview() {
        if (playerMonster == null) return;

        typeLabel.setLabel("Monster: " + playerMonster.getMonsterType());
        strengthLabel.setLabel("STR: " + playerMonster.getStrength());
        speedLabel.setLabel("AGI: " + playerMonster.getSpeed());
        defenseLabel.setLabel("DEF: " + playerMonster.getDefense());
        HealthLabel.setLabel("HP: " + playerMonster.getHealth());
        fatigueLabel.setLabel("FAT: " + playerMonster.getFatigue());
    }


    // =============================================================
    // CLICK HANDLING
    // =============================================================
    @Override
    public void mouseClicked(MouseEvent e) {
        GObject clicked = mainScreen.getElementAtLocation(e.getX(), e.getY());
        if (clicked == null) return;

        // Start battle only when player clicks their monster
        if (clicked == playerImg && !battleStarted) {
            battleStarted = true;
            updateDescription("Combat starting! Your monster charges into battle!");
            startBattle();
        }
    }

    private void startBattle() {
        new Thread(() -> {
            while (playerMonster.getHealth() > 0 && enemyMonster.getHealth() > 0) {

                // Move both toward each other
                playerImg.move(4, 0);  // right
                enemyImg.move(-4, 0);  // left

                // Check for collision
                if (playerImg.getBounds().intersects(enemyImg.getBounds())) {

                    // Calculate damage
                    int playerDamage = Math.max(1, playerMonster.getStrength() - enemyMonster.getDefense());
                    int enemyDamage  = Math.max(1, enemyMonster.getStrength() - playerMonster.getDefense());

                    // Apply damage to health
                    playerMonster.setHealth(Math.max(0, playerMonster.getHealth() - enemyDamage));
                    enemyMonster.setHealth(Math.max(0, enemyMonster.getHealth() - playerDamage));

                    // Update preview
                    updateMonsterPreview();

                    updateDescription("They collided!");

                    // Push them apart slightly
                    playerImg.move(-10, 0);
                    enemyImg.move(10, 0);

                    // Pause collision impact
                    mainScreen.pause(300);
                }

                mainScreen.pause(40);
            }

            finishBattle();
        }).start();
    }

    private void finishBattle() {
        if (playerMonster.getHealth() <= 0 && enemyMonster.getHealth() <= 0) {
            updateDescription("Both monsters fainted! It's a draw!");
            mainScreen.switchToWinScreen(false); // treat draw as lose or create a draw screen
        } 
        else if (playerMonster.getHealth() <= 0) {
            updateDescription("Your monster fainted... you lost the battle!");
            mainScreen.switchToWinScreen(false); // lose
        } 
        else {
            updateDescription("You won! The enemy monster fainted!");
            mainScreen.switchToWinScreen(true); // win
        }

        battleStarted = false;
    }




}
