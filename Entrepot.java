

import java.util.Vector; 
import java.util.*; 
import java.lang.*;


  

public class Entrepot implements Comparable {	

	public int id; 				//identificateur de l'entrepot
	private Vector centrales; // liste de centrales.
	private int coutLivraison;
	private Coefficient coefficientGain;
	
	public Entrepot() 
	{		
		centrales = new Vector(RP_exercice._nbEntrepots);	
		coutLivraison = 0;
		coefficientGain = new Coefficient();
	}
	
	public Centrale get(int i)
	{
		return (Centrale)centrales.get(i);
	}
	
	public void addCentr(int pId, int pCout )
	{
		Centrale neuve = new Centrale();
		neuve.id = pId;
		neuve.idParent = id ;
		neuve.cout = pCout;
		centrales.add(neuve);
		
		coutLivraison +=pCout;
		
	}
	
	public int size()
	{
		return centrales.size();
	}		
	
	public int cout()
	{
		if (coutLivraison == 0)
		{		
			int sortie = 0;
			for (int i=0;i < centrales.size(); i++)
			{
				sortie += ((Centrale)centrales.get(i)).cout;
			}
			coutLivraison = sortie;
		}
		
		return coutLivraison;
	}
	
	
	public Coefficient gain()
	{
		return coefficientGain;
	}
	
	public void gain(Coefficient input)
	{
		coefficientGain = input;
	}
	
	/*
	 *.En ordre décroisant.
	 */
	public int compareTo(Object ob2)
	{
		int ob1c = this.gain().getValeur;
		
		int ob2c = ((Entrepot)ob2).gain().getValeur;
		
		if (ob1c > ob2c)
			return -1;
		else if (ob1c < ob2c)
			return 1;
		else return 0;
		
	}
	
	public boolean equals(Object ob2)
	{
		int id1 = this.id;
		int id2 = ((Entrepot)ob2).id;
		
		return id1  == id2;
		
	}
}