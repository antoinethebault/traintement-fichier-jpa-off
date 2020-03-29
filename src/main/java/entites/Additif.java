package entites;

import javax.persistence.*;

/**
 * @author antoinethebault
 *Additif
 */
@Entity
@Table(name="additif")
public class Additif extends SuperClassEntites{

	/**Constructor
	 * 
	 */
	public Additif() {
		super();
	}
	
	/**Constructor
	 * @param nom
	 */
	public Additif(String nom) {
		super(nom);
	}
}
