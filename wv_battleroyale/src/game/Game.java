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
	public static final int SCALE = 1;
	public final String TITLE = "Westview Battle Royale";

	// variables to make the game work
	private boolean running = false;
	private Thread thread;

	// buffer the window to reduce lag
	//private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private static BufferedImage background = null;
	private static BufferedImage tammy = null;

	private Graphics g;
	
	private MainMenu menu;
	private MainGame game;
	
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
		game = new MainGame(background, tammy, tammy);
		menu = new MainMenu(background);
		try
		{
			background = loader.loadImage("menuBG.jpg");
			tammy = loader.loadImage("tammy.png");
		} catch(IOException e)
		{
			e.printStackTrace();
		}

		this.addMouseListener(new MouseInput(menu));
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
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.initialize();
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	

		game.start();
	}
}