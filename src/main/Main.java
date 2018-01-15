package main;
import java.util.List;

import dataReader.FileDataReader;
import dataReader.IDataReader;
import solver.BinPackingSolverBuilder;
import solver.IBinPackingSolver;
import solver.Solution;
import solver.SolverStrategy;

public class Main {
	
	public static void main(String[] args) {
		
		IDataReader dataReader = new FileDataReader("entryData.txt");
		List<Integer> entryData = dataReader.readAll();
		IBinPackingSolver solver;
		// Le solveur exact ayant une complexité exponentielle, au-delà d'une certaine taille de données d'entrée
		// le temps de résolution devient trop important, donc on peut passer sur un algorithme heuristique.
		if(entryData.size() < 20) {
			solver = BinPackingSolverBuilder.getSolver(SolverStrategy.Exact);
		}
		else {
			solver = BinPackingSolverBuilder.getSolver(SolverStrategy.BestFit);
		}
		Solution solution = solver.solve(entryData);
		System.out.println(solution.toString());
	}

}
