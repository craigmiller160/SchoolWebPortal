package io.craigmiller160.school.persistold;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.craigmiller160.school.context.AppContext;
import io.craigmiller160.school.entity.ScJoinHolder;
import io.craigmiller160.school.entity.Student;
import io.craigmiller160.school.persist.GenericJoinHolderDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration ({"classpath:/test-context.xml"})
public class ScJoinHolderDaoTest {

	@Autowired (required=true)
	private GenericJoinHolderDao<ScJoinHolder> scJoinHolderDao;
	
	private int studentId;
	private int courseId;
	
	public GenericJoinHolderDao<ScJoinHolder> getScJoinHolderDao() {
		return scJoinHolderDao;
	}

	public void setScJoinHolderDao(GenericJoinHolderDao<ScJoinHolder> scJoinHolderDao) {
		this.scJoinHolderDao = scJoinHolderDao;
	}
	
	
	@BeforeClass
	@Transactional
	public void before(){
		Student student = new Student();
		setStudent(student);
		studentId = student.getStudentId();
	}
	
	private void setStudent(Student student){
		student.setFirstName("First");
		student.setLastName("Last");
		student.setBirthDate(LocalDate.of(1900, 1, 1));
		student.setGender('U');
		student.setGrade(1);
	}
	
	@Test
	@Transactional
	public void testInsert(){
		
	}
	
	

	/**
	 * Reset the auto-increment counter of the table being tested
	 * in the database. This method is invoked after all test
	 * cases have completed.
	 */
	@AfterClass
	public static void resetAutoIncrement(){
		ApplicationContext context = AppContext.getApplicationContext();
		HibernateTestUtil testUtil = context.getBean(HibernateTestUtil.class, "hibernateTestUtil");
		testUtil.resetStudentAutoIncrement();
	}
	
}
