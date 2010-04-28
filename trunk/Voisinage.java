import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector; 
import java.util.Hashtable;


   
 
public class Voisinage
{		
	private Solution pointDepart; 
	private Vector<Solution> listeVoisins;
	private Vector<Entrepot> listeRestants; 
	
	private int coutMinimum;
	private int nbEntrOuverts;
	private int nbEntrRestants;		
	
	
	/*
	 *.On va trouver les voisins à partir d'une solution déjà trouvée
	 */
	public Voisinage( Solution sol)
	{
		pointDepart = sol;		
		listeRestants = sol.restants();
		coutMinimum = sol.cout();
		
		nbEntrOuverts= sol.nbEntrepots();
		nbEntrRestants = RP_exercice._nbEntrepots - nbEntrOuverts;
		
		
		listeVoisins = new Vector(nbEntrRestants*nbEntrOuverts);
				
	}
		
	/*
	 *.La liste donné est valide
	 */
	public boolean isValid()
	{
		
		return 	(nbEntrOuverts+listeRestants.size()) == RP_exercice._nbEntrepots;
	}
	
	/*
	 *.Exécution de l'algo
	 */	
	public Solution Executer() 
	{				
		System.out.println("Start Voisinage:");
		
		Solution pointMinimal = pointDepart;
		
		if (!this.isValid()) {
				System.out.println("Error lors de l'execution du voisinage.");
				return pointMinimal;	
		}							    
		
									
		for (int i=0; i< nbEntrOuverts ; i++)
		for (int j=0; j< nbEntrRestants ; j++)		
		{			
			Solution solVoisin = echange(i,j);										
									
			int cout =solVoisin.cout(); 
						
			if (coutMinimum >  cout ){			
				pointMinimal = solVoisin;
				coutMinimum = cout;				
			}
			
			listeVoisins.add(solVoisin);
						
		}		
				
		return pointMinimal;
				
	}
	
	/*
	 *.Donne le nombre des voisins trouvés.
	 */
	public int nbVoisins()
	{
		return listeVoisins.size();
	}
	
	/*
	 *.Méthode pour fermer un entrepôt ouvert et ouvrir un entrepôt fermé.
	 */
	private Solution echange(int pi, int pj)
	{		
		Solution solClonee = pointDepart.clone();
		
		Entrepot sortant  =  (Entrepot)pointDepart.values().get(pi);
		solClonee.supr(sortant);
		
		Entrepot entrant = listeRestants.get(pj);				
		solClonee.add(entrant);
		
		return solClonee;
	}		
		
        
}
