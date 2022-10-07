import java.util.Scanner;

public class Main 
{
    public static void main(String [] args)
    {
        //somme();
        //division();
        volume();
    }


    public static void somme() 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir le premier entier");
        int premierEntier = scanner.nextInt();
        System.out.println("Veuillez saisir le deuxieme entier");
        int deuxiemeEntier = scanner.nextInt();
        int somme = premierEntier + deuxiemeEntier;
        System.out.println("La somme de " + premierEntier + " avec " + deuxiemeEntier + " est egale a " + somme);
    }

    public static void division()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir le premier entier");
        float premierEntier = scanner.nextInt();
        System.out.println("Veuillez saisir le deuxieme entier");
        float deuxiemeEntier = scanner.nextInt();
        if(deuxiemeEntier == 0.0)
        {
            System.out.println("[Error] division par 0 impossible !");
            System.exit(0);
        }
        else
        {
            float division = premierEntier / deuxiemeEntier;
            System.out.println("La division de " + premierEntier + " avec " + deuxiemeEntier + " est egale a " + division);
        }
    }

    public static void volume()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrez la longueur du pavé");
        float longueur = scanner.nextInt();
        System.out.println("Veuillez entrez la largeur du pavé");
        float largeur = scanner.nextInt();
        System.out.println("Veuillez entrez la hauteur du pavé");
        float hauteur = scanner.nextInt();

        float volume = longueur*largeur*hauteur;

        System.out.println("Le volume de votre pavé de dimension " + longueur + "x" + largeur + "x" + hauteur + " est égal à " + volume);
    }
}
