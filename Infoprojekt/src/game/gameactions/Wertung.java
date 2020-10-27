package game.gameactions;

import java.util.List;
import org.newdawn.slick.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

import game.fields.Spielplan;
import game.gameobjects.Tierpl�ttchen;
import game.life.Instanzen;

public class Wertung {
	
	Spielplan plan;
	ArrayList<List<Integer>> wertungsbereiche = new ArrayList<List<Integer>>(12);
	private int xPr�fen;
	private int yPr�fen;
	GameContainer container;
	int xFeld;
	int yFeld;
	int posPr�fen;
	private List<Integer> bereich;
	private boolean openclose = false;
	private Integer[] spielerPunkte = new Integer[4];
	private List<List<Integer>> gewertet= new ArrayList<List<Integer>>();
	private boolean bereitsGewertet = false;
	
	public Wertung(Spielplan plan){
		this.plan = plan;
		if(Instanzen.getSpielLaden() == false){
			for(int i = 0; i <4; i++){
	       	 spielerPunkte[i] = 0;
			}
		}
		else{
			for(int i = 0; i < Instanzen.getSpieler(); i++){
		       	 spielerPunkte[i] = Instanzen.getPunkte(i);
			}
		}
		init();
	}
	
	public Integer[] initSave(){
		return spielerPunkte;
	}
	
	public void init(){
		List<Integer> bereich1 = Arrays.asList(31,41,22,32,13,23,14,24);
		List<Integer> bereich2 = Arrays.asList(42,52,53,63,73);
		List<Integer> bereich3 = Arrays.asList(33,43,44,54,64,74,65,66);
		List<Integer> bereich4 = Arrays.asList(72,82,81,91);
		List<Integer> bereich5 = Arrays.asList(83,93,103,102,92,84,85,86,75);
		List<Integer> bereich6 = Arrays.asList(94,104,114,113);
		List<Integer> bereich7 = Arrays.asList(95,96,97,98,99,89,88,105);
		List<Integer> bereich8 = Arrays.asList(106,107,108,117,116);
		List<Integer> bereich9 = Arrays.asList(16,17,26,25,35,34,45,55,56);
		List<Integer> bereich10 = Arrays.asList(27,28,37,36);
		List<Integer> bereich11 = Arrays.asList(46,47,48,449,39,38,59,58,68);
		List<Integer> bereich12 = Arrays.asList(57,67,77,87,76);
		wertungsbereiche.add(bereich1);
		wertungsbereiche.add(bereich2);
		wertungsbereiche.add(bereich3);
		wertungsbereiche.add(bereich4);
		wertungsbereiche.add(bereich5);
		wertungsbereiche.add(bereich6);
		wertungsbereiche.add(bereich7);
		wertungsbereiche.add(bereich8);
		wertungsbereiche.add(bereich9);
		wertungsbereiche.add(bereich10);
		wertungsbereiche.add(bereich11);
		wertungsbereiche.add(bereich12);
	}

	public boolean wertungPr�fen(Spielplan plan, Tierpl�ttchen pl�ttchen, ArrayList<Tierpl�ttchen> tierpl�ttchen,GameContainer container) {
		this.plan = plan;
		this.container = container;
		xPr�fen = pl�ttchen.getX();
		yPr�fen = pl�ttchen.getY();
		this.xFeld = (xPr�fen -container.getWidth()/2 + container.getWidth()/22*11/2+10)/(container.getWidth()/22)+1;
		this.yFeld = (yPr�fen -container.getHeight()/2 + container.getWidth()/22*9/2+10)/(container.getWidth()/22)+1;
		this.posPr�fen = xFeld * 10 + yFeld;
			for(List<Integer> wertung : wertungsbereiche){
				for(int wert : wertung){
					if(wert == posPr�fen){
						bereich = wertung;
					}
				}
			}
			int �bereinstimmung = 0;
			for (Tierpl�ttchen pl�ttch : tierpl�ttchen) {
				for(int be : bereich){
					int xBe = (be-(be%10))/10;
					int yBe = be%10;
					int pl�ttchX = (pl�ttch.getX() -container.getWidth()/2 + container.getWidth()/22*11/2+10)/(container.getWidth()/22)+1;
					int pl�ttchY = (pl�ttch.getY() -container.getHeight()/2 + container.getWidth()/22*9/2+10)/(container.getWidth()/22)+1;
					if(pl�ttchX==xBe && pl�ttchY==yBe){
						�bereinstimmung++;
					}
				}
			}
			for(List<Integer> wertung : gewertet){
				if(wertung == bereich){
					bereitsGewertet  = true;
				}
			}
			if (�bereinstimmung == bereich.size() && !bereitsGewertet){
				gewertet.add(bereich);
				return true;
			}
			bereitsGewertet = false;
		return false;
	}
	
	public void draw(Graphics g){
		if(openclose ){
			g.setColor(Color.green);
			g.drawString("Wertung", container.getWidth()/2, container.getHeight()/2);
		}
	}
	
	public void close(){
		openclose = false;
	}

	public Integer[] werten(ArrayList<Tierpl�ttchen> tierpl�ttchen) {
			openclose = true;
			Instanzen.setWertungenGemacht();
			for(List<Integer> wertung : wertungsbereiche){
				Integer[] pl�ttchenImGebiet = new Integer[4];
				for(int i = 0; i<4;i++){
					pl�ttchenImGebiet[i] = 0;
				}
				for(int ber: wertung){
					int xBe = (ber-(ber%10))/10;
					int yBe = ber%10;
					for (Tierpl�ttchen pl�ttch : tierpl�ttchen) {
						int pl�ttchX = (pl�ttch.getX() -container.getWidth()/2 + container.getWidth()/22*11/2+10)/(container.getWidth()/22)+1;
						int pl�ttchY = (pl�ttch.getY() -container.getHeight()/2 + container.getWidth()/22*9/2+10)/(container.getWidth()/22)+1;
						if(pl�ttchX==xBe && pl�ttchY==yBe){
							pl�ttchenImGebiet [pl�ttch.getSpieler()-1]++;
						}
					}
				}
				List<Integer> liste = Arrays.asList(pl�ttchenImGebiet);
				List<Integer> sortierteListe = new ArrayList<Integer>();
		        sortierteListe.addAll(liste);
		        sortierteListe.sort((a,b) -> a-b);
		        Integer gr��tes = sortierteListe.get(sortierteListe.size()-1);
		        Integer zweitGr��tes = sortierteListe.get(sortierteListe.size()-2);
		        Integer drittGr��tes = sortierteListe.get(sortierteListe.size()-3);
		        Integer viertGr��tes = sortierteListe.get(sortierteListe.size()-4);
		        if(gr��tes != 0 && gr��tes != zweitGr��tes && gr��tes != drittGr��tes){
		        	spielerPunkte[liste.indexOf(gr��tes)] += 3;
		        }
		        if(zweitGr��tes != 0 && zweitGr��tes != gr��tes && zweitGr��tes != drittGr��tes){
		        	spielerPunkte[liste.indexOf(zweitGr��tes)] += 2;
		        }
		        if(drittGr��tes != 0 && drittGr��tes != gr��tes && drittGr��tes != zweitGr��tes && drittGr��tes != viertGr��tes){
		        	spielerPunkte[liste.indexOf(drittGr��tes)] += 1;
		        }
		        if(gr��tes != 0 && gr��tes == zweitGr��tes && gr��tes != drittGr��tes){
		        	spielerPunkte[liste.indexOf(gr��tes)] += 2;
		        	liste.set(liste.indexOf(gr��tes), 0);
		        	spielerPunkte[liste.indexOf(zweitGr��tes)] += 2;
		        }
		        if(zweitGr��tes != 0 && drittGr��tes == zweitGr��tes && gr��tes != drittGr��tes && drittGr��tes != viertGr��tes){
		        	spielerPunkte[liste.indexOf(drittGr��tes)] += 1;
		        	liste.set(liste.indexOf(drittGr��tes), 0);
		        	spielerPunkte[liste.indexOf(zweitGr��tes)] += 1;
		        }
		        if(gr��tes != 0 && gr��tes == zweitGr��tes && gr��tes == drittGr��tes && gr��tes != viertGr��tes){
		        	spielerPunkte[liste.indexOf(gr��tes)] += 1;
		        	liste.set(liste.indexOf(gr��tes), 0);
		        	spielerPunkte[liste.indexOf(zweitGr��tes)] += 1;
		        	liste.set(liste.indexOf(gr��tes), 0);
		        	spielerPunkte[liste.indexOf(drittGr��tes)] += 1;
		        }
			}
			Instanzen.setPunkte(spielerPunkte);
		return spielerPunkte ;
	}
}
