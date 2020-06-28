package underccity.eve.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import underccity.eve.entity.Blueprint;
import underccity.eve.entity.Components;
import underccity.eve.entity.ComponentsKey;

@Repository
public class BlueprintDAOImpl implements BlueprintDAO{

	private EntityManager entityManager;
	
	@Autowired
	public BlueprintDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<Blueprint> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Blueprint> theQuery =
				currentSession.createQuery("from Blueprint", Blueprint.class);
		
		List<Blueprint> blueprints = theQuery.getResultList();
		
		return blueprints;
	}

	@Override
	public Blueprint findById(Long theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.find(Blueprint.class, theId);
		
	}

	@Override
	public boolean save(Blueprint blueprint) {
		Session currentSession = entityManager.unwrap(Session.class);
		Transaction tx = null;
		try {
			tx = currentSession.beginTransaction();
			currentSession.saveOrUpdate(blueprint);
			if(blueprint.getComponents() != null && !blueprint.getComponents().isEmpty()) {
				for(Components component:blueprint.getComponents()) {
					ComponentsKey ck = new ComponentsKey();
					ck.setBlueprintId(blueprint.getId());
					ck.setItemId(component.getItem().getId());
					component.setId(ck);
					currentSession.merge(component);
				}
				
			}
			tx.commit();
		} catch (Exception e){
			if (tx != null) {
				tx.rollback();
				throw e;
			}
		} finally {
			currentSession.close();
		}
		
		
		return true;
		
		
	}

	@Override
	public void deleteById(Long theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Blueprint blueprint = findById(theId);
		if(blueprint == null) {
			throw new IllegalArgumentException("Can't find blueprint with id = " + theId);
		}
		Transaction tx = null;
		try {
			tx = currentSession.beginTransaction();
			List<Components> components= blueprint.getComponents();
			for(Components component:components) {
				currentSession.delete(component);
			}
			currentSession.delete(blueprint);
			tx.commit();
		} catch (Exception e){
			if (tx != null) {
				tx.rollback();
				throw e;
			}
		} finally {
			currentSession.close();
		}
		
		
	}

}
