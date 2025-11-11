import acm.graphics.GObject;
import acm.program.*;


import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MainApplication extends GraphicsProgram{
	//Settings
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 600;
	
	//List of all the full screen panes
	private Difficulty difficulty;
	private int turnsRemaining;
	private WelcomePane welcomePane;
	private DescriptionPane descriptionPane;
	private GraphicsPane currentScreen;
	private StartPane startPane;
	private DifficultyPane difficultyPane;
	private TrainingPane trainingPane;
    private BattlePane battlePane;
    private RestPane restPane;
    private StoryPane storyPane;
    private TurnsPane turnsPane;
    private Monster monster;
	private MonsterSelectPane monsterSelectPane;


	public MainApplication() {
		super();
	}
	
	protected void setupInteractions() {
		requestFocus();
		addKeyListeners();
		addMouseListeners();
	}
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	public void run() {
		System.out.println("Lets' Begin!");
		setupInteractions();
		
		//Initialize all Panes
		welcomePane = new WelcomePane(this);
		descriptionPane = new DescriptionPane(this);
		startPane = new StartPane(this);
		difficultyPane = new DifficultyPane(this);
		monsterSelectPane = new MonsterSelectPane(this);
		storyPane = new StoryPane(this);
		turnsPane = new TurnsPane(this);
		//TheDefaultPane

		switchToScreen(startPane); //our start screen
		//switchToScreen(welcomePane);
		trainingPane = new TrainingPane(this);
	    battlePane = new BattlePane(this);
	    restPane = new RestPane(this);
	}
	
	public static void main(String[] args) {
		new MainApplication().start();

	}

	
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
	
	public void switchToTrainingScreen() {
		switchToScreen(trainingPane);
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
	
	public void setDifficulty(Difficulty d) {
	    this.difficulty = d;
	}
	
	public void setTurnsRemaining(int turns) {
		turnsRemaining=turns;
	}
	public int getTurnsRemaining() {
		return turnsRemaining;
	}

	protected void switchToScreen(GraphicsPane newScreen) {
		if(currentScreen != null) {
			currentScreen.hideContent();
		}
		newScreen.showContent();
		currentScreen = newScreen;
	}
	
	public GObject getElementAtLocation(double x, double y) {
		return getElementAt(x, y);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(currentScreen != null) {
			currentScreen.mousePressed(e);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(currentScreen != null) {
			currentScreen.mouseReleased(e);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(currentScreen != null) {
			currentScreen.mouseClicked(e);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(currentScreen != null) {
			currentScreen.mouseDragged(e);
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if(currentScreen != null) {
			currentScreen.mouseMoved(e);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(currentScreen != null) {
			currentScreen.keyPressed(e);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(currentScreen != null) {
			currentScreen.keyReleased(e);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		if(currentScreen != null) {
			currentScreen.keyTyped(e);
		}
	}
	
	  public Monster getMonster() {
			return monster;
		}

		public void setMonster(Monster monster) {
			this.monster = monster;
		}


}
