import java.util.ArrayList;

public class PokerCalcMain {

	//1 - 10 und 11 = Bube, 12 = Dame, 13 = König, 1 = Ass
	
	public static void main(String[] args) {		
		PokerCard hand1 = new PokerCard(PokerCard.cardColor.Pig, 11);			//Fehler: Ass ist nur 1;
		PokerCard hand2 = new PokerCard(PokerCard.cardColor.Pig, 10);		
		PokerCard flop = new PokerCard(PokerCard.cardColor.Pig, 13);
		PokerCard flop2 = new PokerCard(PokerCard.cardColor.Kreuz, 11);
		PokerCard flop3 = new PokerCard(PokerCard.cardColor.Herz, 12);
		PokerCard turn = new PokerCard(PokerCard.cardColor.Pig, 12);
		PokerCard river = new PokerCard(PokerCard.cardColor.Pig, 14);
		PokerCard river2 = new PokerCard(PokerCard.cardColor.Caro, 12);
		
		ArrayList<PokerCard> cardArray = new ArrayList<PokerCard>();
		cardArray.add(hand1);
		cardArray.add(hand2);
		cardArray.add(flop);
		cardArray.add(flop2);
		cardArray.add(flop3);
		cardArray.add(turn);
		cardArray.add(river);
		cardArray.add(river2);
		
		
		
		PokerHand ph = new PokerHand(cardArray);
			
	}
	
}
