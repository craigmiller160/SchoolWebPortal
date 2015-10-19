package io.craigmille160.school.deprecated;

import java.util.List;

import io.craigmiller160.school.entity.Student;

/**
 * Interface that defines the public methods of a DAO
 * object for persisting instances of the <tt>Student</tt> class.
 * 
 * @author craig
 * @version 1.0
 */
@Deprecated
public interface StudentDao {

	/**
	 * Insert a new student into the database. The student
	 * parameter cannot have the same ID as a student
	 * already persisted in the database.
	 * 
	 * @param student the student to insert.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	void insertStudent(Student student);
	
	/**
	 * Update a student in the database. The student
	 * parameter must already be persisted in the database.
	 * 
	 * @param student the student to update.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	void updateStudent(Student student);
	
	/**
	 * Get a student from the database, matching the supplied
	 * student id. If the id provided does not match a student
	 * in the database, this method returns null.
	 * 
	 * @param studentId the id of the student to get from the database.
	 * @return the specified student from the database, or null if it doesn't exist.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	Student getStudent(int studentId);
	
	/**
	 * Get a list of all students in the database.
	 * 
	 * @return a list of all students in the database.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	List<Student> getAllStudents();
	
	/**
	 * A convenience method to get the previous page of records of
	 * this entity from the database. Used to facilitate pagination
	 * behavior.
	 * 
	 * @param firstId the first id of the records on the current page.
	 * @param numRecords the number of records to return for the page.
	 * @return a list of the previous page of <tt>Student</tt> objects. 
	 */
	List<Student> getPreviousStudents(long firstId, int numRecords);
	
	/**
	 * A convenience method to get the next page of records of
	 * this entity from the database. Used to facilitate pagination
	 * behavior.
	 * 
	 * @param lastId the last id of the records on the current page.
	 * @param numRecords the number of records to return for the page.
	 * @return a list of the next page of <tt>Student</tt> objects.
	 */
	List<Student> getNextStudents(long lastId, int numRecords);
	
	/**
	 * Delete a student from the database.
	 * 
	 * @param student the student to delete.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	void deleteStudent(Student student);
	
	/**
	 * Get a count of the total number of records
	 * this entity has in the database.
	 * 
	 * @return a count of the total number of students in
	 * the database.
	 */
	long getStudentCount();
	
}
