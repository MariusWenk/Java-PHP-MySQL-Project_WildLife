package game.gameobjects;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import game.life.Instanzen;

public class Charakterkarten {
	
	int spieler;
	int x;
	int y;
	int r;
	int width;
	int height;
	Image image;
	Charakter[] character = new Charakter[4];
	Tierplättchen[] tpstapel = new Tierplättchen[4];
	private int[] xPosTpStapel = new int[4];
	private int[] yPosTpStapel = new int[4];
	int[] ints;
	
	public Charakterkarten(int spieler,GameContainer container) throws SlickException{
		this.spieler = spieler;
		init(container);
	}
	
	public void init(GameContainer container) throws SlickException{
		xPosTpStapel [0] = 0; 
		xPosTpStapel [1] = container.getWidth()-container.getWidth()/39-10;
		xPosTpStapel [2] = container.getWidth()-container.getWidth()/39-10;
		xPosTpStapel [3] = 0;
		yPosTpStapel [0] = container.getHeight()-container.getHeight()/4-container.getWidth()/39+10;
		yPosTpStapel [1] = container.getHeight()-container.getHeight()/4-container.getWidth()/39+10;
		yPosTpStapel [2] = container.getHeight()/4;
		yPosTpStapel [3] = container.getHeight()/4;
		if(Instanzen.getSpielLaden() == false){
			ints = new Random().ints(0, 5).distinct().limit(spieler).toArray();
		}
		else{
			ints = new int[Instanzen.getSpieler()];
			for(int i = 0; i < Instanzen.getSpieler(); i++){
				ints[i] = Instanzen.getCharacter(i+1);
			}
		}
			character[0] = new Charakter(0,container.getHeight()-container.getHeight()/5,container.getWidth()/4,container.getHeight()/5,ints[0]);
			character[1] = new Charakter(container.getWidth()-container.getWidth()/4,container.getHeight()-container.getHeight()/5,container.getWidth()/4,container.getHeight()/5,ints[1]);
			if(Instanzen.getSpieler() > 2){
				character[2] = new Charakter(container.getWidth()-container.getWidth()/4,0,container.getWidth()/4,container.getHeight()/5,ints[2]);
			}
			if(Instanzen.getSpieler() > 3){
				character[3] = new Charakter(0,0,container.getWidth()/4,container.getHeight()/5,ints[3]);
			}
			for(int i = 0; i < Instanzen.getSpieler(); i++){
				tpstapel[i] = new Tierplättchen(xPosTpStapel [i],yPosTpStapel [i],ints[i],container,i);
				Instanzen.setCharacter(ints[i],i);
				Instanzen.setSkillCharacter(character[i].getGebietsWerte(),ints[i]);
			}
	}
	
	public int setTier(Charakter character){
		return character.getTier();
	}

	
	public void draw(Graphics g) {
		for (int i = 0; i < spieler; i++) {
			character[i].draw(g);
			tpstapel[i].draw(g);
			String plättchen = Integer.toString(Instanzen.getPlättchenÜbrig(i+1));
			g.setColor(Color.black);
			g.drawString(plättchen, xPosTpStapel [i]+15, yPosTpStapel [i]+15);
		}
	}

	public void setSpieler(int spieler) {
		this.spieler = spieler;
	}

	public void update(int spieler) throws SlickException {
		character[spieler-1].update(Instanzen.getCharacter(spieler));
	}
	
}
