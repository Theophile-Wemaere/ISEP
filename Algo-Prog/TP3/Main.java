public class Main
{
    public static void main(String [] args)
    {
        int height=5,width=9;
        ascii cat = new ascii();
        
        if(height>=3 && width>=7)
        {
            ascii.drawCat(height,width);
        }
        else
        {
            System.out.println("Error : mininum dimensions for cat head are 3x7");
            System.exit(0);
        }
    }
}
