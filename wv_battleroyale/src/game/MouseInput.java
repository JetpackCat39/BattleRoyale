package game;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class MouseInput implements MouseListener
{

	JFrame myFrame;
	
	public void mouseClicked(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		int myX = e.getX();
		int myY = e.getY();
		
		while (Game.State == Game.STATE.MENU)
		{
			// Play button
			if (MainMenu.play.contains(myX, myY))
			{
				Game.State = Game.STATE.GAME;
			}
			// Help button
			if (MainMenu.help.contains(myX, myY))
			{
				Game.State = Game.STATE.HELP;
			}
			// Controls button
			if (MainMenu.controls.contains(myX, myY))
			{
				Game.State = Game.STATE.CONTROLS;
			}
			// About button
			if (MainMenu.about.contains(myX, myY))
			{
				Game.State = Game.STATE.ABOUT;
			}
			// Quit button
			if (MainMenu.quit.contains(myX, myY))
			{
				System.exit(1);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

}