package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ControlsMenu extends Screen implements IDrawable {
	BufferedImage arrows;
	BufferedImage wasd;
	public ControlsMenu(BufferedImage background){
		super(background);
		
		
		buttonList.add(new Button(width *1/6, height * 2/9, "BACK",32,Font.BOLD));
		buttonList.add(new Button(width*9/30, height * 5/9, 80,80, "W",32,Font.PLAIN));
		buttonList.add(new Button(width*9/30, height * 2/3, 80,80, "S",32,Font.PLAIN));
		buttonList.add(new Button(width*57/240, height * 2/3, 80,80, "A",32,Font.PLAIN));
		buttonList.add(new Button(width*87/240, height * 2/3, 80,80, "D",32,Font.PLAIN));
		buttonList.add(new Button(width*31/48, height * 5/9, 80,80, "\u2191",32,Font.BOLD));
		buttonList.add(new Button(width*31/48, height * 2/3, 80,80, "\u2193",32,Font.BOLD));
		buttonList.add(new Button(width*28/48, height * 2/3, 80,80, "\u2190",32,Font.BOLD));
		buttonList.add(new Button(width*34/48, height * 2/3, 80,80, "\u2192",32,Font.BOLD));
	}
	
	public void draw(Graphics g) {
		
		super.draw(g);
		
		GUIUtils.self().drawText(width / 3, height * 2 / 9, Color.white, "CONTROLS",  72, g,Font.BOLD);
		GUIUtils.self().drawText(width/5,height*4/9,Color.white,"PLAYER 1",54,g,Font.PLAIN);
		GUIUtils.self().drawText(width*5/9,height*4/9,Color.white,"PLAYER 2",54,g,Font.PLAIN);
		
		
		getBack().draw(g);
		for(int i = 1; i < 9; i ++){
			getKey(i).draw(g);
		}
		
	}
	
	public Button getBack() {
		return buttonList.get(0);
	}
	
	public Button getKey(int num){
		return buttonList.get(num);
	}
	

}
