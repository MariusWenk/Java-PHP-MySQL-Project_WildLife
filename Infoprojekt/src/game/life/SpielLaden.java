package game.life;

import org.newdawn.slick.*;
import java.awt.Font;

import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import game.gameobjects.Tierpl‰ttchen;
import game.objects.Button;

public class SpielLaden extends BasicGameState{
	
	Button button1;
	Button button2;
	String spielID = "1";
	TextField textfield;
	Font font;
	boolean spielSetzen = false;
	private int ypos;
	private int tierart;
	private int xpos;
	private int spieler;

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		button1 = new Button(container.getWidth()/2-50,370,100,60,"Spielst‰nde",49);
		button2 = new Button(container.getWidth()/2-50,450,100,60,"Start",23);
		textfield = new TextField(container,container.getDefaultFont(),100,100,300,20);
		textfield.setText("hi");
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawString("SpielID auf "+spielID+" gesetzt", container.getWidth()/2-100, 50);
		button1.draw(g);
		button2.draw(g);
		textfield.render(container, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		Input input = container.getInput();
		if(spielSetzen = true) {
			spielID = Integer.toString(Instanzen.getSpielID());
		}
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
		if(button1.press(mouseX,mouseY)){
			sbg.enterState(4);
			spielSetzen = true;
		}
		if(button2.press(mouseX,mouseY)){
			Instanzen.setSpielLaden();
			 BufferedReader bufferedr = null;
		        try {
		        	BufferedReader bufferedr1 = new BufferedReader(new InputStreamReader(new URL("http://localhost/web/SQLJavaConnect.php?spielID="+spielID).openStream()));
//		            bufferedr = new BufferedReader(new FileReader("G:\\Wechseldatentr‰ger\\Informatik\\Aktuell\\Java Eclipse\\Infoprojekt\\speichern.txt"));
		            String line = null;
		            while((line = bufferedr1.readLine()) != null) { 
		            	String[] teile = line.split("/");
		            	Instanzen.setSpieler(Integer.parseInt(teile[0]));
		            	Instanzen.setSpAZ(Integer.parseInt(teile[1]));
		            	Instanzen.setWertungen(Integer.parseInt(teile[2]));
		                String[] spielst‰nde = teile[3].split("-");
		                String[] charakter = spielst‰nde[0].split(";");
		                String[] pl‰ttchen‹brig = spielst‰nde[1].split(";");
		                String[] punkte = spielst‰nde[18].split(";");
		                int[][] skill = new int[Instanzen.getSpieler()][6];
		                int[][] handkarte = new int[Instanzen.getSpieler()][10];
		                String[][] strSkill = new String[Instanzen.getSpieler()][6];
		            	String[][] handkarteStr = new String[Instanzen.getSpieler()][10];
		            	String[][] spielst‰ndeSkill‹bergang = new String[6][Instanzen.getSpieler()];
		            	String[][] spielst‰ndeHandkarten‹bergang = new String[10][Instanzen.getSpieler()];
		            	Integer[] intPunkte = new Integer[Instanzen.getSpieler()];
		                for(int j = 0; j < 6; j++){
		                	spielst‰ndeSkill‹bergang[j] = spielst‰nde[j+2].split(";");
		                }
		                for(int j = 0; j < 10; j++){
		                	spielst‰ndeHandkarten‹bergang[j] = spielst‰nde[j+8].split(";");
		                }
		                for(int i = 0; i < Instanzen.getSpieler(); i++){
			                for(int j = 0; j < 6; j++){
			                	strSkill[i][j] = spielst‰ndeSkill‹bergang[j][i];
			                }
			                for(int j = 0; j < 10; j++){
			                	handkarteStr[i][j] = spielst‰ndeHandkarten‹bergang[j][i];
			                }
		                }
		                for(int i = 0; i < Instanzen.getSpieler(); i++){
		                	for(int j = 0; j < 6; j++){
		                		skill[i][j] = Integer.parseInt(strSkill[i][j]);
		                	}
		                	for(int j = 0; j < 6; j++){
			                	Instanzen.setSkill(skill[i][j], j, Integer.parseInt(charakter[i]));
				            }
		                	for(int j = 0; j < 10; j++){
		                		handkarte[i][j] = Integer.parseInt(handkarteStr[i][j]);
			                }
			                for(int j = 0; j < 10; j++){
			                	Instanzen.setHandkarten(handkarte[i][j], j, i);
				            }
		                	Instanzen.setCharacter(Integer.parseInt(charakter[i]),i);
		                	Instanzen.setPl‰ttchen‹brig(Integer.parseInt(pl‰ttchen‹brig[i]),i);
		                	intPunkte[i] = Integer.parseInt(punkte[i]);
		                }
		                Instanzen.setPunkte(intPunkte);
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
		        
		        BufferedReader bufferedr2 = null;
		        try {
		        	BufferedReader bufferedr3 = new BufferedReader(new InputStreamReader(new URL("http://localhost/web/SQLJavaFelderConnect.php?spielID="+spielID).openStream()));
		        	String line2 = null;
		            while((line2 = bufferedr3.readLine()) != null) { 
		            	String[] teile2 = line2.split("-");
		            	String[][] splittedTeile2 = new String[teile2.length][4];
		            	ArrayList<Tierpl‰ttchen> tierpl‰ttchen = new ArrayList<Tierpl‰ttchen>();
		            	for (int i = 0; i < teile2.length; i++) {
		            		splittedTeile2[i] = teile2[i].split(";");
		        			ypos = ((container.getHeight()/2 - container.getWidth()/22*9/2)+container.getWidth()/22*(Integer.parseInt(splittedTeile2[i][1])-1))-5;
		        			xpos = ((container.getWidth()/2 - container.getWidth()/22*11/2+10)+container.getWidth()/22*(Integer.parseInt(splittedTeile2[i][0])-1))-5;
		        			tierart = Integer.parseInt(splittedTeile2[i][2]);
		        			spieler = Integer.parseInt(splittedTeile2[i][3]);
		        			Tierpl‰ttchen pl‰ttchen = new Tierpl‰ttchen(xpos,ypos,tierart,container,spieler);
							tierpl‰ttchen.add(pl‰ttchen);
		        		}
		            	Instanzen.setTierpl‰ttchen(tierpl‰ttchen, container);
		            }
		        } catch(FileNotFoundException e) {
		            e.printStackTrace();
		        } catch(IOException e) {
		            e.printStackTrace();
		        } finally {
		            if(bufferedr2 != null) {
		                try {
		                    bufferedr2.close();
		                } catch(IOException e) {
		                    e.printStackTrace();
		                }
		            }
		        }
		        container.reinit();
			sbg.enterState(1);
			}
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(0);
		}
	}

	@Override
	public int getID() {
		return 3;
	}

}
