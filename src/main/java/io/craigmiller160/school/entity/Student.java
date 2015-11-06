package io.craigmiller160.school.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@Column (name="student_id", length=20)
	private int studentId;
	
	/**
	 * The first name of the student.
	 */
	@Column (name="first_name", length=255)
	private String firstName;
	
	/**
	 * The last name of the student.
	 */
	@Column (name="last_name", length=255)
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
	@Enumerated (EnumType.STRING)
	private Gender gender;
	
	/**
	 * The grade of the student.
	 */
	@Column (length=2)
	private int grade;
	
	//TODO address experiment is here
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="student", orphanRemoval=true)
	private Set<AddressStudent> addresses = new HashSet<>();
	
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="student", orphanRemoval=true)
	private Set<PhoneStudent> phones = new HashSet<>();
	
	@OneToMany (cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="student", orphanRemoval=true)
	private Set<EmailStudent> emails = new HashSet<>();
	
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
			LocalDate birthDate, Gender gender, int grade){
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
	public Gender getGender() {
		return gender;
	}

	/**
	 * Set the gender of the student.
	 * 
	 * @param gender the gender of the student.
	 */
	public void setGender(Gender gender) {
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
	
	//TODO address experiment here
	
	public Set<AddressStudent> getAddresses(){
		return addresses;
	}
	
	public void setAddresses(Set<AddressStudent> addresses){
		this.addresses = addresses;
	}
	
	public boolean addAddress(AddressStudent address){
		address.setStudent(this); //TODO review this line
		return this.addresses.add(address);
	}
	
	public boolean removeAddress(AddressStudent address){
		return this.addresses.remove(address);
	}
	
	public Set<PhoneStudent> getPhones(){
		return phones;
	}
	
	public void setPhones(Set<PhoneStudent> phones){
		this.phones = phones;
	}
	
	public boolean addPhone(PhoneStudent phone){
		phone.setStudent(this); //TODO review this line
		return this.phones.add(phone);
	}
	
	public boolean removePhone(PhoneStudent phone){
		return this.phones.remove(phone);
	}
	
	//TODO
	
	public Set<EmailStudent> getEmails(){
		return emails;
	}
	
	public void setEmails(Set<EmailStudent> emails){
		this.emails = emails;
	}
	
	public boolean addEmail(EmailStudent email){
		email.setStudent(this); //TODO review this line
		return this.emails.add(email);
	}
	
	public boolean removeEmail(EmailStudent email){
		return this.emails.remove(email);
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