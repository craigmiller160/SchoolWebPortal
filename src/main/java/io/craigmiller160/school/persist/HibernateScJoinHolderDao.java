package io.craigmiller160.school.persist;

import java.util.List;

import javax.annotation.PreDestroy;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.craigmiller160.school.entity.Course;
import io.craigmiller160.school.entity.ScJoinHolder;
import io.craigmiller160.school.entity.Student;

/**
 * A DAO class for using the <tt>Hibernate</tt> framework
 * to persist <tt>ScJoinHolder</tt> entities. This class depends
 * on a <tt>Hibernate SessionFactory</tt> to generate
 * the database sessions & connections. This class
 * does NOT manage its own transactions, it will
 * depend on a service layer class to handle that 
 * functionality.
 * <p>
 * <b>THREAD SAFETY:</b> This class is thread-safe.
 * It has no mutable state that could cause issues
 * with multiple threads.
 * 
 * @author craig
 * @version 1.0
 */
@Component ("scJoinHolderDao")
public class HibernateScJoinHolderDao 
implements GenericPaginatedDao<ScJoinHolder>, GenericPaginatedJoinHolderDao<ScJoinHolder> {

	//TODO make sure that no JoinHolders are persisted without
	//all properties not null
	
	/**
	 * The <tt>SessionFactory</tt> required for this DAO to function.
	 */
	private final SessionFactory sessionFactory;
	
	/**
	 * Create this DAO with the required <tt>SessionFactory</tt>.
	 * 
	 * @param sessionFactory the <tt>SessionFactory</tt> for this DAO.
	 */
	@Autowired (required=true)
	public HibernateScJoinHolderDao(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * Get the <tt>SessionFactory</tt> for this DAO.
	 * 
	 * @return the <tt>SessionFactory</tt> for this DAO.
	 */
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@SuppressWarnings("unchecked") //Hibernate list() method doesn't support generics
	@Override
	public List<ScJoinHolder> getPreviousEntities(int lastPageFirstRowNum, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(ScJoinHolder.class)
				.setFirstResult(lastPageFirstRowNum)
				.setMaxResults(pageSize)
				.list();
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@SuppressWarnings("unchecked") //Hibernate list() method doesn't support generics
	@Override
	public List<ScJoinHolder> getNextEntities(int lastPageLastRowNum, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(ScJoinHolder.class)
				.setFirstResult(lastPageLastRowNum + 1)
				.setMaxResults(pageSize)
				.list();
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public void insertEntity(ScJoinHolder entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void updateEntity(ScJoinHolder entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public void deleteEntity(ScJoinHolder entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public ScJoinHolder getEntityById(int entityId) {
		return (ScJoinHolder) sessionFactory.getCurrentSession()
				.get(ScJoinHolder.class, entityId);
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public long getEntityCount() {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.createCriteria(ScJoinHolder.class)
				.setProjection(Projections.rowCount())
				.uniqueResult();
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@SuppressWarnings("unchecked") //Hibernate list() method doesn't support generics
	@Override
	public List<ScJoinHolder> getAllEntities() {
		return sessionFactory.getCurrentSession()
				.createCriteria(ScJoinHolder.class)
				.list();
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
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

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@SuppressWarnings("unchecked") //Hibernate list() method doesn't support generics
	@Override
	public <U> List<ScJoinHolder> getPreviousJoinsFor(Class<U> joinedEntityType, int entityId, 
			int lastPageFirstRowNum, int pageSize) {
		List<ScJoinHolder> resultList = null;
		if(joinedEntityType.equals(Student.class)){
			resultList = sessionFactory.getCurrentSession()
					.createCriteria(ScJoinHolder.class)
					.createCriteria("student", "s")
					.add(Restrictions.eq("s.studentId", entityId))
					.setFirstResult(lastPageFirstRowNum - 1 - pageSize)
					.setMaxResults(pageSize)
					.list();
		}
		else if(joinedEntityType.equals(Course.class)){
			resultList = sessionFactory.getCurrentSession()
					.createCriteria(ScJoinHolder.class)
					.createCriteria("course", "c")
					.add(Restrictions.eqOrIsNull("c.courseId", entityId))
					.setFirstResult(lastPageFirstRowNum - pageSize)
					.setMaxResults(pageSize)
					.list();
		}
		else{
			throw new IllegalArgumentException(joinedEntityType + " is not joined via this object");
		}
		
		return resultList;
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@SuppressWarnings("unchecked") //Hibernate list() method doesn't support generics
	@Override
	public <U> List<ScJoinHolder> getNextJoinsFor(Class<U> joinedEntityType, int entityId, 
			int lastPageLastRowNum,	int pageSize) {
		List<ScJoinHolder> resultList = null;
		if(joinedEntityType.equals(Student.class)){
			resultList = sessionFactory.getCurrentSession()
					.createCriteria(ScJoinHolder.class)
					.createCriteria("student", "s")
					.add(Restrictions.eq("s.studentId", entityId))
					.setFirstResult(lastPageLastRowNum + 1)
					.setMaxResults(pageSize)
					.list();
		}
		else if(joinedEntityType.equals(Course.class)){
			resultList = sessionFactory.getCurrentSession()
					.createCriteria(ScJoinHolder.class)
					.createCriteria("course", "c")
					.add(Restrictions.eq("c.courseId", entityId))
					.setFirstResult(lastPageLastRowNum + 1)
					.setMaxResults(pageSize)
					.list();
		}
		else{
			throw new IllegalArgumentException(joinedEntityType + " is not joined via this object");
		}
		
		return resultList;
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public <U> void removeJoinsFor(Class<U> joinedEntityType, int entityId) {
		if(joinedEntityType.equals(Student.class)){
			sessionFactory.getCurrentSession()
				.createQuery("delete from " + ScJoinHolder.class.getName() + " where student.studentId= :id")
				.setInteger("id", entityId)
				.executeUpdate();
		}
		else if(joinedEntityType.equals(Course.class)){
			sessionFactory.getCurrentSession()
				.createQuery("delete from " + ScJoinHolder.class.getName() + " where course.courseId= :id")
				.setInteger("id", entityId)
				.executeUpdate();
		}
		else{
			throw new IllegalArgumentException(joinedEntityType + " is not joined via this object");
		}
	}

	/**
	 * {@inheritDoc}
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@Override
	public <U> long getJoinCountFor(Class<U> joinedEntityType, int entityId) {
		long result = 0;
		if(joinedEntityType.equals(Student.class)){
			result = (Long) sessionFactory.getCurrentSession()
						.createCriteria(ScJoinHolder.class)
						.createCriteria("student", "s")
						.add(Restrictions.eq("s.studentId", entityId))
						.setProjection(Projections.rowCount())
						.uniqueResult();
		}
		else if(joinedEntityType.equals(Course.class)){
			result = (Long) sessionFactory.getCurrentSession()
						.createCriteria(ScJoinHolder.class)
						.createCriteria("course", "c")
						.add(Restrictions.eq("c.courseId", entityId))
						.setProjection(Projections.rowCount())
						.uniqueResult();
		}
		else{
			throw new IllegalArgumentException(joinedEntityType + " is not joined via this object");
		}
		
		return result;
	}
	
	/**
	 * Close the <tt>SessionFactory</tt> when the program shuts down.
	 * 
	 * @throws HibernateException if the database operation fails.
	 * @throws NullPointerException if the <tt>SessionFactory</tt>
	 * was set to null.
	 */
	@PreDestroy
	public void closeSessionFactory(){
		sessionFactory.close();
	}

	//TODO restore this only if it's actually necessary
	/*@Override
	public <U, V> void removeJoinFor(Class<U> firstJoinedEntityType, int firstEntityId, Class<V> secondJoinedEntityType,
			int secondEntityId) {
		//TODO if duplicate joinHolders exist in the table, this
		//will end up throwing a HibernateException
		if(firstJoinedEntityType.equals(Student.class) 
				&& secondJoinedEntityType.equals(Course.class)){
			ScJoinHolder joinHolder = (ScJoinHolder) sessionFactory.getCurrentSession()
						.createCriteria(ScJoinHolder.class)
						.createCriteria("student", "s")
						.createCriteria("course", "c")
						.add(Restrictions.eq("s.studentId", firstEntityId))
						.add(Restrictions.eq("c.courseId", secondEntityId))
						.uniqueResult();
			sessionFactory.getCurrentSession().delete(joinHolder);
		}
		else if(firstJoinedEntityType.equals(Course.class)
				&& secondJoinedEntityType.equals(Student.class)){
			ScJoinHolder joinHolder = (ScJoinHolder) sessionFactory.getCurrentSession()
					.createCriteria(ScJoinHolder.class)
					.createCriteria("student", "s")
					.createCriteria("course", "c")
					.add(Restrictions.eq("s.studentId", secondEntityId))
					.add(Restrictions.eq("c.courseId", firstEntityId))
					.uniqueResult();
		sessionFactory.getCurrentSession().delete(joinHolder);
		}
		else{
			throw new IllegalArgumentException("Invalid Entity Types");
		}
	}*/

}
