package underccity.eve.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import underccity.eve.entity.Blueprint;

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
	public Blueprint findById(int theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Blueprint blueprint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		
	}

}
