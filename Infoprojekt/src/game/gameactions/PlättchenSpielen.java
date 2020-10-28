package game.gameactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import game.fields.Spielplan;
import game.gameobjects.Tierplättchen;
import game.life.Instanzen;

public class PlättchenSpielen {

	int spieler;
	int landschaft;
	int karte;
	int tier;
	int x;
	int y;
	int xpos;
	int ypos;
	GameContainer container;
	Tierplättchen tierplättchen;
	Spielplan plan;
	int mouseX;
	int mouseY;
	int plättchenAusgewählt;
	boolean gespielt = false;
	boolean gewählt = false;
	boolean bereitsGespielt;
	
	public PlättchenSpielen(int spieler, int karte, Spielplan plan, GameContainer container,int mouseX,int mouseY,boolean bereitsGespielt) throws SlickException {
		this.spieler = spieler;
		this.karte = karte;
		this.container = container;
		this.plan = plan;
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.bereitsGespielt = bereitsGespielt;
		init();
	}

	public void init() throws SlickException {
		this.tier = Instanzen.getCharacter(spieler);
	}
	
	public void update() throws SlickException{
		this.plättchenAusgewählt = plan.PlättchenAusgewählt(mouseX, mouseY);
		this.xpos = plättchenAusgewählt%100;
		this.ypos = (plättchenAusgewählt-x)/100;
		this.x = container.getWidth()/2 - container.getWidth()/22*11/2+10 + container.getWidth()/22*xpos;
		this.y = container.getHeight()/2 - container.getWidth()/22*9/2+container.getWidth()/22*ypos;	
		if(plättchenAusgewählt < 5000) {
			gewählt = true;
			this.landschaft = plan.getLandschaft(xpos,ypos);
			if(landschaft == karte) {
				gespielt = true;
			}
		}
	}
	
	public int getXPlättchen(){
		return x-5;
	}
	
	public int getYPlättchen(){
		return y-5;
	}

	public boolean FeldWandernAusgewählt() {
		return !bereitsGespielt && gewählt;
	}
	
	public boolean FeldAusgewählt() {
		return !bereitsGespielt && gespielt;
	}

	public int getFeldart(int x, int y) {
		return landschaft;
	}

}
