package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entites.Ingredient;

public class IngredientDao extends DAO<Ingredient>{

	public IngredientDao(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void create(Ingredient obj) {
		EntityTransaction tx1 = entityManager.getTransaction();
		tx1.begin();
		entityManager.persist(obj);
		tx1.commit();
		
	}

	/**
	 * find
	 * @param name
	 * @return
	 */
	public Ingredient find (String name) {
		TypedQuery<Ingredient> query = entityManager.createQuery("SELECT i FROM Ingredient i WHERE i.nom=:nom", Ingredient.class);
		query.setParameter("nom", name);
		if(query.getResultList().isEmpty())
			return null;
		else 
			return query.getResultList().get(0);
	}
	
}
