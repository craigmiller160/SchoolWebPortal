package io.craigmiller160.school.persist;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.craigmiller160.school.entity.ScJoinHolder;

@Component ("scJoinHolderDao")
public class HibernateScJoinHolderDao implements JoinHolderDao<ScJoinHolder> {

	private final SessionFactory sessionFactory;
	
	@Autowired (required=true)
	public HibernateScJoinHolderDao(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	@SuppressWarnings("unchecked") //Hibernate list() method doesn't support generics
	@Override
	public List<ScJoinHolder> getPreviousEntities(int firstId, int numRecords) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(ScJoinHolder.class)
				.setFirstResult(firstId - numRecords)
				.setMaxResults(numRecords)
				.list();
	}

	@SuppressWarnings("unchecked") //Hibernate list() method doesn't support generics
	@Override
	public List<ScJoinHolder> getNextEntities(int lastId, int numRecords) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(ScJoinHolder.class)
				.setFirstResult(lastId + 1)
				.setMaxResults(numRecords)
				.list();
	}

	@Override
	public void insertEntity(ScJoinHolder entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void updateEntity(ScJoinHolder entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void deleteEntity(ScJoinHolder entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public ScJoinHolder getEntityById(int entityId) {
		return (ScJoinHolder) sessionFactory.getCurrentSession()
				.get(ScJoinHolder.class, entityId);
	}

	@Override
	public long getEntityCount() {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.createCriteria(ScJoinHolder.class)
				.setProjection(Projections.rowCount())
				.uniqueResult();
	}

	@Override
	public List<ScJoinHolder> getAllEntities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> List<ScJoinHolder> getAllJoinsFor(Class<U> joinedEntityType, int entityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> List<ScJoinHolder> getPreviousJoinsFor(Class<U> joinedEntityType, int entityId, long firstId,
			int numRecords) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> List<ScJoinHolder> getNextJoinsFor(Class<U> joinedEntityType, int entityId, long lastId,
			int numRecords) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> List<ScJoinHolder> removeJoinsFor(Class<U> joinedEntityType, int entityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> long getJoinCountFor(Class<U> joinedEntityType, int entityId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
