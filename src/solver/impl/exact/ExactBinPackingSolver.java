package solver.impl.exact;

import java.util.*;

import solver.Bin;
import solver.IBinPackingSolver;
import solver.Solution;

/**
 * Implémentation du solveur permettant d'obtenir une solution exacte (optimale)
 * Complexité : exponentielle
 */
public class ExactBinPackingSolver implements IBinPackingSolver {

	@Override
	public Solution solve(List<Integer> entryData) {
		List<Node> stack = new ArrayList<>();
		Node firstNode = new Node(new Solution(), new ArrayList<>(entryData));
		stack.add(firstNode);
		Node bestNode = null;
		int upperBound = Integer.MAX_VALUE;
		
		// On explore toutes les solutions possibles (en éliminant le plus tôt possible celles dont on peut
		// savoir d'avance que le résultat sera moins bon que le meilleur résultat qu'on a déjà trouvé) 
		while(!stack.isEmpty()) {
			Node nodeToExplore = stack.remove(stack.size() - 1);
			// Plus d'objet à ranger, la solution est donc complète et réalisable, si elle est 
			// meilleure que la meilleure solution déjà trouvée, on la garde
			if(nodeToExplore.remainingObjectsToPack.isEmpty()) {
				if(upperBound > nodeToExplore.solution.getValue()) {
					bestNode = nodeToExplore;
					upperBound = nodeToExplore.solution.getValue();
				}
			}
			// La solution est partielle, on doit la compléter et explorer à partir de là toutes
			// les combinaisons qu'elle peut donner
			else {
				int objectToAdd = nodeToExplore.remainingObjectsToPack.remove(0);
				// L'objet à ajouter peut être potentiellement placé dans tous les cartons où il
				// reste suffisamment de place
				for(int i = 0; i < nodeToExplore.solution.bins.size(); i++) {
					Bin box = nodeToExplore.solution.bins.get(i);
					if(box.canFit(objectToAdd)) {
						Node newNode = new Node(nodeToExplore);
						newNode.solution.bins.get(i).addObject(objectToAdd);
						if(newNode.getLowerBound() <= upperBound) {
							stack.add(newNode);
						}						
					}
				}
				// L'objet à ajouter peut aussi être placé dans un nouveau carton
				Node newNode = new Node(nodeToExplore);
				newNode.solution.addBin(new Bin(objectToAdd));
				if(newNode.getLowerBound() <= upperBound) {
					stack.add(newNode);
				}	
			}
		}
		return bestNode.solution;
	}

}
