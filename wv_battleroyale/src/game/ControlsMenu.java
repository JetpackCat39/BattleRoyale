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
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			arrows = loader.loadImage("arrows.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wasd = loader.loadImage("wasd.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		buttonList.add(new Button(width *1/6, height * 2/9, "BACK",32,Font.BOLD));
	}
	
	public void draw(Graphics g) {
		
		super.draw(g);
		
		GUIUtils.self().drawText(width / 3, height * 2 / 9, Color.white, "CONTROLS",  72, g,Font.BOLD);
		GUIUtils.self().drawText(width/5,height*4/9,Color.white,"PLAYER 1",54,g,Font.PLAIN);
		GUIUtils.self().drawText(width*5/9,height*4/9,Color.white,"PLAYER 2",54,g,Font.PLAIN);
		
		GUIUtils.self().drawImg(wasd, width/5, height/2, wasd.getWidth()*2/3, wasd.getHeight()*2/3, g);
		GUIUtils.self().drawImg(arrows, width*5/9, height/2, arrows.getWidth()*2/3, arrows.getHeight()*2/3, g);
		
		getBack().draw(g);
	}
	
	public Button getBack() {
		return buttonList.get(0);
	}
}
