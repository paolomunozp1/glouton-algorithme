
import java.util.*; 

import java.util.Vector; 
import java.util.Hashtable;
import java.util.TreeSet;

import java.util.Random;
import java.lang.Math;


 
 
 public class ListeRCL
 { 	
 	/*
	 *.En ordre décroisant.
	 */
 	private TreeSet listeCandidats= new TreeSet();
 	
 	/*
	 *.minimum valeur.
	 */
 	private float minVal=-1;
 	
 	/*
	 *.maximum valeur.
	 */
 	private float maxVal=-1;
 	
 	/*
	 *.La valeur limite calculée à partir du coefMin + alfa(coefMax-coefMin).
	 */
 	private int quotaValeur;
 	
 	
 	public static float alfa  = 0.333f;
 	
 	private boolean estPremier = false;
 	
 		
 	/*
	 *.Calcule les couts de livraison de chaque entrepôt.
	 */
 	public void calcule(Solution solTemporaire, Vector<Entrepot> input, int totalEntrepots)
 	{	 		
 		estPremier = totalEntrepots == 0;
 		
 		for (int i=0; i< input.size(); i++) 		
 		{ 			
 			Entrepot candidat = (Entrepot)input.get(i);
 			
 			Coefficient coeff = solTemporaire.comparer(candidat); 			 			
 			
 			candidat.gain(coeff);
 			
 			if (coeff.getValeur < 0 || estPremier)
 			listeCandidats.add(candidat); 	
 			
 			//solTemporaire.montrer();		 			 			
 		}
 		
 		if (listeCandidats.size() > 0 || estPremier)
 		{
 		 		
		maxVal=  ( (Entrepot)listeCandidats.first() ).gain().getValeur;
 		minVal=  ( (Entrepot)listeCandidats.last()  ).gain().getValeur;
 		
 		double quotaValeurExacte = (double)minVal + alfa*((double)maxVal-(double)minVal);
 		
 		quotaValeur = (int)Math.round (quotaValeurExacte);
 		
		}	 		 			
 	}
 	public boolean isValid()
 	{ 		
 		return minVal  < 0 && listeCandidats.size() > 0 || estPremier;
 	}
 	
 	public Entrepot next()
 	{
 		Entrepot restreint = new Entrepot();
 		Coefficient coeffRestr = new Coefficient();
 		coeffRestr.getValeur =  quotaValeur;
 		
 		restreint.gain(coeffRestr);
 		
 		TreeSet setRestreint = (TreeSet)listeCandidats.tailSet(restreint);
 		
 		
		Random rand = new Random();
		int indice = rand.nextInt(setRestreint.size());
				
		Object[] objects = setRestreint.toArray();												    
	    	
 		return (Entrepot)objects[indice];	
 	}
 	
 	public void supr(Entrepot elm)
 	{
 		listeCandidats.remove(elm);	
 	}
 	
 	
 	
 	
 	
 }