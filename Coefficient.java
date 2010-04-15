
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
 * @description   Impl�mentation de l'algo 2
 * @Professor   Fertin Guillaume
 * -----------------------------------------------------------------------------
 */
 
 

public class Coefficient{	
	
	public int getValeur; 			//valuer du coefficient calcul�
	public Vector indG;		// liste d'indices des centrales chang�es de la solution finale.
	public Vector indD;		// liste d'indices des centrales de l'entrep�t � joindre.
	public Entrepot entrepot; // l'entrep�t � joindre
	public int size;			// quantit� d'indices � �changer.
	
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