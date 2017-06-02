package game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;

import game.Fighters.*;
import game.Input.*;
import game.Menus.*;

import java.io.IOException;
import java.util.Stack;

public class BattleRoyale extends Canvas implements MouseListener, KeyListener, IScreen, Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int HEIGHT = 720;
	public static final int WIDTH = HEIGHT * 16 / 9; // 1280
	public static final String TITLE = "Westview Battle Royale";

	// variables to make the game work
	private boolean running;
	private Thread thread;

	// buffer the window to reduce lag
	// private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
	// BufferedImage.TYPE_INT_RGB);
	private static BufferedImage menuBG;
	private static BufferedImage arena;
	public static BufferedImage controlsBG;
	public static BufferedImage pauseBG;
	public static BufferedImage bobHead;
	public static BufferedImage cassenHead;
	public static BufferedImage halanderHead;
	public static BufferedImage jamalHead;
	public static BufferedImage kurthHead;
	public static BufferedImage nguyenHead;
	public static BufferedImage tombocHead;
	public static BufferedImage wayHead;
	public static BufferedImage profileBG;
	public static BufferedImage creditsBG;
	public static BufferedImage victoryBG;

	private Fighter p1, p2;
	private PlayerControls p1Controls;
	private PlayerControls p2Controls;
	private MainGame game;
	private MainMenu menu;
	private ControlsMenu controls;
	private PauseMenu pause;
	private StageMenu stage;
	private FighterMenu champ;
	private CreditsMenu credits;
	private BobMenu bob;
	private CassenMenu cassen;
	private HalanderMenu halander;
	private JamalMenu jamal;
	private KurthMenu kurth;
	private NguyenMenu nguyen;
	private TombocMenu tomboc;
	private WayMenu way;
	private VictoryScreen victory;
	
	private Sound titleTheme;

	private Graphics g;

	private Screen stop;
	private Stack<Screen> screens;

	public BattleRoyale()
	{
		p1Controls = new PlayerControls(true);
		p2Controls = new PlayerControls(false);
		running = false;
		try
		{
			controlsBG = GameUtils.self().loadImage("Images/controlsBG.jpg");
			menuBG = GameUtils.self().loadImage("Images/menuBG.png");
			bobHead = GameUtils.self().loadImage("Images/Bob-Head.jpg");
			cassenHead = GameUtils.self().loadImage("Images/Cassen-Head.jpg");
			halanderHead = GameUtils.self().loadImage("Images/Halander-Head.jpg");
			jamalHead = GameUtils.self().loadImage("Images/Jamal-Head.jpg");
			kurthHead = GameUtils.self().loadImage("Images/Kurth-Head.jpg");
			nguyenHead = GameUtils.self().loadImage("Images/Nguyen-Head.jpg");
			tombocHead = GameUtils.self().loadImage("Images/Tomboc-Head.jpg");
			wayHead = GameUtils.self().loadImage("Images/Way-Head.jpg");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		;
		pauseBG = GameUtils.self().createOverlay(WIDTH, HEIGHT, 0.85f);
		profileBG = GameUtils.self().createOverlay(WIDTH, HEIGHT, 1f);
		creditsBG = GameUtils.self().createOverlay(WIDTH, HEIGHT, 1f);
		victoryBG = GameUtils.self().createOverlay(WIDTH, HEIGHT, 0f);
		arena = null;
		screens = new Stack<Screen>();
	}

	private void initialize()
	{
		setScreen(getMenu(), false);
		this.addMouseListener(this);
		this.addKeyListener(this);
		titleTheme = new Sound("Sounds/cs-menu.wav");
		titleTheme.play();
	}

	private synchronized void start()
	{
		if (running)
		{
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	private synchronized void stop()
	{
		if (!running)
		{
			return;
		}
		running = false;
		System.exit(1);
	}

	private void render()
	{
		BufferStrategy strat = this.getBufferStrategy();

		if (strat == null)
		{
			createBufferStrategy(3); // its a triple buffer, so the system
										// thinks 2 steps ahead
			return;
		}

		g = strat.getDrawGraphics();
		if (!screens.isEmpty())
		{
			screens.peek().draw(g);
		}
		g.dispose();
		strat.show();
	}

	public void run()
	{
		long lastTime = System.nanoTime();
		final double fps = 60.0;
		final double ns = 1000000000 / fps;
		final double ns2 = ns / 3;
		double delta = 0, delta2 = 0; // time passed

		// to display time and fps
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while (running)
		{
			long currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / ns;
			delta2 += (currentTime - lastTime) / ns2;
			lastTime = currentTime;
			if (delta >= 1)
			{
				tick();
				updates++;
				delta--;
			}
			if (delta2 >= 1)
			{
				render();
				frames++;
				delta2--;
			}
			if (System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println("Ticks: " + updates + ", FPS: " + frames);
				updates = 0;
				frames = 0;
			}
		}
	}

	private void tick()
	{
		if (game != null)
		{
			if (screens.peek().equals(getGame()))
			{
				game.move();
			}
		}
		if(getScreen() == getMenu() && !titleTheme.playing()) {
			titleTheme = new Sound("Sounds/cs-menu.wav");
			titleTheme.play();
		}
	}

	private MainGame createGame() throws IOException
	{
		MainGame tempGame;
		p1 = champ.getP1();
		p2 = champ.getP2();
		p1.setOpponent(p2);
		p2.setOpponent(p1);

		tempGame = new MainGame(arena, p1, p2);

		titleTheme.pause();
		
		return tempGame;
	}

	public static void main(String args[])
	{
		BattleRoyale game = new BattleRoyale();
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		game.initialize();

		JFrame frame = new JFrame(BattleRoyale.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setFocusable(true);

		game.start();
	}

	public void mouseClicked(MouseEvent e)
	{
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		try
		{
			getScreen().mousePressed(this, x, y);
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}

	public void mouseReleased(MouseEvent e)
	{
	}

	public void mouseEntered(MouseEvent e)
	{
	}

	public void mouseExited(MouseEvent e)
	{
	}

	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		getScreen().keyPressed(this, e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		getScreen().keyReleased(e.getKeyCode());
	}

	@Override
	public Screen getGame()
	{
		if (game == null)
		{
			try
			{
				createGame();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return game;
	}

	@Override
	public Screen getNewGame()
	{
		try
		{
			game = createGame();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return game;
	}

	@Override
	public Screen getPause()
	{
		if (pause == null)
		{
			pause = new PauseMenu(this, pauseBG);
		}
		return pause;
	}

	@Override
	public Screen getStageSelect()
	{
		if (stage == null)
		{
			stage = new StageMenu(menuBG);
		}
		return stage;
	}

	@Override
	public Screen getChampSelect()
	{

		champ = new FighterMenu(menuBG, p1Controls, p2Controls);
		return champ;
	}

	@Override
	public Screen getControls()
	{
		if (controls == null)
		{
			controls = new ControlsMenu(controlsBG, p1Controls, p2Controls);
		}
		return controls;
	}

	@Override
	public Screen getExit()
	{
		return stop;
	}
	
	@Override
	public Screen getVictory(Fighter player)
	{
		victory = new VictoryScreen(this, victoryBG, p1, p2, player.getPlayerNum());
		return victory;
	}

	@Override
	public Screen getMenu()
	{
		if (menu == null)
		{
			menu = new MainMenu(menuBG);
		}
		return menu;
	}

	public Screen getCredits()
	{
		if (credits == null)
		{
			credits = new CreditsMenu(creditsBG);
		}
		return credits;
	}

	@Override
	public Screen getScreen()
	{
		if (screens.isEmpty())
		{
			screens.push(getMenu());
		}
		return screens.peek();
	}

	@Override
	public void setScreen(Screen screen, boolean doReset)
	{
		if (screen == stop)
		{
			stop();
			return;
		}
		if (screen != getScreen())
		{
			screens.push(screen);
			if (doReset)
			{
				screen.reset();
			}
		}
	}

	@Override
	public Screen getPrevScreen()
	{
		screens.pop();
		return getScreen();
	}

	@Override
	public void setPlayer(Fighter p)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setBackground(BufferedImage b)
	{
		arena = b;
	}

	@Override
	public Screen getBob()
	{
		if (bob == null)
		{
			bob = new BobMenu(profileBG, bobHead);
		}
		return bob;
	}

	@Override
	public Screen getCassen()
	{
		if (cassen == null)
		{
			cassen = new CassenMenu(profileBG, cassenHead);
		}
		return cassen;
	}

	@Override
	public Screen getHalander()
	{
		if (halander == null)
		{
			halander = new HalanderMenu(profileBG, halanderHead);
		}
		return halander;
	}

	@Override
	public Screen getJamal()
	{
		if (jamal == null)
		{
			jamal = new JamalMenu(profileBG, jamalHead);
		}
		return jamal;
	}

	@Override
	public Screen getKurth()
	{
		if (kurth == null)
		{
			kurth = new KurthMenu(profileBG, kurthHead);
		}
		return kurth;
	}

	@Override
	public Screen getNguyen()
	{
		if (nguyen == null)
		{
			nguyen = new NguyenMenu(profileBG, nguyenHead);
		}
		return nguyen;
	}

	@Override
	public Screen getTomboc()
	{
		if (tomboc == null)
		{
			tomboc = new TombocMenu(profileBG, tombocHead);
		}
		return tomboc;
	}

	@Override
	public Screen getWay()
	{
		if (way == null)
		{
			way = new WayMenu(profileBG, wayHead);
		}
		return way;
	}

}