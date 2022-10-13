import java.util.Scanner;

// TO DO :
// checkmate detector
// color detection + 2nd turn
// eat piece if on on another
// movement rules for each types
// print black in another color

public class Main
{
   public static int digit = 8;
   public static boolean checkmate = false;
   private static String move;
   private static int isMoveValid;
   private static boolean side = true;

   public static void main(String [] args)
   {
      Scanner scanner = new Scanner(System.in);

      // Handle keyboard interrupt
      Runtime.getRuntime().addShutdownHook(new Thread()
      {
          @Override
          public void run()
          {
              System.out.println("\n\nUser interrupt detected");
          }
      });

      board board = new board();
      board.createBoard();
      pieces[] white_pieces = initialisePieces("white");
      pieces[] black_pieces = initialisePieces("black");

      board.updateBoard(white_pieces,black_pieces);

      while(!checkmate)
      {
         digit = 8;
         clearConsole();
         board.printBoard();
         if(side)
            move = "white";
         else
            move = "black";
         
         System.out.printf("\nColor %s : make your move (XY_src->XY_dest): ", move);
         move = scanner.nextLine();
         int[] pos = actions.transformPos(move);
         String current_piece = board.getSquareValue(pos[0],pos[1]);
         isMoveValid = actions.isMoveValid(current_piece,pos);
         if(isMoveValid == 1 || isMoveValid == 2)
         {
            if(side)
               white_pieces = actions.updatePiecePos(white_pieces,pos);
            else
               black_pieces = actions.updatePiecePos(black_pieces,pos);
            
            board.emptySquare(pos[0],pos[1]);
            side = !side;
            
            board.updateBoard(white_pieces, black_pieces);
            if(isMoveValid == 2)
            {
               checkmate = true;
            }
         }
      }
   }

   public static void clearConsole()
   {   
       System.out.print("\033[H\033[2J");
       System.out.flush();
   }   
   
   public static pieces[] initialisePieces(String side)
   {
      int x;
      pieces[] array = new pieces[16];
      if(side.equals("white") || side.equals("black"))
      {
         if(side.equals("white"))
         {
           pieces king = new pieces("king",side, 0, 3);
           array[0]=king;
           pieces queen = new pieces("queen", side, 0, 4);
           array[1]=queen;
           int c=1;
           for(int i=0;i<8;i++)
           {
              c++;
              pieces pawn = new pieces("pawn", side, 1, i);
              array[c] = pawn;
           }
           x=0;
         }
         else
         { 
           pieces king = new pieces("king",side, 7, 3);
           array[0]=king;
           pieces queen = new pieces("queen", side, 7, 4);
           array[1]=queen;
           int c=1;
           for(int i=0;i<8;i++)
           {
              c++;
              pieces pawn = new pieces("pawn", side, 6, i);
              array[c] = pawn;
           }
           x=7;
         }
         pieces tower = new pieces("tower",side,x,0);
         array[10] = tower;
         pieces tower2 = new pieces("tower",side,x,7);
         array[11] = tower2;
         pieces knight = new pieces("knight",side,x,1);
         array[12] = knight;
         pieces knight2 = new pieces("knight",side,x,6);
         array[13] = knight2;
         pieces bishop = new pieces("bishop",side,x,2);
         array[14] = bishop;
         pieces bishop2 = new pieces("bishop",side,x,5);
         array[15] = bishop2;

      }
      else
      {
         System.out.println("Error : side " + side + " unknow");
         System.exit(0);
      }
      return array;
   }
}
