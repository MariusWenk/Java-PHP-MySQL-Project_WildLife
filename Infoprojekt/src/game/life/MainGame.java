package game.life;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import game.gameactions.Kartenaktion;
import game.gameactions.SpielerAmZug;
import game.gameactions.Wertung;
import game.gameobjects.Charakterkarten;
import game.gameobjects.Handkarte;
import game.gameobjects.Spielregel;
import game.gameobjects.Tierplättchen;
import game.fields.Spielplan;
import game.life.Instanzen;
import game.objects.Label;

public class MainGame extends BasicGameState{
	
	public static final int ID = 1;
	Spielregel spielregel;
	Input input;
	Charakterkarten Charaktere;
	Spielplan plan;
	int spieler;
	int k = 1;
	Label tutorial;
	Label tutorial2;
	SpielerAmZug player;
	boolean karteGespielt;
	int ausgespielteKarte;
	Handkarte gespielteKarte;
	boolean z;
	boolean keineAktion = true;
	boolean aktionopen;
	Kartenaktion aktion;
	int karteAusgewählt;
	private ArrayList<Tierplättchen> tierplättchen;
	private ArrayList<Tierplättchen> tierplättchenLöschen = new ArrayList<Tierplättchen>();
	int skill;
	boolean plättchenHinweise = false;
	boolean plättchenAusgewählt = false;
	private boolean gespielt = false;
	private boolean firstInitialized = true;
	private int yWandern;
	private int xWandern;
	private boolean drawAction = true;
	private boolean tutorialText = true;
	Wertung wertung;
	private Integer[] spielerPunkte = new Integer[4];
	private int wertungen = 0;
	private boolean spielBeendet = false;
	private int gewinner;
	private boolean tutorial2Text = true;
	private boolean wertungsBeginn = false;
	private int wertungsZeit = 0;
	private boolean werten = false;

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		input = container.getInput();
		this.spieler = 4;
		wertung = new Wertung(plan);
		spielregel = new Spielregel(container);
		tutorial = new Label(container.getWidth()/2-container.getWidth()/4,container.getHeight()/2-container.getHeight()/5,container.getWidth()/2,container.getHeight()/5*2,true);
		tutorial2 = new Label(container.getWidth()/2-container.getWidth()/4+20,container.getHeight()/2-container.getHeight()/5-20,container.getWidth()/2,container.getHeight()/5*2,true);
		Charaktere = new Charakterkarten(spieler, container);
		plan = new Spielplan(container.getWidth()/2 - container.getWidth()/22*11/2+10,container.getHeight()/2 - container.getWidth()/22*9/2,container);
//		System.out.println(container.getWidth());
//		System.out.println(container.getHeight());
		plan.initPlan1();
		player = new SpielerAmZug(container);
		if(Instanzen.getSpielLaden() == false){
			player.setSpAZ(1);
			tierplättchen = new ArrayList<Tierplättchen>();
			for(int i = 0; i <spieler;i++){
				spielerPunkte[i] = 0;
			}
		}
		else{
			tierplättchen = Instanzen.getTierplättchen();
			wertungen = Instanzen.getWertungen();
			player.setSpAZ(Instanzen.getSpAZ());
			tutorialText = false;
			tutorial2Text = false;
			tutorial.close();
			tutorial2.close();
			spielerPunkte = wertung.initSave();
		}
		gespielteKarte = new Handkarte(50,container.getHeight()/2-container.getWidth()/11*3/4,container.getWidth()/11,container.getWidth()/11*3/2,12);
		aktion = new Kartenaktion(12,container,plan);
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setAntiAlias(true);
		Charaktere.draw(g);
		plan.draw(g);
		for (Tierplättchen plättchen : tierplättchen) {
			plättchen.draw(g);
		}
		spielregel.draw(g);
		tutorial2.draw(g);
		if(tutorial2Text ){
			g.setColor(Color.black);
			g.drawString("Wertungen:", container.getWidth()/2-container.getWidth()/4+30,container.getHeight()/2-container.getHeight()/5);
			g.drawString("Immer wenn ein zusammenh�ngendes Gebiet vollst�ndig mit Pl�ttchen besetzt ist,", container.getWidth()/2-container.getWidth()/4+30,container.getHeight()/2-container.getHeight()/5+15);
			g.drawString("kommt es zu einer Wertung. Dann bekommt jeder pro zusammenh�ngendes Gebiet, ", container.getWidth()/2-container.getWidth()/4+30,container.getHeight()/2-container.getHeight()/5+30);
			g.drawString("indem er die meisten Pl�ttchen hat 3 Punkte. Au�erdem gibt es f�r die zweitmesten", container.getWidth()/2-container.getWidth()/4+30,container.getHeight()/2-container.getHeight()/5+45);
			g.drawString("2 und f�r die drittmeisten 1 Punkt.", container.getWidth()/2-container.getWidth()/4+30,container.getHeight()/2-container.getHeight()/5+60);
			g.drawString("Spielende:", container.getWidth()/2-container.getWidth()/4+30,container.getHeight()/2-container.getHeight()/5+75);
			g.drawString("Nach 5 Wertungen oder wen ein Spieler keine Pl�ttchen mehr hat endet das Spiel", container.getWidth()/2-container.getWidth()/4+30,container.getHeight()/2-container.getHeight()/5+90);
			g.drawString("und der Spieler mit den meisten Punkten gewinnt.", container.getWidth()/2-container.getWidth()/4+30,container.getHeight()/2-container.getHeight()/5+105);
			g.drawString("Das Spiel kann au�erdem zwischendurch abgespeichert werden.", container.getWidth()/2-container.getWidth()/4+30,container.getHeight()/2-container.getHeight()/5+120);
		}
		tutorial.draw(g);
		if(tutorialText){
			g.setColor(Color.black);
			g.drawString("Wilkommen in WildLife, hier ist das Spiel kurz erkl�rt, f�r weitere Informationen", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+10);
			g.drawString("kannst du a dr�cken (dieses Tutorial kannst du mit t erneut ansehen):", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+25);
			g.drawString("Der Spieler an der Reihe (rot markiert) kann mit i sein Handkartenmen� �ffnen und", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+40);
			g.drawString("die Karte, die er spielen m�chte ausw�hlen. Diese wird dann links gr�n markiert", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+55);
			g.drawString("ausgegeben. Um sie zu benutzen muss sie erneut angeclickt werden. Mit o beendest du", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+70);
			g.drawString("deinen Zug und der n�chste ist an der Reihe. Au�erdem wird die benutzte Karte getauscht", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+85);
			g.drawString("f�r den n�chsten Zug. Mit Esc kannst du ins Hauptmen� zur�ckkehren.(Um Fenster zu", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+100);
			g.drawString("schlie�en dr�cke auf das schwarze Kreuz oben rechts)", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+115);
			g.drawString("Die Karten (Teilweise sind diese nach dem ausw�hlen oben in blau noch einmal erkl�rt):", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+130);
			g.drawString("Blitz(/Baum) = Joker, du kannst dir eine beliebige andere Karte aussuchen.", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+145);
			g.drawString("Rad = Weiterentwickeln, eine F�higkeit in einem Gebiet deiner Wahl um eins verbessern ", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+160);
			g.drawString("-> Auf deinem Charakterfeld kannst du deine F�higkeiten f�r jedes Gebiet sehen,", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+175);
			g.drawString("dabei ist keine Aktion (keAk) quasi Level 0, worauf Wandern (Wan) mit Level 1 folgt,", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+190);
			g.drawString("dann Expandieren (Exp) Level 2 und das Beste (Level 3) ist Attackieren (Att).", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+205);
			g.drawString("Die anderen 6 Karten sind Gebietskarten, wobei man je nach Art der Karte in dem", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+220);
			g.drawString("Die Aktionsm�glichkeiten (hierbei handelt es sich um Aktionen, die du mit deinen", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+235);
			g.drawString("Tierpl�ttchen ausf�hren kannst):.", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+250);
			g.drawString("keAk = keine Aktionsm�glichkeiten, also kannst du dieses Gebiet gar nicht betreten", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+265);
			g.drawString("Wan = Du kannst ein bereits gesetztes Pl�ttchen in ein benachbartes Feld der Kartenart", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+280);
			g.drawString("verschieben (also betreten). Dazu w�hlst du zuerst das Pl�ttchen aus und dann das Ziel", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+295);
			g.drawString("Exp = Du kannst in diesem Gebiet nachdem du die Karte rechts ausgew�hlt hast durch", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+310);
			g.drawString("clicken ein neues Pl�ttchen platzieren.", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+325);
			g.drawString("Att = Wie Exp, allerdings kannst du auch andere Spieler �berbauen.", container.getWidth()/2-container.getWidth()/4+10,container.getHeight()/2-container.getHeight()/5+340);
			
		}
		g.setColor(Color.white);
		g.drawString("Wertungen: "+wertungen, (container.getWidth()/2)-60, container.getHeight()-50);
		g.drawString("i = Handkarten", 430, 0);
		g.drawString("a = Spielregeln", 620, 0);
		g.drawString("o = Zug beenden", 810, 0);
		g.drawString("t = Tutorial", 1000, 0);
		g.drawString("Punkte Spieler 1 = "+spielerPunkte[0], 430, container.getHeight()-20);
		g.drawString("Punkte Spieler 2 = "+spielerPunkte[1], 1010, container.getHeight()-20);
		if(Instanzen.getSpieler() > 2){
			g.drawString("Punkte Spieler 3 = "+spielerPunkte[2], 1010, 20);
		}
		if(Instanzen.getSpieler() > 3){
			g.drawString("Punkte Spieler 4 = "+spielerPunkte[3], 430, 20);
		}
		player.draw(g);
		g.setColor(Color.green);
		g.drawString("gespielte Karte:",50,container.getHeight()/2-container.getWidth()/11*3/4-25);
		g.drawString("clicken zum benutzen",30,container.getHeight()/2+container.getWidth()/11*3/4+25);
		g.setLineWidth(5f);
		g.drawRect(50,container.getHeight()/2-container.getWidth()/11*3/4,container.getWidth()/11,container.getWidth()/11*3/2);
		gespielteKarte.draw(g);
		aktion.draw(g);
		if(plättchenHinweise){
			g.setColor(Color.blue);
			if(skill == 0){
				g.drawString("Du hast leider keine Aktionsm�glichkeiten, w�hle eine andere Karte", container.getWidth()/2-300, 70);
			}
			if(skill == 1){
				g.drawString("Du kannst Wandern, w�hle eines deiner Pl�ttchen und bewege es", container.getWidth()/2-300, 70);
				g.drawString("in ein benachbartes Feld der Kartenart", container.getWidth()/2-180, 90);
			}
			if(skill == 2){
				g.drawString("Du kannst Expandieren, setzte ein Pl�ttchen in ein freies Feld der Kartenart", container.getWidth()/2-330, 70);
			}
			if(skill == 3){
				g.drawString("Du kannst Attackieren, setzte ein Pl�ttchen in ein freies oder belegtes Feld", container.getWidth()/2-330, 70);
			}
		}
		wertung.draw(g);
		if(spielBeendet){
			g.drawString("Das Spiel ist beendet, Spieler "+gewinner+" hat gewonnen",container.getWidth()/2-300, container.getHeight()/2-5);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		if(werten){
			spielerPunkte  = wertung.werten(tierplättchen);
			werten = false;
		}
		if(wertungsBeginn){
			wertungsZeit  += delta;
			if(wertungsZeit >= 2000){
				wertung.close();
				wertungsZeit = 0;
				wertungsBeginn = false;
			}
		}
		for(int i = 1; i<=4;i++){
			if(wertungen == 5 || Instanzen.getPlättchenÜbrig(i)==0){
				spielBeendet  = true;
				List<Integer> liste = Arrays.asList(spielerPunkte);
		        List<Integer> sortierteListe = new ArrayList<Integer>();
		        sortierteListe.addAll(liste);
		        sortierteListe.sort((a,b) -> a-b);
		        Integer größtes = sortierteListe.get(sortierteListe.size()-1);
				gewinner = liste.indexOf(größtes)+1;
			}
		}
		this.spieler = Instanzen.getSpieler();
		Charaktere.setSpieler(spieler);
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (player.but(mouseX, mouseY)) {
			    player.close();
			}
			if (spielregel.but(mouseX, mouseY)) {
			    spielregel.close();
			}
			if (tutorial.but(mouseX, mouseY)) {
			    tutorial.close();
			    tutorialText = false;
			}
			if (tutorial2.but(mouseX, mouseY)) {
			    tutorial2.close();
			    tutorial2Text = false;
			}
			if(player.LabelOpen()){
				 if(player.KarteAusgewählt(mouseX,mouseY) <= 10){
					karteAusgewählt = player.KarteAusgewählt(mouseX,mouseY);
					ausgespielteKarte = player.getKartenart(karteAusgewählt);
					karteGespielt = true;
					player.close();
					gespielteKarte.setKartenart(ausgespielteKarte);
					z = true;
				}
			}
			if(aktion.JokerOpen()){
				 if(aktion.JokerKarteAusgewählt(mouseX,mouseY) <= 7){
					ausgespielteKarte = aktion.getJokerKartenart(aktion.JokerKarteAusgewählt(mouseX,mouseY));
					aktionopen = false;
					aktion.close();
					gespielteKarte.setKartenart(ausgespielteKarte);
					z = true;
				}
			}
			if(aktion.UpgradeOpen()){
				 if(aktion.UpgradeKarteAusgewählt(mouseX,mouseY) <= 6){
					int upgradeGebiet = aktion.getUpgradeKartenart(aktion.UpgradeKarteAusgewählt(mouseX,mouseY));
					if(Instanzen.getSkillCharacter(upgradeGebiet, Instanzen.getCharacter(player.getSpAZ())) < 3){
						Instanzen.upgradeSkillCharacter(upgradeGebiet,Instanzen.getCharacter(player.getSpAZ()));
					}
					Charaktere.update(player.getSpAZ());
					aktionopen = false;
					aktion.close();
					z = true;
					keineAktion = false;
					drawAction = false;
				}
			}
			if(karteGespielt){
				if (aktion.press(mouseX, mouseY)){	
					aktionopen = true;	
				}
				if(aktionopen){
					aktion.init(mouseX,mouseY);
					aktion.update();
					if(ausgespielteKarte == 8 || ausgespielteKarte == 7 && drawAction){
						aktion.open();
					}
					if(ausgespielteKarte < 7){
						this.plättchenHinweise = true;
						int gebiet = aktion.getLandschaft();
						this.skill = Instanzen.getSkillCharacter(gebiet,Instanzen.getCharacter(player.getSpAZ()));
						boolean interaktion = aktion.PlättchenInteraktion(skill);
						if(skill == 0){
							karteGespielt = false;
							aktionopen = false;
							keineAktion = true;
							gespielteKarte.setKartenart(12);
							aktion.close();
						}
						if(skill == 1){
							if(interaktion){
								if(firstInitialized){
									this.xWandern = aktion.getXPlättchen();
									this.yWandern = aktion.getYPlättchen();
									aktion.initCoords(xWandern, yWandern);
									firstInitialized = false;
								}
								for (Tierplättchen plättchen : tierplättchen) {
									if(plättchen.getX()==xWandern && plättchen.getY()==yWandern && plättchen.getTier() == Instanzen.getCharacter(player.getSpAZ())){
										plättchenAusgewählt = true;
									}
								}
								if(plättchenAusgewählt){
									if(aktion.FeldBenachbart(xWandern,yWandern)){
										int xNeu = aktion.getXFeldBenachbart();
										int yNeu = aktion.getYFeldBenachbart();
										boolean feldBelegt = false;
										for (Tierplättchen plättchen : tierplättchen) {
											if(plättchen.getX()==xNeu && plättchen.getY()==yNeu){
												feldBelegt = true;
											}
										}
										if(feldBelegt == false){
											boolean move = false;
											for (Tierplättchen plättchen : tierplättchen) {
												if(plättchen.getX()==xWandern && plättchen.getY()==yWandern && plättchen.getTier() == Instanzen.getCharacter(player.getSpAZ())&& aktion.getFeldart(xNeu,yNeu)== ausgespielteKarte){
													move = true;
													tierplättchenLöschen.add(plättchen);
												}
											}
											if(move){
												tierplättchen.removeAll(tierplättchenLöschen);
												Instanzen.setTierplättchen(tierplättchen, container);
												while(tierplättchenLöschen.size() > 0) {
													tierplättchenLöschen.remove(0);
												}
												neuesPlättchen(xNeu,yNeu,player.getSpAZ(),container,false);
												if(gespielt){
													aktion.setGespielt();
													keineAktion = false;
												}
											}
										}
									}
								}
							}
						}
						if(skill == 2 || skill == 3){
							if(interaktion){ 
								int x = aktion.getXPlättchen();
								int y = aktion.getYPlättchen();
								neuesPlättchen(x,y,player.getSpAZ(),container,true);
								if(gespielt){
									aktion.setGespielt();
									keineAktion = false;
								}
							}
						}
					}
					if (aktion.JokerButton(mouseX, mouseY)) {
					    aktion.close();
					}
					if (aktion.UpgradeButton(mouseX, mouseY)) {
					    aktion.close();
					}
				}
			}
		}
		if(karteGespielt){
			aktion.setKartenart(ausgespielteKarte);
			if(aktion.AktionAbgeschlossen()){
				keineAktion = false;
			}
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(0);
		}
		if (input.isKeyPressed(Input.KEY_I) && keineAktion){
			player.open();
			if(skill == 0){
				plättchenHinweise = false;
			}
		}
		if (input.isKeyPressed(Input.KEY_T)){
			tutorial.open();
			tutorialText  = true;
			tutorial2Text = true;
			tutorial2.open();
		}
		
		if (input.isKeyPressed(Input.KEY_A)){
			spielregel.open();
		}
		
		if (input.isKeyPressed(Input.KEY_O)) {
			gespielt = false;
			plättchenHinweise = false;
			plättchenAusgewählt = false;
			firstInitialized = true;
			aktionopen = false;
			keineAktion = true;
			gespielteKarte.setKartenart(12);
			aktion.close();
			karteGespielt = false;
			drawAction = true;
			aktion.NächsterSpieler();
			if(z){
				player.KarteWechseln(karteAusgewählt);
			}
			z = false;
			if(spieler == k){
				player.setSpAZ(1);
				k = 1;
			}
			else{
				player.setSpAZ(player.getSpAZ()+1);
				k++;
			}
		}
	}
	
	private void neuesPlättchen(int x, int y,int spieler, GameContainer container,boolean neu) throws SlickException {
		int tier = Instanzen.getCharacter(spieler);
		boolean nichtBesetzt = true;
		boolean nichtSelbstBesetzt = true;
		for (Tierplättchen plättchen : tierplättchen) {
			if(plättchen.getX()==x && plättchen.getY()==y){
				nichtBesetzt = false;
			}
			if(plättchen.getX()==x && plättchen.getY()==y&&plättchen.getTier() == Instanzen.getCharacter(player.getSpAZ())){
				nichtSelbstBesetzt = false;
			}
		}
		if(nichtBesetzt || nichtSelbstBesetzt && skill == 3){
			gespielt = true;
			Tierplättchen plättchen = new Tierplättchen(x,y,tier,container,spieler);
			if(neu){
				Instanzen.setPlättchenBenutzt(player.getSpAZ());
			}
			if(skill == 3){
				for (Tierplättchen plättche : tierplättchen) {
					if(plättche.getX()==x && plättche.getY()==y){
						tierplättchenLöschen.add(plättche);
					}
				}
			}
			tierplättchen.removeAll(tierplättchenLöschen);
			while(tierplättchenLöschen.size() > 0) {
				tierplättchenLöschen.remove(0);
			}
			tierplättchen.add(plättchen);
			if(wertung.wertungPrüfen(plan,plättchen,tierplättchen, container)){
				werten  = true;
				wertungsBeginn  = true;
				wertungen ++;
			}
			Instanzen.setTierplättchen(tierplättchen, container);
		}
	}
	
	@Override
	public int getID() {
		return ID;
	}

}
