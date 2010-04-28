
import java.util.Vector; 
import java.util.*;


public class Grasp {
         
    private Solution solutionCourante; // 
    private Vector<Entrepot> listeRestants; // 
    
        
    public Grasp(float alpha)
    {
    	solutionCourante = new Solution();						
    	
		listeRestants = new Vector();
		
		Hashtable<Integer,Entrepot> listeComplete = RP_exercice._listeComplete;
		Object[] keysListeComplete = listeComplete.keySet().toArray();
				
		for (int i=0; i<listeComplete.size(); i++)
 		{ 			
 			Entrepot temp = (Entrepot)listeComplete.get(keysListeComplete[i]); 			 			
 			listeRestants.add(temp); 			
 		}
 		
 		ListeRCL.alfa = alpha;
 		 		
    }    
    public  Solution Executer()  
    {
    	System.out.println("Start Grasp:");
   	    	   
   	    ListeRCL graspListe;
   	    boolean conditionFin = false;
   	    int totalEntrepots = 0;
   	    
   	   	while (!conditionFin)
   	   	{   
   	   		graspListe = new ListeRCL();
   	   			   		   	    
   	    	graspListe.calcule(solutionCourante,listeRestants,totalEntrepots);
   	   		
   	   		if (graspListe.isValid() ){
   	   		
	   	   		Entrepot entrMettre = graspListe.next();
	   	   		
	   	   		solutionCourante.add(entrMettre.gain());  	   		
	   	   		
	   	   		listeRestants.remove(entrMettre);
	   	   		
	   	   		totalEntrepots++;
	   	   		
   	   		}
   	   		   	   		
   	   		conditionFin = (listeRestants.size() == 0) ||  !graspListe.isValid() ;
   	   		   	   		   	   		
   	   	}
 		
 		return solutionCourante.clone();
 		    	    	        		
    }   
 
}


