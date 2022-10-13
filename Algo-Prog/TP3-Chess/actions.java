public class actions
{
   public static pieces[] updatePiecePos(pieces[] piecesArray, int[] pos)
   {
       for(int i=0;i<16;i++)
       {
          int[] currentPos = piecesArray[i].getPos();
          System.out.printf("real pos : %d %d -> current pos : %d %d\n",pos[0],pos[1],currentPos[0],currentPos[1]);
          if(pos[0]==currentPos[0] && pos[1]==currentPos[1])
          {
            piecesArray[i].setPos(pos[2],pos[3]);
            break;
          }
       }
       return piecesArray;
   }
   

   public static int isMoveValid(String piece, int[] pos)
   {
      switch(piece)
      {
         case "\ue263":
            System.out.println("tower");
            break;
         case "\ue26e":
            System.out.println("queen");
            break; 
         case "\ue261":
            System.out.println("pawn");
            break; 
         case "\uf6a4":
            System.out.println("king");
            break; 
         case "\ue29c":
            System.out.println("bishop");
            break; 
         case "\ue25f":
            System.out.println("knight");
            break; 
      }
      return 1;
   }

   public static int[] transformPos(String input)
   {
      String[] positions = input.split("->");
      int x_src,y_src=0,x_dest,y_dest=0;

      int[] digit2digit = {0,7,6,5,4,3,2,1,0};
      x_src = digit2digit[positions[0].charAt(1) - '0'];
      x_dest = digit2digit[positions[1].charAt(1) - '0'];
      
      char[] alpha2digit = {'a','b','c','d','e','f','g','h'};
      for(int i=0;i<8;i++)
      {
         if(alpha2digit[i] == positions[0].charAt(0))
         {
            y_src = i;
         }
         
         if(alpha2digit[i] == positions[1].charAt(0))
         {
            y_dest = i;
         }
      }

      int[] pos = {x_src,y_src,x_dest,y_dest};
      return pos;
   }
}
