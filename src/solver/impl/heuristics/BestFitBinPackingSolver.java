package solver.impl.heuristics;

import java.util.ArrayList;
import java.util.List;

import solver.Bin;
import solver.IBinPackingSolver;
import solver.Solution;

/**
 * Implémentation de la stratégie dite "best fit" : l'objet est affecté au carton qu'il permet
 * de remplir le plus possible.
 * Complexité : O(n^2)
 */
public class BestFitBinPackingSolver implements IBinPackingSolver {

	@Override
	public Solution solve(List<Integer> entryData) {
		List<Bin> boxes = new ArrayList<Bin>();
		
		if(entryData != null && entryData.size() > 0) { 						
			boxes.add(new Bin());
			
			for(int object : entryData) {
				Bin bestBox = null;
				int bestRemainingSpace = 0;
				for(Bin box : boxes) {
					if(box.canFit(object)) {	
						if(bestBox == null || box.getRemainingSpace() < bestRemainingSpace) {
							bestBox = box;
							bestRemainingSpace = box.getRemainingSpace();
						}						
					}
				}
				if(bestBox != null) {
					bestBox.addObject(object);
				} else {
					boxes.add(new Bin(object));
				}
			}			
		}
		return new Solution(boxes);
	}

}
