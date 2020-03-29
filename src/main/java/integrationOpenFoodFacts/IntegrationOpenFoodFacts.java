package integrationOpenFoodFacts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.io.FileUtils;

import dao.*;
import entites.*;

public class IntegrationOpenFoodFacts {
	
	private static final Logger LOGGER = Logger.getLogger(IntegrationOpenFoodFacts.class.getName());
	
	public static void main (String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu_open_food_facts");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//loadDataTest(entityManager);
		loadStock(entityManager);
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
	public static void loadDataTest(EntityManager entityManager) {
		AdditifDao additifDao = new AdditifDao(entityManager);
		ProduitDao produitDao = new ProduitDao(entityManager);
		CategorieDao categorieDao = new CategorieDao(entityManager);
		MarqueDao marqueDao = new MarqueDao(entityManager);
		
		Additif additif = new Additif("additif");
		additifDao.create(additif);
		additifDao.create(additif);
		
		Marque marque = new Marque("marque");
		marqueDao.create(marque);
		
		Categorie categorie = new Categorie("categorie");
		categorieDao.create(categorie);
		
		Produit produit = new Produit("produit", marque , categorie);
		produit.addAdditif(additif);
		produitDao.create(produit);
	}
	
	/**
	 * loadStock
	 * @return le stock de produits
	 */
	public static void loadStock(EntityManager entityManager) {
		AdditifDao additifDao = new AdditifDao(entityManager);
		ProduitDao produitDao = new ProduitDao(entityManager);
		CategorieDao categorieDao = new CategorieDao(entityManager);
		MarqueDao marqueDao = new MarqueDao(entityManager);
		AllergeneDao allergeneDao = new AllergeneDao(entityManager);
		IngredientDao ingredientDao = new IngredientDao(entityManager);
		
		String filePath = ClassLoader.getSystemClassLoader().getResource("open-food-facts.csv").getFile();
		File file = new File(filePath);
		if (!file.exists()) {
			System.out.println("Impossible de trouver le fichier");
		}
		try {
			List<String> lignes = FileUtils.readLines(file, "UTF-8");
			lignes.remove(0);
			int i=1;
			for (String ligne: lignes) {
				String[] tableau = ligne.split("\\|");
				
				String nom=tableau[2];
				
				Categorie categorie = categorieDao.find(tableau[0]);
				if (categorie == null){
					categorie = new Categorie(tableau[0]);
					categorieDao.create(categorie);
				}
					
				Marque marque = marqueDao.find(tableau[1]);
				if(marque == null) {
					marque = new Marque(tableau[1]);
					marqueDao.create(marque);
				}
				
				Produit produit = new Produit(nom, marque, categorie);
				
				ArrayList<Ingredient> ingredients = new ArrayList<>();
				tableau[4]=tableau[4].replaceAll("\\s", "");
				tableau[4]=tableau[4].replaceAll("\\[", "");
				tableau[4]=tableau[4].replaceAll("\\]", "");
				String separateur;
				if (tableau[4].contains("-"))
					separateur="-";
				else
					separateur=",";
				for (String chaine : tableau[4].split(separateur)) {
					if (chaine.length()<255) {
						Ingredient ingredient = ingredientDao.find(chaine);
						if (ingredient==null) {
							ingredient = new Ingredient(chaine.toLowerCase());
							ingredientDao.create(ingredient);
						}
						ingredients.add(ingredient);
					}
				}
				produit.setIngredients(ingredients);
				
				ArrayList<Allergene> allergenes = new ArrayList<>();
				tableau[28]=tableau[28].replaceAll("\\s", "");
				tableau[28]=tableau[28].replaceAll("\\[", "");
				tableau[28]=tableau[28].replaceAll("\\]", "");
				for (String chaine : tableau[28].split(",")) {
					Allergene allergene = allergeneDao.find(chaine);
					if (allergene==null) {
						allergene = new Allergene(chaine.toLowerCase());
						allergeneDao.create(allergene);
					}
					allergenes.add(allergene);
				}
				produit.setAllergenes(allergenes);
				
				ArrayList<Additif> additifs = new ArrayList<>();
				tableau[29]=tableau[29].replaceAll("\\s", "");
				tableau[29]=tableau[29].replaceAll("\\[", "");
				tableau[29]=tableau[29].replaceAll("\\]", "");
				for (String chaine : tableau[29].split(",")) {
					Additif additif = additifDao.find(chaine);
					if (additif==null) {
						additif = new Additif(chaine.toLowerCase());
						additifDao.create(additif);
					}
					additifs.add(additif);
				}
				produit.setAdditifs(additifs);
				
				produitDao.create(produit);
				i++;
				LOGGER.log(Level.INFO, "produit ligne "+i+" cree : "+produit.getNom());
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
