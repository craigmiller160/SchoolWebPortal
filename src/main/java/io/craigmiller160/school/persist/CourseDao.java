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
	 * Get a list of all courses in the database between the
	 * range of indexes specified.
	 * <p>
	 * <b>NOTE:</b> The indexes refer to row numbers, not
	 * primary key/Id numbers.
	 * 
	 * @param startIndex the starting index of the range.
	 * @param endIndex the ending index of the range.
	 * @return a list of courses within the range of indexes.
	 */
	List<Course> getCoursesInRange(long startIndex, long endIndex);
	
	/**
	 * Delete a course from the database.
	 * 
	 * @param course the course to delete.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	void deleteCourse(Course course);
	
	//TODO document this
	long getCourseCount();
	
}
