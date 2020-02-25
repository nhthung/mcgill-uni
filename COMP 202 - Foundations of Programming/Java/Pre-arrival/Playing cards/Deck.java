import java.util.*;

public class Deck{
  private static int deckCount = 0;
  
  public static Card[] newDeck(){
    deckCount++;
    return Card.makeDeck();
  }
  
  public static Card[] shuffle(Card[] deck){
    Collections.shuffle(Arrays.asList(deck));
    return deck;
  }
  
  public static void getDeck(Card[] deck){
    for(int i = 0; i < deck.length; i++)
      System.out.println(deck[i]);
    System.out.println();
  }
  
  public static void deckNb(){
    System.out.println(deckCount);
  }
  
  public static void drawFrom(Card[] deck, int cards){
    for(int i = 0; i < cards && cards >= 0 && cards <= 52; i++)
      System.out.println(deck[i]);
  }
  
  public static void main(String[] args){
    Card[] d1 = newDeck();
    shuffle(d1);
    drawFrom(d1, 3);
  }
}