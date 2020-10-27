package game.gameobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import game.objects.Label;

public class Spielregel {
	
	GameContainer container;
	Label label1;
	boolean openclose;
	int width;
	int height;
	Image image1;
	Image image2;

	public Spielregel(GameContainer container) throws SlickException {
		super();
		this.container = container;
		this.width = container.getWidth()/2;
		this.height = container.getWidth()/3;
		label1 = new Label(container.getWidth()/2-width/2,container.getHeight()/2-height/2,width,height,false);
		image1 = new Image("res/Spielregel (1).jpg").getScaledCopy((width-30)/2, (height-20));
		image2 = new Image("res/Spielregel (2).jpg").getScaledCopy((width-30)/2, (height-20));
	}
	
	public boolean but(int mouseX, int mouseY) {
		if(label1.but(mouseX,mouseY)){
			return true;
		}
		return false;
	}

	public void close() {
		openclose = false;
		label1.close();
	}

	public void open() {
		openclose = true;
		label1.open();
	}
	
	public void draw(Graphics g){
		if(openclose){
			label1.draw(g);
			g.drawImage(image1, container.getWidth()/2-(width/2)+10,container.getHeight()/2-height/2+10);	
			g.drawImage(image2, container.getWidth()/2+5,container.getHeight()/2-height/2+10);	
		}
	}

}
