
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector; 
/**
 * -----------------------------------------------------------------------------
 * Class pour   
 *
 * @version 1.0
 * @responsable   Alexandro
 *@responsable   Evarille
 *@responsable   Anouchka
 * @description   Implémentation de l'algo 2
 * @Professor   Fertin Guillaume
 * -----------------------------------------------------------------------------
 */
 
 
public class Glouton {
         
    private static Solution ensSolutionFinale; // solution d'entrepôts
    private static Vector ensRestant; // entrepôts restants
    
    
    
    // préparer les couts non specifies avec une valeur M = Max+1
    private static void preparer() 
    {
    	ensSolutionFinale= new Solution();
    	ensRestant = new Vector();
    	
    	int grandM = RP_exercice.maxValeur; // mettre une valuer superieur a toutes, un grand M RP_exercice.maxValeur
    	int n= RP_exercice._nbEntrepots;		//quantité d'entrepôts ou des centrales.
    	
    	for (int i=0; i<n; i++)
    	{
    		Entrepot neufEntrepot = new Entrepot();
    		
    		//identificateur de chaque entrepôt est egal a indice dans la matrice couts.
    		neufEntrepot.id = i;				
    		
    		
    		for (int j=0; j<n; j++)	
    		{    									
    			if (RP_exercice._matrCouts[i][j] < 0)	
				{						
    				// on met le grand M a ceux qui n'apparaient pas dans le fichier txt.
    				neufEntrepot.addCentr(j,grandM);
				}
    			else 
    			//if (RP_exercice._matrCouts[i][j] > 0 || RP_exercice._prendZeros)	
				{ 	
					// il faut savoir si on prendra les couts = 0 autant que vrais couts.				    							
    				neufEntrepot.addCentr(j,RP_exercice._matrCouts[i][j]);
				}				
				
				
    		}
    		ensRestant.add(neufEntrepot);
    	}
    }    	
    
    public static void Montrer()
    {
     	ensSolutionFinale.montrer();
    }
    
    
    public  static void Executer()  
    {
    	System.out.println("Start Glouton:");
    	    	    	    	    	
    	preparer();    	
    	
    	/*
    	 *Calcule de la solution initiale.
    	 */    	 		    
    	ensSolutionFinale.add((Entrepot)ensRestant.get(1));
    	ensRestant.remove(1);	    	
    	       	   	   
		 boolean coutDescend = true;    	
    	/*
    	 *Continuer la recherche jusqu'a ne plus avoir d'entrepôts restants.ensRestant.size()>0
    	 */    	 
	    while(coutDescend)
	    {
	    	
	    	int min=RP_exercice.maxValeur;		    
	    	
	    	/*
	    	 * Il contiendra l'entrepôt idéal et les respectifs indices de chaque central à ouvrir, 
	    	 * aussi le coefficient ?????
	    	 */
		    Coefficient minCoeff = null;
		    
		    int indice = -1;
		    
		    
		    //System.out.println("Min: "+min);
		    /*
	    	 *Parcours de tous les entrepôts restants.
	    	 */
		    for(int i=0; i<ensRestant.size(); i++)
		    {
	    		Entrepot neuf = (Entrepot)ensRestant.get(i);	    		
	    		Coefficient coeff = ensSolutionFinale.comparer(neuf);    		
	    		
	    		//System.out.println("Coeff: "+coeff.getValeur+" id="+coeff.entrepot.id);
  		    	/*
		    	 *Comparer le meilleur coefficient de tous les entrepôts à mettre.
		    	 */
	    		if (min > coeff.getValeur )	    		
	    		{
	    			min= coeff.getValeur;
	    			minCoeff = coeff;
	    			indice = i;
	    			if (min >0) coutDescend = false;
	    		}    		
		    }			
		    
		    /*
	    	 *La recherche a été productive, on a trouvé donc le meilleur entrepôt à insérer.
	    	 */	    	
		    if (minCoeff !=  null && coutDescend)
		    {		    	
		    	//System.out.println("<Add: Id="+minCoeff.entrepot.id);
		    	/*
		    	 * Joindre l'entrepôt obtenu à partir du meilleur coefficient .
		    	 */
		    	ensSolutionFinale.add(minCoeff);		    			    	
		    	
		    	
		    	/*
		    	 * Enlever l'entrepôt seléctioné de l'ensemble d'entrepôt restants.
		    	 */
		    	ensRestant.remove(indice);	    	
		    }		    		
	    }
	    
	    	    	        		
    }   
 
}


