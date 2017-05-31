package game.Fighters;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import game.BattleRoyale;
import game.GUIUtils;
import game.Input.PlayerControls;
import game.Menus.IScreen;

public abstract class Fighter
{
	private int height = BattleRoyale.HEIGHT;
	private static int width = BattleRoyale.WIDTH;
	private static final int MAX_Y_SPEED = 20;
	private static final int HP_BAR_WIDTH = 100;
	private static final int HP_BAR_HEIGHT = 30;
	private static final int HP_BAR_MARGIN = 100;
	private static final int HP_BAR_Y = 100;
	private static final int HP_BAR_X_P1 = HP_BAR_MARGIN;
	private static final int HP_BAR_X_P2 = width - HP_BAR_WIDTH - HP_BAR_MARGIN;
	private static final Color P1COLOR = Color.red;
	private static final Color P2COLOR = Color.blue;
	private final int BASE;
	private int x, y, xSpeed, ySpeed, changeAnimation, health, frame;
	private boolean isP1;
	private PlayerControls controls;
	private Fighter opponent;
	private BufferedImage sprites, winorloss;

	enum STATE
	{
		IDLE(0), WALK(1), KICK(2), PUNCH(3), JUMP(4), CROUCH(5), ENTER(6), BLOCK(7);

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
//		System.out.println("From " + State + " To " + s);
		State = s;
	}

	protected boolean checkState(STATE s)
	{
		return State == s;
	}

	public Fighter(int newX, int newY, BufferedImage spriteSheet, BufferedImage worl, boolean isPlayer1, PlayerControls c)
	{
		if (isPlayer1)
		{
			sprites = spriteSheet;
			frame = 0;
			x = newX;
		}
		else 
		{
			sprites = GUIUtils.self().flipImage(spriteSheet);
			frame = getMaxFrames();
			x = newX - getDrawWidth();
		}
		y = newY + getDrawHeight();
		xSpeed = 0;
		ySpeed = 0;
		BASE = newY + getSrcHeight();
		opponent = null;
		health = getMaxHealth();
		isP1 = isPlayer1;
		controls = c;
		changeAnimation = 0;
		winorloss = worl;
	}

	protected abstract int getNumImages(STATE s);

	protected abstract int getAnimationSpeed(STATE s);
	
	protected abstract int getMaxFrames();

	protected BufferedImage getSpriteSheet()
	{
		return sprites;
	}

	protected BufferedImage getWLAnimation()
	{
		return winorloss;
	}
	
	public abstract int getWidth();
	public abstract int getSrcWidth();
	public abstract int getDrawWidth();

	public abstract int getSrcHeight();
	public abstract int getDrawHeight();
	
	public abstract int getPunchDamage();
	public abstract int getBlockedPunchDamage();
	public abstract int getKickDamage();
	public abstract int getBlockedKickDamage();
	
	public abstract int getMaxHealth();

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
		moveX(minX, maxX);
		moveY();
		moveCollisionChecker();
		if (checkState(STATE.JUMP) && y == BASE)
		{
			setState(STATE.IDLE);
		}
		return (prevX != x) || (prevY != y);
	}

	private void moveX(int minX, int maxX)
	{
		x += xSpeed;
		if (getLeft() > maxX)
		{
			setLeft(maxX);
		}
		if (getLeft() < minX)
		{
			setLeft(minX);
		}
	}
	
	private void moveY()
	{
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
			ySpeed *= -1.5;
		}
	}

	public void moveCollisionChecker()
	{
		if (compareXPosition() > 0)
		{
			setLeft(opponent.getLeft() - getWidth());
		}
		else if (compareXPosition() < 0)
		{
			setLeft(opponent.getRight());
		}
	}

	// will only return non-0 values if fighters are touching
	public int compareXPosition()
	{
		// if you're to the left of them
		if (getLeft() < opponent.getRight() && getLeft() >= opponent.getLeft())
		{
			return 1;
		}
		// if you're to the right of them
		else if (getRight() > opponent.getLeft() && opponent.getLeft() >= getLeft())
		{
			return -1;
		}
		return 0;
	}

	public void setY(int val)
	{
		y = val;
	}
	
	public int getLeft()
	{
		return isP1 ? x : (x + getDrawWidth() - getWidth());
	}
	
	public void setLeft(int left)
	{
		if (isP1)
		{
			x = left;
		}
		else
		{
			x = left + getWidth() - getDrawWidth();
		}
	}
	
	public int getRight()
	{
		return isP1 ? (x + getWidth()) : (x + getDrawWidth());
	}

	public int getY()
	{
		return y;
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

	public void jump()
	{
		setState(STATE.JUMP);
		if (y > BASE)
		{
			return;
		}
		setYSpeed(MAX_Y_SPEED);
	}
	
	public void block()
	{
		setState(STATE.BLOCK);
	}

	public void punch()
	{
		if (checkState(STATE.PUNCH))
		{
			return;
		}
		setState(STATE.PUNCH);
		if (isP1 ? (opponent.getLeft() < getLeft() + getDrawWidth()) : (opponent.getRight() > getRight() - getDrawWidth()))
		{
			if (!opponent.checkState(STATE.CROUCH))
			{
				if (opponent.checkState(STATE.BLOCK))
				{
					opponent.damage(getBlockedPunchDamage());
				}
				else
				{
					opponent.damage(getPunchDamage());
				}
			}
		}
	}

	public void kick()
	{
		if (checkState(STATE.KICK))
		{
			return;
		}
		setState(STATE.KICK);
		if (isP1 ? (opponent.getLeft() < getLeft() + getDrawWidth()):(opponent.getRight() > getRight() - getDrawWidth()))
		{
			if (!opponent.checkState(STATE.JUMP))
			{
				if (opponent.checkState(STATE.BLOCK))
				{
					opponent.damage(getBlockedKickDamage());
				}
				else
				{
					opponent.damage(getKickDamage());
				}
			}
		}
	}

	private void stopWalking()
	{
		setXSpeed(0);
		setState(STATE.IDLE);
	}

	public void setHealth(int newHealth)
	{
		health = newHealth;
	}
	
	public int getPause()
	{
		return controls.getPause();
	}

	public void damage(int damage)
	{
		health -= damage;
		if (health < 0)
		{
			setHealth(0);
		}
		if (health > getMaxHealth())
		{
			setHealth(getMaxHealth());
		}
		if (health == 0)
		{

		}
	}

	public void draw(Graphics g, int offset) {
	
		GUIUtils.self().drawHP(isP1 ? HP_BAR_X_P1 : HP_BAR_X_P2, HP_BAR_Y, HP_BAR_WIDTH, HP_BAR_HEIGHT, health, 
				getMaxHealth(), isP1 ? P1COLOR : P2COLOR, g);

		FontMetrics fontMetrics = new JFrame().getFontMetrics(new Font("arial", Font.BOLD, 36));
	    
		GUIUtils.self().drawText(isP1 ? HP_BAR_X_P1 - fontMetrics.stringWidth("P1 "): 
			HP_BAR_X_P2 + HP_BAR_WIDTH + fontMetrics.stringWidth(" "), HP_BAR_Y + (int) fontMetrics.getAscent() - 5, Color.WHITE, isP1 ? "P1 " : " P2", 
				36, g, Font.BOLD);
		GUIUtils.self().drawImg(getSpriteSheet(), frame * getSrcWidth(), State.getIndex() * getSrcHeight(),
			x + offset, height - y, getSrcWidth(), getSrcHeight(), getDrawWidth(), getDrawHeight(), g);
		changeAnimation++;
		if(isP1) drawP1();
		else drawP2();
		
	}

	private int drawP1()
	{
		if (changeAnimation >= getAnimationSpeed(State))
		{
			frame++;
			changeAnimation = 0;
		}
		if (frame >= getNumImages(State))
		{
			setIdles();
			frame = 0;
			if (checkState(STATE.CROUCH))
			{
				frame = getNumImages(STATE.CROUCH) - 1;
			}
			if (checkState(STATE.BLOCK))
			{
				frame = getNumImages(STATE.BLOCK) - 1;
			}
		}
		return 1;
	}
	
	private void drawP2()
	{
		if (changeAnimation >= getAnimationSpeed(State))
		{
			frame--;
			changeAnimation = 0;
		}
		if (frame <= (getMaxFrames() - getNumImages(State)))
		{
			setIdles();
			frame = getMaxFrames();
			if (checkState(STATE.CROUCH))
			{
				frame = getMaxFrames() - getNumImages(STATE.CROUCH) + 1;
			}
			if (checkState(STATE.BLOCK))
			{
				frame = getMaxFrames() - getNumImages(STATE.BLOCK) + 1;
			}
		}
	}

	private void setIdles()
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
		else if (keyCode == controls.getBlock())
		{
			block();
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
		else if (keyCode == controls.getBlock())
		{
			setState(STATE.IDLE);
		}
		else if (keyCode == controls.getCrouch())
		{
			setState(STATE.IDLE);
		}
	}
}