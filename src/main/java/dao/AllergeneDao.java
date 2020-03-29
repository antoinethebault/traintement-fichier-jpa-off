package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entites.Additif;
import entites.Allergene;

public class AllergeneDao extends DAO<Allergene>{

	public AllergeneDao(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void create(Allergene obj) {
		EntityTransaction tx1 = entityManager.getTransaction();
		tx1.begin();
		entityManager.persist(obj);
		tx1.commit();
		
	}
	
	public Allergene find (String name) {
		TypedQuery<Allergene> query = entityManager.createQuery("SELECT a FROM Allergene a WHERE a.nom=:nom", Allergene.class);
		query.setParameter("nom", name);
		if(query.getResultList().isEmpty())
			return null;
		else 
			return query.getResultList().get(0);
	}

}
