package solver;

import java.util.List;

/**
 * Interface pour une classe implémentant une solution au problème de bin packing.
 */
public interface IBinPackingSolver {
	
	/**
	 * Résoudre le problème de bin packing
	 * @param Données d'entrée (les objets à ranger)
	 * @return La solution du problème
	 */
	public Solution solve(List<Integer> entryData);

}
