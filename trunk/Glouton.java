
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector; 
import java.util.*;

 
public class Glouton {
         
    private Solution ensSolutionFinale; // solution d'centrep�ts
    private Vector ensRestant; // entrep�ts restants
        
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
    	 *Continuer la recherche jusqu'a ne plus avoir d'entrep�ts restants.ensRestant.size()>0
    	 */    	 
	    while(coutDescend)
	    {
	    	
	    	int min=RP_exercice.maxValeur;		    
	    	
	    	/*
	    	 * Il contiendra l'entrep�t id�al et les respectifs indices de chaque central � ouvrir, 
	    	 * aussi le coefficient ?????
	    	 */
		    Coefficient minCoeff = null;
		    
		    int indice = -1;
		    
		    
		    //System.out.println("Min: "+min);
		    /*
	    	 *Parcours de tous les entrep�ts restants.
	    	 */
		    for(int i=0; i<ensRestant.size(); i++)
		    {
	    		Entrepot neuf = (Entrepot)ensRestant.get(i);	    		
	    		Coefficient coeff = ensSolutionFinale.comparer(neuf);    		
	    		
	    		//System.out.println("Coeff: "+coeff.getValeur+" id="+coeff.entrepot.id);
  		    	/*
		    	 *Comparer le meilleur coefficient de tous les entrep�ts � mettre.
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
	    	 *Si La recherche a �t� productive, on a trouv� donc le meilleur entrep�t � ins�rer.
	    	 * Sans oublier que il est interdit de d�grader la solution.
	    	 */	    	
		    if (minCoeff !=  null && coutDescend)
		    {		    	
		    	//System.out.println("<Add: Id="+minCoeff.entrepot.id);
		    	/*
		    	 * Joindre l'entrep�t obtenu � partir du meilleur coefficient .
		    	 */
		    	ensSolutionFinale.add(minCoeff);		    			    	
		    	
		    	
		    	/*
		    	 * Enlever l'entrep�t sel�ction� de l'ensemble d'entrep�t restants.
		    	 */
		    	ensRestant.remove(minCoeff.entrepot);	    	
		    }		    		
		    
		    
	    }
	    
	    return ensSolutionFinale.clone();
	    	    	    	     	    	    	    	        		
    }   
 
}


