package game.gameactions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import game.fields.Spielplan;
import game.life.Instanzen;

public class Kartenaktion {
	
	int kartenart;
	Joker joker;
	UpgradeSkill upgradeSkill;
	GameContainer container;
	boolean openclose;
	boolean[] upgrade = new boolean[4];
	Spielplan plan;
	PlättchenSpielen plättchenSpielen;
	int spieler;
	int mouseX;
	int mouseY;
	boolean plättchenGesetzt = false;
	boolean bereitsGespielt = false;
	private int xFeldBenachbart;
	private int yFeldBenachbart;
	private int xFeld;
	private int yFeld;
	
	public Kartenaktion(int kartenart, GameContainer container,Spielplan plan) throws SlickException{
		this.kartenart = kartenart;
		this.container = container;
		this.plan = plan;
		joker = new Joker(container);
	}
	
	public void init(int mouseX, int mouseY) throws SlickException{
		this.spieler = Instanzen.getSpAZ();
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		joker = new Joker(container);
		upgradeSkill = new UpgradeSkill(spieler,container);
		if(kartenart < 7) {
			plättchenSpielen = new PlättchenSpielen(spieler,kartenart,plan,container,mouseX,mouseY,bereitsGespielt);
		}
	}
	
	public boolean press(int mouseX, int mouseY){
		if (mouseX >= 50 && mouseX <= 50 + container.getWidth()/11 && mouseY >= container.getHeight()/2-container.getWidth()/11*3/4 && mouseY <= container.getHeight()/2-container.getWidth()/11*3/4 + container.getWidth()/11*3/2) {
			return true;
		}
		return false;
}
	
	public boolean AktionAbgeschlossen(){
		if(plättchenGesetzt){
			return true;
		}
		return false;
	}
	
	public void NächsterSpieler(){
		this.bereitsGespielt = false;
	}
	
	public void update() throws SlickException{
		if(kartenart < 7) {
			plättchenSpielen.update();
		}
	}
	
	public void close(){
		this.openclose = false;
	}
	
	public void open(){
		this.openclose = true;
	}
	
	public boolean UpgradeButton(int mouseX, int mouseY){
		return upgradeSkill.but(mouseX, mouseY);
	}
	
	public boolean JokerButton(int mouseX, int mouseY){
		return joker.but(mouseX, mouseY);
	}
	
	public int JokerKarteAusgewählt(int mouseX, int mouseY){
		return joker.KarteAusgewählt(mouseX, mouseY);
	}
	
	public int UpgradeKarteAusgewählt(int mouseX, int mouseY){
		return upgradeSkill.KarteAusgewählt(mouseX, mouseY);
	}

	public void setKartenart(int ausgespielteKarte) throws SlickException {
		this.kartenart = ausgespielteKarte;
		init(mouseX,mouseY);
	}
	
	public int getLandschaft(){
		return kartenart;
	}
	
	public void draw(Graphics g){
		if(openclose){
			if(kartenart == 8){
				joker.draw(g);
			}
			if(kartenart == 7){
				upgradeSkill.draw(g);
			}
		}
	}
	
	public int getXPlättchen(){
		return plättchenSpielen.getXPlättchen();
	}
	
	public int getYPlättchen(){
		return plättchenSpielen.getYPlättchen();
	}
	
	public boolean PlättchenInteraktion(int skill){
		if(kartenart < 7) {
			if(plättchenSpielen.FeldAusgewählt()){
				if(skill == 2 || skill == 3){
					return true;
				}
			}
			if(plättchenSpielen.FeldWandernAusgewählt()){
				if(skill == 1){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean JokerOpen(){
		return openclose && kartenart == 8;
	}
	
	public boolean UpgradeOpen(){
		return openclose && kartenart == 7;
	}
	
	public int getJokerKartenart(int karte){
		return joker.getKartenart(karte);
	}
	
	public int getUpgradeKartenart(int karte){
		return upgradeSkill.getKartenart(karte);
	}
	
	public void initCoords(int x, int y){
		this.xFeld = (x -container.getWidth()/2 + container.getWidth()/22*11/2+10)/(container.getWidth()/22);
		this.yFeld = (y -container.getHeight()/2 + container.getWidth()/22*9/2+10)/(container.getWidth()/22);
	}

	public boolean FeldBenachbart(int x, int y) {
		this.xFeldBenachbart = (plättchenSpielen.getXPlättchen()-container.getWidth()/2 + container.getWidth()/22*11/2+10)/(container.getWidth()/22);
		this.yFeldBenachbart = (plättchenSpielen.getYPlättchen()-container.getHeight()/2 + container.getWidth()/22*9/2+10)/(container.getWidth()/22);
		if(xFeld == xFeldBenachbart+1 && yFeld == yFeldBenachbart || xFeld == xFeldBenachbart-1 && yFeld == yFeldBenachbart || xFeld == xFeldBenachbart && yFeld == yFeldBenachbart+1 || xFeld == xFeldBenachbart && yFeld == yFeldBenachbart-1){
			return true;
		}
		return false;
	}

	public int getXFeldBenachbart() {
		return plättchenSpielen.getXPlättchen();
	}
	
	public int getYFeldBenachbart() {
		return plättchenSpielen.getYPlättchen();
	}

	public void setGespielt() {
		plättchenGesetzt = true;
		bereitsGespielt = true;
	}

	public int getFeldart(int xNeu, int yNeu) {
		return plättchenSpielen.getFeldart(xNeu,yNeu);
	}

}
