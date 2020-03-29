package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entites.Produit;

public class ProduitDao extends DAO<Produit> {

	public ProduitDao(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void create(Produit obj) {
		EntityTransaction tx1 = entityManager.getTransaction();
		TypedQuery<Produit> query = entityManager.createQuery("SELECT p FROM Produit p WHERE p.nom=:nom", Produit.class);
		query.setParameter("nom", obj.getNom());
		if (query.getResultList().isEmpty()) {
			tx1.begin();
			entityManager.persist(obj);
			tx1.commit();
		}
		
	}

}
