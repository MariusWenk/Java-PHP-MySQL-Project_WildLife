package game.gameactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import game.fields.Spielplan;
import game.gameobjects.Tierpl�ttchen;
import game.life.Instanzen;

public class Pl�ttchenSpielen {

	int spieler;
	int landschaft;
	int karte;
	int tier;
	int x;
	int y;
	int xpos;
	int ypos;
	GameContainer container;
	Tierpl�ttchen tierpl�ttchen;
	Spielplan plan;
	int mouseX;
	int mouseY;
	int pl�ttchenAusgew�hlt;
	boolean gespielt = false;
	boolean gew�hlt = false;
	boolean bereitsGespielt;
	
	public Pl�ttchenSpielen(int spieler, int karte, Spielplan plan, GameContainer container,int mouseX,int mouseY,boolean bereitsGespielt) throws SlickException {
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
		this.pl�ttchenAusgew�hlt = plan.Pl�ttchenAusgew�hlt(mouseX, mouseY);
		this.xpos = pl�ttchenAusgew�hlt%100;
		this.ypos = (pl�ttchenAusgew�hlt-x)/100;
		this.x = container.getWidth()/2 - container.getWidth()/22*11/2+10 + container.getWidth()/22*xpos;
		this.y = container.getHeight()/2 - container.getWidth()/22*9/2+container.getWidth()/22*ypos;	
		if(pl�ttchenAusgew�hlt < 5000) {
			gew�hlt = true;
			this.landschaft = plan.getLandschaft(xpos,ypos);
			if(landschaft == karte) {
				gespielt = true;
			}
		}
	}
	
	public int getXPl�ttchen(){
		return x-5;
	}
	
	public int getYPl�ttchen(){
		return y-5;
	}

	public boolean FeldWandernAusgew�hlt() {
		return !bereitsGespielt && gew�hlt;
	}
	
	public boolean FeldAusgew�hlt() {
		return !bereitsGespielt && gespielt;
	}

	public int getFeldart(int x, int y) {
		return landschaft;
	}

}
