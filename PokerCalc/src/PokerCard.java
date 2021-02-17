
/**
 * Repräsentativ für eine Poker Karte.
 * 
 * @author Elisha O. Nneji
 * 
 */

public class PokerCard {

	public cardColor cardCol;
	public int cardNum;

	public PokerCard(cardColor cardCol, int cardNum) {
		this.cardCol = cardCol;
		this.cardNum = cardNum;
	}
	
	//enum Farben HERZ KREUZ PIG CARO
	public enum cardColor {
		Herz, Pig, Kreuz, Caro;
	}

	//getter-Funktionen
	public int getNumber(){
		return cardNum;
	}
	
	public cardColor getColor(){
		return cardCol;
	}
	
	public void printInfo(){
		System.out.println(cardCol + ", " + cardNum);
		
	}

	public void setNumber(int cardNum){
		this.cardNum = cardNum;
	}
	
}
