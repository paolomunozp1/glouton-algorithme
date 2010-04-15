
import java.util.*;

 import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * -----------------------------------------------------------------------------
 * Class pour   
 *
 * @version 1.0
 * @responsable   Alexandro 
 * @responsable   Keith  
 * @responsable   Anouchka
 * @description   Class Main d'exécution 
 * @Professor   
 * -----------------------------------------------------------------------------
 */
 
public class RP_exercice { 	

 	public static int maxValeur = 1000000; // mettre une valuer superieur a toutes, un grand M RP_exercice.maxValeur
 	public  static int _coutOuverture; 
    public static int _nbEntrepots; //quantité d'entrepôts ou des centrales.
    public static int[][] _matrCouts;
    public static boolean _prendZeros = false;
    
    private static void Begin()  
    {
    
        try  
        {
        
			
			
            System.out.println();
            System.out.println("Bienvenu                                                    ");
			System.out.println("------------------------------------------------------------");
			System.out.println("|               VEUILLEZ SAISIR:                           |");
			System.out.println("|               ----------------                           |");
			System.out.println("|   Nom du fichier.                                        |");
    		System.out.println("|                                                          |");
			System.out.println("------------------------------------------------------------");
    		System.out.println();
    	
    		System.out.print("OPTION: ");
			Scanner sc = new Scanner(System.in);		
						
			String option1=sc.nextLine();
			
			//option1 = "T1001.txt";
			
			FileReader inputFileReader   = new FileReader(option1);
            BufferedReader inputStream   = new BufferedReader(inputFileReader);
      		String line = inputStream.readLine();

			_nbEntrepots = Integer.parseInt(line.split(" ")[0]);
			_coutOuverture = Integer.parseInt(line.split(" ")[1]);
			
			boolean[][] solFinale = new  boolean[_nbEntrepots][_nbEntrepots];
			_matrCouts = new  int[_nbEntrepots][_nbEntrepots];
			
			for (int i=0; i<_nbEntrepots; i++)
	    	{	    		
	    		for (int j=0; j<_nbEntrepots; j++)	
	    		{    			
	    			_matrCouts[i][j] = -1;    			
	    		}	    	
	    	}
	    	
			while ((line = inputStream.readLine()) != null)
			{
							
				int indiceEntrepot = Integer.parseInt(line.split(" ")[0])-1;
				int indiceCentrale = Integer.parseInt(line.split(" ")[1])-1;
				int coutLivraison = Integer.parseInt(line.split(" ")[2]);
				_matrCouts[indiceEntrepot][indiceCentrale]=coutLivraison;
						
			};

			inputStream.close();

			
			/*
	    	 *On va lire la matrice des coûts obtenu à partir de la lecture du fichier.
	    	 */
			Glouton.preparer();
			
			/*
	    	 *On va déterminer une solution initiale à utiliser lors du démarrage de l'algo.
	    	 */
			Glouton.creerSolutionInit();
			
			
			/*
	    	 *On va exécuter l'algorithme de descente GLOUTON.
	    	 */
			Compteur.start();	    			    	
			Glouton.Executer();
			Compteur.stop();			  
			
			/*
	    	 *On va montrer la solution finale locale.
	    	 */ 									
			Glouton.Montrer();
			
			
			/*
	    	 *On va déterminer et montrer les voisins de la solution finale.
	    	 */ 
	    	Glouton.creerVoisinage();

          	System.out.println("Temps Glouton="+Compteur.value()+"ms");
        }  
        catch (Exception e)  
        {
            System.out.println("Erreur lors de l'éxécution du programme.");
            e.printStackTrace();
        }
        
    }      
	
    
    public static void main(String[] args)  
    {
        Begin();
    }
 
}


