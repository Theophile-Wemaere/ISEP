import java.util.Scanner;
import java.lang.Math;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.*;

public class Main 
{
    public static void main(String [] args)
    {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel exercice ? Saisissez :");
        System.out.println("[1] Discriminant");
        System.out.println("[2] Parite d'un nombre");
        System.out.println("[3] Calcul d'extremum");
        System.out.println("[4] Egalite entre chaine de caracteres");
        System.out.println("[5] Factorielle");
        System.out.println("[6] Compte a rebours");
        System.out.println("[7] Valeurs de carrés");
        System.out.println("[8] Table de multiplication");
        System.out.println("[9] Division d'entier");
        System.out.println("[10] Regle graduee");
        System.out.println("[11] Nombres premiers");
        System.out.println("[12] Manipulation sur tableau");
        System.out.print("\nChoisissez une fonction : ");

        int c = scanner.nextInt();

        switch(c)
        {
            case 1:
                discriminant();
                break;
            case 2:
                parite();
                break;
            case 3:
                max();
                break;
            case 4:
                egaliteStr();
                break;
            case 5:
                factorielle();
                break;
            case 6:
                countdown();
                break;
            case 7:
                carres();
                break;
            case 8:
                tableMultiplication();
                break;
            case 9:
                division();
                break;
            case 10:
                regle();
                break;
            case 11:
                nombrePremier();
                break;
            case 12:
                initialisationTableau();
                break;
        }
    }

    public static void discriminant()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Quelle est la valeur de a : ");
        int a = scanner.nextInt();
        System.out.print("Quelle est la valeur de b : ");
        int b = scanner.nextInt();
        System.out.print("Quelle est la valeur de c : ");
        int c = scanner.nextInt();

        int delta = (int) (Math.pow(b, 2) - 4 * a * c);

        if (delta < 0)
        {
            System.out.println("Ce polynome n'a pas de racine reelle");
            String x = String.valueOf(-b/(2.0*a));
            String xi = String.valueOf(Math.sqrt(-delta)/(2*a));
            String x0 = x + " + i" + xi;
            String x1 = x + " - i" + xi;
            System.out.println("Racine x0 = " + x0 + "\nRacine x1 = " + x1);
        }
        else if (delta == 0)
        {
            float x0 = -b/(2*a);
            System.out.println("Racine x0 = " + x0);
        }
        else
        {
            double x0 = (-b+Math.sqrt(delta))/(2*a);
            double x1 = (-b-Math.sqrt(delta))/(2*a);
            System.out.println("Racine x0 = " + x0 + "\nRacine x1 = " + x1);
        }
    }

    public static void parite()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Quelle est la valeur de votre entier : ");
        int num = scanner.nextInt();

        if(num%2 == 0)
            System.out.println("L'entier " + num + " est pair");
        else
            System.out.println("L'entier " + num + " est impair");
    }

    public static void max()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Quelle est la valeur de a : ");
        int a = scanner.nextInt();
        System.out.print("Quelle est la valeur de b : ");
        int b = scanner.nextInt();
        
        if (a>b)
            System.out.println(a + " est supérieur à " + b);
        else if (a<b)
            System.out.println(b + " est supérieur à " + a);
        else
            System.out.println(a + " est égal à " + b);
    }

    public static void egaliteStr()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez une premiere chaine de characteres : ");
        String s1 = scanner.nextLine();
        System.out.print("Entrez une deuxieme chaine de characteres : ");
        String s2 = scanner.nextLine();

        if(s1.equals(s2))
        {
            System.out.println("Les 2 chaines sont identiques");
        }
        else
        {
            System.out.println("Les 2 chaines sont différentes");
        }
    }

    public static void factorielle()
    {
        int factorielle = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Saisir un entier positif ou nul : ");
        int n = scanner.nextInt();
        for (int i = 1; i <= n; i++) 
        {
            factorielle *= i;
        }
        System.out.println(n + "! = " + factorielle);
    }

    public static void countdown()
    {
        for(int i=10;i>0;i--)
        {
            System.out.println(i);
            try
            {
                TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("BOOOOOOOOM");
    }

    public static void carres()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Saisir un entier positif ou nul : ");
        int n = scanner.nextInt();
        int result = n * n;
        System.out.println("La valeur au carré de " + n + " est égale à " + result);
    }

    public static void tableMultiplication()
    {
        for(int i=1;i<=10;i++)
        {
            for(int j=1;j<=10;j++)
            {
                System.out.print(i*j + " ");
            }
            System.out.print("\n");
        }
    }

    public static void division()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Saisir un entier positif ou nul : ");
        int b,a = scanner.nextInt();
        do 
        {
            System.out.print("Veuillez saisir un deuxieme entier positif : ");
            b = scanner.nextInt();
        } while (b <= 0);

        System.out.println("Resultat de " + a + "/" + b + " = " + (float) a / b);
    }

    public static void regle()
    {
        Scanner scanner = new Scanner(System.in);
        int l;
        do
        {
            System.out.print("Saisir la longueur de la regle (superieur a 0) : ");
            l = scanner.nextInt();
        } while (l <= 0);

        System.out.print("|");
        for(int i=1;i<=l;i++)
        {
            if(i%10 == 0)
                System.out.print("|");
            else
                System.out.print("-");

        }
        System.out.print("\n");
    }

    public static void nombrePremier()
    {
        Scanner scanner = new Scanner(System.in);
        int n;
        do
        {
            System.out.print("Veuillez saisir un entier positif : ");
            n = scanner.nextInt();
        } while (n <= 0);

        int c = 0;
        for(int i=1;i<=n;i++)
        {
            if (n%i == 0)
            {
                c++;
            }
        }

        if (c==2)
            System.out.println(n + " est un nombre premier");
        else
            System.out.println(n + " n'est pas un nombre premier");
    }

    public static void initialisationTableau() 
    {
        int[] tableau = new int[10];
        Scanner scanner = new Scanner(System.in);
        int i;

        for (i = 0; i < tableau.length; i++) 
        {
            System.out.print("Saisir un entier : ");
            tableau[i] = scanner.nextInt();
        }

        int s=0;
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        int min=tableau[0],max=tableau[0];
        
        System.out.print("[ ");
        for(i=0; i<tableau.length; i++)
        {
            System.out.print(tableau[i] + " ");
            s+=tableau[i];

            if(tableau[i] > max)
                max = tableau[i];

            if(tableau[i] < min)
                min = tableau[i];
            
            if(tableau[i]%2 == 0)
                list.add(tableau[i]);

            if(i%2==0)
                list2.add(tableau[i]);
        }
        System.out.println("]");

        System.out.println("Max : " + max + "\nMin : " + min);
        System.out.println("Somme des elements : " + s);
        
        System.out.print("Nombre pair dans le tableau : [ ");
        for(i=0;i<list.size();i++)
        {
            System.out.print(list.get(i) + " ");
        }
        System.out.println("]");
        
        System.out.print("Nombre d'indice pair dans le tableau : [ ");
        for(i=0;i<list2.size();i++)
        {
            System.out.print(list2.get(i) + " ");
        }
        System.out.println("]");

        inverseTableau(tableau);
    }

    public static void inverseTableau(int[] tableau)
    {
        int l = tableau.length-1;
        int[] newtable = new int[tableau.length];
        for(int i=0;i<tableau.length;i++)
        {
            newtable[l]=tableau[i];
            l--;
        }

        System.out.print("Tableau inversé [ ");
        for(int i=0;i<newtable.length;i++)
        {
            System.out.print(newtable[i] + " ");
        }
        System.out.println("]");
    }
}
