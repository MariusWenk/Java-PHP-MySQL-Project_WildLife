package game.gameobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tierplättchen {
	
	int x;
	int y;
	int tier;
	int spieler;
	GameContainer container;
	Image image;
	
	public Tierplättchen(int x, int y, int tier,GameContainer container,int spieler) throws SlickException {
		super();
		this.x = x;
		this.y = y;
		this.tier = tier;
		this.container = container;
		this.spieler = spieler;
		init();
	}
	
	public void init() throws SlickException{
		image = new Image("res/Tierplättchen (" + (tier+1)+ ").PNG").getScaledCopy(container.getWidth()/39+10,container.getWidth()/39+10);
	}	
	
	public void draw(Graphics g) {
		g.drawImage(image, x, y);
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getTier() {
		return tier;
	}
	
	public int getSpieler() {
		return spieler;
	}

}
