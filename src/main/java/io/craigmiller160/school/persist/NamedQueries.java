package io.craigmiller160.school.persist;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import io.craigmiller160.school.entity.Course;
import io.craigmiller160.school.entity.Student;

/**
 * A utility class for holding <tt>NamedNativeQueries</tt>
 * in annotation form. It holds four queries:
 * <p>
 * <b>coursePreviousPage:</b> Display a previous page of 
 * records from the course table.<br>
 * <i>PARAM firstId:</i> the first ID of the current page.<br>
 * <i>PARAM records:</i> the number of records to return.
 * <p>
 * <b>studentPreviousPage:</b> Display a previous page of
 * records from the student table.<br>
 * <i>PARAM firstId:</i> the first ID of the current page.<br>
 * <i>PARAM records:</i> the number of records to return.
 * <p>
 * <b>courseNextPage:</b> Display a next page of records
 * from the course table.<br>
 * <i>PARAM lastId:</i> the last ID of the current page.<br>
 * <i>PARAM records:</i> the number of records to return.
 * <p>
 * <b>studentNextPage:</b> Display a next page of records
 * from the student table.<br>
 * <i>PARAM lastId:</i> the last ID of the current page.<br>
 * <i>PARAM records:</i> the number of records to return.
 * <p>
 * Because this class needs to be an entity to be detected
 * by <tt>Hibernate</tt>, it has an ID field and a getter
 * and setter for that field. However, those methods should
 * be ignored, this class is only intended as a holder for
 * annotated queries.
 * 
 * @author craig
 * @version 1.0
 */
@Entity
@NamedNativeQueries({ 
	@NamedNativeQuery(
			name="coursePreviousPage",
			query="call course_previous_page(:firstId, :records)",
			resultClass=Course.class),
	@NamedNativeQuery(
			name="studentPreviousPage",
			query="call student_previous_page (:firstId, :records)",
			resultClass=Student.class),
	@NamedNativeQuery(
			name="courseNextPage",
			query="call course_next_page(:lastId, :records)",
			resultClass=Course.class
			),
	@NamedNativeQuery(
			name="studentNextPage",
			query="call student_next_page(:lastId, :records)",
			resultClass=Student.class
			)
	})
public class NamedQueries {

	/**
	 * As an entity, <tt>Hibernate</tt> expects this
	 * class to have an ID, even though it doesn't need it.
	 */
	@Id
	private int id;

	/**
	 * As an entity, <tt>Hibernate</tt> expects this
	 * class to have an ID. This getter is provided for
	 * that reason, and should not be used.
	 * 
	 * @return the ID of this class.
	 */
	public int getId() {
		return id;
	}

	/**
	 * As an entity, <tt>Hibernate</tt> expects this
	 * class to have an ID. This setter is provided for
	 * that reason, and should not be used.
	 * 
	 * @param id the ID of this class.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
}
