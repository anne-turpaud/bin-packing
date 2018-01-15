package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import solver.Bin;
import solver.BinPackingSolverBuilder;
import solver.IBinPackingSolver;
import solver.Solution;
import solver.SolverStrategy;

/**
 * Tests unitaires des solveurs
 */
class SolverTest {

	protected static final List<Integer> entryData = new ArrayList<>(
			Arrays.asList(1, 6, 3, 8, 4, 1, 6, 8, 9, 5, 2, 5, 7, 7, 3));

	// TEST DU SOLVEUR QUI DONNE LA SOLUTION OPTIMALE
	
	@Test
	void testExactSolver() {
		IBinPackingSolver solver = BinPackingSolverBuilder.getSolver(SolverStrategy.Exact);
		Solution solution = solver.solve(entryData);
		// On vérifie que c'est bien la solution optimale qui est trouvée
		assertEquals(8, solution.getValue());
		
		// On vérifie que les contraintes du problème sont bien respectées
		this.checkConstraintsAreRespected(solution);
	}
	
	// TEST DES HEURISTIQUES
	// Pour certaines heuristiques, la borne supérieure du résultat est connue, donc on vérifie
	// que le résultat trouvé ne dépasse pas cette borne. Pour d'autres, aucune borne n'est connue.

	@Test
	void testBestFitSolver() {
		IBinPackingSolver solver = BinPackingSolverBuilder.getSolver(SolverStrategy.BestFit);
		Solution solution = solver.solve(entryData);
		
		this.checkConstraintsAreRespected(solution);
	}
	
	@Test
	void testFirstFitSolver() {
		IBinPackingSolver solver = BinPackingSolverBuilder.getSolver(SolverStrategy.FirstFit);
		Solution solution = solver.solve(entryData);
		assertTrue(solution.getValue() < 16);
		
		this.checkConstraintsAreRespected(solution);
	}
	
	@Test
	void testFirstFitDecreasingSolver() {
		IBinPackingSolver solver = BinPackingSolverBuilder.getSolver(SolverStrategy.FirstFitDecreasing);
		Solution solution = solver.solve(entryData);
		assertTrue(solution.getValue() < 14);
		
		this.checkConstraintsAreRespected(solution);
	}
	
	@Test
	void testNextFitSolver() {
		IBinPackingSolver solver = BinPackingSolverBuilder.getSolver(SolverStrategy.NextFit);
		Solution solution = solver.solve(entryData);
		assertTrue(solution.getValue() <= 16);
		
		this.checkConstraintsAreRespected(solution);
	}
	
	@Test
	void testNextFitDecreasingSolver() {
		IBinPackingSolver solver = BinPackingSolverBuilder.getSolver(SolverStrategy.NextFitDecreasing);
		Solution solution = solver.solve(entryData);
		
		this.checkConstraintsAreRespected(solution);
	}
	
	@Test
	void testMaxRestSolver() {
		IBinPackingSolver solver = BinPackingSolverBuilder.getSolver(SolverStrategy.MaxRest);
		Solution solution = solver.solve(entryData);
		
		this.checkConstraintsAreRespected(solution);
	}
	
	@Test
	void testBestHeuristicSolver() {
		IBinPackingSolver solver = BinPackingSolverBuilder.getSolver(SolverStrategy.BestHeuristic);
		Solution solution = solver.solve(entryData);
		
		this.checkConstraintsAreRespected(solution);
	}
	
	/**
	 * On vérifie que la solution respecte bien les règles du problème
	 */
	void checkConstraintsAreRespected(Solution solution) {
		// Aucun carton ne doit être trop rempli
		for(Bin bin: solution.bins) {
			assertTrue(bin.getOccupiedSpace() <= Bin.MAX_SPACE_IN_BIN);
		}		
		// Tous les objets qu'on a donnés en entrée doivent avoir été rangés
		Map<Integer,Integer> map = new HashMap<>();
		for(Integer i : entryData) {
			map.compute(i, (k, v) -> (v == null) ? 1 : v + 1);
		}
		for(Bin bin : solution.bins) {
			for(Integer i : bin.getBinContent()) {
				map.compute(i, (k, v) -> v - 1);
			}
		}

		for(Integer v : map.values()) {
			assertEquals(0, (int)v);
		}
	}

}
