import java.awt.Color;
import java.awt.event.MouseEvent;
import acm.graphics.*;

public class BattlePane extends GraphicsPane {

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
    }

    @Override
    public void showContent() {
        System.out.println("SHOW BATTLE PANE");

        background = new GImage("BattleBackground.jpeg");
        background.setLocation(0, 0);
        background.setSize(mainScreen.getWidth(), mainScreen.getHeight());

        battleButton = new GImage("Battle Button.jpeg");
        battleButton.setLocation(500, 400);

        contents.add(background);
        contents.add(battleButton);

        mainScreen.add(background);
        mainScreen.add(battleButton);

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
        monsterPreviewBox.setColor(new Color(0, 0, 0, 0));

        monsterPreviewLabel = new GLabel("", monsterPreviewBox.getX() + 10, monsterPreviewBox.getY() + 20);
        monsterPreviewLabel.setFont("Arial-14");
        monsterPreviewLabel.setColor(Color.WHITE);

        contents.add(monsterPreviewBox);
        contents.add(monsterPreviewLabel);
        mainScreen.add(monsterPreviewBox);
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

        if (clicked == battleButton) {
            System.out.println("Combat starting!");
            updateDescription("Combat starting! Your monster charges into battle with its current stats.");
            updateMonsterPreview();
        }
    }
}
