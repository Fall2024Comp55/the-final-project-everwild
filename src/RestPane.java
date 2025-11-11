import java.awt.event.MouseEvent;
import acm.graphics.*;

public class RestPane extends GraphicsPane {

    private GImage background;
    private GImage restButton;

    public RestPane(MainApplication mainScreen) {
        this.mainScreen = mainScreen;
    }

    @Override
    public void showContent() {
        System.out.println("SHOW REST PANE");

        background = new GImage("RestBackground.jpeg");
        background.setLocation(0, 0);
        background.setSize(mainScreen.getWidth(), mainScreen.getHeight());

        restButton = new GImage("Rest Button.jpeg");
        restButton.setLocation(500, 400);

        contents.add(background);
        contents.add(restButton);

        mainScreen.add(background);
        mainScreen.add(restButton);
    }

    @Override
    public void hideContent() {
        for (GObject g : contents) {
            mainScreen.remove(g);
        }
        contents.clear();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GObject clicked = mainScreen.getElementAtLocation(e.getX(), e.getY());
        if (clicked == null) return;

        if (clicked == restButton) {
            System.out.println("REST → BATTLE");
            mainScreen.switchToBattleScreen();
        }
    }
}
