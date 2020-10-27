package game.life;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;

import game.gameobjects.Tierpl‰ttchen;

public class Instanzen {

	public static int s = 4;
	public static int[] charakter = new int[6];
	public static int[] pl‰ttchen‹brig = {30,30,30,30};
	public static int spielerAmZug = 1;
	static int[][] skill = new int[6][6];
	public static String[][] speicherString;
	public static String[][] Feld;
	private static int[][] handkarten = new int[4][10];
	public static int wertungen = 0;
	public static Integer[] spielerpunkte;
	public static boolean spielLaden = false;
	private static ArrayList<Tierpl‰ttchen> tierpl‰ttchen = new ArrayList<Tierpl‰ttchen>();
	static GameContainer container;
	private static int spielID;
	
	public static void setSpieler(int spieler){
		s = spieler;
		spielerpunkte = new Integer[s];
		speicherString = new String[19][s];
	}
	
	public static int getSpieler(){
		return s;
	}

	public static void setCharacter(int i,int character) {
		charakter[character] = i;
	}
	
	public static int getCharacter(int i) {
		return charakter[i-1];
	}

	public static void setPl‰ttchenBenutzt(int spieler) {
		pl‰ttchen‹brig[spieler-1] = pl‰ttchen‹brig[spieler-1] - 1;
	}
	
	public static void setPl‰ttchen‹brig(int pl‰ttchen, int i) {
		pl‰ttchen‹brig[i] = pl‰ttchen;
	}
	
	public static int getPl‰ttchen‹brig(int spieler) {
		return pl‰ttchen‹brig[spieler-1];
	}

	public static void setSpAZ(int spieler) {
		spielerAmZug = spieler;
	}
	
	public static int getSpAZ() {
		return spielerAmZug;
	}
	
	public static void setSkillCharacter(int[] gebietsWerte,int character) {
		for(int i = 0; i < 6;i++){
			skill[character][i] = gebietsWerte[i];
		}
	}
	
	public static void setSkillGebiet(int[] characterWerte,int gebiet) {
		for(int i = 0; i < s;i++){
			skill[i][gebiet] = characterWerte[i];
		}
	}
	
	public static void setSkill(int Skill, int gebiet, int character) {
		skill[character][gebiet] = Skill;
	}
	
	public static int getSkillCharacter(int gebiet,int character) {
		return skill[character][gebiet-1];
	}
	
	public static void upgradeSkillCharacter(int gebiet,int character) {
		skill[character][gebiet - 1] +=1;
	}

	public static void setHandkarten(int handkarte, int i, int spieler) {
		handkarten[spieler][i] = handkarte;
	}
	
	public static int[] getHandkarten(int spieler) {
		return handkarten[spieler];
	}
	
	public static void setPunkte(Integer[] spielerPunkte) {
		spielerpunkte = spielerPunkte;
	}
	
	public static Integer getPunkte(int spieler) {
		return spielerpunkte[spieler];
	}
	
	public static Integer[] getHandkarten() {
		return spielerpunkte;
	}
	
	public static void setWertungenGemacht() {
		wertungen += 1;
	}
	
	public static void setWertungen(int wert) {
		wertungen = wert;
	}
	
	public static int getWertungen() {
		return wertungen;
	}
	
	public static void setSpielLaden() {
		spielLaden = true;
	}
	
	public static boolean getSpielLaden() {
		return spielLaden;
	}
	
	public static void setSpielID(int SpielID) {
		spielID = SpielID;
	}
	
	public static int getSpielID() {
		return spielID;
	}
	
	public static void setTierpl‰ttchen(ArrayList<Tierpl‰ttchen> tierPl‰ttchen, GameContainer Container) {
		tierpl‰ttchen = tierPl‰ttchen;
		container = Container;
	}
	
	public static ArrayList<Tierpl‰ttchen> getTierpl‰ttchen() {
		return tierpl‰ttchen;
	}

	public static String[][] getSpeicherStringFelder() {
		Feld = new String[tierpl‰ttchen.size()][s];
		int i = 0;
		for (Tierpl‰ttchen pl‰ttchen : tierpl‰ttchen) {
			int x = pl‰ttchen.getX();
			int y = pl‰ttchen.getY();
			int xFeld = (x -container.getWidth()/2 + container.getWidth()/22*11/2+10)/(container.getWidth()/22)+1;
			int yFeld = (y -container.getHeight()/2 + container.getWidth()/22*9/2+10)/(container.getWidth()/22)+1;
			Feld[i][0] = Integer.toString(xFeld);
			Feld[i][1] = Integer.toString(yFeld);
			Feld[i][2] = Integer.toString(pl‰ttchen.getTier());
			i++;
		}
		return Feld;
	}
	
	public static String[][] getSpeicherString() {
		for(int i = 0; i<s;i++){
			if(spielerpunkte[i] == null) {
				spielerpunkte[i] = 0;
			}
			speicherString[0][i] = Integer.toString(charakter[i]);
			speicherString[1][i] = Integer.toString(pl‰ttchen‹brig[i]);
			speicherString[2][i] = Integer.toString(skill[charakter[i]][0]);
			speicherString[3][i] = Integer.toString(skill[charakter[i]][1]);
			speicherString[4][i] = Integer.toString(skill[charakter[i]][2]);
			speicherString[5][i] = Integer.toString(skill[charakter[i]][3]);
			speicherString[6][i] = Integer.toString(skill[charakter[i]][4]);
			speicherString[7][i] = Integer.toString(skill[charakter[i]][5]);
			speicherString[8][i] = Integer.toString(handkarten[i][0]);
			speicherString[9][i] = Integer.toString(handkarten[i][1]);
			speicherString[10][i] = Integer.toString(handkarten[i][2]);
			speicherString[11][i] = Integer.toString(handkarten[i][3]);
			speicherString[12][i] = Integer.toString(handkarten[i][4]);
			speicherString[13][i] = Integer.toString(handkarten[i][5]);
			speicherString[14][i] = Integer.toString(handkarten[i][6]);
			speicherString[15][i] = Integer.toString(handkarten[i][7]);
			speicherString[16][i] = Integer.toString(handkarten[i][8]);
			speicherString[17][i] = Integer.toString(handkarten[i][9]);
			speicherString[18][i] = Integer.toString(spielerpunkte[i]);
		}
		return speicherString;
	}
}
