public class Main
{
    public static void main(String [] args)
    {
        int k=355;
        int[] a = {3,7,5,9,3,5,1,6,4,2,8,353};
        boolean result = exo1(a,k);
        if(result)
        {
            System.out.println("True");
        }
        else
        {
            System.out.println("False");
        }
    }

    private static boolean exo1(int[] a, int k)
    {
        boolean result = false;
        for(int i=0;i<a.length;i++)
        {
            for(int j=0;j<a.length;j++)
            {
                if(a[j]+a[i]==k)
                {
                    result = true;
                }
            }
        }
        return result;
    }
}
