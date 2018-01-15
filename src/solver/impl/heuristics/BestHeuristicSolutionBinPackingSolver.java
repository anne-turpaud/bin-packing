package solver.impl.heuristics;

import java.util.*;

import solver.BinPackingSolverBuilder;
import solver.IBinPackingSolver;
import solver.Solution;
import solver.SolverStrategy;

/**
 * Solveur qui a pour stratégie de calculer toutes les heuristiques et de récupérer le 
 * meilleur résultat.
 */
public class BestHeuristicSolutionBinPackingSolver implements IBinPackingSolver {

	@Override
	public Solution solve(List<Integer> entryData) {
		List<Solution> heuristicSolutions = new ArrayList<>();
		heuristicSolutions.add(BinPackingSolverBuilder.getSolver(SolverStrategy.BestFit).solve(entryData));
		heuristicSolutions.add(BinPackingSolverBuilder.getSolver(SolverStrategy.FirstFit).solve(entryData));
		heuristicSolutions.add(BinPackingSolverBuilder.getSolver(SolverStrategy.FirstFitDecreasing).solve(entryData));
		heuristicSolutions.add(BinPackingSolverBuilder.getSolver(SolverStrategy.MaxRest).solve(entryData));
		heuristicSolutions.add(BinPackingSolverBuilder.getSolver(SolverStrategy.NextFit).solve(entryData));
		heuristicSolutions.add(BinPackingSolverBuilder.getSolver(SolverStrategy.NextFitDecreasing).solve(entryData));
		
		return Collections.min(heuristicSolutions);
		
	}

}
