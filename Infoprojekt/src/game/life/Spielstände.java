package game.life;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Spielstände extends BasicGameState  {

	Input input;
	String[] spielID;
	String[] anzahlSpieler;
	String[] wertungen;
	String[][] punkte;
	String[][] entwicklung;
	String[][] plättchen;

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		BufferedReader bufferedr = null;
		try {
        	BufferedReader bufferedr1 = new BufferedReader(new InputStreamReader(new URL("http://localhost/web/SQLAusgabe%20-%20Kopie.php").openStream()));
            String line = null;
            while((line = bufferedr1.readLine()) != null) { 
            	String[] spielstände = line.split("%");
            	spielID = spielstände[0].split("-");
            	anzahlSpieler = spielstände[1].split("-");
            	wertungen = spielstände[2].split("-");
            	String[] entwicklun = spielstände[3].split("-");
            	String[] plättche = spielstände[4].split("-");
            	String[] punkt = spielstände[5].split("-");
            	punkte = new String[punkt.length][4];
            	entwicklung = new String[punkt.length][4];
            	plättchen = new String[punkt.length][4];
            	for(int i = 0; i < (punkt.length); i++){
            		punkte[i] = punkt[i].split(";");
            		plättchen[i] = plättche[i].split(";");
            		entwicklung[i] = entwicklun[i].split(";");
            		for(int j = 0; j < 4; j++){
            			if(punkte[i][j] == null){
            				punkte[i][j] = "/";
            			}
            			if(plättchen[i][j] == null){
            				plättchen[i][j] = "/";
            			}
            			if(entwicklung[i][j] == null){
            				entwicklung[i][j] = "/";
            			}
            		}
            	}
            }
	    } catch(FileNotFoundException e) {
	        e.printStackTrace();
	    } catch(IOException e) {
	        e.printStackTrace();
	    } finally {
	        if(bufferedr != null) {
	            try {
	                bufferedr.close();
	            } catch(IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawString("W�hle den Spielstand, den du laden m�chtest durch klicken auf die jeweilige Zeile", 100, 10);
		g.drawLine(20, 40, container.getWidth()-20, 40);
		g.drawString("SpielID    Anzahl Spieler    Wertungen    Gesamtentwicklung: Spieler  1    2    3    4   Pl�ttcen auf Plan: Spieler  1    2    3    4    Punkte: Spieler  1    2    3    4", 20, 50);
		g.drawLine(20, 80, container.getWidth()-20, 80);
		for(int i = 0; i < (spielID.length); i++){
			g.drawString("   "+spielID[i]+"             "+anzahlSpieler[i]+"               "+wertungen[i]+"                                    "+entwicklung[i][0]+"    "+entwicklung[i][1]+"    "+entwicklung[i][2]+"    "+entwicklung[i][3]+"                                "+plättchen[i][0]+"    "+plättchen[i][1]+"    "+plättchen[i][2]+"    "+plättchen[i][3]+"                     "+punkte[i][0]+"    "+punkte[i][1]+"    "+punkte[i][2]+"    "+punkte[i][3], 20, 90+(i*40));
			g.drawLine(20, 120+(i*40), container.getWidth()-20, 120+(i*40));
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		this.input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(3);
		}
		int mouseY = input.getMouseY();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			for(int i = 0; i < spielID.length; i++){
				if(mouseY > 80+(i*40) && mouseY < 120+(i*40)){
					Instanzen.setSpielID(i+1);
					sbg.enterState(3);
				}
			}
		}
	}

	@Override
	public int getID() {
		return 4;
	}
	
}


