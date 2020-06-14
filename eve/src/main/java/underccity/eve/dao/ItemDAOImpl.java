package underccity.eve.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import underccity.eve.entity.Item;

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

}
