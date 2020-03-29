package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entites.Categorie;

public class CategorieDao extends DAO<Categorie> {

	public CategorieDao(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void create(Categorie obj) {
		EntityTransaction tx1 = entityManager.getTransaction();
		tx1.begin();
		entityManager.persist(obj);
		tx1.commit();
		
	}
	
	public Categorie find (String name) {
		TypedQuery<Categorie> query = entityManager.createQuery("SELECT c FROM Categorie c WHERE c.nom=:nom", Categorie.class);
		query.setParameter("nom", name);
		if(query.getResultList().isEmpty())
			return null;
		else 
			return query.getResultList().get(0);
	}
	
}
