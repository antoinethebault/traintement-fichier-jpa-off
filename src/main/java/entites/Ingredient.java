package entites;

import javax.persistence.*;

/**
 * @author antoinethebault
 *Ingredient
 */
@Entity
@Table(name="ingredient")
public class Ingredient extends SuperClassEntites{
	/**Constructor
	 * 
	 */
	public Ingredient() {
		super();
	}
	public Ingredient(String nom) {
		super(nom);
	}
}
