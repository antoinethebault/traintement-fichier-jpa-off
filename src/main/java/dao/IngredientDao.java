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
		TypedQuery<Ingredient> query = entityManager.createQuery("SELECT i FROM Ingredient i WHERE i.nom=:nom", Ingredient.class);
		query.setParameter("nom", obj.getNom());
		if (query.getResultList().isEmpty()) {
			tx1.begin();
			entityManager.persist(obj);
			tx1.commit();
		}
		
	}

}
