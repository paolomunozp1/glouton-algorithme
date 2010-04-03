/**
 * -----------------------------------------------------------------------------
 * Class pour   
 *
 * @version 1.0
 * @responsable   Alexandro Ruiz 
 * @description   Class calcule d'un temps d'exécution défini
 * @Professor   Fertin Guillaume
 * -----------------------------------------------------------------------------
 */
 
 
public class Compteur
{
	private static long valeur;
	private static long valueInitial;
	private static long valueFinal;
		
	public static void start(){
		valueInitial = System.currentTimeMillis();
		
	}
	public static void stop(){
		valueFinal = System.currentTimeMillis();
		
	}
	public static long value(){		
		return 	(valueFinal-valueInitial);
	}
}
