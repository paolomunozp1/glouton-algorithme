
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
 
 

public class Entrepot{	
	public int id; 				//identificateur de l'entrepot
	private Vector centrales; // liste de centrales.
	
	public Entrepot() 
	{		
		centrales = new Vector(RP_exercice._nbEntrepots);	
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
	}
	
	public int size()
	{
		return centrales.size();
	}		
	
}