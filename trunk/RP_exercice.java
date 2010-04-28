
import java.util.*;

 import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Hashtable;


 
public class RP_exercice { 	

 	public static int maxValeur = 1000000; // mettre une valuer superieur a toutes, un grand M RP_exercice.maxValeur
 	public  static int _coutOuverture; //co�t d'ouverture d'un entrep�t
    public static int _nbEntrepots; //quantit� d'entrep�ts ou des centrales.
    public static int[][] _matrCouts;    // matrice avec tous les co�ts de livraison.
    public static String nomFichier;	// nom du fichier � traiter.
    
    public static Hashtable<Integer,Entrepot> _listeComplete ; // liste d'entrep�ts, identifi�s par un id.
    public static Controleur controleur;	// class lectrice et cr�atrice des entrep�ts charg�s en m�moire.
    
    
    public static Solution solutionGlouton;
    public static Solution solutionGrasp;
    
    private static void Menu()
    {
    		System.out.println("------------------------------------------------------------");
			System.out.println("|      Veuillez choisir un algorithme � ex�cuter:          |");
			System.out.println("|      ------------------------------------------          |");
			System.out.println("|   1) Variante du Glouton.                                |");
			System.out.println("|   2) GRASP.                                              |");
			System.out.println("|   3) Calcul du Voisinage.                                |");
			System.out.println("|   4) Montrer optimum global.                             |");
			System.out.println("|   5) Quitter.                                            |");
    		System.out.println("|                                                          |");
			System.out.println("------------------------------------------------------------");
    		System.out.println();			
			System.out.print("OPTION: ");			
			Scanner sc = new Scanner(System.in);											
			int algoExec=sc.nextInt();
			
			switch(algoExec)
			{
				case 1: MenuGlouton();
				break;
				case 2: MenuGrasp();
				break;
				case 3: MenuVoisinage();
				break;
				case 4: MenuOptimumGlobal();
				break;
			}
    }
    private static void MenuBienvenue()
    {
    	try
    	{
    		    	
    	    System.out.println();
            System.out.println("Bienvenue                                                    ");
			System.out.println("------------------------------------------------------------");
			System.out.println("|               VEUILLEZ SAISIR:                           |");
			System.out.println("------------------------------------------------------------");
			System.out.print("Nom du fichier:");    	    		
			Scanner sc = new Scanner(System.in);								
			String option1=sc.nextLine();
			nomFichier = option1;
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
			
			
			controleur = new Controleur();
			
			/*
	    	 *On va lire la matrice des co�ts obtenu � partir de la lecture du fichier.
	    	 */
			controleur.init();
			System.out.println("D�marrage:");
			System.out.println("Nombre total d'entrep�ts charg�s en m�moire:"+_nbEntrepots);
			System.out.println("Co�t d'ouverture charg� en m�moire:"+_coutOuverture);
			System.out.println("Co�t de livraison vers une centrale manquante:"+maxValeur);
			System.out.println();
											
		}
    	catch(Exception ex)
    	{
    		System.out.println("Erreur lors de lecture du fichier.");
            ex.printStackTrace();
    	}
    	Menu();	
    }
    
    private static void MenuVoisinage()
    {
    	try
    	{
    		    	
    	    System.out.println();
            System.out.println("------------------------------------------------------------");
			System.out.println("|               Calculer le voisinage � partir de :        |");
			System.out.println("|               -----------------------------------        |");
			System.out.println("|   1) Solution obtenue de la Variente du Glouton.         |");
			System.out.println("|   2) Solution obtenue du GRASP.                          |");
    		System.out.println("|                                                          |");
			System.out.println("------------------------------------------------------------");
    		System.out.println();
    	
    		System.out.print("OPTION: ");
			Scanner sc = new Scanner(System.in);		
						
			int voisinageMethode=sc.nextInt();
			
										

			boolean arreter = false;
			int coutAvant = -1;
			
			Solution solDepart = (voisinageMethode==2)?solutionGrasp:solutionGlouton;
			
			if (solDepart == null) {
				System.out.println("Elle n'existe pas.Veuillez ex�cuter Variante du Glouton.");
				System.out.println();
				System.out.println();
				Menu();
				return;
			}
			
			System.out.println("Recherche du meilleur voisin dans le voisinage du Meilleur Voisin.");
////////////////////////////////////////////////////////////			
					
			for (int i=0; i<30 && !arreter; i++)			
			{
			
			System.out.println();
			System.out.println("it�ration:"+(i+1));
			/*
	    	 *On va d�terminer et montrer les voisins de la solution minimale locale.
	    	 */ 	    		    	 	    	 						
			Voisinage voisins = new Voisinage(solDepart);			
			
			/*
			*On va d�terminer les voisins de la solution finale.
			*/ 
			Compteur.start();	    			    	
			Solution meilleurVoisin = voisins.Executer();	
			Compteur.stop();			  
			System.out.println("Temps �coul�="+Compteur.value()+"ms");
			
			/*
			*On montrer combien des voisins on a calcul�.
			*/ 
			System.out.println(" "+voisins.nbVoisins()+"voisins ont �t� trouv�s.");    	     	
			
			/*
			*On va montrer les voisins de la solution finale.
			*/ 
			meilleurVoisin.montrer();																		
			
			
			solDepart = meilleurVoisin.clone();
			
			if (coutAvant == meilleurVoisin.cout()) arreter = true;
			else coutAvant = meilleurVoisin.cout();
			
			}
////////////////////////////////////////////////////////////			
						
		}
    	catch(Exception ex)
    	{
    		System.out.println("Erreur lors de l'ex�cution du Voisinage.");
            ex.printStackTrace();
    	}
    	
    	Menu();
    }
    
    private static void MenuGlouton()
    {
    	try
    	{
    		    	
    	    System.out.println();
            System.out.println("------------------------------------------------------------");
			System.out.println("|        Comment d�terminer l'entrep�t de d�part? :        |");
			System.out.println("|        ------------------------------------------        |");
			System.out.println("|   1) Al�atoirement.                                      |");
			System.out.println("|   2) Saisir le num�ro d'entrep�t.                           |");
			System.out.println("|   3) Par ordre d�croissante du co�t de livraison.        |");
    		System.out.println("|                                                          |");
			System.out.println("------------------------------------------------------------");
    		System.out.println();
    	
    		System.out.print("OPTION: ");
			Scanner sc = new Scanner(System.in);		
						
			int methodeDepart=sc.nextInt();
			
			/*
	    	 *On va d�terminer 1 entrep�t de d�part � utiliser lors du d�marrage de l'algo.
	    	 */
			Entrepot entrepotDepart;
			
			if (methodeDepart == 2){				
				System.out.print("Saisir le num�ro d'entrep�t [entre 1 et "+_nbEntrepots+"]: ");						
				int indiceEntrepot=sc.nextInt();
				entrepotDepart = controleur.entrepotDepart(indiceEntrepot-1);
				System.out.println();
			}
			else 
			if (methodeDepart == 1)
			{
				entrepotDepart = controleur.entrepotDepartRandom();
			}
			else
			{
				entrepotDepart = controleur.entrepotDepart();
			}
			
			
			
			/*
	    	 *On va ex�cuter l'algorithme variante du GLOUTON en calculant une minimum locale.
	    	 */
	    	Glouton calculeVarientGlouton = new Glouton(entrepotDepart);
	    	
			Compteur.start();	    			    	
			solutionGlouton = calculeVarientGlouton.Executer();
			Compteur.stop();			  
			System.out.println("Faite en:"+Compteur.value()+"ms");
			
			/*
	    	 *On va montrer la solution minimale locale.
	    	 */ 									
			solutionGlouton.montrer();
			
			
		}
    	catch(Exception ex)
    	{
    		System.out.println("Erreur lors de l'ex�cution du GLOUTON.");
            ex.printStackTrace();
    	}
    	
    	Menu();
    }
    
    private static void MenuGrasp()
    {
    	try
    	{
			Scanner sc = new Scanner(System.in);    		    	    	    
			System.out.print("Saisir la valeur alpha (exemple 0,33): ");			    		    	    		
			float alpha=sc.nextFloat();
						
			System.out.println();
			System.out.println();
			
          	Grasp calculeGrasp = new Grasp(alpha);
          	
			/*
			*On va rechercher une solution optimale utilisant GRASP.
			*/ 
			Compteur.start();	    			    	
			solutionGrasp = calculeGrasp.Executer();	
			Compteur.stop();			  
			System.out.println("Temps �coul�="+Compteur.value()+"ms");
						
			/*
			*On va montrer la solution finale selon GRASP.
			*/ 
			solutionGrasp.montrer();
			
		}
    	catch(Exception ex)
    	{
    		System.out.println("Erreur lors de l'ex�cution GRASP.");
            ex.printStackTrace();
    	}
    	Menu();
    }
    
    private static void MenuMultiStart()
    {
    	try
    	{
    		
    		Scanner sc = new Scanner(System.in);	    	
    	    System.out.println();
            System.out.println("------------------------------------------------------------");
			System.out.println("|               Multistart Configuration:                  |");
			System.out.println("|               -------------------------                  |");			
			System.out.println("|   1) Nombre des threads de recherche:");
			int nbSolutions=sc.nextInt();
			
			System.out.println("|   M�thode de recherche de l'optimale.                    |");			
			System.out.println("|   1) Pour tous les threads GRASP.                        |");			
			System.out.println("|   2) Pour tous les threads utiliser Voisinage.           |");						    		
    		System.out.print("OPTION: ");
			int methodeRecherche=sc.nextInt();
			
			if (methodeRecherche == 2)
			{
			
    		System.out.println("|   M�thode de calcule de la solution de d�part.           |");						
			System.out.println("|   1) Compl�tement Sym�trique.                            |");			
			System.out.println("|   2) Asym�trique vers les entrep�ts le plus cher.        |");			
			System.out.println("|   3) Asym�trique vers les entrep�ts le moins cher.       |");						
			System.out.print("OPTION: ");
			int methodeCalcule=sc.nextInt();
			switch(methodeCalcule)
			{
				case 1:
				break;
				case 2:
				break;
				case 3:
				break;
			}
			
    		}
					
			System.out.println();
			System.out.println();
			
			/*
			*On va cr�er la solution optimale propos�e par le professeur.
			*/ 
			System.out.println("solution selon le professeur");
          	Solution optimaleGlobale = new Solution();
          	int[] listeIndices = {1,11, 13, 23, 35, 51, 55, 56, 62, 68, 72, 82, 87, 92, 95};
          	for(int i=0; i< listeIndices.length; i++)
          	{
          		Entrepot neuf = (Entrepot)RP_exercice._listeComplete.get(listeIndices[i]-1);
          		optimaleGlobale.add(neuf);
          	}
          	optimaleGlobale.montrer();
						
			
			
			
		}
    	catch(Exception ex)
    	{
    		System.out.println("Erreur lors de lecture du fichier.");
            ex.printStackTrace();
    	}
    }
    
    private static void MenuOptimumGlobal()
    {
    		System.out.println();
			System.out.println();
			
			/*
			*On va cr�er la solution optimale propos�e par le professeur.
			*/ 
			System.out.println("solution selon le professeur");
          	Solution optimaleGlobale = new Solution();
          	int[] listeIndices = new int[0];
          	
          	int[] listeIndices1 = {1,11, 13, 23, 35, 51, 55, 56, 62, 68, 72, 82, 87, 92, 95};
          	int[] listeIndices2 = {7, 8, 9, 22, 32, 42, 58, 62, 67, 69, 71, 77, 83, 84, 100};
          	int[] listeIndices3 = {1, 2, 7, 8, 9};
          	int[] listeIndices4 = {1, 6, 7, 10};
          	
          	if (nomFichier.toUpperCase().equals("T1001.TXT")) listeIndices = listeIndices1;
          	if (nomFichier.toUpperCase().equals("T1002.TXT")) listeIndices = listeIndices2;
          	if (nomFichier.toUpperCase().equals("JOUET1.TXT")) listeIndices = listeIndices3;
          	if (nomFichier.toUpperCase().equals("JOUET2.TXT")) listeIndices = listeIndices4;
          	
          	for(int i=0; i< listeIndices.length; i++)
          	{
          		Entrepot neuf = (Entrepot)RP_exercice._listeComplete.get(listeIndices[i]-1);
          		optimaleGlobale.add(neuf);
          	}
          	optimaleGlobale.montrer();
          	
          	Menu();
    }
    
    public static void main(String[] args)  
    {
        MenuBienvenue();	
    }
 
 	
}


