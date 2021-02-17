import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Repräsentiert alle Pokerkarten als Array
 * 
 * 
 * @author Elisha O. Nneji
 *
 */

public class PokerHand {

	ArrayList<PokerCard> hand = new ArrayList<PokerCard>();

	public PokerHand(ArrayList<PokerCard> cards) {
		
		
		if ((hand = royalFlushCheck(cards)) != null) {
			System.out.println("Royal Flush:");
			printAll(hand);
		} else if ((hand = straightFlushCheck(cards)) != null) {
			System.out.println("Straight Flush:");
			printAll(hand);
		} else if ((hand = fourOfaKindCheck(cards)) != null) {
			System.out.println("Four of a kind:");
			printAll(hand);
		} else if ((hand = fullHouseCheck(cards)) != null) {
			System.out.println("Full House:");
			printAll(hand);
		} else if ((hand = flushCheck(cards)) != null) {
			System.out.println("Flush:");
			printAll(hand);
		} else if ((hand = straightCheck(cards)) != null) {
			System.out.println("Straight:");
			printAll(hand);
		} else if ((hand = tripsCheck(cards)) != null) {
			System.out.println("Three of a kind:");
			printAll(hand);
		} else if ((hand = twoPairCheck(cards)) != null) {
			System.out.println("TwoPair:");
			printAll(hand);
		} else if ((hand = pairCheck(cards)) != null) {
			System.out.println("Pair:");
			printAll(hand);
		} else {
			System.out.println("Highcard:");
			hand = highCardCheck(cards);
			printAll(hand);	
		}
	}
	
	private ArrayList<PokerCard> royalFlushCheck(ArrayList<PokerCard> cards){
		
		ArrayList<PokerCard> royalFlush = new ArrayList<PokerCard>();
		
		for(int i = 0; i < 4; i++){
			PokerCard.cardColor cC = null;
			
			if(i == 0){
				cC = PokerCard.cardColor.Herz;
			}else if(i == 1){
				cC = PokerCard.cardColor.Caro;
			}else if(i == 2){
				cC = PokerCard.cardColor.Kreuz;
			}else if(i == 3){
				cC = PokerCard.cardColor.Pig;
			}
			
			for(int j = 0; j < 5; j++){
				int value = j + 10;
				
				for(PokerCard card : cards){
					if(card.getColor().equals(cC) && card.getNumber() == value){
						royalFlush.add(card);
					}
				}
				
			}
			
			if(royalFlush.size() == 5){
				return royalFlush;
			}else{
				royalFlush.clear();
			}
			
		}
		
		
		return null;
	}
	
	private ArrayList<PokerCard> straightFlushCheck(ArrayList<PokerCard> cards) {	//nicht Optimal: Algorithmus gibt den ersten straigh Flush aus den er findet
		ArrayList<PokerCard> flush = new ArrayList<PokerCard>();
		ArrayList<PokerCard> finalStraightFlush = new ArrayList<PokerCard>();
		
		if(countHearts(cards) >= 5){								//wenn es 5 oder mehr Herz karten gibt
			for(PokerCard pc : cards){			//geht alle karten durch
				if(pc.getColor().equals(PokerCard.cardColor.Herz)){	
					//Wenn die Karte eine Herz Karte ist wir die der Liste hinzugefügt
					flush.add(pc);
				}
			}
			
			if((finalStraightFlush = straightCheck(flush)) != null){	//Wenn in der sortierten Flush-Liste auch ein Straight ist...
				return finalStraightFlush;								//...wird dieser zurückgegeben
			}else{
				flush = null;			//ansonsten wird der flush gelöcht für die nächste überprüfung
			}
		}
		
		if(countCaros(cards) >= 5){								//wenn es 5 oder mehr Herz karten gibt
			for(PokerCard pc : cards){			//geht alle karten durch
				if(pc.getColor().equals(PokerCard.cardColor.Caro)){	
					//Wenn die Karte eine Herz Karte ist wir die der Liste hinzugefügt
					flush.add(pc);
				}
			}
			
			if((finalStraightFlush = straightCheck(flush)) != null){	//Wenn in der sortierten Flush-Liste auch ein Straight ist...
				return finalStraightFlush;								//...wird dieser zurückgegeben
			}else{
				flush = null;			//ansonsten wird der flush gelöcht für die nächste überprüfung
			}
		}
		
		if(countKreuz(cards) >= 5){								//wenn es 5 oder mehr Herz karten gibt
			for(PokerCard pc : cards){			//geht alle karten durch
				if(pc.getColor().equals(PokerCard.cardColor.Kreuz)){	
					//Wenn die Karte eine Herz Karte ist wir die der Liste hinzugefügt
					flush.add(pc);
				}
			}
			
			if((finalStraightFlush = straightCheck(flush)) != null){	//Wenn in der sortierten Flush-Liste auch ein Straight ist...
				return finalStraightFlush;								//...wird dieser zurückgegeben
			}else{
				flush = null;			//ansonsten wird der flush gelöcht für die nächste überprüfung
			}
		}
		
		if(countPigs(cards) >= 5){								//wenn es 5 oder mehr Herz karten gibt
			for(PokerCard pc : cards){			//geht alle karten durch
				if(pc.getColor().equals(PokerCard.cardColor.Pig)){	
					//Wenn die Karte eine Herz Karte ist wir die der Liste hinzugefügt
					flush.add(pc);
				}
			}
			
			if((finalStraightFlush = straightCheck(flush)) != null){	//Wenn in der sortierten Flush-Liste auch ein Straight ist...
				return finalStraightFlush;								//...wird dieser zurückgegeben
			}else{
				flush = null;			//ansonsten wird der flush gelöcht für die nächste überprüfung
			}
		}
		
		
		return null;
	}
	
	private ArrayList<PokerCard> fourOfaKindCheck(ArrayList<PokerCard> cards) {
		
		int fourCounter = 0;
		ArrayList<PokerCard> fourofaKind = new ArrayList<PokerCard>();
		ArrayList<PokerCard> fourofaKind2 = new ArrayList<PokerCard>();
		
		for(int i = 0; i < cards.size(); i++){							//4 fache for schleife um die 4 Karten zu finden
			
			for(int j = i+1; j < cards.size(); j++){
				
				if(cards.get(i).getNumber() == cards.get(j).getNumber()){	
					//algoritmus optimieren indem die schleife nur gestartet wird wenn die ersten beiden schon gleich sind
					
					for(int k = j+1; k < cards.size(); k++){
						
						if(cards.get(i).getNumber() == cards.get(j).getNumber() && cards.get(j).getNumber() == cards.get(k).getNumber()){
							//wieder algoritmus optimieren (Wenn drei gleiche gefunden wurden)
							
							for(int l = k+1; l < cards.size(); l++){
								
								if(cards.get(k).getNumber() == cards.get(l).getNumber()){		//Wenn alle vier Karten die selbe nummer haben
									fourCounter++;
									
									fourofaKind.add(cards.get(i));
									fourofaKind.add(cards.get(j));
									fourofaKind.add(cards.get(k));
									fourofaKind.add(cards.get(l));
																		
								}
								
							}
						}
					}
					
				}
				
			}
			
		}
		
		if(fourCounter == 1){	//Wenn nur ein Four of a kind gefunden wurde
			return fourofaKind;
		}else if(fourCounter == 0){		//Wenn kein four of a kind gefunden wurde
			return null;
		}else{			//Wenn mehr als ein four of a kind gefunden wurde
			
			fourofaKind = sortAll(fourofaKind);
			
			fourofaKind2.add(fourofaKind.get(0));
			fourofaKind2.add(fourofaKind.get(1));
			fourofaKind2.add(fourofaKind.get(2));
			fourofaKind2.add(fourofaKind.get(3));
						
			return fourofaKind2;
			
		}
		

	}

	private ArrayList<PokerCard> fullHouseCheck(ArrayList<PokerCard> cards) {
		
		ArrayList<PokerCard> hightrips = tripsCheck(cards);				//Hier werden das größte Trio / Paar gespeichert
		
		cards = sortOutNumber(cards, hightrips.get(0).getNumber());
		
		ArrayList<PokerCard> highpair = pairCheck(cards);
		
		ArrayList<PokerCard> fullHouse = new ArrayList<PokerCard>(); 
		
		if(hightrips != null && highpair != null){
			
			for(PokerCard card : hightrips){					//Das größte Paar und Trio wird im FullHose gespeichert
				fullHouse.add(card);
			}
			
			for(PokerCard card : highpair){
				fullHouse.add(card);
			}
		
			
			return fullHouse;
		}
		
		return null;
	}
	
	private ArrayList<PokerCard> flushCheck(ArrayList<PokerCard> cards) {
		
		ArrayList<PokerCard> flush = new ArrayList<PokerCard>();
		ArrayList<PokerCard> finalFlush = new ArrayList<PokerCard>();
		
		if(countHearts(cards) >= 5){								//wenn es 5 oder mehr Herz karten gibt
			for(PokerCard pc : cards){	//geht alle karten durch
				if(pc.getColor().equals(PokerCard.cardColor.Herz)){	
					//Wenn die Karte eine Herz Karte ist wir die der Liste hinzugefügt
					flush.add(pc);
				}
			}
			
			flush = sortAll(flush);		//Liste wird sortiert
			
			for(int i = 0; i < 5; i++){
				finalFlush.add(flush.get(i));	//die ersten 5 elemente der Liste werden gespeichert und...
			}
				
			return finalFlush;		//... dann zurückgegeben
			
		}else if(countCaros(cards) >= 5){								//wenn es 5 oder mehr Karo karten gibt
			for(PokerCard pc : cards){
				if(pc.getColor().equals(PokerCard.cardColor.Caro)){
					flush.add(pc);
				}
			}
			
			flush = sortAll(flush);
			
			for(int i = 0; i < 5; i++){
				finalFlush.add(flush.get(i));
			}
				
			return finalFlush;
			
		}else if(countPigs(cards) >= 5){								//wenn es 5 oder mehr Pig karten gibt
			for(PokerCard pc : cards){
				if(pc.getColor().equals(PokerCard.cardColor.Pig)){
					flush.add(pc);
				}
			}
			
			flush = sortAll(flush);
			
			for(int i = 0; i < 5; i++){
				finalFlush.add(flush.get(i));
			}
			
			return finalFlush;
			
		}else if(countKreuz(cards) >= 5){								//wenn es 5 oder mehr Kreuz karten gibt
			for(PokerCard pc : cards){
				if(pc.getColor().equals(PokerCard.cardColor.Kreuz)){
					flush.add(pc);
				}
			}
			
			flush = sortAll(flush);
			
			for(int i = 0; i < 5; i++){
				finalFlush.add(flush.get(i));
			}
			
			return finalFlush;
			
		}
		return null;					//Wenn keine der 4 'Farben' 5 mal vorkommt wird null returned
		
	}
	
	private ArrayList<PokerCard> straightCheck(ArrayList<PokerCard> cards) {
		int counter = 0;
		ArrayList<PokerCard> straight = new ArrayList<PokerCard>();
		
		A: for(int i = 14; i >= 5; i--){			// i = [14; 5]
			
			counter = 0;
			straight.clear();
			
			B: for(int j = i; j > i - 5; j--){		// j = [i; i - 5]
				
				C: for(PokerCard card : cards){
					if(card.getNumber() == j){
						counter += 1;
						straight.add(card);
						
						if(counter == 5){
							return straight;
						}
						
						break C;
					}					
				}
				
				
			}
			
		}
		
		
		return null;
	}

	private ArrayList<PokerCard> tripsCheck(ArrayList<PokerCard> cards) {
		
		ArrayList<PokerCard> tripsList = new ArrayList<PokerCard>();
		
		for(int i = 0; i < cards.size(); i++){		//3-fache for Schlefe um alle trios zu finden und im tripsCounter zu speichern
			
			for(int j = i+1; j < cards.size(); j++){
				
				if(cards.get(i).getNumber() == cards.get(j).getNumber()){
					for(int k = j+1; k < cards.size(); k++){
						
						if(cards.get(j).getNumber() == cards.get(k).getNumber()){
							tripsList.add(cards.get(i));
							tripsList.add(cards.get(j));
							tripsList.add(cards.get(k));
						}
					}
				}
			}
		}
		
		if(tripsList.size() == 3){
			return tripsList;
		}else if(tripsList.size() > 3){
			
			TreeMap<Integer, Integer> tripsMapList = new TreeMap<Integer, Integer>();		//TreeMap wird erstellt die später die Position und die Nummer der ersten Karte jedes Trios enthält
			ArrayList<PokerCard> returnTrips = new ArrayList<PokerCard>();					//diese Liste wird später returned
			
			for(int i = 0; i < tripsList.size(); i += 3){									//Von jedem Trio wird immer der Wert und die Stelle einer Karte zur Treemap hinzugefügt
				tripsMapList.put(i, tripsList.get(i).getNumber());
			}
			
			int groeste = 0;
			int groesterKey = 0;
			
			for(int key : tripsMapList.keySet()){
				if(tripsMapList.get(key) > tripsMapList.get(groesterKey)){
					groesterKey = key;														//die Stelle der Karte mit dem größten Wert wird gespeichert
				}
			}
			
			ArrayList<PokerCard> returntrips = new ArrayList<PokerCard>();
			returntrips.add(tripsList.get(groesterKey));									//die Karte mit dem höhsten wert und die zwei nebenan (das Trio) werden returned
			returntrips.add(tripsList.get(groesterKey+1));
			returntrips.add(tripsList.get(groesterKey+2));
			return returntrips;
			
		}
		return null;
	}

	private ArrayList<PokerCard> twoPairCheck(ArrayList<PokerCard> cards) {
		int counter = 0;
		int pairCounter = 0;
		ArrayList<PokerCard> twoPair = new ArrayList<PokerCard>();

		for (PokerCard card : cards) {						//Alle Paare werden in twoPair gespeichert und mit pairCounter gezählt wieviele es sind

			for (int i = counter + 1; i < cards.size(); i++) {

				if (card.getNumber() == cards.get(i).getNumber()) {
					twoPair.add(card);
					twoPair.add(cards.get(i));
					pairCounter++;
				}

			}

			counter++;
		}

		if (pairCounter == 1) {									//Wenn pairCounter == 1 gibt es also genau 1 paar (und damit kein twoPair -> return null)
			return null;
		} else if (pairCounter == 2) {							//es gibt genau 2 Paare
			return twoPair;
		} else if (pairCounter > 2){							//es gibt mehr als 2 Paare in diesem Fall müssen die 2 Größeren aussortiert und returned werden
			
			TreeMap<Integer, Integer> pairList = new TreeMap<Integer, Integer>();		//TreeMap wird erstellt die später die Position und die Nummer der ersten Karte jedes Paares enthält
			ArrayList<PokerCard> returnTwoPair = new ArrayList<PokerCard>();			//diese Liste wird später returned
			
			for(int i = 0; i < twoPair.size(); i+=2){			//for Schleife in der i bei 0 anfängt und immer um 2 hoch geht bis i > die länge von twoPair
								
				pairList.put(i, twoPair.get(i).getNumber());		//hier wird also jede 2te Karte (da 2 ja immer die selbe nummer haben) in pairList gespeichert
			}
			
			for(int j = 0; j <= 1; j++){			//2 Durchgänge, da es ja auch am ende 2 Paare geben soll
				
				int highestNumber = 0;
				int highestKey = 0;
				
				for(int key : pairList.keySet()){				//Es wird überprüft welche Karte die Größte Nummer ist
					if(pairList.get(key) > highestNumber){
						highestKey = key;							//Der Wert und der Key (also die Stelle) der höhsten Karte werden gespeichert
						highestNumber = pairList.get(key);
					}
				}
				returnTwoPair.add(twoPair.get(highestKey));			//von twoPair wird die Karte an der Stelle wo der Wert am größten ist und die Karte daneben (also die Paar karte)
				returnTwoPair.add(twoPair.get(highestKey + 1));		//in returnTwoPair gespeichert
				pairList.remove(highestKey);						//Damit nun auch die zweit höhste Karte gespeichert wird, wird der Key der größten Karte aus pairList gelöscht (und dann wiederholt sich die Schleife
			}
			return returnTwoPair;			//Die 4 Karten (2 Paare) werden ausgegeben
				
		} else {
			return null;
		}
	}

	private ArrayList<PokerCard> pairCheck(ArrayList<PokerCard> cards) {

		int counter = 0;
		
		cards = sortAll(cards);
		
		ArrayList<PokerCard> pair = new ArrayList<PokerCard>();

		for (PokerCard card : cards) {			//geht jede einzelne Karte durch

			for (int i = 0; i < cards.size() - 1; i++) {				//i nimmt die Indizes von cards an
				if (counter != i) {										//Wenn die zwei zu vergleichenden Karten nicht an der selben Stelle sind
					if (card.getNumber() == cards.get(i).getNumber()) {			//dann werden sie vergliechten und wenn sie gleich sind
						pair.add(card);											//werden sie dem pair hinzugefügt
						pair.add(cards.get(i));
						return pair;
					}
				}
			}

			counter++;

		}
		return null;
	}

	private ArrayList<PokerCard> highCardCheck(ArrayList<PokerCard> cards) {

		int highestNum = 0;
		PokerCard highestCard = null;
		ArrayList<PokerCard> highestCardArr = new ArrayList<PokerCard>();

		for (PokerCard card : cards) {
			if (card.getNumber() > highestNum) {
				highestCard = card;
				highestNum = card.getNumber();
			}
		}

		highestCardArr.add(highestCard);
		return highestCardArr;

	}
	
	public ArrayList<PokerCard> sortAll(ArrayList<PokerCard> hand) {
		
		ArrayList<PokerCard> sortedHand = hand;					//sortiert die Karten der größe nach absteigend
		PokerCard temp;
		
		for(int i = 0; i < sortedHand.size(); i++){
			
			for(int j = i; j < sortedHand.size(); j++){
				if(sortedHand.get(i).getNumber() < sortedHand.get(j).getNumber()){
					temp = sortedHand.get(i);
					sortedHand.set(i, sortedHand.get(j));
					sortedHand.set(j, temp);
				}
			}
			
		}		
		return sortedHand;
	}

	public void printAll(ArrayList<PokerCard> hand) {
		for (PokerCard card : hand) {
			card.printInfo();
		}
	}

	public ArrayList<PokerCard> sortOutNumber(ArrayList<PokerCard> hand, int number){			//sortiert alle PokerKarten mit einer bestimmten Nummer aus
		
		for(int i = 0; i < hand.size(); i++){
			if(hand.get(i).getNumber() == number){
				hand.remove(i);
				i = 0;
			}
		}
		
		return hand;
		
	}
	
	public int countHearts(ArrayList<PokerCard> hand){
		int counter = 0;
		
		for(PokerCard pc : hand){
			if(pc.getColor().equals(PokerCard.cardColor.Herz)){
				counter++;
			}	
		}
		
		return counter;
	}
	
	public int countCaros(ArrayList<PokerCard> hand){
		int counter = 0;
		
		for(PokerCard pc : hand){
			if(pc.getColor().equals(PokerCard.cardColor.Caro)){
				counter++;
			}	
		}
		
		return counter;
	}
	
	public int countKreuz(ArrayList<PokerCard> hand){
		int counter = 0;
		
		for(PokerCard pc : hand){
			if(pc.getColor().equals(PokerCard.cardColor.Kreuz)){
				counter++;
			}	
		}
		
		return counter;
	}
	
	public int countPigs(ArrayList<PokerCard> hand){
		int counter = 0;
		
		for(PokerCard pc : hand){
			if(pc.getColor().equals(PokerCard.cardColor.Pig)){
				counter++;
			}	
		}
		
		return counter;
	}
	
	
}