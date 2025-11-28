import acm.graphics.GImage;
import java.awt.event.MouseEvent;

public class WinScreen extends GraphicsPane {

    private MainApplication program;
    private GImage screen;

    private boolean waitingForClick = true;

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

        // Wait 3 seconds, but ONLY if user has not clicked yet
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {}

            if (waitingForClick) {
                goBackToTraining();
            }
        }).start();
    }

    @Override
    public void hideContent() {
        program.remove(screen);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        waitingForClick = false;
        goBackToTraining();
    }

    private void goBackToTraining() {
        program.switchToTrainingPane();
    }
}
