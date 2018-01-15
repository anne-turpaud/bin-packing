package solver; 
import java.util.ArrayList;
import java.util.List;

/**
 * Représentation d'un carton
 */
public class Bin {
	
	/**
	 * Place maximum disponible (la même pour tous les cartons)
	 */
	public static final int MAX_SPACE_IN_BIN = 10;
	
	/**
	 * Liste des objets contenus dans le carton
	 */
	private List<Integer> objects;
	
	/**
	 * Place restante dans le carton
	 */
	private int remainingSpace;
	
	/**
	 * Place disponible dans le carton
	 */
	private int occupiedSpace;
	
	public Bin() { 
		this.objects = new ArrayList<Integer>();
		this.occupiedSpace = 0;
		this.remainingSpace = MAX_SPACE_IN_BIN;
	}
	
	public Bin(int object) { 
		this();
		this.addObject(object);
	}
	
	public Bin(Bin bin) {
		this.objects = new ArrayList<>(bin.objects);
		this.remainingSpace = bin.remainingSpace;
		this.occupiedSpace = bin.occupiedSpace;
	}
	
	public int getRemainingSpace() {
		return this.remainingSpace;
	}
	
	public int getOccupiedSpace() {
		return this.occupiedSpace;
	}
	
	public List<Integer> getBinContent() {
		return this.objects;
	}
	
	/**
	 * Ajoute un objet au carton
	 * @param Objet à ajouter au carton
	 */
	public void addObject(int object) {
		if(this.canFit(object)) {
			this.objects.add(object);
			this.occupiedSpace += object;
			this.remainingSpace -= object;
		}
		else {
			System.err.println("Le carton n'a pas suffisamment de place pour contenir cet objet");
		}
	}
	
	/**
	 * @return Vrai si le carton est plein, faux sinon
	 */
	public boolean isFull() { 
		return this.remainingSpace == 0;
	}
	
	/**
	 * @param L'objet à tester
	 * @return Vrai si l'objet peut rentrer dans le  carton, faux sinon
	 */
	public boolean canFit(int object) { 
		return this.remainingSpace >= object;
	}
	
	@Override
	public String toString() {
		String res = "";
		for(int object: this.objects) {
			res = res.concat(String.valueOf(object));
		}
		return res;
	}

}
