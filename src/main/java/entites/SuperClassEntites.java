package entites;

import javax.persistence.*;

@MappedSuperclass
public abstract class SuperClassEntites {
	/**id : Integer*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**name : String*/
	private String nom;

	/**Constructor
	 * 
	 */
	public SuperClassEntites() {
		super();
	}
	
	/**Constructor
	 * @param nom
	 */
	public SuperClassEntites(String nom) {
		super();
		this.nom = nom;
	}

	/**Getter
	 * @return the name
	 */
	public String getNom() {
		return nom;
	}

	/**Setter
	 * @param name the name to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
