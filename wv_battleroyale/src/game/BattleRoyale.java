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
import game.Menus.MainMenu;
import game.Menus.PauseMenu;
import game.Menus.Screen;
import game.Menus.StageMenu;

import java.io.IOException;
import java.util.Hashtable;

public class BattleRoyale extends Canvas implements Runnable, MouseListener, KeyListener
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
	private boolean running = false;
	private Thread thread;
	private Screen currentScreen;

	// buffer the window to reduce lag
	// private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
	// BufferedImage.TYPE_INT_RGB);
	private static BufferedImage menuBG = null;
	private static BufferedImage arena = null;
	private static BufferedImage tammy = null;
	private static BufferedImage fire = null;
	public static BufferedImage controlsBG = null;
	public static BufferedImage pauseBG = null;

	private Fighter p1, p2;
	private PlayerControls p1Controls = new PlayerControls(true);
	private PlayerControls p2Controls = new PlayerControls(false);

	private Graphics g;

	private MainGame game;
	private MainMenu menu;
	private ControlsMenu controls;
	private PauseMenu pause;
	private StageMenu stage;
	private ChampMenu champ;
	
	private Hashtable<STATE,Screen> screens = new Hashtable<STATE,Screen>();

	public enum STATE
	{
		GAME, CONTROLS, MENU, PAUSE, STAGESELECT, CHAMPSELECT, STOP
	};

	private STATE State, prevState;

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

		game = createGame();
		screens.put(STATE.GAME, game);
		menu = new MainMenu(menuBG, fire);
		screens.put(STATE.MENU, menu);
		controls = new ControlsMenu(controlsBG, p1Controls, p2Controls);
		screens.put(STATE.CONTROLS, controls);
		pause = new PauseMenu(game, pauseBG);
		screens.put(STATE.PAUSE, pause);
		stage = new StageMenu(menuBG);
		screens.put(STATE.STAGESELECT, stage);
		champ = new ChampMenu(menuBG);
		screens.put(STATE.CHAMPSELECT, champ);

		setState(STATE.MENU);
		setPreviousState(null);
		
		this.addMouseListener(this);
		//this.addMouseListener(change);
	}

	private void imageLoader() throws IOException
	{
		menuBG = GUIUtils.self().loadImage("Images/menuBG.png");
		arena = GUIUtils.self().loadImage("Images/ampitheater.png");
		tammy = GUIUtils.self().loadImage("Images/tammy.png");
		controlsBG = GUIUtils.self().loadImage("Images/controlsBG.jpg");
		pauseBG = GUIUtils.self().createOverlay(WIDTH, HEIGHT, 0.85f);
		//fire image: http://dreamicus.com/data/fire/fire-04.jpg
		fire = GUIUtils.self().loadImage("Images/fire.png");
	}

	public void setState(STATE newState)
	{
		if (newState == STATE.STOP)
		{
			stop();
			return;
		}
		setPreviousState(State);
		currentScreen = screens.get(newState);
		State = newState;
	}

	public STATE getState()
	{
		return State;
	}
	
	private void setPreviousState(STATE oldState)
	{
		prevState = oldState;
	}
	
	public STATE getPreviousState()
	{
		return prevState;
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

	public synchronized void stop()
	{
		if (!running)
		{
			return;
		}
		running = false;
		try
		{
			thread.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.exit(1);
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

		initialize();
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
		p1.move();
		p2.move();
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
		currentScreen.draw(g);
		g.dispose();
		strat.show();
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
		STATE newState = currentScreen.mousePressed(x, y, getState(), getPreviousState());
		
		if (getState() != newState)
		{
			setState(newState);
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
		STATE newState = currentScreen.keyPressed(e.getKeyCode(), getState(), getPreviousState());
		
		if (getState() != newState)
		{
			setState(newState);
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		STATE newState = currentScreen.keyReleased(e.getKeyCode(), getState(), getPreviousState());
		
		if (getState() != newState)
		{
			setState(newState);
		}
	}
}