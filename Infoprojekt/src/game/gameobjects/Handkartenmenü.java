package game.gameobjects;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import game.life.Instanzen;
import game.objects.Label;

public class Handkartenmenü {
	
	int x;
	int y;
	int spieler;
	GameContainer container;
	Handkarte[] karten = new Handkarte[10];
	Label label1;
	int[] kartenart = new int[10];
	boolean openclose;
	int[] xpos = new int[10];
	int[] ypos = new int[10];
	int[] r;

	public Handkartenmenü(int spieler,GameContainer container) throws SlickException {
		super();
		this.spieler = spieler;
		this.x = container.getWidth()/2-55/2-container.getWidth()/11*5/2;
		this.y = container.getHeight()/2-10-container.getWidth()/11*3/2;
		this.container = container;
		init();
	}
	
	public void init() throws SlickException{
		label1 = new Label(x-10,y,75+container.getWidth()/11*5,30+container.getWidth()/11*3,true);
		if(Instanzen.getSpielLaden() == false){
			r = new Random().ints(1, 9).limit(10).toArray();
		}
		else{
			r = new int[Instanzen.getSpieler()];
			r = Instanzen.getHandkarten(spieler);
		}
		for (int i = 0; i < karten.length; i++) {
			if(i < 5){
				xpos[i] = x+((i+1)%6)*10+container.getWidth()/11*(i%5);
				ypos[i] = y+10;
			}
			else{
				xpos[i] = x+((i+2)%6)*10+container.getWidth()/11*(i%5);
				ypos[i] = y+20+container.getWidth()/11*3/2;
			}
			karten[i] = new Handkarte(xpos[i],ypos[i],container.getWidth()/11,container.getWidth()/11*3/2,r[i]);
			this.kartenart[i] = r[i];
			Instanzen.setHandkarten(kartenart[i],i,spieler);
		}
	}
	
	public int KarteAusgewählt(int mouseX, int mouseY){
		for (int i = 0; i < karten.length; i++) {
			if(mouseX >= xpos[i] && mouseX <= xpos[i]+ container.getWidth()/11 && mouseY >= ypos[i] && mouseY <= ypos[i] + container.getWidth()/11*3/2){
				return i;
			}
		}
		return 20;
	}
	
	public int getKartenart(int karte){
		return kartenart[karte];
	}
	
	public void KarteWechseln(int karte) throws SlickException{
		final int[] r = new Random().ints(1, 9).limit(1).toArray();
		karten[karte].setKartenart(r[0]);
		this.kartenart[karte] = r[0];
		Instanzen.setHandkarten(kartenart[karte],karte,spieler);
	}

	public void SaveLaden(){
		this.kartenart = Instanzen.getHandkarten(spieler);
	}
	
	public void draw(Graphics g){
		if(openclose){
			label1.draw(g);
			for(int i = 0; i < karten.length; i++){
				karten[i].draw(g);
			}	
		}
	}
	

	public boolean but(int mouseX, int mouseY) {
		if(label1.but(mouseX,mouseY)){
			return true;
		}
		return false;
	}

	public void close() {
		openclose = false;
	}

	public void open() {
		openclose = true;
	}
	
	public boolean LabelOpen(){
		return openclose;
	}

}
