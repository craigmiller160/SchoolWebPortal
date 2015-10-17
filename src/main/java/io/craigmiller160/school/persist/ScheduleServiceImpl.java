package io.craigmiller160.school.persist;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import io.craigmiller160.school.entity.Course;
import io.craigmiller160.school.entity.Student;

public class ScheduleServiceImpl implements ScheduleService {

	/**
	 * The DAO for persisting <tt>Student</tt> objects.
	 */
	private final StudentDao studentDao;
	
	/**
	 * The DAO for persisting <tt>Course</tt> objects.
	 */
	private final CourseDao courseDao;
	
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
	 */
	public ScheduleServiceImpl(StudentDao studentDao, CourseDao courseDao){
		this.studentDao = studentDao;
		this.courseDao = courseDao;
	}
	
	@Transactional
	@Override
	public <T> void saveEntity(T entity) {
		if(entity instanceof Student){
			studentDao.updateStudent((Student) entity);
		}
		else if(entity instanceof Course){
			courseDao.updateCourse((Course) entity);
		}
		else{
			throw new IllegalArgumentException(entity.getClass() + " is not a valid entity");
		}
	}
	
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
			studentDao.insertStudent(student);
			return (T) student;
		}
		else if(entityType.equals(Course.class)){
			Course course = createCourse(
					params.length >= 1 ? (String) params[0] : null,
					params.length >= 2 ? (String) params[1] : null,
					params.length >= 3 ? (String) params[2] : null,
					params.length >= 4 ? (int) params[3] : null);
			courseDao.insertCourse(course);
			return (T) course;
		}
		else{
			throw new IllegalArgumentException(entityType + " is not a valid entity");
		}
	}
	
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
			studentDao.insertStudent((Student) entity);
		}
		else if(entity instanceof Course){
			courseDao.insertCourse((Course) entity);
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
			return (List<T>) studentDao.getAllStudents();
		}
		else if(entityType.equals(Course.class)){
			return (List<T>) courseDao.getAllCourses();
		}
		else{
			throw new IllegalArgumentException(entityType + " is not a valid entity");
		}
	}

	@SuppressWarnings("unchecked") //The entityType.equals(Class<?>) is the type check
	@Transactional
	@Override
	public <T> List<T> getEntitiesInRange(Class<T> entityType, long startIndex, long endIndex) {
		List<T> resultList = null;
		if(entityType.equals(Student.class)){
			resultList = (List<T>) studentDao.getStudentsInRange(startIndex, endIndex);
		}
		else if(entityType.equals(Course.class)){
			resultList = (List<T>) courseDao.getCoursesInRange(startIndex, endIndex);
		}
		else{
			throw new IllegalArgumentException(entityType + " is not a valid Entity");
		}
		
		return resultList;
	}

	@SuppressWarnings("unchecked") //The entityType.equals(Class<?>) is the type check
	@Transactional
	@Override
	public <T> T getEntity(Class<T> entityType, int entityId) {
		if(entityType.equals(Student.class)){
			return (T) studentDao.getStudent(entityId);
		}
		else if(entityType.equals(Course.class)){
			return (T) courseDao.getCourse(entityId);
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
			result = studentDao.getStudentCount();
		}
		else if(entityType.equals(Course.class)){
			result = courseDao.getCourseCount();
		}
		else{
			throw new IllegalArgumentException(entityType + " is not a valid Entity");
		}
		
		return result;
	}
	
	

}
