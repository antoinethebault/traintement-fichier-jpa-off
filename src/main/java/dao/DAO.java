package dao;

import javax.persistence.EntityManager;

/**
 * @author antoinethebault
 *DAO
 * @param <T>
 */
public abstract class DAO<T> {
	/**entityManager : EntityManager*/
	protected EntityManager entityManager;
	
	/**Constructor
	 * @param entityManager
	 */
	public DAO(EntityManager entityManager){
	    this.entityManager = entityManager;
	}
	
	public abstract void create(T obj);
}
