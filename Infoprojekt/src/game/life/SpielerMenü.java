package game.life;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import game.objects.Button;

public class SpielerMenü extends BasicGameState{

	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	htmlEinbinden html;
	int spieler = 4;

	
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		button1 = new Button(container.getWidth()/2-50,350,100,60,"Spiel laden",50);
		button2 = new Button(container.getWidth()/2-50,430,100,60,"2 Spieler",41);
		button3 = new Button(container.getWidth()/2-50,510,100,60,"3 Spieler",41);
		button4 = new Button(container.getWidth()/2-50,590,100,60,"4 Spieler",41);
		button5 = new Button(container.getWidth()/2-50,670,100,60,"Start",23);
		html = new htmlEinbinden();
		spieler = Instanzen.getSpieler();
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		button1.draw(g);
		button2.draw(g);
		button3.draw(g);
		button4.draw(g);
		button5.draw(g);
		g.setColor(Color.white);
		g.drawString("Spieleranzahl auf "+spieler+" Spieler gesetzt", container.getWidth()/2-150, 50);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(0);
		}
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if(button1.press(mouseX,mouseY)){
				html.setURL("http://localhost/web/SQLAusgabe.php");
				sbg.enterState(3);
			}
			if(button2.press(mouseX,mouseY)){
				this.spieler = 2;
			}
			if(button3.press(mouseX,mouseY)){
				this.spieler = 3;
			}
			if(button4.press(mouseX,mouseY)){
				this.spieler = 4;
			}
			if(button5.press(mouseX,mouseY)){
				sbg.enterState(1);
			}
			Instanzen.setSpieler(spieler);
		}
	}
	
	@Override
	public int getID() {
		return 2;
	}

}
