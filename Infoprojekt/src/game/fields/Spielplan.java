package game.fields;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class Spielplan {
	
	int x;
	int y;
	Felder[] felder = new Felder[9];
	GameContainer cont;
	int[] xpos = new int[11];
	int[] ypos = new int[9];
	int[][] cords = new int[9][11];
	int landschaftskarte;
	
	public Spielplan(int x,int y,GameContainer container){
		this.x = x;
		this.y = y;
		this.cont = container;
		init();
	}
	
	public void init(){
		for (int i = 0; i < 9; i++) {
			ypos[i] = y+cont.getWidth()/22*i;
		}
		for (int j = 0; j < 11; j++) {
			xpos[j] = x+cont.getWidth()/22*j;
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 11; j++) {
				cords[i][j] = (i*100)+j;
			}
		}
	}
	
	public void initPlan1() throws SlickException{
		felder[0] = new Felder(x,y,7,7,2,2,7,7,7,6,6,7,7,cont);
		felder[1] = new Felder(x,y+cont.getWidth()/22,7,2,2,4,4,7,6,6,1,1,7,cont);
		felder[2] = new Felder(x,y+cont.getWidth()/22*2,2,2,3,3,4,4,4,1,1,1,5,cont);
		felder[3] = new Felder(x,y+cont.getWidth()/22*3,2,2,6,3,3,3,3,1,5,5,5,cont);
		felder[4] = new Felder(x,y+cont.getWidth()/22*4,7,6,6,6,6,3,1,1,4,4,7,cont);
		felder[5] = new Felder(x,y+cont.getWidth()/22*5,6,6,1,5,6,3,2,1,4,3,3,cont);
		felder[6] = new Felder(x,y+cont.getWidth()/22*6,6,1,1,5,2,2,2,2,4,3,3,cont);
		felder[7] = new Felder(x,y+cont.getWidth()/22*7,7,1,5,5,5,5,7,4,4,3,7,cont);
		felder[8] = new Felder(x,y+cont.getWidth()/22*8,7,7,5,5,5,7,7,4,4,7,7,cont);
	}

	public int PlättchenAusgewählt(int mouseX, int mouseY){
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 11; j++) {
				if(mouseX >= xpos[j] && mouseX <= xpos[j]+ cont.getWidth()/39 && mouseY >= ypos[i] && mouseY <= ypos[i] + cont.getWidth()/39){
					return cords[i][j];
				}
			}
		}
		return 5000;
	}
	
	public void draw(Graphics g){
		for (int i = 0; i < felder.length; i++) {
			felder[i].draw(g);
		}
	}

	public int getLandschaft(int x, int y) {
		int landschaft = felder[y].getLandschaft(x);
		if(landschaft == 1){
			landschaftskarte = 3;
		}
		if(landschaft == 2){
			landschaftskarte = 2;
		}
		if(landschaft == 3){
			landschaftskarte = 1;
		}
		if(landschaft == 4){
			landschaftskarte = 6;
		}
		if(landschaft == 5){
			landschaftskarte = 4;
		}
		if(landschaft == 6){
			landschaftskarte = 5;
		}
		if(landschaft == 7){
			landschaftskarte = 7;
		}
		return landschaftskarte;
	}

}
