package io.craigmiller160.school.persist;

import java.util.List;

import io.craigmiller160.school.entity.Course;

/**
 * Interface that defines the public methods of a DAO
 * object for persisting instances of the <tt>Course</tt> class.
 * 
 * @author craig
 * @version 1.0
 */
public interface CourseDao {

	/**
	 * Insert a new course into the database. The course
	 * parameter cannot have the same ID as a course
	 * already persisted in the database.
	 * 
	 * @param course the course to insert.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	void insertCourse(Course course);
	
	/**
	 * Update a course in the database. The course
	 * parameter must already be persisted in the database.
	 * 
	 * @param course the course to update.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	void updateCourse(Course course);
	
	/**
	 * Get a course from the database, matching the supplied
	 * course id. If the id provided does not match a course
	 * in the database, this method returns null.
	 * 
	 * @param courseId the id of the course to get from the database.
	 * @return the specified course from the database, or null if it doesn't exist.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	Course getCourse(int courseId);
	
	/**
	 * Get a list of all courses in the database.
	 * 
	 * @return a list of all courses in the database.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	List<Course> getAllCourses();
	
	/**
	 * A convenience method to get the previous page of records of
	 * this entity from the database. Used to facilitate pagination
	 * behavior.
	 * 
	 * @param firstId the first id of the records on the current page.
	 * @param numRecords the number of records to return for the page.
	 * @return a list of the previous page of <tt>Course</tt> objects. 
	 */
	List<Course> getPreviousCourses(long firstId, int numRecords);
	
	/**
	 * A convenience method to get the next page of records of
	 * this entity from the database. Used to facilitate pagination
	 * behavior.
	 * 
	 * @param lastId the last id of the records on the current page.
	 * @param numRecords the number of records to return for the page.
	 * @return a list of the next page of <tt>Course</tt> objects.
	 */
	List<Course> getNextCourses(long lastId, int numRecords);
	
	/**
	 * Delete a course from the database.
	 * 
	 * @param course the course to delete.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	void deleteCourse(Course course);
	
	/**
	 * Get a count of the total number of records
	 * this entity has in the database.
	 * 
	 * @return a count of the total number of courses in
	 * the database.
	 */
	long getCourseCount();
	
}
