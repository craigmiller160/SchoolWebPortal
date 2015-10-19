package io.craigmiller160.school.persist;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.craigmiller160.school.entity.Course;
import io.craigmiller160.school.entity.ScJoinHolder;
import io.craigmiller160.school.entity.Student;

@Component ("scJoinHolderDao")
public class HibernateScJoinHolderDao implements GenericJoinHolderDao<ScJoinHolder> {

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

	@SuppressWarnings("unchecked") //Hibernate list() method doesn't support generics
	@Override
	public List<ScJoinHolder> getAllEntities() {
		return sessionFactory.getCurrentSession()
				.createCriteria(ScJoinHolder.class)
				.list();
	}

	@SuppressWarnings("unchecked") //Hibernate list() method doesn't support generics
	@Override
	public <U> List<ScJoinHolder> getAllJoinsFor(Class<U> joinedEntityType, int entityId) {
		List<ScJoinHolder> resultList = null;
		if(joinedEntityType.equals(Student.class)){
			resultList = sessionFactory.getCurrentSession()
					.createCriteria(ScJoinHolder.class)
					.add(Restrictions.eq("student.studentId", entityId))
					.list();
		}
		else if(joinedEntityType.equals(Course.class)){
			resultList = sessionFactory.getCurrentSession()
					.createCriteria(ScJoinHolder.class)
					.add(Restrictions.eq("course.courseId", entityId))
					.list();
		}
		else{
			throw new IllegalArgumentException(joinedEntityType + " is not joined via this object");
		}
		
		return resultList;
	}

	@SuppressWarnings("unchecked") //Hibernate list() method doesn't support generics
	@Override
	public <U> List<ScJoinHolder> getPreviousJoinsFor(Class<U> joinedEntityType, int entityId, int firstId,
			int numRecords) {
		List<ScJoinHolder> resultList = null;
		if(joinedEntityType.equals(Student.class)){
			resultList = sessionFactory.getCurrentSession()
					.createCriteria(ScJoinHolder.class)
					.add(Restrictions.eqOrIsNull("student.studentId", entityId))
					.setFirstResult(firstId - numRecords)
					.setMaxResults(numRecords)
					.list();
		}
		else if(joinedEntityType.equals(Course.class)){
			resultList = sessionFactory.getCurrentSession()
					.createCriteria(ScJoinHolder.class)
					.add(Restrictions.eqOrIsNull("course.courseId", entityId))
					.setFirstResult(firstId - numRecords)
					.setMaxResults(numRecords)
					.list();
		}
		else{
			throw new IllegalArgumentException(joinedEntityType + " is not joined via this object");
		}
		
		return resultList;
	}

	@SuppressWarnings("unchecked") //Hibernate list() method doesn't support generics
	@Override
	public <U> List<ScJoinHolder> getNextJoinsFor(Class<U> joinedEntityType, int entityId, int lastId,
			int numRecords) {
		List<ScJoinHolder> resultList = null;
		if(joinedEntityType.equals(Student.class)){
			resultList = sessionFactory.getCurrentSession()
					.createCriteria(ScJoinHolder.class)
					.add(Restrictions.eqOrIsNull("student.studentId", entityId))
					.setFirstResult(lastId + 1)
					.setMaxResults(numRecords)
					.list();
		}
		else if(joinedEntityType.equals(Course.class)){
			resultList = sessionFactory.getCurrentSession()
					.createCriteria(ScJoinHolder.class)
					.add(Restrictions.eqOrIsNull("course.courseId", entityId))
					.setFirstResult(lastId + 1)
					.setMaxResults(numRecords)
					.list();
		}
		else{
			throw new IllegalArgumentException(joinedEntityType + " is not joined via this object");
		}
		
		return resultList;
	}

	@Override
	public <U> void removeJoinsFor(Class<U> joinedEntityType, int entityId) {
		if(joinedEntityType.equals(Student.class)){
			sessionFactory.getCurrentSession()
				.createQuery("delete from ScJoinHolder where studentId= :id")
				.setInteger("id", entityId)
				.executeUpdate();
		}
		else if(joinedEntityType.equals(Course.class)){
			sessionFactory.getCurrentSession()
				.createQuery("delete from ScJoinHolder where courseId= :id")
				.setInteger("id", entityId)
				.executeUpdate();
		}
		else{
			throw new IllegalArgumentException(joinedEntityType + " is not joined via this object");
		}
	}

	@Override
	public <U> long getJoinCountFor(Class<U> joinedEntityType, int entityId) {
		long result = 0;
		if(joinedEntityType.equals(Student.class)){
			result = (Long) sessionFactory.getCurrentSession()
						.createCriteria(ScJoinHolder.class)
						.add(Restrictions.eqOrIsNull("course.courseId", entityId))
						.setProjection(Projections.rowCount())
						.uniqueResult();
		}
		else if(joinedEntityType.equals(Course.class)){
			result = (Long) sessionFactory.getCurrentSession()
						.createCriteria(ScJoinHolder.class)
						.add(Restrictions.eqOrIsNull("student.studentId", entityId))
						.setProjection(Projections.rowCount())
						.uniqueResult();
		}
		else{
			throw new IllegalArgumentException(joinedEntityType + " is not joined via this object");
		}
		
		return result;
	}

}
