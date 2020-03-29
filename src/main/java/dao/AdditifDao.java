package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import entites.Additif;
import entites.Categorie;

public class AdditifDao extends DAO<Additif> {

	public AdditifDao(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void create(Additif obj) {
		EntityTransaction tx1 = entityManager.getTransaction();
		tx1.begin();
		entityManager.persist(obj);
		tx1.commit();
	}
	
	public Additif find (String name) {
		TypedQuery<Additif> query = entityManager.createQuery("SELECT a FROM Additif a WHERE a.nom=:nom", Additif.class);
		query.setParameter("nom", name);
		if(query.getResultList().isEmpty())
			return null;
		else 
			return query.getResultList().get(0);
	}

}
