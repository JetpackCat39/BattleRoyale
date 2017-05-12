package game;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

import java.io.IOException;

public class Game extends Canvas implements Runnable
{
	private static final int PLAYERY = 90;
	private static final int PLAYERX = 300;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int HEIGHT = 720;
	public static final int WIDTH = HEIGHT * 16 / 9;
	public static final String TITLE = "Westview Battle Royale";

	// variables to make the game work
	private boolean running = false;
	private Thread thread;

	// buffer the window to reduce lag
	// private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
	// BufferedImage.TYPE_INT_RGB);
	private static BufferedImage menuBG = null;
	private static BufferedImage arena1 = null;
	private static BufferedImage arena2 = null;
	private static BufferedImage tammy = null;
	public static BufferedImage controlsBG = null;

	private Fighter p1, p2;

	private Graphics g;

	private MainMenu menu;
	private MainGame game;
	private ControlsMenu controls;

	private KeyInputP1 input1;
	private KeyInputP2 input2;

	enum STATE
	{
		GAME, CONTROLS, MENU, PAUSE
	};

	private STATE State = STATE.MENU;

	private void initialize()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		try
		{
			menuBG = loader.loadImage("menuBG.jpg");
			arena1 = loader.loadImage("arena1.jpg");
			arena2 = loader.loadImage("arena2.jpg");
			tammy = loader.loadImage("photo.jpg");
			controlsBG = loader.loadImage("menuBG.jpg");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		menu = new MainMenu(menuBG);
		game = createGame();
		controls = createControls();

		this.addMouseListener(new MouseInput(menu, this,controls));
	}

	public void setState(STATE newState)
	{
		State = newState;
	}

	public STATE getState()
	{
		return State;
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

	private ControlsMenu createControls(){
		ControlsMenu controls;
		controls = new ControlsMenu(controlsBG);
		return controls;
	}
	private MainGame createGame()
	{
		MainGame tempGame;
		p1 = new Fighter(PLAYERX, tammy.getHeight() + PLAYERY, tammy);
		p2 = new Fighter(WIDTH - PLAYERX - tammy.getWidth(), tammy.getHeight() + PLAYERY, tammy);
		p1.setOpponent(p2);
		p2.setOpponent(p1);

		if (Math.random() > .5)
		{
			tempGame = new MainGame(arena1, p1, p2);
		}
		else
		{
			tempGame = new MainGame(arena2, p1, p2);
		}

		input1 = new KeyInputP1(p1);
		input2 = new KeyInputP2(p2);

		this.addKeyListener(input1);
		this.addKeyListener(input2);
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
		switch (State)
		{
		case MENU:
			menu.draw(g);
			break;
		case GAME:
			game.draw(g);
			break;
		case CONTROLS:
			controls.draw(g);
		default:
			break;
		}
		g.dispose();
		strat.show();
	}

	public static void main(String args[])
	{
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		game.initialize();

		JFrame frame = new JFrame(Game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setFocusable(true);

		game.start();
	}
}