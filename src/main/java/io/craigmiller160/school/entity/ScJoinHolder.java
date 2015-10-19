package io.craigmiller160.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity (name="student_course")
public class ScJoinHolder implements JoinHolder{

	@Id
	@Column (name="sc_id")
	@GeneratedValue (strategy=GenerationType.AUTO)
	private int scIdd;
	
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
	}
	
	public int getScIdd() {
		return scIdd;
	}

	public void setScIdd(int scIdd) {
		this.scIdd = scIdd;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
}
