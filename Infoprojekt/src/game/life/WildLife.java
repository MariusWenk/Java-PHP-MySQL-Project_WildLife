package game.life;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class WildLife extends StateBasedGame{
	
    public static final int fps = 60;
	private static String[] icons = new String[2];

	public WildLife() {
		super("WildLife");
		
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(new MainMenu());
		this.addState(new SpielerMenü());
		this.addState(new MainGame());
		this.addState(new SpielLaden());
		this.addState(new Spielstände());
		enterState(0);
	}
	
	public static void main(String[] args) {
		AppGameContainer apgc;
		try {
			apgc = new AppGameContainer(new WildLife());
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			icons[0] = "res/Icon 64.PNG";
			icons[1] = "res/Icon 32.PNG";
			apgc.setIcons(icons );
			apgc.setDisplayMode( (int) screenSize.getWidth(),  (int) screenSize.getHeight(), true);
			apgc.setTargetFrameRate(fps);
			apgc.setShowFPS(true);
			apgc.setMultiSample(16);
			apgc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

}

