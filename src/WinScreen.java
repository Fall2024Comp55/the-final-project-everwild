import acm.graphics.GImage;
import java.awt.event.MouseEvent;

public class WinScreen extends GraphicsPane {

    private MainApplication program;
    private GImage screen;


    public WinScreen(MainApplication app, boolean playerWon) {
        this.program = app;

        if (playerWon) {
            screen = new GImage("win.jpg");
        } else {
            screen = new GImage("lose.jpg");
        }

        screen.setSize(program.getWidth(), program.getHeight());
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
    	program.switchToStartScreen();
    }

    
}
