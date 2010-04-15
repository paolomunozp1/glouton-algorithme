
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
 
 

public class Coefficient{	
	
	public int getValeur; 			//valuer du coefficient calculé
	public Vector indG;		// liste d'indices des centrales changées de la solution finale.
	public Vector indD;		// liste d'indices des centrales de l'entrepôt à joindre.
	public Entrepot entrepot; // l'entrepôt à joindre
	public int size;			// quantité d'indices à échanger.
	
	public Coefficient()
	{
		indG = new Vector();	
		indD = new Vector();	
		size = 0;
	}
	
	public int size()
	{
		return size;
	}
	
	
	public void add(int i, int j)
	{
		indG.add(i);
		indD.add(j);
		size++;
	}
	
	public int getD(int i)
	{
		return (Integer)indD.get(i);
	}
	
	
	public int getG(int i)
	{
		return (Integer)indG.get(i);
	}
}	