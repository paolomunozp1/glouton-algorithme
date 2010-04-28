
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector; 
import java.util.*;

 
public class Glouton {
         
    private Solution ensSolutionFinale; // solution d'centrepôts
    private Vector ensRestant; // entrepôts restants
        
    public Glouton(Entrepot depart)
    {    	
    	ensSolutionFinale= new Solution();
    	ensRestant = new Vector();
    		
		Hashtable<Integer,Entrepot> listeComplete = RP_exercice._listeComplete;
		Object[] keysListeComplete = listeComplete.keySet().toArray();
				
		for (int i=0; i<listeComplete.size(); i++)
 		{ 			
 			Entrepot temp = (Entrepot)listeComplete.get(keysListeComplete[i]); 			 			
 			ensRestant.add(temp); 			
 		} 	
 		
 		ensSolutionFinale.add(depart);	    	
 		ensRestant.remove(depart);
    	
    }
  
        
    public  Solution Executer()  
    {
    	System.out.println("Start Glouton:");
   	    	    	     	     	     	     	    	
    	   	   
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
	    		}   
	    		if (min >= 0) coutDescend = false;
	    		else coutDescend = true;
	    		
		    }			
		    
		    /*
	    	 *Si La recherche a été productive, on a trouvé donc le meilleur entrepôt à insérer.
	    	 * Sans oublier que il est interdit de dégrader la solution.
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
		    	ensRestant.remove(minCoeff.entrepot);	    	
		    }		    		
		    
		    
	    }
	    
	    return ensSolutionFinale.clone();
	    	    	    	     	    	    	    	        		
    }   
 
}


