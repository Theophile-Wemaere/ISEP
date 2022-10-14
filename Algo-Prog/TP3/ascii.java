public class ascii
{

    private static int width, height;

    public ascii()
    {
    }

    public static void drawCat(int height, int width)
    {

        // ears and top of the head
        System.out.print("|\\");
        printCharLoop(width-4,'-');
        System.out.println("/|");
 
        // middle of the head and eye
        for(int i=1;i<height-1;i++)
        {
            System.out.print("|");
            if(i==height/2)
            {
                System.out.print(" ");
                System.out.print("0");
                printCharLoop(width-6,'_');
                System.out.print("0");
                System.out.print(" ");
            }
            else
            { 
                printCharLoop(width-2,' ');
            }
            System.out.println("|");
        }

        // bottom of the head
        System.out.print(" \\_");
        printCharLoop(width-6,'^');
        System.out.println("_/ ");
    }

    private static void printCharLoop(int n, char c)
    {

        for(int i=0;i<n;i++)
        {
            System.out.print(c);
        }
    }
}
