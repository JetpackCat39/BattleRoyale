package game.Fighters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.BattleRoyale;
import game.GUIUtils;
import game.Input.PlayerControls;
import game.Menus.IScreen;

public abstract class Fighter
{
	private static final int KICK = 2;
	private static final int MAX_Y_SPEED = 15;
	private static final int STARTHEALTH = 20;
	private static final int PUNCH = 3;
	private final int BASE;
	private int height = BattleRoyale.HEIGHT;
	private int x, y, xSpeed, ySpeed, changeAnimation, health, frame;
	// private int width = BattleRoyale.WIDTH;
	private PlayerControls controls;
	private Fighter opponent;
	private BufferedImage sprites, win, loss;

	enum STATE
	{
		IDLE(0), WALK(1), KICK(2), PUNCH(3), JUMP(4), CROUCH(5), ENTER(6), BLOCK(7), HIT(8);

		private final int _index;

		STATE(int index)
		{
			_index = index;
		}

		public int getIndex()
		{
			return _index;
		}
	}

	private STATE State = STATE.IDLE;

	protected void setState(STATE s)
	{
		if (checkState(s))
		{
			return;
		}
		System.out.println("From " + State + " To " + s);
		State = s;
	}

	protected boolean checkState(STATE s)
	{
		return State == s;
	}

	public Fighter(int newX, int newY, BufferedImage spriteSheet, BufferedImage victory, BufferedImage KO,
			boolean isPlayer1)
	{
		x = newX + getWidth();
		y = newY + getHeight();
		xSpeed = 0;
		ySpeed = 0;
		BASE = newY;
		opponent = null;
		health = STARTHEALTH;
		controls = new PlayerControls(isPlayer1);
		frame = 0;
		changeAnimation = 0;
		sprites = spriteSheet;
		win = victory;
		loss = KO;
	}

	protected abstract int getNumImages(STATE s);

	protected abstract int getAnimationSpeed(STATE s);

	protected BufferedImage getSpriteSheet()
	{
		return sprites;
	}

	protected BufferedImage getWinAnimation()
	{
		return win;
	}

	protected BufferedImage getLossAnimation()
	{
		return loss;
	}

	public void changeYSpeed(int howMuch)
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

	public int getXSpeed()
	{
		return xSpeed;
	}

	public int getYSpeed()
	{
		return ySpeed;
	}

	public boolean move(int minX, int maxX)
	{
		int prevX = x;
		int prevY = y;
		x += xSpeed;
		if (x > maxX)
		{
			x = maxX;
		}
		if (x < minX)
		{
			x = minX;
		}
		y += ySpeed;
		if (y < BASE)
		{
			y = BASE;
		}
		if (y > BASE)
		{
			changeYSpeed(-1);
		}
		if (height - y < 0)
		{
			y = height;
			ySpeed *= -1;
		}
		moveCollisionChecker();
		if (checkState(STATE.JUMP) && y == BASE)
		{
			setState(STATE.IDLE);
		}
		return (prevX != x) || (prevY != y);
	}

	public void moveCollisionChecker()
	{
		if (compareXPosition() > 0)
		{
			x = opponent.getX() + opponent.getWidth();
		}
		else if (compareXPosition() < 0)
		{
			x = opponent.getX() - getWidth();
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

	public void setX(int val)
	{
		x = val;
	}

	public void setY(int val)
	{
		y = val;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public abstract int getWidth();

	public abstract int getHeight();

	public void jump()
	{
		setState(STATE.JUMP);
		if (y > BASE)
		{
			return;
		}
		setYSpeed(MAX_Y_SPEED);
	}

	public void punch()
	{
		if (checkState(STATE.PUNCH))
		{
			return;
		}
		setState(STATE.PUNCH);
		if (compareXPosition() != 0)
		{
			opponent.damage(PUNCH);
		}
	}

	public void kick()
	{
		if (checkState(STATE.KICK))
		{
			return;
		}
		setState(STATE.KICK);
		if (compareXPosition() != 0)
		{
			opponent.damage(KICK);
		}
	}

	public int getHealth()
	{
		return health;
	}

	public int getPause()
	{
		return controls.getPause();
	}

	public void setHealth(int newHealth)
	{
		health = newHealth;
	}

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

	public void draw(Graphics g, int offset)
	{
		GUIUtils.self().drawImg(getSpriteSheet(), frame * (getWidth() + 1), State.getIndex() * (getHeight() + 1),
				x + offset, height - y, getWidth(), getHeight(), g);
		changeAnimation++;
		if (changeAnimation >= getAnimationSpeed(State))
		{
			if (checkState(STATE.JUMP))
			{
				System.out.println(frame);
			}
			frame++;
			changeAnimation = 0;
		}
		if (frame >= getNumImages(State))
		{
			if (checkState(STATE.PUNCH))
			{
				setState(STATE.IDLE);
			}
			if (checkState(STATE.KICK))
			{
				setState(STATE.IDLE);
			}
			if (checkState(STATE.JUMP) && y == BASE)
			{
				setState(STATE.IDLE);
			}
			frame = 0;
			if (checkState(STATE.CROUCH))
			{
				frame = 1;
			}
		}
	}

	public void setOpponent(Fighter fighter)
	{
		opponent = fighter;
	}

	public void keyPressed(IScreen screen, int keyCode)
	{
		if (keyCode == controls.getLeft())
		{
			walkLeft();
		}
		else if (keyCode == controls.getRight())
		{
			walkRight();
		}
		else if (keyCode == controls.getJump())
		{
			jump();
		}
		else if (keyCode == controls.getCrouch())
		{
			crouch();
		}
		else if (keyCode == controls.getPunch())
		{
			punch();
		}
		else if (keyCode == controls.getKick())
		{
			kick();
		}
	}

	private void walkRight()
	{
		setState(STATE.WALK);
		setXSpeed(5);
	}

	private void walkLeft()
	{
		setState(STATE.WALK);
		setXSpeed(-5);
	}

	private void crouch()
	{
		setState(STATE.CROUCH);
	}

	public void keyReleased(int keyCode)
	{
		if (keyCode == controls.getLeft())
		{
			if (getXSpeed() != 0)
			{
				stopWalking();
			}
		}
		else if (keyCode == controls.getRight())
		{
			if (getXSpeed() != 0)
			{
				stopWalking();
			}
		}
		else if (keyCode == controls.getCrouch())
		{
			setState(STATE.IDLE);
		}
	}

	private void stopWalking()
	{
		setXSpeed(0);
		setState(STATE.IDLE);
	}
}