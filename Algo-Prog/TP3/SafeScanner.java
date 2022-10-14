import java.util.Scanner;
import java.io.InputStream;

public class SafeScanner 
{
    Scanner sc;
    
    public SafeScanner(InputStream is) 
    {
        this.sc = new Scanner(is);
    }
    
    public int getInt()
    {
        int input = this.sc.nextInt();
        return input;
    }
   
    public int[] getDimFromFile()
    {
        String input = this.sc.nextLine();
        int[] dim = new int[2];
        dim[0] = input.charAt(0) - '0';
        dim[1] = input.charAt(2) - '0';
        return dim;
    }

    public void closeScanner()
    {
        this.sc.close();
    }
}
