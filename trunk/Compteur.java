import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector; 
import java.util.Hashtable;

 
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
