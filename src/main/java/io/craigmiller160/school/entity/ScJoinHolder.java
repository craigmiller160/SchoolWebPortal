package io.craigmiller160.school.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity (name="student_course")
public class ScJoinHolder 
implements JoinHolder, Comparable<ScJoinHolder>, Serializable{

	/**
	 * SerialVersionUID for serialization support.
	 */
	private static final long serialVersionUID = 7552021427875579397L;

	@Id
	@Column (name="sc_id")
	@GeneratedValue (strategy=GenerationType.AUTO)
	private int scId;
	
	@OneToOne
	@JoinColumn (name="student_id", nullable=false)
	private Student student;
	
	@OneToOne
	@JoinColumn (name="course_id", nullable=false)
	private Course course;
	
	public ScJoinHolder(){}
	
	public ScJoinHolder(Student student, Course course){
		this.student = student;
		this.course = course;
		String sId = student != null ? "" + student.getStudentId() : "0";
		String cId = course != null ? "" + course.getCourseId() : "0";
		this.scId = Integer.parseInt(sId + cId);
	}
	
	public int getScId() {
		return scId;
	}

	public void setScId(int scIdd) {
		this.scId = scIdd;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
		this.scId = course != null 
				? Integer.parseInt(student.getStudentId() + "" + course.getCourseId()) 
				: Integer.parseInt(student.getStudentId() + "0");
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
		this.scId = student != null
				? Integer.parseInt(course.getCourseId() + "" + student.getStudentId())
				: Integer.parseInt(course.getCourseId() + "0");
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
