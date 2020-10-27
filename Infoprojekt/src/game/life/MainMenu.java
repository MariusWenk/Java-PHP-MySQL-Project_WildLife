package game.life;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import game.objects.Button;

import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainMenu extends BasicGameState{
	
	public static final int ID = 0;
	private Image titel;
	Button button1;
	Button button2;
	Button button3;
	Input input;
	GameContainer container;
	int spieler;
	

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		titel = new Image("res/Logo.PNG");
		button1 = new Button(container.getWidth()/2-50,350,100,60,"Start",23);
		button2 = new Button(container.getWidth()/2-50,430,100,60,"Speichern",40);
		button3 = new Button(container.getWidth()/2-50,510,100,60,"Beenden",30);
		this.container = container;
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setAntiAlias(true);
		titel.draw(container.getWidth()/2-titel.getWidth()/2,100);
		button1.draw(g);
		button2.draw(g);
		button3.draw(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		this.spieler = Instanzen.getSpieler();
		this.input = container.getInput();
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if(button1.press(mouseX,mouseY)){
				sbg.enterState(2);
			}
			if(button2.press(mouseX,mouseY)){
//				try{
//					File file = new File("G:\\Wechseldatenträger\\Informatik\\Aktuell\\Java Eclipse\\Infoprojekt\\speichern.txt");
//					FileWriter filew = new FileWriter(file);
					StringBuffer sbf = new StringBuffer();
		            String strSeparator = ";";
		            String[][] ij = Instanzen.getSpeicherString();
		            if(ij.length > 0){  	
		            	for(int p=0; p < ij.length; p++){
		            		if(p>0){
		            			sbf.append("-");
		            		}
		            		for(int g=0; g < ij[p].length; g++){
		            			if(g>0){
		            				sbf.append(strSeparator);
		            			}
		            			sbf.append(ij[p][g]);
		            		}
	                    }
		            }
		            String o = sbf.toString();
		            
		            StringBuffer sbf2 = new StringBuffer();
		            String strSeparator2 = ";";
		            String[][] ij2 = Instanzen.getSpeicherStringFelder();
		            if(ij2.length > 0){  	
		            	for(int p=0; p < ij2.length; p++){
		            		if(p>0){
		            			sbf2.append("-");
		            		}
		            		for(int g=0; g < ij2[p].length; g++){
		            			if(g>0){
		            				sbf2.append(strSeparator2);
		            			}
		            			sbf2.append(ij2[p][g]);
		            		}
	                    }
		            }
		            String f = sbf2.toString();
		            try {
		    			BufferedReader k = new BufferedReader(new InputStreamReader(new URL("http://localhost/web/JavaSQLConnect.php?spieler="+Instanzen.getSpieler()+"&spielerAmZug="+Instanzen.getSpAZ()+"&data="+o+"&wertungen="+Instanzen.getWertungen()).openStream()));
		    			BufferedReader e = new BufferedReader(new InputStreamReader(new URL("http://localhost/web/JavaSQLFelderConnect.php?data="+f+"&dataLength="+ij2.length ).openStream()));
		    			System.out.println(k);
		    			System.out.println(e);
//		    			return k.readLine();
		    		} catch (MalformedURLException e) {
		    			e.printStackTrace();
		    		} catch (IOException e) {
		    			e.printStackTrace();
		    		}
//					filew.write(Instanzen.getSpieler()+"/"+Instanzen.getSpAZ()+"/"+o);
//					filew.flush();
//					filew.close();
//				}
//				catch( IOException e ){
//					e.printStackTrace();
//				}
			}
			if(button3.press(mouseX,mouseY)){
				container.exit();
			}
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
		
	}
	
	@Override
	public int getID() {
	   return ID;
	}

}
