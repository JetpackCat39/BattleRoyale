package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Input.PlayerControls;
import game.Menus.Screen;

public class Fighter implements IOpponent
{

	private static final int KICK = 2;
	private static final int MAX_Y_SPEED = 15;
	private static final int MAX_X_SPEED = 5;
	private static final int STARTHEALTH = 20;
	private static final int PUNCH = 3;
	private int x, y, xSpeed, ySpeed;
	private BufferedImage image;
	private int height = BattleRoyale.HEIGHT;
	private int width = BattleRoyale.WIDTH;
	private final int BASE;
	private int jumpCount;
	private final int JUMPS = 1;
	private int health;
	private PlayerControls controls;
	private IOpponent opponent;
	private Hitbox hitbox;

	public Fighter(int newX, int newY, BufferedImage img, PlayerControls ctrls)
	{
		x = newX;
		y = newY;
		xSpeed = 0;
		ySpeed = 0;
		BASE = newY;
		jumpCount = 0;
		opponent = null;
		health = STARTHEALTH;
		image = img;
		controls = ctrls;
		hitbox = new Hitbox(newX, newY, img.getWidth(), img.getHeight());
		// BufferedImageLoader loader = new BufferedImageLoader();
		// try
		// {
		// image = loader.loadImage("tammy.png");
		// }
		// catch (IOException e)
		// {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public void incrementXSpeed(int howMuch)
	{
		int temp = xSpeed + howMuch;
		if (temp > MAX_X_SPEED)
		{
			temp = MAX_X_SPEED;
		}
		if (temp < -MAX_X_SPEED)
		{
			temp = -MAX_X_SPEED;
		}
		xSpeed = temp;
	}

	public void incrementYSpeed(int howMuch)
	{
		int temp = ySpeed + howMuch;
		if (temp > MAX_Y_SPEED)
		{
			temp = MAX_Y_SPEED;
		}
		if (temp < -MAX_Y_SPEED)
		{
			temp = -MAX_Y_SPEED;
		}
		ySpeed = temp;
	}

	public void setXSpeed(int newSpeed)
	{
		xSpeed = newSpeed;
	}

	public void setYSpeed(int newSpeed)
	{
		ySpeed = newSpeed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.iOpponent#getXSpeed()
	 */
	@Override
	public int getXSpeed()
	{
		return xSpeed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.iOpponent#getYSpeed()
	 */
	@Override
	public int getYSpeed()
	{
		return ySpeed;
	}

	public void move()
	{
		x += xSpeed;
		if (x > width - image.getWidth())
		{
			x = width - image.getWidth();
		}
		if (x < 0)
		{
			x = 0;
		}
		y += ySpeed;
		if (y < BASE)
		{
			y = BASE;
			jumpCount = 0;
		}
		if (y > BASE)
		{
			incrementYSpeed(-1);
		}
		if (height - y < 0)
		{
			y = height;
			ySpeed *= -1;
		}
		moveCollisionChecker();
	}

	public void moveCollisionChecker()
	{
		if (compareXPosition() != 0 && compareYPosition() != 0)
		{
			if (y >= (opponent.getY() + opponent.getHeight() - 50))
			{
				if (ySpeed < 0)
				{
					ySpeed = 0;
				}
				jumpCount = 0;
				y = opponent.getY() + opponent.getHeight();
			}
			else if (compareYPosition() < 0)
			{
				ySpeed = -1;
				jumpCount = 2;
			}
			else
			{
				x -= xSpeed;
				opponent.setX(opponent.getX() - opponent.getXSpeed());
				if (ySpeed <= 0)
				{
					xSpeed = 0;
					opponent.setXSpeed(0);
				}
			}
		}
	}

	public boolean isCollision()
	{
		return (compareXPosition() != 0 && compareYPosition() != 0)
				&& !(y >= (opponent.getY() + opponent.getHeight() - 50)) && !(compareYPosition() < 0);
	}

	// will only return non-0 values if fighters are touching
	public int compareXPosition()
	{
		// if you're to the right of them
		if (x < opponent.getX() + opponent.getWidth() && x >= opponent.getX())
		{
			return 1;
		}
		// if you're to the left of them
		else if (x + getWidth() > opponent.getX() && opponent.getX() >= x)
		{
			return -1;
		}
		return 0;
	}

	// will only return non-0 values if fighters are touching
	public int compareYPosition()
	{
		// if you're above them
		if ((y <= opponent.getY() + opponent.getHeight()) && (y >= (opponent.getY())))
		{
			return 1;
		}
		// if you're below them
		else if (y + getHeight() >= opponent.getY() && opponent.getY() >= y)
		{
			return -1;
		}
		return 0;
	}

	@Override
	public void setX(int val)
	{
		x = val;
	}

	public void setY(int val)
	{
		y = val;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.iOpponent#getX()
	 */
	@Override
	public int getX()
	{
		return x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.iOpponent#getY()
	 */
	@Override
	public int getY()
	{
		return y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.iOpponent#getWidth()
	 */
	@Override
	public int getWidth()
	{
		return image.getWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.iOpponent#getHeight()
	 */
	@Override
	public int getHeight()
	{
		return image.getHeight();
	}

	public int getJumpCount()
	{
		return jumpCount;
	}

	public void setJumpCount(int count)
	{
		jumpCount = count;
	}

	public void jump()
	{
		if (jumpCount < JUMPS)
		{
			ySpeed = MAX_Y_SPEED;
		}
		jumpCount++;
	}

	public void punch()
	{
		if (compareXPosition() != 0)
		{
			opponent.damage(PUNCH);
		}
	}

	public void kick()
	{
		if (compareXPosition() != 0)
		{
			opponent.damage(KICK);
		}
	}

	public int getHealth()
	{
		return health;
	}

	public void setHealth(int newHealth)
	{
		health = newHealth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.iOpponent#damage(int)
	 */
	@Override
	public void damage(int damage)
	{
		health -= damage;
		if (health < 0)
		{
			setHealth(0);
		}
		if (health > STARTHEALTH)
		{
			setHealth(STARTHEALTH);
		}
		if (health == 0)
		{

		}
	}

	public void draw(Graphics g)
	{
		GUIUtils.self().drawImg(image, x, height - y, image.getWidth(), image.getHeight(), g);
	}

	public void setOpponent(IOpponent fighter)
	{
		opponent = fighter;
	}

	@Override
	public Screen keyPressed(BattleRoyale g, int keyCode, Screen currentScreen, Screen previousScreen)
	{
		if (keyCode == controls.getLeft())
		{
			setXSpeed(-5);
		}
		else if (keyCode == controls.getRight())
		{
			setXSpeed(5);
		}
		else if (keyCode == controls.getJump())
		{
			jump();
		}
		else if (keyCode == controls.getPunch())
		{
			punch();
		}
		else if (keyCode == controls.getKick())
		{
			kick();
		}
		return currentScreen;
	}

	@Override
	public Screen keyReleased(int keyCode, Screen currentScreen, Screen previousScreen)
	{
		if (keyCode == controls.getLeft())
		{
			if (getXSpeed() != 0)
			{
				incrementXSpeed(5);
			}
		}
		else if (keyCode == controls.getRight())
		{
			if (getXSpeed() != 0)
			{
				incrementXSpeed(-5);
			}
		}
		return currentScreen;
	}
}