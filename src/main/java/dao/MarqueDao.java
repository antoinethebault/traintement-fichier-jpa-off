package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entites.Categorie;
import entites.Marque;

public class MarqueDao extends DAO<Marque> {

	public MarqueDao(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void create(Marque obj) {
		EntityTransaction tx1 = entityManager.getTransaction();
		tx1.begin();
		entityManager.persist(obj);
		tx1.commit();
	}
	
	public Marque find (String name) {
		TypedQuery<Marque> query = entityManager.createQuery("SELECT m FROM Marque m WHERE m.nom=:nom", Marque.class);
		query.setParameter("nom", name);
		if(query.getResultList().isEmpty())
			return null;
		else 
			return query.getResultList().get(0);
	}

}
