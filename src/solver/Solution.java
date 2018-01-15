package solver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Représentation d'une solution au problème de bin packing
 */
public class Solution implements Comparable<Solution> {
	
	/**
	 * Liste des cartons trouvés, avec les objets à l'intérieur
	 */
	public List<Bin> bins;
	
	public Solution(List<Bin> bins) {
		this.bins = bins;
	}
	
	public Solution() {
		this(new ArrayList<Bin>());
	}
	
	public Solution(Solution sol) {
		this.bins = new ArrayList<>();
		for(Bin b : sol.bins) {
			this.bins.add(new Bin(b));
		}
	}
	
	/**
	 * Ajoute un carton à la solution
	 * @param Carton à ajouter
	 */
	public void addBin(Bin bin) {
		this.bins.add(bin);
	}
	
	/**
	 * @return Le nombre de cartons utilisés par cette solution
	 */
	public int getValue() {
		return this.bins.size();
	}
	
	@Override
	public String toString() {
		return bins.stream().map(Bin::toString).collect(Collectors.joining("/"));
	}

	@Override
	public int compareTo(Solution other) {
		return Integer.compare(this.getValue(), other.getValue());
	}

}
