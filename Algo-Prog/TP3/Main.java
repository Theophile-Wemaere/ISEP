import java.util.Objects;
import java.util.*;

public class Main
{
    public static void main(String [] args)
    {
        int height=0,width=0;
        ascii cat = new ascii();
        boolean inputGood = false;
        //SafeScanner sc = new SafeScanner(
        //        Objects.requireNonNull(SafeScanner.class.getResourceAsStream("demo.txt"))
        //        );

        //int[] output = sc.getDimFromFile();
        //height = output[0];
        //width = output[1];

        while(!inputGood)
        {
            try
            {
                SafeScanner sc = new SafeScanner(System.in);
                System.out.print("Enter cat height (min 3) : ");
                height = sc.getInt();
                System.out.print("Enter cat width (min 7) : ");
                width = sc.getInt();
                inputGood = true;
                sc.closeScanner();  
            }
            catch(Exception e)
            {
                System.out.println("Please enter only integers");
            }
        }

        if(height>=3 && width>=7)
        {
            cat.drawCat(height,width);
        }
        else
        {
            System.out.println("Error : mininum dimensions for cat head are 3x7");
            System.exit(0);
        }

    }
}
