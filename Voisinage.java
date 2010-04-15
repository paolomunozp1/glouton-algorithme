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
   
 
public class Voisinage
{
	private Solution _solution;	
		
	private int _i=1;
	private int _j=0;
	
	public Voisinage( Solution sol)
	{
		_solution = new Solution();
		_solution = sol;
		
	}
		
	public boolean isValid()
	{
		return 	_solution.size() == RP_exercice._nbEntrepots && _i < RP_exercice._nbEntrepots;
	}
	
	public Solution next()
	{		
		echange(_i,_j);
		
		if ( _j < _i) _j++;
		if ( _j == _i ) {_i++; _j=0;}
		
		
		return _solution;
	}
	
	private void echange(int pi, int pj)
	{		
				
		Centrale centrPi = _solution.get(pi);
		Centrale centrPj = _solution.get(pj);
		
		
		int piParentEntrepot = centrPi.idParent;
		int pjParentEntrepot = centrPj.idParent;
		
		int piCout = RP_exercice._matrCouts[piParentEntrepot][pi];
		int pjCout = RP_exercice._matrCouts[pjParentEntrepot][pj];
		
		if (piParentEntrepot != pjParentEntrepot)
		{
			centrPi.idParent = pjParentEntrepot;
			centrPi.cout = pjCout;
			
			centrPj.idParent = piParentEntrepot;
			centrPj.cout = piCout;
		}				
	}
		
        
}
