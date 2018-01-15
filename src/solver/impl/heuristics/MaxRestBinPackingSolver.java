package solver.impl.heuristics;

import java.util.ArrayList;
import java.util.List;

import solver.Bin;
import solver.IBinPackingSolver;
import solver.Solution;

/**
 * Implémentation de la stratégie dit "max rest". Pour chaque objet, on l'ajoute
 * au carton dans lequel il reste le plus d'espace. Si l'objet ne peut pas
 * rentrer dedans, on l'ajoute à un nouveau carton.
 * Complexité : O(n^2)
 */
public class MaxRestBinPackingSolver implements IBinPackingSolver {

	@Override
	public Solution solve(List<Integer> entryData) {
		List<Bin> boxes = new ArrayList<Bin>();

		if (entryData != null && entryData.size() > 0) {
			boxes.add(new Bin());

			for (int object : entryData) {
				Bin boxWithMostSpace = boxes.stream()
						.min((x, y) -> Integer.compare(x.getOccupiedSpace(), y.getOccupiedSpace())).get();
				if (boxWithMostSpace.canFit(object)) {
					boxWithMostSpace.addObject(object);
				} else {
					boxes.add(new Bin(object));
				}
			}
		}
		return new Solution(boxes);
	}

}
