package game.gameactions;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import game.gameobjects.Handkartenmenü;
import game.life.Instanzen;

public class SpielerAmZug {

	int spieler;
	int x;
	int y;
	Handkartenmenü[] handkarten = new Handkartenmenü[4];
	GameContainer container;
	
	public SpielerAmZug(GameContainer container) throws SlickException {
		this.container = container;
		for (int i = 0; i < 4; i++) {
			handkarten[i] = new Handkartenmenü(i,container);
		}
		this.spieler = Instanzen.getSpAZ();
		init();
	}
	
	public void setSpAZ(int spieler) throws SlickException {
		Instanzen.setSpAZ(spieler);
		this.spieler = Instanzen.getSpAZ();
		init();
	}
	
	public void init() throws SlickException {
		handkarten[spieler-1].SaveLaden();
		if(spieler == 1) {
			this.x = 0;
			this.y = container.getHeight()-container.getHeight()/5;
		}
		if(spieler == 2) {
			this.x = container.getWidth()-container.getWidth()/4;
			this.y = container.getHeight()-container.getHeight()/5;
		}
		if(spieler == 3) {
			this.x = container.getWidth()-container.getWidth()/4;
			this.y = 0;
		}
		if(spieler == 4) {
			this.x = 0;
			this.y = 0;
		}
	}
	
	public int getSpAZ() {
		return spieler;
	}
	
	public boolean but(int mouseX, int mouseY) {
		if(handkarten[spieler-1].but(mouseX, mouseY)){
			return true;
		}
		return false;
	}
	
	public boolean LabelOpen(){
		return handkarten[spieler-1].LabelOpen();
	}

	public void close() {
		handkarten[spieler-1].close();
	}

	public void open() {
		handkarten[spieler-1].open();
	}

	public int KarteAusgewählt(int mouseX, int mouseY) {
		return handkarten[spieler-1].KarteAusgewählt(mouseX,mouseY);
	}
	
	public int getKartenart(int karte){
		return handkarten[spieler-1].getKartenart(karte);
	}
	
	public void KarteWechseln(int karte) throws SlickException{
		handkarten[spieler-1].KarteWechseln(karte);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.setLineWidth(5f);
		g.drawRect(x, y, container.getWidth()/4,container.getHeight()/5);
		g.drawString("Spieler "+spieler+" ist an der Reihe", 700, 40);
		handkarten[spieler-1].draw(g);
	}
}
