package game.gameobjects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Handkarte {

	int x;
	int y;
	int width;
	int height;
	int kartenart;
	Image image;

	public Handkarte(int x, int y, int width, int height, int kartenart) throws SlickException {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.kartenart = kartenart;
		init();
	}

	public void init() throws SlickException {	
		if(kartenart == 12){
			image = new Image("res/Lücke.PNG").getScaledCopy(width, height);
		}
		else{
			image = new Image("res/Handkarten (" + kartenart + ").jpg").getScaledCopy(width, height);
		}
	}

	public int getKartenart() {
		return kartenart;
	}

	public void draw(Graphics g) {
		g.drawImage(image, x, y);
	}

	public void setKartenart(int kartenart) throws SlickException {
		this.kartenart = kartenart;
		init();
	}

}
