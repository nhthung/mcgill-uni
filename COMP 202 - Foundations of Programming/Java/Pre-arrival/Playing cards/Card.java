public class Card{
  private final int rank;
  private final int suit;
  
  public static final String[] SUITS = {
    "Clubs", "Diamonds", "Hearts", "Spades"};
  
  public static final String[] RANKS = {
    null, "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
  
  public Card(int rank, int suit){
    this.rank = rank;
    this.suit = suit;
  }
  
  public String toString(){
    return RANKS[this.rank] + " of " + SUITS[this.suit];
  }
  
  public boolean equals(Card that){
    return this.rank == that.rank && this.suit == that.suit;
  }
  
  public int compareTo(Card that){
    if(this.suit < that.suit)
      return -1;
    if(this.suit > that.suit)
      return 1;
    if(this.rank < that.rank){
      if(this.rank == 1)
        return 1;
      return -1;
    }
    if(this.rank > that.rank){
      if(that.rank == 1)
        return -1;
      return 1;
    }
    return 0;
  }
  
  public int getRank(){
    return this.rank;
  }
  
  public int getSuit(){
    return this.suit;
  }
  
  public static Card[] makeDeck(){
    Card[] cards = new Card[52];
    int index = 0;
    for(int suit = 0; suit <= 3; suit++){
      for(int rank = 1; rank <= 13; rank++){
        cards[index] = new Card(rank, suit);
        index++;
      }
    }
    return cards;
  }
  
  public static int[] suitHist(Card[] hand){
    int[] suitHist = new int[4];
    for(int i = 0; i < hand.length; i++)
      suitHist[hand[i].suit]++;
    return suitHist;
  }
  
  public static boolean hasFlush(Card[] hand){
    for(int i = 0; i < 4; i++){
      if(suitHist(hand)[i] >= 5)
        return true;
    }
    return false;
  }
  
  public static void main(String[] args){
    Card[] deck = makeDeck();
    for(int i = 0; i < deck.length; i++)
      System.out.println(deck[i]);
  }
}