import java.util.*;
import java.time.*;

public class Main
{
    public static int SIZE = Integer.MAX_VALUE/15000;
    //public static int SIZE = 20;
    public static int[] table = new int[SIZE];
    public static boolean printvar = false;
    
    public static void main(String [] args)
    {
        initialiserTableau();
        printTable(table);    
        // arraycopy(src, startIndex, dest, startIndex, size)
        int[] tableauSelection = new int[SIZE];
        System.arraycopy(table, 0, tableauSelection, 0, SIZE);
        triSelection(tableauSelection);
        
        int[] tableauInsertion = new int[SIZE];
        System.arraycopy(table, 0, tableauInsertion, 0, SIZE);
        triInsertion(tableauInsertion);
        
        int[] tableauBulles = new int[SIZE];
        System.arraycopy(table, 0, tableauBulles, 0, SIZE);
        triBulles(tableauBulles);
        
        int[] tableauQuickSort = new int[SIZE];
        System.arraycopy(table, 0, tableauQuickSort, 0, SIZE);
        quickSort(tableauQuickSort);;
        
        int[] tableauSort = new int[SIZE];
        System.arraycopy(table, 0, tableauSort, 0, SIZE);
        triJava(tableauSort);;

    }

    public static void initialiserTableau() 
    {
        Instant start = Instant.now();
        System.out.println("Debut d’initialisation...");
        Random random = new Random();
        for (int i = 0; i < table.length; i++) {
        table[i] = random.nextInt(Main.SIZE);
        }
        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        System.out.println("L’initialisation a pris " + duration + " ms");
    }

    public static void triSelection(int[] tableau)
    {
        System.out.println("[Tri par selection]");
        Instant start = Instant.now();
        for (int i = 0; i < tableau.length - 1; i++) 
        {
            // Initialisation de indiceMin par le premier élément de la partie non-triée du tableau
            int indiceMin = i;
            // Cette boucle permet de parcourir la partie non-triée du tableau et de déterminer le minimum
            for (int j = i; j < tableau.length; j++) 
            {
                if (tableau[j] < tableau[indiceMin]) 
                {
                    indiceMin = j;
                }
            }
            // Opération de permutation classique (attention, il faut créer une variable temporaire)
            int swap = tableau[i];
            tableau[i] = tableau[indiceMin];
            tableau[indiceMin] = swap;
        }
        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        System.out.println("L’execution a pris " + duration + " ms"); 
        printTable(tableau);
    }

    public static void triInsertion(int[] tableau)
    {
        System.out.println("[Tri par insertion]");
        Instant start = Instant.now();
        for (int i = 0 ; i < tableau.length ; i++)
        {
            int elementATrier = tableau[i] ;
            int j = i ;
            while (j > 0 && tableau[j-1] > elementATrier)
            {
                tableau[j] = tableau[j-1] ;
                j-- ;
            }
            tableau[j] = elementATrier ;
        }
        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        System.out.println("L’execution a pris " + duration + " ms"); 
        printTable(tableau);
    }
    
    public static void triBulles(int[] tableau)
    {
        System.out.println("[Tri à bulles]");
        Instant start = Instant.now();
        boolean estTrie = false;
        while (!estTrie) 
        {
            // À chaque début de boucle, on va considérer que le tableau est trié
            estTrie = true;
            // On parcourt tous les couples du tableau
             for (int i = 1; i < tableau.length; i++) 
             {
                // Si le couple n'est pas trié, on permute les éléments
                if (tableau[i - 1] > tableau[i]) 
                {
                    int swap = tableau[i - 1];
                    tableau[i - 1] = tableau[i];
                    tableau[i] = swap;
                    // On notifie aussi que le tableau n'est pas trié pour reboucler
                    estTrie = false;
                }
            }
        }
        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        System.out.println("L’execution a pris " + duration + " ms"); 
        printTable(tableau);
    }
    
    public static void quickSort(int[] tableau)
    {
        System.out.println("[Quick sort]");
        Instant start = Instant.now();
        
        quicksort(tableau, 0, tableau.length-1);

        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        System.out.println("L’execution a pris " + duration + " ms"); 
        printTable(tableau);
    }
    
    public static void quicksort(int[] tableau, int indiceGauche, int indiceDroit) {
        if (indiceGauche < indiceDroit) {
            int indicePartition = partition(tableau, indiceGauche, indiceDroit);
            quicksort(tableau, indiceGauche, indicePartition);
            quicksort(tableau, indicePartition + 1, indiceDroit);
        }
    }

    public static int partition(int[] tableau, int indiceGauche, int indiceDroit) 
    {
        int elementPivot = tableau[indiceGauche + (indiceDroit - indiceGauche) / 2];
        int left = indiceGauche - 1;
        int right = indiceDroit + 1;
        while (true) 
        {
            do 
            {
                left++;
            } while (tableau[left] < elementPivot);
            do 
            {
                right--;
            } while (tableau[right] > elementPivot);
            if (left >= right) 
            {
                return right;
            }
            int tmp = tableau[left];
            tableau[left] = tableau[right];
            tableau[right] = tmp;
        }
    }

    public static void triJava(int[] tableau)
    {
        System.out.println("[Array.sort]");
        Instant start = Instant.now();
        Arrays.sort(tableau);
        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        System.out.println("L’execution a pris " + duration + " ms"); 
        printTable(tableau);

    }

    public static void printTable(int[] table)
    {
        if (Main.printvar)
        {
            System.out.print("[");
            for(int i=0;i<table.length;i++)
            {
                System.out.print(table[i]);
                if (i != table.length-1)
                    System.out.print(",");
            }
            System.out.println("]");
        }
    }

}
