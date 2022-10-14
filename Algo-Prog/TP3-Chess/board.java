public class board
{

  squares[][] board = new squares[8][8];
  
  public board()
  {
    this.board = board;
  }

  public void createBoard()
  {
    for(int x=0; x<8;x++)
    {
      for(int y=0;y<8;y++)
      {
        this.board[x][y] = new squares(x,y); 
      }
    }
  }

  public void printBoard()
  {
    printAlpha();
    for(int i=0;i<8;i++)
    {
      printLine1();
      for(int j=0;j<3;j++)
      {
        if(j==1)
        {
          printLine2(this.board[i]);
        }
        else
        {
          printLine2(null);
        }
      }
    }
    printLine1();
  }

  public void printAlpha()
  {
      System.out.print("   ");
      char[] alphabet = {'a','b','c','d','e','f','g','h'};
      for(int i=0;i<8;i++)
      {
          System.out.print("      " + alphabet[i] + "     ");
      }
      System.out.print("\n");
  }

  public void printLine1()
  {  
      System.out.print("   ");
      for(int i=0;i<8;i++)
      {
          System.out.print("+-----------");
      }
      System.out.println("+");
  }

  public void printLine2(squares row[])
    {
        if(row != null)
        {
            System.out.print(" " + Main.digit + " ");
            Main.digit--;
        }
        else
        {
            System.out.print("   ");
        }
        
        for(int i=0; i<8;i++)
        {
            if (row == null)
              System.out.print("|           ");
            else
              System.out.print("|     " + row[i].getValue() + "     ");
        }
        System.out.println("|");
    }

  public String getSquareValue(int x, int y)
  {
    return this.board[x][y].getValue();
  }

  public void emptySquare(int x, int y)
  {
    this.board[x][y].emptyCase();
  }

  public void updateBoard(pieces[] white_pieces, pieces[] black_pieces)
  {
    int x,y;
    for(int i=0;i<white_pieces.length;i++)
    {
      x = white_pieces[i].getPos()[0];
      y = white_pieces[i].getPos()[1];
      this.board[x][y].fillCase(white_pieces[i].getType());
    }
    for(int i=0;i<black_pieces.length;i++)
    {
      x = black_pieces[i].getPos()[0];
      y = black_pieces[i].getPos()[1];
      this.board[x][y].fillCase(black_pieces[i].getType());
    }
  }
}
