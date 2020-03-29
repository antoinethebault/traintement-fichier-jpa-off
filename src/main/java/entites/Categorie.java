package entites;

import javax.persistence.*;

/**
 * @author antoinethebault
 *Categorie
 */
@Entity
@Table(name="categorie")
public class Categorie extends SuperClassEntites{
	/**
	 * Constructor
	 */
	public Categorie() {
		super();
	}
	
	public Categorie (String nom) {
		super(nom);
	}
}
