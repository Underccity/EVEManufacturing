package underccity.eve.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import underccity.eve.entity.Blueprint;
import underccity.eve.entity.Item;
import underccity.eve.exception.ExistReferencesItemException;

@Repository
public class ItemDAOImpl implements ItemDAO {

	private EntityManager entityManager;
	
	public ItemDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<Item> findByName(String name) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Item> theQuery =
				currentSession.createQuery("from Item i WHERE i.name LIKE ?1", Item.class);
		theQuery.setParameter(1, name + "%");
		List<Item> items = theQuery.getResultList();
		
		return items;
	}

	@Override
	public boolean upsert(Item item) {
		Session currentSession = entityManager.unwrap(Session.class);
		Transaction tx = currentSession.beginTransaction();
		currentSession.saveOrUpdate(item);
		tx.commit();
		return true;
	}

	@Override
	public List<Item> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Item> theQuery =
				currentSession.createQuery("from Item", Item.class);
		List<Item> items = theQuery.getResultList();
		
		return items;
	}

	@Override
	public Item findById(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.find(Item.class, id);
	}

	@Override
	public void deleteById(Long id) throws ExistReferencesItemException {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Item item = findById(id);
		if(item == null) {
			throw new IllegalArgumentException("Can't find item with id = " + id);
		}
		Transaction tx = null;
		try {
			tx = currentSession.beginTransaction();
			currentSession.delete(item);
			tx.commit();
		} catch (ConstraintViolationException | RollbackException ex ) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ExistReferencesItemException(item);
		} catch (Exception e){
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			currentSession.close();
		}
		
		
	}
	
	

}
