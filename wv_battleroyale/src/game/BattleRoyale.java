package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;

import javax.swing.*;

import game.Input.PlayerControls;
import game.Menus.ChampMenu;
import game.Menus.ControlsMenu;
import game.Menus.CreditsMenu;
import game.Menus.IScreen;
import game.Menus.MainMenu;
import game.Menus.PauseMenu;
import game.Menus.Screen;
import game.Menus.StageMenu;

import java.io.IOException;
import java.util.Stack;

public class BattleRoyale extends Canvas implements MouseListener, KeyListener, IScreen, Runnable
{
	private static final int PLAYERY = 90;
	private static final int PLAYERX = 300;
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
	private static BufferedImage tammy;
	private static BufferedImage fire;
	public static BufferedImage controlsBG;
	public static BufferedImage pauseBG;

	private Fighter p1, p2;
	private PlayerControls p1Controls;
	private PlayerControls p2Controls;
	private MainGame game;
	private MainMenu menu;
	private ControlsMenu controls;
	private PauseMenu pause;
	private StageMenu stage;
	private ChampMenu champ;
	private CreditsMenu credits;
	

	private Graphics g;

	private Screen stop;
	private Stack<Screen> screens;

	public BattleRoyale()
	{
		p1Controls = new PlayerControls(true);
		p2Controls = new PlayerControls(false);
		running = false;
		menuBG = null;
		arena = null;
		tammy = null;
		fire = null;
		controlsBG = null;
		pauseBG = null;
		screens = new Stack<Screen>();
	}

	private void initialize()
	{
		try
		{
			imageLoader();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		g = null;

		game = createGame();
		champ = new ChampMenu(menuBG, p1Controls, p2Controls);
		stage = new StageMenu(menuBG);
		menu = new MainMenu(menuBG, fire);
		controls = new ControlsMenu(controlsBG, p1Controls, p2Controls);
		pause = new PauseMenu(this, pauseBG);
		credits = new CreditsMenu(menuBG);
		stop = new Screen(null);
		

		setScreen(getMenu(), false);

		this.addMouseListener(this);
	}

	private void imageLoader() throws IOException
	{
		arena = GUIUtils.self().loadImage("Images/ampitheater.png");
		menuBG = GUIUtils.self().loadImage("Images/menuBG.png");
		tammy = GUIUtils.self().loadImage("Images/tammy.png");
		controlsBG = GUIUtils.self().loadImage("Images/controlsBG.jpg");
		pauseBG = GUIUtils.self().createOverlay(WIDTH, HEIGHT, 0.85f);
		// fire image: http://dreamicus.com/data/fire/fire-04.jpg
		fire = GUIUtils.self().loadImage("Images/fire.png");
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
		double delta = 0; // time passed

		// to display time and fps
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while (running)
		{
			long currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / ns;
			lastTime = currentTime;
			if (delta >= 1)
			{
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
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
		if (screens.peek().equals(getGame()))
		{
			if (game != null)
			{
				game.move();
			}
		}
	}

	private MainGame createGame()
	{
		MainGame tempGame;
		p1 = new Fighter(PLAYERX, tammy.getHeight() + PLAYERY, tammy, p1Controls);
		p2 = new Fighter(WIDTH - PLAYERX - tammy.getWidth(), tammy.getHeight() + PLAYERY, tammy, p2Controls);
		p1.setOpponent(p2);
		p2.setOpponent(p1);

		tempGame = new MainGame(arena, p1, p2);

		this.addKeyListener(this);
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
		return game;
	}
	
	@Override
	public Screen getNewGame()
	{
		game = createGame();
		return game;
	}

	@Override
	public Screen getPause()
	{
		return pause;
	}

	@Override
	public Screen getStageSelect()
	{
		return stage;
	}

	@Override
	public Screen getChampSelect()
	{
		return champ;
	}

	@Override
	public Screen getControls()
	{
		return controls;
	}

	@Override
	public Screen getExit()
	{
		return stop;
	}

	@Override
	public Screen getMenu()
	{
		return menu;
	}
	
	public Screen getCredits(){
		return credits;
	}

	@Override
	public Screen getScreen()
	{
		if (screens.isEmpty())
		{
			return null;
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
	public void setPlayer(IOpponent p)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBackground(BufferedImage b)
	{
		arena = b;
	}

}