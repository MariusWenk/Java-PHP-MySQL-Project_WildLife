package game.gameactions;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import game.gameobjects.Handkarte;
import game.objects.Label;

public class UpgradeSkill {

	int spieler;
	GameContainer container;
	int x;
	int y;
	Label label1;
	Handkarte[] karten = new Handkarte[6];
	int[] kartenart = new int[6];
	int[] xpos = new int[6];
	int[] ypos = new int[6];

	public UpgradeSkill(int spieler, GameContainer container) throws SlickException {
		this.spieler = spieler;
		this.container = container;
		this.x = container.getWidth()/2-55/2-container.getWidth()/11*3/2;
		this.y = container.getHeight()/2-10-container.getWidth()/11*3/2;
		init();
	}
	
	public void init() throws SlickException{
		label1 = new Label(x-10,y,75+container.getWidth()/11*3,30+container.getWidth()/11*3,true);
		for (int i = 0; i < karten.length; i++) {
			if(i < 3){
				xpos[i] = x+((i+1)%4)*10+container.getWidth()/11*(i%3);
				ypos[i] = y+10;
			}
			else{
				xpos[i] = x+((i+2)%4)*10+container.getWidth()/11*(i%3);
				ypos[i] = y+20+container.getWidth()/11*3/2;
			}
			karten[i] = new Handkarte(xpos[i],ypos[i],container.getWidth()/11,container.getWidth()/11*3/2,i+1);
			this.kartenart[i] = i+1;
		}
	}
	
	public int getKartenart(int karte){
		return kartenart[karte];
	}
	
	public boolean but(int mouseX, int mouseY) {
		return label1.but(mouseX,mouseY);
	}
	
	public int KarteAusgewählt(int mouseX, int mouseY){
		for (int i = 0; i < karten.length; i++) {
			if(mouseX >= xpos[i] && mouseX <= xpos[i]+ container.getWidth()/11 && mouseY >= ypos[i] && mouseY <= ypos[i] + container.getWidth()/11*3/2){
				return i;
			}
		}
		return 20;
	}

	public void draw(Graphics g) {
		label1.draw(g);
		for(int i = 0; i < karten.length; i++){
			karten[i].draw(g);
		}
		g.setColor(Color.blue);															
		g.drawString("W�hle eine Gebietsart, in der du deine F�higkeiten verbessern willst", container.getWidth()/2-300, 70);
	}

}
