package game.gameobjects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import game.life.Instanzen;

public class Charakter {
	
	int x;
	int y;
	int width;
	int height;
	Image image;
	int tier;
	int[] gebietsWerte = new int[6];
	String[] werteAusgabe = new String[6];
	
	public Charakter(int x, int y, int width, int height, int tier) throws SlickException {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tier = tier;
		if(Instanzen.getSpielLaden() == false){
			init();
		}
		else{
			initSave();
		}
	}
	
	public int getTier(){
		return this.tier;
	}
	
	public void init() throws SlickException{
		if(tier < 5){
			this.image = new Image("res/Charakterkarten ("+(tier+1)+").PNG");
		}
		if(tier == 0){
			gebietsWerte[0] = 0;
			gebietsWerte[1] = 1;
			gebietsWerte[2] = 0;
			gebietsWerte[3] = 2;
			gebietsWerte[4] = 3;
			gebietsWerte[5] = 1;
		}
		if(tier == 1){
			gebietsWerte[0] = 1;
			gebietsWerte[1] = 0;
			gebietsWerte[2] = 3;
			gebietsWerte[3] = 0;
			gebietsWerte[4] = 2;
			gebietsWerte[5] = 1;
		}
		if(tier == 2){
			gebietsWerte[0] = 1;
			gebietsWerte[1] = 3;
			gebietsWerte[2] = 2;
			gebietsWerte[3] = 1;
			gebietsWerte[4] = 0;
			gebietsWerte[5] = 0;
		}
		if(tier == 3){
			gebietsWerte[0] = 1;
			gebietsWerte[1] = 2;
			gebietsWerte[2] = 0;
			gebietsWerte[3] = 3;
			gebietsWerte[4] = 1;
			gebietsWerte[5] = 0;
		}
		if(tier == 4){
			gebietsWerte[0] = 2;
			gebietsWerte[1] = 1;
			gebietsWerte[2] = 1;
			gebietsWerte[3] = 0;
			gebietsWerte[4] = 0;
			gebietsWerte[5] = 3;
		}
		if(tier == 5){
			this.image = new Image("res/Charakterkarten (1).jpg");
			gebietsWerte[0] = 3;
			gebietsWerte[1] = 1;
			gebietsWerte[2] = 1;
			gebietsWerte[3] = 0;
			gebietsWerte[4] = 0;
			gebietsWerte[5] = 2;
		}
		for(int i = 0; i < 6;i++){
			if(gebietsWerte[i] == 0){
				werteAusgabe[i] = "KeAk";
			}
			if(gebietsWerte[i] == 1){
				werteAusgabe[i] = "Wan";
			}
			if(gebietsWerte[i] == 2){
				werteAusgabe[i] = "Exp";
			}
			if(gebietsWerte[i] == 3){
				werteAusgabe[i] = "Att";
			}
		}
	}
	
	public int[] getGebietsWerte(){
		return this.gebietsWerte;
	}
	
	public void update(int character){
		for(int i = 0; i < 6;i++){
			gebietsWerte[i] = Instanzen.getSkillCharacter(i+1,character);
			if(gebietsWerte[i] == 0){
				werteAusgabe[i] = "KeAk";
			}
			if(gebietsWerte[i] == 1){
				werteAusgabe[i] = "Wan";
			}
			if(gebietsWerte[i] == 2){
				werteAusgabe[i] = "Exp";
			}
			if(gebietsWerte[i] == 3){
				werteAusgabe[i] = "Att";
			}
		}
	}
	
	public void initSave() throws SlickException{
		if(tier < 5){
			this.image = new Image("res/Charakterkarten ("+(tier+1)+").PNG");
		}
		if(tier == 5){
			this.image = new Image("res/Charakterkarten (1).jpg");
		}
		for(int i = 0; i < 6;i++){
			gebietsWerte[i] = Instanzen.getSkillCharacter(i+1,tier);
			if(gebietsWerte[i] == 0){
				werteAusgabe[i] = "KeAk";
			}
			if(gebietsWerte[i] == 1){
				werteAusgabe[i] = "Wan";
			}
			if(gebietsWerte[i] == 2){
				werteAusgabe[i] = "Exp";
			}
			if(gebietsWerte[i] == 3){
				werteAusgabe[i] = "Att";
			}
		}
	}
	
	public void draw(Graphics g) {
		Image im = image.getScaledCopy(width, height);
		g.drawImage(im, (float)x, (float)y);
		g.setColor(Color.white);
		g.fillRect(x+width/15,y+height/3,width/7,height/10);
		g.fillRect(x+width/25*11,y+height/3*2,width/7,height/10);
		g.fillRect(x+width/4,y+height/3,width/7,height/10);
		g.fillRect(x+width/15,y+height/3*2,width/7,height/10);
		g.fillRect(x+width/25*11,y+height/3,width/7,height/10);
		g.fillRect(x+width/4,y+height/3*2,width/7,height/10);
		g.setColor(Color.black);
		g.drawString(werteAusgabe[0], x+width/15,y+height/3);
		g.drawString(werteAusgabe[5], x+width/25*11,y+height/3*2);
		g.drawString(werteAusgabe[1], x+width/4,y+height/3);
		g.drawString(werteAusgabe[3], x+width/15,y+height/3*2);
		g.drawString(werteAusgabe[2], x+width/25*11,y+height/3);
		g.drawString(werteAusgabe[4], x+width/4,y+height/3*2);
	}
	
}
