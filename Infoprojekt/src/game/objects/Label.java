package game.objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Label {
	
	int x;
	int y;
	int width;
	int height;
	Boolean openclose;
	Button closebut;
	
	public Label(int x, int y, int width, int height,Boolean openclose) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.openclose = openclose;
	}
	
	public void draw(Graphics g) {
		if(openclose) {
			g.setColor(Color.white);
			g.fillRect(x, y, width, height);
			closebut = new Button(x+width-10,y,10,10,"X",7);
			closebut.draw(g);
		}
	}

	public void close() {
	    openclose = false;
	}
	
	public void open() {
		openclose = true;
    }

	public boolean but(int mouseX ,int mouseY) {
		if (mouseX >= x+width-10 && mouseX <= x + width && mouseY >= y && mouseY <= y + 10) {
			return true;
		}
		return false;
	}
	


}
