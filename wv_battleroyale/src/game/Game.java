package game;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

import java.io.IOException;

public class Game extends Canvas implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int HEIGHT = 720;
	public static final int WIDTH = HEIGHT * 16 / 9;
	public final String TITLE = "Westview Battle Royale";

	// variables to make the game work
	private boolean running = false;
	private Thread thread;

	// buffer the window to reduce lag
	//private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private static BufferedImage menuBG = null;
	private static BufferedImage arena1 = null;
	private static BufferedImage arena2 = null;
	private static BufferedImage tammy = null;
	
	private Fighter p1, p2;
	private CollisionChecker colCheck;

	private Graphics g;
	
	private MainMenu menu;
	private MainGame game;
	
	private KeyInputP1 input1;
	private KeyInputP2 input2;
	
	enum STATE
	{
		GAME,
		CONTROLS,
		MENU
	};
	public static STATE State = STATE.MENU;

	private void initialize()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		try
		{
			menuBG = loader.loadImage("menuBG.jpg");
			arena1 = loader.loadImage("arena1.jpg");
			arena2 = loader.loadImage("arena2.jpg");
			tammy = loader.loadImage("tammy.png");
		} catch(IOException e)
		{
			e.printStackTrace();
		}
		
		menu = new MainMenu(menuBG);
		p1 = new Fighter(300, tammy.getHeight() + 90);
		p2 = new Fighter(WIDTH - 300 - tammy.getWidth(), tammy.getHeight() + 90);
		colCheck = new CollisionChecker(p1, p2);
		
//		p1.setOpponent(p2);
//		p2.setOpponent(p1);
		
		if(Math.random() > .5) {
			game = new MainGame(arena1, p1, p2);
		} else {
			game = new MainGame(arena2, p1, p2);
		}

		input1 = new KeyInputP1(p1);
		input2 = new KeyInputP2(p2);
		
		this.addMouseListener(new MouseInput(menu));
		this.addKeyListener(input1);
		this.addKeyListener(input2);
	}

	private synchronized void start()
	{
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	private synchronized void stop()
	{
		if(!running)
			return;
		running = false;
		try
		{
			thread.join();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.exit(1);
	}

	public void run()
	{
		long lastTime = System.nanoTime(); 
		final double fps = 60.0;
		double ns = 1000000000 / fps;
		double delta = 0; // time passed

		// to display time and fps
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		initialize();
		while(running)
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

			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println("Ticks: " + updates + ", FPS: " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	private void tick()
	{
		p1.move();
		p2.move();
		colCheck.fixCollisions();
//		input1.timerDown();
//		input2.timerDown();
	}

	private void render()
	{
		BufferStrategy strat = this.getBufferStrategy();

		if (strat == null)
		{
			createBufferStrategy(3);  // its a triple buffer, so the system thinks 2 steps ahead
			return;
		}

		g = strat.getDrawGraphics();
		if (State == STATE.MENU) {
			menu.draw(g);
		}
		if (State == STATE.GAME)
		{
			game.draw(g);
			p1.draw(g);
			p2.draw(g);
		}
		// This is where we draw shit /////////////
//		menu.render(g);
		///////////////////////////////////////////
		g.dispose();
		strat.show();
	}
	
	public static void main(String  args[])
	{
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		game.initialize();
		
		JFrame frame = new JFrame(game.TITLE);
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