package io.craigmiller160.school.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.craigmiller160.school.util.LocalDateConverter;

/**
 * An entity that defines a student taking courses. It contains
 * the attributes that define the student, as well as a list
 * of all courses the student is taking.
 * <p>
 * <b>THREAD SAFETY:</b> This class is NOT thread-safe. Its state
 * is not synchronized, and instances of it should be handled in
 * a way that is not shared between threads.
 * 
 * @author craig
 * @version 1.0
 */
@Entity
@Table (name="student")
public class Student 
implements Comparable<Student>, Serializable{

	/**
	 * SerialVersionUID for serialization support.
	 */
	private static final long serialVersionUID = -4803136968378896960L;

	/**
	 * The id of the student.
	 */
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="student_id")
	private int studentId;
	
	/**
	 * The first name of the student.
	 */
	@Column (name="first_name")
	private String firstName;
	
	/**
	 * The last name of the student.
	 */
	@Column (name="last_name")
	private String lastName;
	
	/**
	 * The birth date of the student.
	 */
	@Column (name="birth_date")
	@Convert (converter=LocalDateConverter.class)
	private LocalDate birthDate;
	
	/**
	 * The gender of the student.
	 */
	private char gender;
	
	/**
	 * The grade of the student.
	 */
	private int grade;
	
	/**
	 * Create a new student with none of its properties set.
	 */
	public Student() {}
	
	/**
	 * Create a new student and set all properties
	 * but its id.
	 * 
	 * @param firstName the first name of the student.
	 * @param lastName the last name of the student.
	 * @param birthDate the birth date of the student.
	 * @param gender the gender of the student.
	 * @param grade the grade of the student.
	 */
	public Student(String firstName, String lastName, 
			LocalDate birthDate, char gender, int grade){
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.grade = grade;
		this.gender = gender;
	}
	
	/**
	 * Get the id of the student.
	 * 
	 * @return the id of the student.
	 * @throws NullPointerException if the field 
	 * being retrieved was not set.
	 */
	public int getStudentId() {
		return studentId;
	}
	
	/**
	 * Set the id of the student.
	 * 
	 * @param studentId the id of the student.
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * Get the first name of the student.
	 * 
	 * @return the first name of the student.
	 * @throws NullPointerException if the field 
	 * being retrieved was not set.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Set the first name of the student.
	 * 
	 * @param firstName the first name of the student.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Get the last name of the student.
	 * 
	 * @return the last name of the student.
	 * @throws NullPointerException if the field 
	 * being retrieved was not set.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Set the last name of the student.
	 * 
	 * @param lastName the last name of the student.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Get the birth date of the student.
	 * 
	 * @return the birth date of the student.
	 * @throws NullPointerException if the field 
	 * being retrieved was not set.
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	/**
	 * Set the birth date of the student.
	 * 
	 * @param birthDate the birth date of the student.
	 */
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	/**
	 * Get the grade of the student.
	 * 
	 * @return the grade of the student.
	 * @throws NullPointerException if the field 
	 * being retrieved was not set.
	 */
	public int getGrade() {
		return grade;
	}
	
	/**
	 * Set the grade of the student.
	 * 
	 * @param grade the grade of the student.
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}	

	/**
	 * Get the gender of the student.
	 * 
	 * @return the gender of the student.
	 * @throws NullPointerException if the field 
	 * being retrieved was not set.
	 */
	public char getGender() {
		return gender;
	}

	/**
	 * Set the gender of the student.
	 * 
	 * @param gender the gender of the student.
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	/**
	 * Get the age based on the currently set
	 * date of birth.
	 * 
	 * @return the age based on the currently set 
	 * date of birth.
	 */
	public int getAge(){
		LocalDate today = LocalDate.now();
		return birthDate.until(today).getYears();
	}
	
	@Override
	public String toString(){
		return firstName + " " + lastName;
	}
	
	@Override
	public int hashCode(){
		return studentId;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Student){
			return ((Student) obj).studentId == this.studentId;
		}
		else{
			return false;
		}
	}

	@Override
	public int compareTo(Student student) {
		return ((Integer) this.studentId)
				.compareTo((Integer) student.studentId);
	}
	
}