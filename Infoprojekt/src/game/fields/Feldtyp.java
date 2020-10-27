package game.fields;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Feldtyp {
	
	int type;
	Image image;
	
	public Feldtyp(int type){
		this.type = type;
	}
	
	public Image getImage() throws SlickException{
		if (type == 1){
			image = new Image("res/Gebirge.PNG");
		}
		if (type == 2){
			image = new Image("res/Wald.PNG");
		}
		if (type == 3){
			image = new Image("res/Savanne.PNG");
		}
		if (type == 4){
			image = new Image("res/Wiese.PNG");
		}
		if (type == 5){
			image = new Image("res/Wasser.PNG");
		}
		if (type == 6){
			image = new Image("res/Wüste.PNG");
		}
		if (type == 7){
			image = new Image("res/Lücke.PNG");
		}
		return image;
	}

}
