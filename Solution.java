
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector; 
import java.util.Hashtable;

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
 
 public class Solution
 {
 	private Hashtable vector; // il gardera toutes les  centrales de la solution courante
 	public int totalCout;
 	
 	public Solution(){ 			
 		vector = new Hashtable(); 
 	}
 	
 	/*
	 *.
	 */
 	public void add(Coefficient input)
 	{ 		
 		Entrepot inputEntr = input.entrepot;	
 		for (int i=0; i<input.size(); i++) // parcours de toutes les changes enregistrés
 		{ 		 			
			int idxG = input.getG(i);
			int idxD = input.getD(i);
			Centrale neuve = inputEntr.get(idxD);
					
			if (idxG != -1)
			vector.remove(idxG);	// Alors, on ferme la centrale de la solution
			vector.put(neuve.id, neuve);
 		} 	 		
 	}
 	
 	
 	/*
	 *.
	 */
 	public void add(Entrepot input)
 	{ 		
 		
 		for (int i=0; i<input.size(); i++) // parcours de toutes les centrales enregistrées
 		{ 		 		
 			Centrale neuve = input.get(i);
 			//if (neuve.cout >0 || RP_exercice._prendZeros)
			vector.put(neuve.id, neuve);
 		} 	 		
 	}
 	
 	
 	
 	/*
	 *.
	 */
 	public Centrale get(int i) // il marche avec les id de la centrale
 	{ 		 			
 		return (Centrale)vector.get(i); 		
 	}
 	
 	
 	/*
	 *.
	 */
 	public boolean has(int i)
 	{
 		return vector.containsKey(i);
 	}
 	
 	
 	/*
	 *.
	 */
 	public Coefficient comparer(Entrepot coutDiff)// calcule du coefficient
 	{ 		
 		int sum = 0;
 		Coefficient coeff = new Coefficient();
 		coeff.getValeur = RP_exercice._coutOuverture;
 		coeff.entrepot = coutDiff;
 								
		for (int j=0; j<coutDiff.size(); j++) // on compare les couts de chaque
		{ 				
			int id = coutDiff.get(j).id;
			int cout = coutDiff.get(j).cout;
			
			/*
	    	 * Enlever l'entrepôt seléctioné de l'ensemble d'entrepôt restants.
	    	 */
			if ( has(id) )
			{
				Centrale centr = get(id); // pour chaque centrale de la solution
				
				//if (cout >0 || RP_exercice._prendZeros)
				if (cout< centr.cout)  // on va gagner une baisse de cout?
				{
					coeff.add(id,j);						
					sum+=centr.cout-cout;				 				
				} 	
			}
			/*
	    	 * Enlever l'entrepôt seléctioné de l'ensemble d'entrepôt restants.
	    	 */
			else
			{
				coeff.add(-1,j);						
				sum -=cout;
			}
			
		
		} 			
 		
 		coeff.getValeur =coeff.getValeur-sum;
 		return coeff; 	
 	}
 	
 	
 	/*
	 *.
	 */
 	public void supr(int i)
 	{
 		vector.remove(i);	
 	}
	
	
 	/*
	 *.
	 */
	public int size()
	{
		return vector.size();
	}
		
	
	
 	/*
	 *.
	 */
	public void montrer()
	{
		Object[] sortie = vector.values().toArray();
		int somme = 0;
		String idx ="";
		int totalEntrepots = 0;
		for (int i=0; i<sortie.length; i++)
 		{ 			
 			Centrale centr = (Centrale)sortie[i];
 			if (idx.indexOf(centr.idParent+";") == -1){ 				
 				idx +=centr.idParent+";";
 				totalEntrepots++;
 			}
 			
 			String str = ""; 			 			
 			str+="Centrale:"+centr.id+" Entrepot:"+centr.idParent+" cout="+centr.cout; 			 			 			
 			System.out.println(str);
 			somme +=centr.cout;
 		}	
 		somme += totalEntrepots*RP_exercice._coutOuverture;
 		
 		System.out.println("Centrales couvertes:"+sortie.length);
 		System.out.println("Cout Total:"+somme);
	}
 
 	
 }