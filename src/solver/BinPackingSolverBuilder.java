package solver;

import solver.impl.exact.ExactBinPackingSolver;
import solver.impl.heuristics.*;

/**
 * Classe de builder pour construire les solveurs
 */
public class BinPackingSolverBuilder {
	
	public static IBinPackingSolver getSolver(SolverStrategy strategy) {
		switch (strategy) {
			case FirstFit: return new FirstFitBinPackingSolver();
			case FirstFitDecreasing: return new FirstFitBinPackingSolver(true);
			case BestFit: return new BestFitBinPackingSolver();
			case MaxRest: return new MaxRestBinPackingSolver();
			case NextFit: return new NextFitBinPackingSolver();
			case NextFitDecreasing: return new NextFitBinPackingSolver(true);
			case BestHeuristic: return new BestHeuristicSolutionBinPackingSolver();
			case Exact: return new ExactBinPackingSolver();
			default: return new ExactBinPackingSolver();
		}
	}
	

}
