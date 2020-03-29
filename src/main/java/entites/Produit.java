package entites;

import java.util.List;
import javax.persistence.*;
import java.util.ArrayList;

/**
 * @author antoinethebault
 *Produit
 */
@Entity
@Table(name="produit")
public class Produit extends SuperClassEntites{
	
	/**marque : Marque*/
	@ManyToOne 
	@JoinColumn(name="id_marque") 
	private Marque marque;
	
	/**categorie : Categorie*/
	@ManyToOne 
	@JoinColumn(name="id_categorie") 
	private Categorie categorie;
	
	/**ingredients : List<Ingredient>*/
	@ManyToMany 
	@JoinTable(name="ing_prdt",
			joinColumns= @JoinColumn(name="id_prdt", referencedColumnName="id"), 
			inverseJoinColumns= @JoinColumn(name="id_ing", referencedColumnName="id")
			)
	private List<Ingredient> ingredients;
	
	/**allergenes : List<Allergene>*/
	@ManyToMany 
	@JoinTable(name="all_prdt",
			joinColumns= @JoinColumn(name="id_prdt", referencedColumnName="id"), 
			inverseJoinColumns= @JoinColumn(name="id_all", referencedColumnName="id")
			)
	private List<Allergene> allergenes;
	
	/**additifs : List<Additif>*/
	@ManyToMany 
	@JoinTable(name="add_prdt",
			joinColumns= @JoinColumn(name="id_prdt", referencedColumnName="id"), 
			inverseJoinColumns= @JoinColumn(name="id_add", referencedColumnName="id")
			)
	private List<Additif> additifs;

	/**Constructor
	 * 
	 */
	public Produit() {
		super();
		ingredients = new ArrayList<>();
		allergenes = new ArrayList<>();
		additifs = new ArrayList<>();
		
	}

	/**Constructor
	 * @param name
	 * @param marque
	 * @param categorie
	 */
	public Produit(String nom, Marque marque, Categorie categorie) {
		super(nom);
		ingredients = new ArrayList<>();
		allergenes = new ArrayList<>();
		additifs = new ArrayList<>();
		this.marque = marque;
		this.categorie = categorie;
	}

	/**Getter
	 * @return the marque
	 */
	public Marque getMarque() {
		return marque;
	}

	/**Getter
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**Getter
	 * @return the ingredients
	 */
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	
	/**Getter
	 * @return the allergenes
	 */
	public List<Allergene> getAllergenes() {
		return allergenes;
	}

	/**Getter
	 * @return the additifs
	 */
	public List<Additif> getAdditifs() {
		return additifs;
	}

	/**Setter
	 * @param marque the marque to set
	 */
	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	/**Setter
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**Setter
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	
	
	/**Setter
	 * @param allergenes the allergenes to set
	 */
	public void setAllergenes(List<Allergene> allergenes) {
		this.allergenes = allergenes;
	}
	
	/**Setter
	 * @param additifs the additifs to set
	 */
	public void setAdditifs(List<Additif> additifs) {
		this.additifs = additifs;
	}

	/**
	 * addIngredient
	 * @param ingredient
	 */
	public void addIngredient(Ingredient ingredient) {
		ingredients.add(ingredient);
	}
	
	/**
	 * addAllergne
	 * @param allergene
	 */
	public void addAllergne(Allergene allergene) {
		allergenes.add(allergene);
	}
	
	public void addAdditif(Additif additif) {
		additifs.add(additif);
	}
	
}
