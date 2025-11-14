import acm.graphics.GObject;
import acm.program.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MainApplication extends GraphicsProgram {

    // Window Settings
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 600;

    // Game State
    private Difficulty difficulty;
    private int turnsRemaining;

    // All Game Screens (Panes)
    private WelcomePane welcomePane;
    private DescriptionPane descriptionPane;
    private StartPane startPane;
    private DifficultyPane difficultyPane;
    private MonsterSelectPane monsterSelectPane;
    private StoryPane storyPane;
    private TurnsPane turnsPane;

    //// >>> IMPORTANT: TrainingPane is created *after* monster selection
    private TrainingPane trainingPane;

    private BattlePane battlePane;
    private RestPane restPane;

    // The active screen currently shown
    private GraphicsPane currentScreen;

    // The monster player selected
    private Monster monster;


    public MainApplication() {
        super();
    }

    @Override
    public void init() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    @Override
    public void run() {
        System.out.println("Let's Begin!");
        setupInteractions();

        // Initialize all screens EXCEPT TrainingPane (created after monster selection)
        welcomePane = new WelcomePane(this);
        descriptionPane = new DescriptionPane(this);
        startPane = new StartPane(this);
        difficultyPane = new DifficultyPane(this);
        monsterSelectPane = new MonsterSelectPane(this);
        storyPane = new StoryPane(this);
        turnsPane = new TurnsPane(this);

        battlePane = new BattlePane(this);
        restPane = new RestPane(this);

        //// >>> DO NOT CREATE TRAININGPANE HERE

        // Start screen
        switchToScreen(startPane);
    }


    /** Enables mouse + keyboard interaction */
    protected void setupInteractions() {
        requestFocus();
        addKeyListeners();
        addMouseListeners();
    }


    // =============================================================
    //  SCREEN SWITCHING FUNCTIONS
    // =============================================================

    public void switchToDescriptionScreen() {
        switchToScreen(descriptionPane);
    }

    public void switchToTurnsScreen() {
        switchToScreen(turnsPane);
    }

    public void switchToStoryScreen() {
        switchToScreen(storyPane);
    }

    public void switchToMonsterSelectScreen() {
        switchToScreen(monsterSelectPane);
    }

    public void switchToBattleScreen() {
        switchToScreen(battlePane);
    }

    public void switchToWelcomeScreen() {
        switchToScreen(welcomePane);
    }

    public void switchToRestScreen() {
        switchToScreen(restPane);
    }

    public void switchToDifficultyScreen() {
        switchToScreen(difficultyPane);
    }


    //// >>> NEW: TRAINING SCREEN WITH MONSTER PASSED IN
    public void switchToTrainingScreen(Monster monster) {
        this.monster = monster;   // Save selected monster
        trainingPane = new TrainingPane(this, monster);
        switchToScreen(trainingPane);
    }


    // =============================================================
    // GENERAL SCREEN SWITCHER
    // =============================================================
    protected void switchToScreen(GraphicsPane newScreen) {
        if (currentScreen != null) {
            currentScreen.hideContent();
        }
        newScreen.showContent();
        currentScreen = newScreen;
    }


    // =============================================================
    // INPUT PASSTHROUGH
    // =============================================================
    public GObject getElementAtLocation(double x, double y) {
        return getElementAt(x, y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (currentScreen != null) currentScreen.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (currentScreen != null) currentScreen.mouseReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (currentScreen != null) currentScreen.mouseClicked(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (currentScreen != null) currentScreen.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (currentScreen != null) currentScreen.mouseMoved(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (currentScreen != null) currentScreen.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (currentScreen != null) currentScreen.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (currentScreen != null) currentScreen.keyTyped(e);
    }


    // =============================================================
    // GETTERS / SETTERS
    // =============================================================

    public void setDifficulty(Difficulty d) {
        this.difficulty = d;
    }

    public void setTurnsRemaining(int turns) {
        this.turnsRemaining = turns;
    }

    public int getTurnsRemaining() {
        return turnsRemaining;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }


    // =============================================================
    // MAIN ENTRY POINT
    // =============================================================
    public static void main(String[] args) {
        new MainApplication().start();
    }
}
