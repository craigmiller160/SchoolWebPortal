package io.craigmiller160.school.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * An entity that defines a course that students can take. It
 * contains the attributes defining the course, and a list of
 * all students currently taking it.
 * <p>
 * <b>THREAD SAFETY:</b> This class is NOT thread-safe. Its state
 * is not synchronized, and instances of it should be handled in
 * a way that is not shared between threads.
 * 
 * @author craig
 * @version 1.0
 */
@Entity
@Table (name="course")
public class Course 
implements Comparable<Course>, Serializable{

	/**
	 * SerialVersionUID for serialization support.
	 */
	private static final long serialVersionUID = 7802449844196979359L;

	/**
	 * The id of the course.
	 */
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="course_id", length=6)
	private int courseId;
	
	/**
	 * The subject of the course.
	 */
	@Column (length=255)
	private String subject;
	
	/**
	 * The name of the course.
	 */
	@Column (name="course_name", length=255)
	private String courseName;
	
	/**
	 * The last name of the course's teacher
	 */
	@Column (name="teacher_last_name", length=255)
	private String teacherLastName;
	
	/**
	 * The period the course is taught.
	 */
	@Column (length=2)
	private int period;
	
	/**
	 * Create a new course with none of its properties set.
	 */
	public Course() {}
	
	/**
	 * Create a new course and set all properties but its
	 * id.
	 * 
	 * @param courseName the name of the course.
	 * @param teacherLastName the last name of the course's teacher.
	 * @param subject the subject of the course.
	 * @param period the period the course is taught.
	 */
	public Course(String courseName, String teacherLastName, 
			String subject, int period){
		this.courseName = courseName;
		this.teacherLastName = teacherLastName;
		this.period = period;
		this.subject = subject;
	}
	
	/**
	 * Get the subject of the course.
	 * 
	 * @return the subject of the course.
	 * @throws NullPointerException if the field 
	 * being retrieved was not set.
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Set the subject of the course.
	 * 
	 * @param subject the subject of the course.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Get the id of the course. 
	 * 
	 * @return the id of the course.
	 * @throws NullPointerException if the field 
	 * being retrieved was not set.
	 */
	public int getCourseId() {
		return courseId;
	}
	
	/**
	 * Set the id of the course. 
	 * 
	 * @param courseId the id of the course.
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	/**
	 * Get the name of the course.
	 * 
	 * @return the name of the course.
	 * @throws NullPointerException if the field 
	 * being retrieved was not set.
	 */
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * Get the last name of the course's teacher.
	 * 
	 * @return the last name of the course's teacher.
	 * @throws NullPointerException if the field 
	 * being retrieved was not set.
	 */
	public String getTeacherLastName() {
		return teacherLastName;
	}
	
	/**
	 * Set the last name of the course's teacher.
	 * 
	 * @param teacherLastName the last name of the course's teacher.
	 */
	public void setTeacherLastName(String teacherLastName) {
		this.teacherLastName = teacherLastName;
	}
	
	/**
	 * Get the period of the course.
	 * 
	 * @return the period of the course.
	 * @throws NullPointerException if the field 
	 * being retrieved was not set.
	 */
	public int getPeriod() {
		return period;
	}
	
	/**
	 * Set the period the course is taught.
	 * 
	 * @param period the period the course is taught.
	 */
	public void setPeriod(int period) {
		this.period = period;
	}
	
	@Override
	public String toString(){
		return courseName;
	}
	
	@Override
	public int hashCode(){
		return courseId;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Course){
			return ((Course) obj).courseId == this.courseId;
		}
		else{
			return false;
		}
	}

	@Override
	public int compareTo(Course course) {
		return ((Integer) this.courseId)
				.compareTo((Integer) course.courseId);
	}
	
}
