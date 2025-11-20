import acm.graphics.GImage;
import java.awt.event.MouseEvent;

public class WinScreen extends GraphicsPane {

    private MainApplication program;
    private GImage screen;

    public WinScreen(MainApplication app, boolean playerWon) {
        this.program = app;

        if (playerWon) {
            screen = new GImage("win.png");
        } else {
            screen = new GImage("lose.png");
        }

        // center
        double x = (program.getWidth() - screen.getWidth()) / 2;
        double y = (program.getHeight() - screen.getHeight()) / 2;
        screen.setLocation(x, y);
    }

    @Override
    public void showContent() {
        program.add(screen);
    }

    @Override
    public void hideContent() {
        program.remove(screen);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.exit(0);
    }
}
