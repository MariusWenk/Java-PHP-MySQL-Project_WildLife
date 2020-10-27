package game.fields;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Felder {
	
	int x;
	int y;
	int[] type = new int[11];
	int ra;
	int i = 0;
	ArrayList<Spielfeld> rects = new ArrayList<Spielfeld>();
	Image image;
	Feldtyp[] feldtyp = new Feldtyp[11];
	GameContainer container;
	
	public Felder(int x, int y, int type1,int type2, int type3,int type4,int type5,int type6,int type7, int type8, int type9, int type10, int type11, GameContainer container) throws SlickException {
		this.x = x;
		this.y = y;
		this.type[0] = type1;
		this.type[1] = type2;
		this.type[2] = type3;
		this.type[3] = type4;
		this.type[4] = type5;
		this.type[5] = type6;
		this.type[6] = type7;
		this.type[7] = type8;
		this.type[8] = type9;
		this.type[9] = type10;
		this.type[10] = type11;
		this.container = container;
		init();
	}
	
	public void init() throws SlickException {
		for (int i = 0; i < feldtyp.length; i++) {
			feldtyp[i] = new Feldtyp(type[i]);
			neuesFeld(x+container.getWidth()/22*i,y,feldtyp[i].getImage());
		}
	}
	
	public void draw(Graphics g) {		
		for (Spielfeld feld : rects) {
			feld.draw(g);
		}
	}
	
	private void neuesFeld(int X, int Y,Image image) {
		Spielfeld sch = new Spielfeld(X, Y,container.getWidth()/39,container.getWidth()/39,image);
		rects.add(sch);
	}

	public int getLandschaft(int position) {
		return type[position];
	}

}
