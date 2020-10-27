package game.fields;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Spielfeld {
	
	int x;
	int y;
	int width;
	int height;
	Image image;
	
	public Spielfeld(int x, int y, int width, int height, Image image) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
	}
	
	public void draw(Graphics g){
		Image ima = image.getScaledCopy(width + 20, height + 20);
		g.drawImage(ima,x - 10, y - 10);
		g.setColor(Color.black);
		g.setLineWidth(2f);
		g.drawRect(x, y, width, height);
	}
}
