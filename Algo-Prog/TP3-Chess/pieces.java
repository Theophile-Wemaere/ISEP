public class pieces
{
  public static String tower = "\ue263";
  public static String queen = "\ue26e";
  public static String pawn = "\ue261";                                      
  public static String king = "\uf6a4";
  public static String bishop = "\ue29c";
  public static String knight = "\ue25f";
    
  private int[] location = new int[2];
  private String type, side;

  public pieces(String type, String side, int x, int y )
  {
    this.location[0] = x;
    this.location[1] = y;

    switch(type)
    {
      case "tower":
          this.type = tower;
          break;                                                          
      case "queen":
          this.type = queen;
          break;
      case "pawn":
          this.type = pawn;
          break;
      case "king":
          this.type = king;
          break;
      case "bishop":
          this.type = bishop;
          break;
      case "knight":
          this.type = knight;
          break;
    }

    if(side.equals("white"))
    {
      this.side = "white";
      this.type = "\u001B[37m" + this.type + "\u001B[0m";
    }
    else if(side.equals("black"))
      this.side = "black";
    else
    {
      System.out.println("Error : side " + side + " unknow");
      System.exit(0);
    }
  }

  public int[] getPos()
  {
    int[] pos = {this.location[0],this.location[1]};
    return pos;
  }

  public void setPos(int x, int y)
  {
    System.out.println("Setting new pos : " + x + " " + y);
    this.location[0] = x;
    this.location[1] = y;
  }

  public String getType()
  {
    return this.type;
  }

  public String getSide()
  {
    return this.side;
  }
}
