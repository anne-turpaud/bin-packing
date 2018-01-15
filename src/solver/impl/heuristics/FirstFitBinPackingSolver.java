package solver.impl.heuristics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import solver.Bin;
import solver.IBinPackingSolver;
import solver.Solution;

/**
 * Implémentation de la stratégie dite "first fit" : l'objet est affecté au premier carton
 * qui a suffisamment d'espace. Pour améliorer le résultat dans certains cas, il est possible de 
 * procéder dans un premier temps à un tri par ordre décroissant des données d'entrée.
 * Complexité : O(n^2)
 */
public class FirstFitBinPackingSolver implements IBinPackingSolver {
	
	private boolean sortBeforeSolving;
	
	public FirstFitBinPackingSolver(boolean sortBeforeSolving) {
		this.sortBeforeSolving = sortBeforeSolving;
	}
	
	public FirstFitBinPackingSolver() {
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
				boolean objectWasPacked = false;
				for(Bin box : boxes) {
					if(box.canFit(object)) {
						box.addObject(object);
						objectWasPacked = true;
						break;
					}
				}
				if(!objectWasPacked) {
					boxes.add(new Bin(object));
				}
			}			
		}
		return new Solution(boxes);
	}

}
