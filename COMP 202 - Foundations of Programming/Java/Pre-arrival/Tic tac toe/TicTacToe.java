import java.util.Scanner;

public class TicTacToe{
  public static String[][] gameBoard = new String[3][3];
  public static boolean isGameOver = false;
  public static boolean isPlayerOne = true;
  public static Scanner scan = new Scanner(System.in);
  public static int nextMove;
  
  public static String symbol;
  public static Integer[] validSquares = {0, 1, 2, 3, 4, 5, 6, 7, 8};
  
  public static String[][] startGame(){
    int index = 0;
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++)
        gameBoard[i][j] = index++ + ": ";
    }
    return gameBoard;
  }
  
  public static void printBoard(){
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        if(gameBoard[i][j].length() == 4)
          System.out.print(gameBoard[i][j] + " | ");
        else
          System.out.print(gameBoard[i][j] + "  | ");
      }
      System.out.println();
    }
  }
  
  public static int getNextMove(){
    do{
      nextMove = scan.nextInt();
      if(nextMove < 0 || 8 < nextMove){
        System.out.println("Only between 0 and 8 pls");
        printBoard();
      }
      else if(validSquares[nextMove] == null){
        System.out.println("That square is already taken");
        printBoard();
      }
    } while(nextMove < 0 || 8 < nextMove || validSquares[nextMove] == null);
    validSquares[nextMove] = null;
    return nextMove;
  }
  
  public static String[][] applyNextMove(){
    if(isPlayerOne == true)
      symbol = "X";
    else
      symbol = "O";
    if(nextMove == 0 || nextMove == 1 || nextMove == 2)
      gameBoard[0][nextMove] += symbol;
    else if(nextMove == 3 || nextMove == 4 || nextMove == 5)
      gameBoard[1][nextMove - 3] += symbol;
    else if(nextMove == 6 || nextMove == 7 || nextMove == 8)
      gameBoard[2][nextMove - 6] += symbol;
    isPlayerOne = !isPlayerOne;
    return gameBoard;
  }
  
  public static boolean hasGameEnded(){
    if(validSquares[4] == null){
      if(validSquares[0] == null && validSquares[8] == null &&
         gameBoard[1][1].charAt(3) == gameBoard[0][0].charAt(3) &&
         gameBoard[1][1].charAt(3) == gameBoard[2][2].charAt(3)){
        return true;
      }
      if(validSquares[2] == null && validSquares[6] == null &&
         gameBoard[1][1].charAt(3) == gameBoard[0][2].charAt(3) &&
         gameBoard[1][1].charAt(3) == gameBoard[2][0].charAt(3)){
        return true;
      }
      if(validSquares[3] == null && validSquares[5] == null &&
         gameBoard[1][1].charAt(3) == gameBoard[1][0].charAt(3) &&
         gameBoard[1][1].charAt(3) == gameBoard[1][2].charAt(3)){
        return true;
      }
      if(validSquares[1] == null && validSquares[7] == null &&
         gameBoard[1][1].charAt(3) == gameBoard[0][1].charAt(3) &&
         gameBoard[1][1].charAt(3) == gameBoard[2][1].charAt(3)){
        return true;
      }
    }
    
    if(validSquares[0] == null){
      if(validSquares[1] == null && validSquares[2] == null &&
         gameBoard[0][0].charAt(3) == gameBoard[0][1].charAt(3) &&
         gameBoard[0][0].charAt(3) == gameBoard[0][2].charAt(3)){
        return true;
      }
      if(validSquares[3] == null && validSquares[6] == null &&
         gameBoard[0][0].charAt(3) == gameBoard[1][0].charAt(3) &&
         gameBoard[0][0].charAt(3) == gameBoard[2][0].charAt(3)){
        return true;
      }
    }
    
    if(validSquares[8] == null){
      if(validSquares[7] == null && validSquares[6] == null &&
         gameBoard[2][2].charAt(3) == gameBoard[2][1].charAt(3) &&
         gameBoard[2][2].charAt(3) == gameBoard[2][0].charAt(3)){
        return true;
      }
      if(validSquares[5] == null && validSquares[2] == null &&
         gameBoard[2][2].charAt(3) == gameBoard[1][2].charAt(3) &&
         gameBoard[2][2].charAt(3) == gameBoard[0][2].charAt(3)){
        return true;
      }
    }
        
    return false;
  }
  
  public static void endGame(){
    if(hasGameEnded() == true){
      if(isPlayerOne == false)
        System.out.println("Player 1 wins");
      else
        System.out.println("Player 2 wins");
    }
    else
      System.out.println("It's a draw");
  }
  
  public static void main(String[] args){
    startGame();
    printBoard();
    for(int i = 0; i < 9 && hasGameEnded() == false; i++){
      getNextMove();
      applyNextMove();
      printBoard();
    }
    endGame();
  }
}