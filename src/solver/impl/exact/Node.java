package solver.impl.exact;

import java.util.ArrayList;
import java.util.List;

import solver.Bin;
import solver.Solution;

/**
 * Représentation d'un noeud. Un noeud est composé d'une solution (complète ou
 * partielle), et d'une liste des objets qui restent encore à attribuer.
 */
public class Node {

	public Solution solution;
	public List<Integer> remainingObjectsToPack;

	public Node(Solution solution, List<Integer> remainingObjectsToPack) {
		this.solution = solution;
		this.remainingObjectsToPack = remainingObjectsToPack;
	}

	public Node(Node node) {
		this.solution = new Solution(node.solution);
		this.remainingObjectsToPack = new ArrayList<>(node.remainingObjectsToPack);
	}

	/**
	 * Retourne le nombre minimal de cartons que nécessitera cette solution
	 * (utile pour pouvoir écarter le plus tôt possible les solutions qui seront 
	 * moins bonnes)
	 */
	public int getLowerBound() {
		int remainingSpace = 0;
		for (Bin bin : solution.bins) {
			remainingSpace += bin.getRemainingSpace();
		}
		int totalRemainingObjectsToPack = 0;
		for (int object : remainingObjectsToPack) {
			totalRemainingObjectsToPack += object;
		}
		return (int) Math.ceil((totalRemainingObjectsToPack - remainingSpace) /(double) Bin.MAX_SPACE_IN_BIN)
				+ solution.bins.size();
	}

}
