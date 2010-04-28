
import java.util.*;

 import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Hashtable;


public class Controleur
{
	
	// préparer les couts non specifies avec une valeur M = 1000000
    public Vector init() 
    {
    	
    	Vector<Entrepot> ensRestant = new Vector();
    	RP_exercice._listeComplete = new Hashtable();
    	
    	int grandM = RP_exercice.maxValeur; 
    	int n= RP_exercice._nbEntrepots;		
    	
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
    				RP_exercice._matrCouts[i][j] = grandM;
				}
    			else 
    			//if (RP_exercice._matrCouts[i][j] > 0 || RP_exercice._prendZeros)	
				{ 	
					// il faut savoir si on prendra les couts = 0 autant que vrais couts.				    							
    				neufEntrepot.addCentr(j,RP_exercice._matrCouts[i][j]);    				
				}				
				
				
    		}
			
    		ensRestant.add(neufEntrepot);
			RP_exercice._listeComplete.put(neufEntrepot.id,neufEntrepot);
			
    	}
		
		return ensRestant;
		
    } 
    
    
    public Entrepot entrepotDepartRandom()
    {
    	
    	Random rand = new Random();
		int id = rand.nextInt(RP_exercice._listeComplete.size());
		    		    
    	return (Entrepot)RP_exercice._listeComplete.get(id);
    	
    }
    
    
    public Entrepot entrepotDepart(int indice)
    {
    			    		    
    	return (Entrepot)RP_exercice._listeComplete.get(indice);
    	
    }
    
    
    public Entrepot entrepotDepart()
    {
    		
    	Entrepot temp = (Entrepot)RP_exercice._listeComplete.get(0);	    		    
    	int coutMinimum = temp.cout();
    	
    	int indice = -1;
    	for(int i=1;i<RP_exercice._nbEntrepots; i++)
    	{
    		temp = (Entrepot)RP_exercice._listeComplete.get(i);
    		int cout = temp.cout();    		
    		if (cout < coutMinimum)
    		{
    			coutMinimum = cout;
    			indice = i;
    		}
    	}
    	
    	return (Entrepot)RP_exercice._listeComplete.get(indice);
    	
    }
    
}