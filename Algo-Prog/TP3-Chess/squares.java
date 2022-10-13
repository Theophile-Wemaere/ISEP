public class squares
{
  private int[] location = new int[2];  
  private boolean empty = true;
  private String value = " "; 

  public squares(int x, int y)
  {
    this.location[0] = x;
    this.location[1] = y;
    this.empty = true;
  }
  

  public void emptyCase()
  {
    this.value = " ";
    this.empty = true;
  }

  public String getValue()
  {
    return this.value;
  }
  
  public void fillCase(String piece)
  {
    this.value = piece;
    this.empty = false;
  }
}
