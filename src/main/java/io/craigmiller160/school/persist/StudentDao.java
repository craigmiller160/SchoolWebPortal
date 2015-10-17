package io.craigmiller160.school.persist;

import java.util.List;

import io.craigmiller160.school.entity.Student;

/**
 * Interface that defines the public methods of a DAO
 * object for persisting instances of the <tt>Student</tt> class.
 * 
 * @author craig
 * @version 1.0
 */
public interface StudentDao {

	//TODO test case will need to be expanded for new sublist queries
	
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
	 * Get a list of all students in the database between the
	 * range of indexes specified.
	 * <p>
	 * <b>NOTE:</b> The indexes refer to row numbers, not
	 * primary key/Id numbers.
	 * 
	 * @param startIndex the starting index of the range.
	 * @param endIndex the ending index of the range.
	 * @return a list of students within the range of indexes.
	 */
	List<Student> getStudentsInRange(long startIndex, long endIndex);
	
	/**
	 * Delete a student from the database.
	 * 
	 * @param student the student to delete.
	 * @throws RuntimeException a subclass of <tt>RuntimeException</tt>
	 * is thrown if this operation fails in some way.
	 */
	void deleteStudent(Student student);
	
	//TODO document this
	long getStudentCount();
	
}
