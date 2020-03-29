package entites;

import javax.persistence.*;

/**
 * @author antoinethebault
 *Marque
 */
@Entity
@Table(name="marque")
public class Marque extends SuperClassEntites{
	/**Constructor
	 * 
	 */
	public Marque() {
		super();
	}
	
	public Marque(String nom) {
		super(nom);
	}
}
