package io.craigmiller160.school.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * A representation of the join table in the database
 * joining <tt>Student</tt> and <tt>Course</tt> entities.
 * It is used to get lists of either or both entities
 * via the join between them.
 * <p>
 * <b>THREAD SAFETY:</b> This class is NOT thread-safe. 
 * Its state is not synchronized, and instances of it should be handled in
 * a way that is not shared between threads.
 * 
 * @author craig
 * @version 1.0
 */
@Entity (name="student_course")
public class ScJoinHolder 
implements JoinHolder, Comparable<ScJoinHolder>, Serializable{

	/**
	 * SerialVersionUID for serialization support.
	 */
	private static final long serialVersionUID = 7552021427875579397L;

	/**
	 * Unique ID for each join in the table.
	 */
	@Id
	@Column (name="sc_id")
	@GeneratedValue (strategy=GenerationType.AUTO)
	private int scId;
	
	/**
	 * The <tt>Student</tt> entity being joined.
	 */
	@OneToOne
	@JoinColumn (name="student_id", nullable=false)
	private Student student;
	
	/**
	 * The <tt>Course</tt> entity being joined.
	 */
	@OneToOne
	@JoinColumn (name="course_id", nullable=false)
	private Course course;
	
	/**
	 * Create a new entity without setting its fields.
	 */
	public ScJoinHolder(){}
	
	/**
	 * Create a new entity, setting the <tt>Student</tt>
	 * and <tt>Course</tt> entities that it holds.
	 * 
	 * @param student the <tt>Student</tt> entity being joined.
	 * @param course the <tt>Course</tt> entity being joined.
	 */
	public ScJoinHolder(Student student, Course course){
		this.student = student;
		this.course = course;
	}
	
	/**
	 * Get the unique ID for each join in the table.
	 * 
	 * @return the unique ID for each join in the table.
	 */
	public int getScId() {
		return scId;
	}

	/**
	 * Set the unique ID for each join in the table.
	 * 
	 * @param scId the unique ID for each join in the table.
	 */
	public void setScId(int scId) {
		this.scId = scId;
	}

	/**
	 * Get the <tt>Student</tt> entity being joined.
	 * 
	 * @return the <tt>Student</tt> entity being joined.
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * Set the <tt>Student</tt> entity being joined.
	 * 
	 * @param student the <tt>Student</tt> entity being joined.
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * Get the <tt>Course</tt> entity being joined.
	 * 
	 * @return the <tt>Course</tt> entity being joined.
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * Set the <tt>Course</tt> entity being joined.
	 * 
	 * @param course the <tt>Course</tt> entity being joined.
	 */
	public void setCourse(Course course) {
		this.course = course;
	}
	
	@Override
	public int hashCode(){
		return scId;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof ScJoinHolder){
			return this.scId == ((ScJoinHolder) obj).scId;
		}
		else{
			return false;
		}
	}

	@Override
	public int compareTo(ScJoinHolder joinHolder) {
		return ((Integer) this.scId).compareTo(((Integer) joinHolder.scId));
	}
	
	@Override
	public String toString(){
		return student.toString() + " : " + course.toString();
	}
	
}
