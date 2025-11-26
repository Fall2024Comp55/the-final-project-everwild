import java.awt.Color;
import java.awt.event.MouseEvent;
import acm.graphics.*;

public class BattlePane extends GraphicsPane {
	private GImage playerImg;
	private GImage enemyImg;
	private GImage monsterPreviewImage;

	private Monster playerMonster;
	private Monster enemyMonster;

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
        this.enemyMonster = new Monster(
            0,   // fatigue
            8,   // strength
            3,   // agility
            10,  // defense
            20,  // health
            MonsterType.ENEMY  
        );
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
        updateDescription("Battle mode: your monster will fight using its current stats. Click the Battle button to begin.");
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
        playerImg = new GImage("BattleCuteMonster.png"); 
        playerImg.setSize(220, 200);
        playerImg.setLocation(80, 260);

        // Enemy monster on RIGHT (static built-in enemy)
        enemyImg = new GImage("ENEMY.png");
        enemyImg.setSize(220, 200);
        enemyImg.setLocation(700, 260);

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
        monsterPreviewBox = new GRect(200, 80);
        monsterPreviewBox.setFilled(true);
        monsterPreviewBox.setFillColor(new Color(0, 0, 0, 120));
        monsterPreviewBox.setLocation(777, 140);
        monsterPreviewBox.setLineWidth(0);

        // --- Monster image shown inside the preview ---
        monsterPreviewImage = new GImage("BattleCuteMonster.png");
        monsterPreviewImage.setSize(230, 190);
        monsterPreviewImage.setLocation(730, 10);
        
        monsterPreviewLabel = new GLabel("", 790, 270);
        monsterPreviewLabel.setFont("Arial-14");
        monsterPreviewLabel.setColor(Color.WHITE);

        contents.add(monsterPreviewBox);
        contents.add(monsterPreviewImage);   // ← ADD THIS
        contents.add(monsterPreviewLabel);

        mainScreen.add(monsterPreviewBox);
        mainScreen.add(monsterPreviewImage); // ← ADD THIS
        mainScreen.add(monsterPreviewLabel);

        updateMonsterPreview();
    }


    private void updateMonsterPreview() {
        if (monsterPreviewLabel == null) return;

        Monster m = mainScreen.getMonster();
        if (m == null) {
            monsterPreviewLabel.setLabel("Monster: (none selected)");
            return;
        }

        String text =
                "Monster: " + m.getMonsterType() +
                "  STR " + m.getStrength() +
                "  AGI " + m.getSpeed() +
                "  DEF " + m.getDefense() +
                "  HP " + m.getHealth() +
                "  FAT " + m.getFatigue();

        monsterPreviewLabel.setLabel(text);
    }

    // =============================================================
    // CLICK HANDLING
    // =============================================================
    @Override
    public void mouseClicked(MouseEvent e) {
        GObject clicked = mainScreen.getElementAtLocation(e.getX(), e.getY());
        if (clicked == null) return;

        if (clicked == battleButton && !battleStarted) {
            battleStarted = true;
            updateDescription("Combat starting! Your monster charges into battle!");
            startBattle();
        }
    }
    private void startBattle() {
        new Thread(() -> {
            while (playerMonster.getDefense() > 0 && enemyMonster.getDefense() > 0) {

                // Move both toward each other
                playerImg.move(4, 0);  // right
                enemyImg.move(-4, 0);  // left

                // Check for collision
                if (playerImg.getBounds().intersects(enemyImg.getBounds())) {

                    // Damage both monsters
                    playerMonster.setDefense(playerMonster.getDefense() - 1);
                    enemyMonster.setDefense(enemyMonster.getDefense() - 1);

                    updateMonsterPreview();
                    updateDescription("They collided! Both took damage!");

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
        if (playerMonster.getDefense() <= 0 && enemyMonster.getDefense() <= 0) {
            updateDescription("Both monsters fainted! It's a draw!");
        }
        else if (playerMonster.getDefense() <= 0) {
            updateDescription("Your monster fainted... you lost the battle!");
        }
        else {
            updateDescription("You won! The enemy monster fainted!");
        }

        battleStarted = false;
    }


}
