package entites;

import javax.persistence.*;

/**
 * @author antoinethebault
 *Allergene
 */
@Entity
@Table(name="allergene")
public class Allergene extends SuperClassEntites{

	/**Constructor
	 * 
	 */
	public Allergene() {
		super();
	}
	
	public Allergene(String nom) {
		super(nom);
	}
}
