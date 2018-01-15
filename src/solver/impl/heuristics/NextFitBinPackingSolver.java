package solver.impl.heuristics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import solver.Bin;
import solver.IBinPackingSolver;
import solver.Solution;

/**
 * Implémentation de la stratégie dit "next fit" : on affecte l'objet au carton courant s'il peut le
 * contenir. Dans le cas contraire, on place l'objet dans un nouveau carton, qu'on considère 
 * désormais comme étant le carton courant. Pour améliorer le résultat dans certains cas, il est possible de 
 * procéder dans un premier temps à un tri par ordre décroissant des données d'entrée.
 * Complexité : O(n) (en négligeant le tri initial qui est en O(n log(n)))
 */
public class NextFitBinPackingSolver implements IBinPackingSolver {
	
	private boolean sortBeforeSolving;
	
	public NextFitBinPackingSolver(boolean sortBeforeSolving) {
		this.sortBeforeSolving = sortBeforeSolving;
	}
	
	public NextFitBinPackingSolver() {
		this(false);
	}

	@Override
	public Solution solve(List<Integer> entryData) {
		List<Bin> boxes = new ArrayList<Bin>();
		
		if(entryData != null && entryData.size() > 0) {
			if(this.sortBeforeSolving) {
				Collections.sort(entryData, Collections.reverseOrder());	
			}
			boxes.add(new Bin());
			
			for(int object : entryData) { 
				if(boxes.get(boxes.size() - 1).canFit(object)) { 
					boxes.get(boxes.size() - 1).addObject(object);
				}
				else { 
					boxes.add(new Bin(object));
				}
			}			
		}
		return new Solution(boxes);
	}

}
