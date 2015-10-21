package io.craigmiller160.school.persist;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.craigmiller160.school.entity.Course;
import io.craigmiller160.school.entity.JoinHolder;
import io.craigmiller160.school.entity.ScJoinHolder;
import io.craigmiller160.school.entity.Student;

/**
 * Default implementation of the <tt>SchoolService</tt>
 * interface. Accepts DAO objects for the <tt>Student</tt> 
 * and <tt>Course</tt> entities and the <tt>ScJoinHolder</tt> entity
 * that they're joined to, with logic to perform
 * the necessary operations for all three via the generic methods.
 * <p>
 * <b>THREAD SAFETY:</b> This class is thread-safe. Its only
 * state is contained in the DAO fields, which are final
 * and cannot be changed after instantiation. 
 * 
 * @author craig
 * @version 1.0
 */
@Component ("schoolService")
public class SchoolDataService 
implements GenericPaginatedEntityService, GenericPaginatedJoinHolderService {

	/**
	 * The DAO for persisting <tt>Student</tt> objects.
	 */
	private final GenericPaginatedDao<Student> studentDao;
	
	/**
	 * The DAO for persisting <tt>Course</tt> objects.
	 */
	private final GenericPaginatedDao<Course> courseDao;
	
	/**
	 * The DAO for persisting <tt>ScJoinHolder</tt> objects.
	 */
	private final GenericPaginatedJoinHolderDao<ScJoinHolder> scJoinHolderDao;
	
	/**
	 * Create a new instance of this service, setting
	 * both DAOs that it requires. If null is passed as 
	 * either DAO parameter, this class will not be able
	 * to function.
	 * 
	 * @param studentDao the DAO for persisting <tt>Student</tt>
	 * objects.
	 * @param courseDao the DAO for persisting <tt>Course</tt>
	 * objects.
	 * @param scJoinHolderDao the DAO for persisting <tt>ScJoinHolder</tt>
	 * objects.
	 */
	@Autowired (required=true)
	public SchoolDataService(GenericPaginatedDao<Student> studentDao, 
			GenericPaginatedDao<Course> courseDao,
			GenericPaginatedJoinHolderDao<ScJoinHolder> scJoinHolderDao){
		this.studentDao = studentDao;
		this.courseDao = courseDao;
		this.scJoinHolderDao = scJoinHolderDao;
	}
	
	@Transactional
	@Override
	public <T> void updateEntity(T entity) {
		if(entity instanceof Student){
			studentDao.updateEntity((Student) entity);
		}
		else if(entity instanceof Course){
			courseDao.updateEntity((Course) entity);
		}
		else if(entity instanceof ScJoinHolder){
			scJoinHolderDao.updateEntity((ScJoinHolder) entity);
		}
		else{
			throw new IllegalArgumentException(entity.getClass() + " is not a valid entity");
		}
	}
	
	@Transactional
	@Override
	public <T> void deleteEntity(T entity){
		if(entity instanceof Student){
			studentDao.deleteEntity((Student) entity);
		}
		else if(entity instanceof Course){
			courseDao.deleteEntity((Course) entity);
		}
		else if(entity instanceof ScJoinHolder){
			scJoinHolderDao.deleteEntity((ScJoinHolder) entity);
		}
		else{
			throw new IllegalArgumentException(entity.getClass() + " is not a valid entity");
		}
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * <b>NOTE:</b> This implementation of this method does NOT support
	 * creating the <tt>ScJoinHolder</tt> entity. That is handled by the
	 * separate <tt>joinEntities(Class<T>,Object...)</tt> method.
	 */
	@Transactional
	@SuppressWarnings("unchecked") //Casting to <T> works because of the check on entityType
	@Override
	public <T> T createEntity(Class<T> entityType, Object... params) {
		if(entityType.equals(Student.class)){
			Student student = createStudent(
					params.length >= 1 ? (String) params[0] : null,
					params.length >= 2 ? (String) params[1] : null,
					params.length >= 3 ? (LocalDate) params[2] : null,
					params.length >= 4 ? (char) params[3] : null,
					params.length >= 5 ? (int) params[4] : null);
			studentDao.insertEntity(student);
			return (T) student;
		}
		else if(entityType.equals(Course.class)){
			Course course = createCourse(
					params.length >= 1 ? (String) params[0] : null,
					params.length >= 2 ? (String) params[1] : null,
					params.length >= 3 ? (String) params[2] : null,
					params.length >= 4 ? (int) params[3] : null);
			courseDao.insertEntity(course);
			return (T) course;
		}
		else{
			throw new IllegalArgumentException(entityType + " is not a valid entity");
		}
	}
	
	/**
	 * Internal method to create a <tt>Student</tt>
	 * entity for the <tt>createEntity(Class<T>, Object...)
	 * convenience method.
	 * 
	 * @param firstName the student's first name.
	 * @param lastName the student's last name.
	 * @param birthDate the student's birth date.
	 * @param gender the student's gender.
	 * @param grade the student's grade.
	 * @return the created student.
	 */
	private Student createStudent(String firstName, String lastName, 
			LocalDate birthDate, Character gender, Integer grade){
		Student student = new Student();
		if(firstName != null){
			student.setFirstName(firstName);
		}
		if(lastName != null){
			student.setLastName(lastName);
		}
		if(birthDate != null){
			student.setBirthDate(birthDate);
		}
		if(gender != null){
			student.setGender(gender);
		}
		if(grade != null){
			student.setGrade(grade);
		}
		
		return student;
	}
	
	/**
	 * Internal method to create a <tt>Course</tt>
	 * entity for the <tt>createEntity(Class<T>, Object...)
	 * convenience method.
	 * 
	 * @param courseName the name of the course.
	 * @param subject the subject of the course.
	 * @param teacherLastName the name of the course's teacher.
	 * @param period the period the course is taught.
	 * @return the created course.
	 */
	private Course createCourse(String courseName, String subject, 
			String teacherLastName, Integer period){
		Course course = new Course();
		if(courseName != null){
			course.setCourseName(courseName);
		}
		if(subject != null){
			course.setSubject(subject);
		}
		if(teacherLastName != null){
			course.setTeacherLastName(teacherLastName);
		}
		if(period != null){
			course.setPeriod(period);
		}
		
		return course;
	}

	@Transactional
	@Override
	public <T> void insertEntity(T entity) {
		if(entity instanceof Student){
			studentDao.insertEntity((Student) entity);
		}
		else if(entity instanceof Course){
			courseDao.insertEntity((Course) entity);
		}
		else if(entity instanceof ScJoinHolder){
			scJoinHolderDao.insertEntity((ScJoinHolder) entity);
		}
		else{
			throw new IllegalArgumentException(entity.getClass() + " is not a valid entity");
		}
	}

	@SuppressWarnings("unchecked") //The entityType.equals(Class<?>) is the type check
	@Transactional
	@Override
	public <T> List<T> getAllEntities(Class<T> entityType) {
		if(entityType.equals(Student.class)){
			return (List<T>) studentDao.getAllEntities();
		}
		else if(entityType.equals(Course.class)){
			return (List<T>) courseDao.getAllEntities();
		}
		else if(entityType.equals(ScJoinHolder.class)){
			return (List<T>) scJoinHolderDao.getAllEntities();
		}
		else{
			throw new IllegalArgumentException(entityType + " is not a valid entity");
		}
	}

	@SuppressWarnings("unchecked") //The entityType.equals(Class<?>) is the type check
	@Transactional
	@Override
	public <T> T getEntityById(Class<T> entityType, int entityId) {
		if(entityType.equals(Student.class)){
			return (T) studentDao.getEntityById(entityId);
		}
		else if(entityType.equals(Course.class)){
			return (T) courseDao.getEntityById(entityId);
		}
		else if(entityType.equals(ScJoinHolder.class)){
			return (T) scJoinHolderDao.getEntityById(entityId);
		}
		else{
			throw new IllegalArgumentException(entityType + " is not a valid entity");
		}
	}

	@Transactional
	@Override
	public <T> long getEntityCount(Class<T> entityType) {
		long result = 0;
		if(entityType.equals(Student.class)){
			result = studentDao.getEntityCount();
		}
		else if(entityType.equals(Course.class)){
			result = courseDao.getEntityCount();
		}
		else if(entityType.equals(ScJoinHolder.class)){
			result = scJoinHolderDao.getEntityCount();
		}
		else{
			throw new IllegalArgumentException(entityType + " is not a valid Entity");
		}
		
		return result;
	}

	@SuppressWarnings("unchecked") //The entityType.equals(Class<?>) is the type check
	@Transactional
	@Override
	public <T> List<T> getPreviousEntities(Class<T> entityType, int lastPageFirstRowNum, int pageSize) {
		List<T> resultList = null;
		if(entityType.equals(Student.class)){
			resultList = (List<T>) studentDao.getPreviousEntities(lastPageFirstRowNum, pageSize);
		}
		else if(entityType.equals(Course.class)){
			resultList = (List<T>) courseDao.getPreviousEntities(lastPageFirstRowNum, pageSize);
		}
		else if(entityType.equals(ScJoinHolder.class)){
			resultList = (List<T>) scJoinHolderDao.getPreviousEntities(lastPageFirstRowNum, pageSize);
		}
		else{
			throw new IllegalArgumentException(entityType + " is not a valid Entity");
		}
		
		return resultList;
	}

	@SuppressWarnings("unchecked") //The entityType.equals(Class<?>) is the type check
	@Transactional
	@Override
	public <T> List<T> getNextEntities(Class<T> entityType, int lastPageLastRowNum, int pageSize) {
		List<T> resultList = null;
		if(entityType.equals(Student.class)){
			resultList = (List<T>) studentDao.getNextEntities(lastPageLastRowNum, pageSize);
		}
		else if(entityType.equals(Course.class)){
			resultList = (List<T>) courseDao.getNextEntities(lastPageLastRowNum, pageSize);
		}
		else if(entityType.equals(ScJoinHolder.class)){
			resultList = (List<T>) scJoinHolderDao.getNextEntities(lastPageLastRowNum, pageSize);
		}
		else{
			throw new IllegalArgumentException(entityType + " is not a valid Entity");
		}
		
		return resultList;
	}

	@SuppressWarnings("unchecked") //The entityType.equals(Class<?>) is the type check
	@Transactional
	@Override
	public <T extends JoinHolder, U> List<T> getAllJoinsFor(Class<T> joinHolderType, 
			Class<U> joinedEntityType, int entityId) {
		List<T> resultList = null;
		if(joinHolderType.equals(ScJoinHolder.class)){
			if(joinedEntityType.equals(Student.class) 
					|| joinedEntityType.equals(Course.class)){
				resultList = (List<T>) scJoinHolderDao.getAllJoinsFor(joinedEntityType, entityId);
			}
			else{
				throw new IllegalArgumentException(joinedEntityType + " is not a valid Joined Entity");
			}
		}
		else{
			throw new IllegalArgumentException(joinHolderType + " is not a valid JoinHolder");
		}
		
		return resultList;
	}

	@SuppressWarnings("unchecked") //The entityType.equals(Class<?>) is the type check
	@Transactional
	@Override
	public <T extends JoinHolder, U> List<T> getPreviousJoinsFor(Class<T> joinHolderType, 
			Class<U> joinedEntityType, int entityId, int lastPageFirstRowNum, int pageSize) {
		List<T> resultList = null;
		if(joinHolderType.equals(ScJoinHolder.class)){
			if(joinedEntityType.equals(Student.class) 
					|| joinedEntityType.equals(Course.class)){
				resultList = (List<T>) scJoinHolderDao.getPreviousJoinsFor(joinedEntityType, entityId, lastPageFirstRowNum, pageSize);
			}
			else{
				throw new IllegalArgumentException(joinedEntityType + " is not a valid Joined Entity");
			}
		}
		else{
			throw new IllegalArgumentException(joinHolderType + " is not a valid JoinHolder");
		}
		
		return resultList;
	}

	@SuppressWarnings("unchecked") //The entityType.equals(Class<?>) is the type check
	@Transactional
	@Override
	public <T extends JoinHolder, U> List<T> getNextJoinsFor(Class<T> joinHolderType, 
			Class<U> joinedEntityType, int entityId, int lastPageLastRowNum, int pageSize) {
		List<T> resultList = null;
		if(joinHolderType.equals(ScJoinHolder.class)){
			if(joinedEntityType.equals(Student.class) 
					|| joinedEntityType.equals(Course.class)){
				resultList = (List<T>) scJoinHolderDao.getNextJoinsFor(joinedEntityType, entityId, lastPageLastRowNum, pageSize);
			}
			else{
				throw new IllegalArgumentException(joinedEntityType + " is not a valid Joined Entity");
			}
		}
		else{
			throw new IllegalArgumentException(joinHolderType + " is not a valid JoinHolder");
		}
		
		return resultList;
	}

	@Transactional
	@Override
	public <T extends JoinHolder,U> long getJoinCountFor(Class<T> joinHolderType, Class<U> joinedEntityType, int entityId) {
		long result = 0;
		if(joinHolderType.equals(ScJoinHolder.class)){
			if(joinedEntityType.equals(Student.class) 
					|| joinedEntityType.equals(Course.class)){
				result = (Long) scJoinHolderDao.getJoinCountFor(joinedEntityType, entityId);
			}
			else{
				throw new IllegalArgumentException(joinedEntityType + " is not a valid Joined Entity");
			}
		}
		else{
			throw new IllegalArgumentException(joinHolderType + " is not a valid JoinHolder");
		}
		
		return result;
	}
	
	@Transactional
	@Override
	public <T extends JoinHolder> void joinEntities(Class<T> joinHolderType, Object...entitiesToJoin){
		if(joinHolderType.equals(ScJoinHolder.class)){
			if(entitiesToJoin.length > 2){
				throw new IllegalArgumentException("Wrong number of parameters");
			}
			if(entitiesToJoin[0] instanceof Student && entitiesToJoin[1] instanceof Course){
				ScJoinHolder joinHolder = new ScJoinHolder();
				joinHolder.setStudent((Student) entitiesToJoin[0]);
				joinHolder.setCourse((Course) entitiesToJoin[1]);
				scJoinHolderDao.insertEntity(joinHolder);
			}
			else if(entitiesToJoin[0] instanceof Course && entitiesToJoin[1] instanceof Student){
				ScJoinHolder joinHolder = new ScJoinHolder();
				joinHolder.setStudent((Student) entitiesToJoin[1]);
				joinHolder.setCourse((Course) entitiesToJoin[0]);
				scJoinHolderDao.insertEntity(joinHolder);
			}
			else{
				throw new IllegalArgumentException("Wrong entities as parameters");
			}
		}
		else{
			throw new IllegalArgumentException(joinHolderType + " is not a valid JoinHolder");
		}
	}

	@Transactional
	@Override
	public <T extends JoinHolder, U> void removeJoinsFor(Class<T> joinHolderType, Class<U> joinedEntityType,
			int entityId) {
		if(joinHolderType.equals(ScJoinHolder.class)){
			scJoinHolderDao.removeJoinsFor(joinedEntityType, entityId);
		}
		else{
			throw new IllegalArgumentException(joinHolderType + " is not a valid JoinHolder");
		}
	}

}