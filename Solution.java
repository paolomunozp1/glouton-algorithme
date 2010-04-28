
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector; 
import java.util.Hashtable;


 
 public class Solution
 {
 	private Hashtable vector; // il gardera toutes les  centrales de la solution courante
 	private int totalCout;
 	private int nombreEntrepots;
 	private String entrOuverts;
 	private Vector<Entrepot> vecEntrepot;
 	private Vector<Entrepot> vecRestants;
 	private boolean estEnEdition = true;
 	
 	public Solution(){ 			
 		vector = new Hashtable(); 
 	}
 	
 	
 	/*
	 *.Pour copier une solution donnée
	 */
 	public Solution(Solution originale){ 			
 	
 		this(); 
 		Vector<Entrepot> vecEntrepot = originale.values();						
 		
		for (int i=0; i<vecEntrepot.size(); i++) 		 			
 			this.add((Entrepot)vecEntrepot.get(i));
 		
 	}
 	
 	
 	/*
	 *.Ajouter un entrepôt à partir de son coefficient de gain
	 */
 	public void add(Coefficient input)
 	{ 		
 		estEnEdition = true;
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
	 *.Ajouter un entrepòt
	 */
 	public void add(Entrepot input)
 	{ 		
 		estEnEdition = true;
 		for (int i=0; i<input.size(); i++) // parcours de toutes les centrales enregistrées
 		{ 		 		
 			Centrale neuve = input.get(i);
 			if (!vector.containsKey(neuve.id))
			vector.put(neuve.id, neuve);
			else if ( ( (Centrale)vector.get(neuve.id) ).cout >  neuve.cout)
			vector.put(neuve.id, neuve);
			
 		} 	 		
 	}
 	
 	
 	
 	/*
	 *.Obtenir une centrale à partir de son indice
	 */
 	public Centrale get(int i) // il marche avec les id de la centrale
 	{ 		 			
 		return (Centrale)vector.get(i); 		
 	}
 	
 	
 	/*
	 *.Savoir si il existe une centrale avec l'indice i
	 */
 	public boolean has(int i)
 	{
 		return vector.containsKey(i);
 	}
 	
 	
 	/*
	 *.Comparer la solution existente, cad les valeurs dans cet objet par rappor à un entrepôt candidat coutDiff
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
	 *.Supprimer un entrepòt
	 */
 	public void supr(int i)
 	{
 		estEnEdition = true;
 		vector.remove(i);	
 	}
	
	
	
 	/*
	 *.suprimer un entrepòt
	 */	 
	public void supr(Entrepot inEntr)
	{
		estEnEdition = true;
		Object[] arrCentr = vector.keySet().toArray();
		for (int i=0; i< arrCentr.length; i++)
		{
			if (((Centrale)vector.get(arrCentr[i])).idParent == inEntr.id )
				supr(((Centrale)vector.get(arrCentr[i])).id);	
		}
		
	
	}
	
	
 	/*
	 *.déterminer la taille des centrales desservies.
	 */
	public int size()
	{
		return vector.size();
	}
		
	
	/*
	 *.Faire des précalculs avant de donner information sur nombre d'entrepôts 
	 * à ouvrir, la liste d'entrepôts à ouvrir, et la liste d'entrepôts fermés.
	 */
	private void faireAuTotal()
	{
		
		if (estEnEdition)		
			estEnEdition = false;		
		else 
			return;
			
		Object[] sortie = vector.values().toArray();
		vecEntrepot = new Vector();
		
		
		int somme = 0;
		String idx =";";
		int totalEntrepots = 0;
		for (int i=0; i<sortie.length; i++)
 		{ 			
 			Centrale centr = (Centrale)sortie[i];
 			if (idx.indexOf(";"+centr.idParent+";") == -1){ 				
 				idx +=centr.idParent+";";
 				totalEntrepots++;
 				Entrepot neuf = RP_exercice._listeComplete.get(centr.idParent);
 				vecEntrepot.add(neuf);
 			} 			
 			somme +=centr.cout;
 		}	
 		somme += totalEntrepots*RP_exercice._coutOuverture;
 		
 		totalCout = somme;
 		entrOuverts = idx;
 		nombreEntrepots = totalEntrepots;
 		
 		vecRestants = new Vector();
		
		Hashtable<Integer,Entrepot> listeComplete = RP_exercice._listeComplete;
		Object[] keysListeComplete = listeComplete.keySet().toArray();
				
		for (int i=0; i<listeComplete.size(); i++)
 		{ 			
 			Entrepot temp = (Entrepot)listeComplete.get(keysListeComplete[i]);
 			
 			if (idx.indexOf(";"+temp.id+";") == -1)
 				vecRestants.add(temp); 			
 		}	
 		
 		
	}
	
 	/*
	 * Montrer à l'utilisateur la solution.
	 */
	public void montrer()
	{
		faireAuTotal();
 		
 		
 		String[] idxPre = this.entrOuverts.split(";");
 		String sort = ";";
 		for (int i=0; i< idxPre.length; i++)
 		{
 			try
 			{
 				int idx = Integer.parseInt(idxPre[i])+1;
 				sort += idx+";";
 			}
 			catch(Exception ex){
 			}
 		}
 		System.out.println(" Entrepôts ouverts "+sort.substring(1));
 		System.out.println(" Nombre d'entrepôts:"+this.nombreEntrepots);
 		System.out.println(" Coût Total:"+this.totalCout);
	}
 	
 	/*
	 *.Donne le coût entrainé de cette solution
	 */
 	public int cout() 	 	
 	{ 		
 		faireAuTotal();
 		return totalCout;
 	}
 
 
 	/*
	 * Donne le nombre d'entrepôts à ouvrir selon cette solution.
	 */	 
 	public int nbEntrepots()
	{
		faireAuTotal();
		return nombreEntrepots;
	}
	
	
	
 	/*
	 *.Donne la liste d'entrepôts à ouvrir selon cette solution.
	 */
	public Vector values()
	{		
		faireAuTotal();
		return (Vector)vecEntrepot.clone();
	}
	
	
	
	/*
	 *.Donne la liste d'entrepôts fermés selon cette solution.
	 */
	public Vector restants()
	{	 		
		faireAuTotal();
		return (Vector)vecRestants.clone();
	}
	
 	/*
	 *.Donne une copie cette solution.
	 */
	public Solution clone()
	{
		faireAuTotal();
		Solution sortie = new Solution(this);
		return sortie;
	
	}
	
	/*
	 *.Donne la serie littéraire cette solution d'entrepôts à ouvrir.
	 */
	public String serie()
	{
		faireAuTotal();
		return entrOuverts;
	}
 }