package introsde.assignment.test;

import introsde.assignment.model.Measure;
import introsde.assignment.model.MeasureType;
import introsde.assignment.model.Person;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonTest {
        
	@BeforeClass
    public static void beforeClass() {
        System.out.println("Testing JPA on lifecoach database using 'introsde-jpa' persistence unit");
    }

    @AfterClass
    public static void afterClass() {
    }

    @Before
    public void before() {
    }


	@Test
	public void readPeopleListTest() {
        System.out.println("--> TEST: readPeopleList");
        List<Person> list = Person.getAll();
        for (Person person : list) {
            System.out.println("--> Person = "+person.toString());
        }
        Assert.assertTrue(list.size()>0);
    }
	
	@Test
	public void personCRUDTest(){
		System.out.println("--> TEST: personCRUD");
		
		// Create a new person and save it
		System.out.println("----->Create person");
		Person p = new Person();
		p.setFirstname("Pedro");
		p.setLastname("Palma");
		Calendar c = Calendar.getInstance();
		c.set(1990, 3, 7,0,0,0);
		p.setBirthdate(c.getTime());
		
		Person savedP = Person.savePerson(p);
		
		Assert.assertNotNull(savedP);
		Assert.assertNotEquals(savedP.getId().longValue(), 0L);
		
		// Read the newly created person
		System.out.println("----->Read person");
		Person readP = Person.getPersonById(savedP.getId());
		
		Assert.assertEquals(readP.getFirstname(), p.getFirstname());
		Assert.assertEquals(readP.getLastname(), p.getLastname());
		Assert.assertEquals(readP.getBirthdate().getTime()/1000, p.getBirthdate().getTime()/1000);
		
		// Update the person's details and check they were persisted
		System.out.println("----->Update person");
		readP.setFirstname("Joshua");
		readP.setLastname("Smith");
		c.set(1989, 9, 22,0,0,0);
		readP.setBirthdate(c.getTime());
		Person.updatePerson(readP);
		Person reReadP = Person.getPersonById(savedP.getId());
		
		Assert.assertEquals(reReadP.getFirstname(), readP.getFirstname());
		Assert.assertEquals(reReadP.getLastname(), readP.getLastname());
		Assert.assertEquals(reReadP.getBirthdate().getTime()/1000, readP.getBirthdate().getTime()/1000);
		
		// Delete the person
		System.out.println("----->Delete person");
		Person.removePerson(savedP);
		readP = Person.getPersonById(savedP.getId());
		Assert.assertNull(readP);
	}
	
	
	@Test
	public void createPersonWithHealthHistoryTest(){
		System.out.println("--> TEST: createPersonWithHealthHistory");
		
		Person p = new Person();
		p.setFirstname("Sonia");
		p.setLastname("Perez");
		Calendar c = Calendar.getInstance();
		c.set(1974, 5, 3);
		System.out.println("-->Date in epoch: "+c.getTimeInMillis());
		p.setBirthdate(c.getTime());
		
		List<Measure> hProfile = new ArrayList<Measure>();
		MeasureType weight = MeasureType.getMeasureTypeByName("weight");
		MeasureType height = MeasureType.getMeasureTypeByName("height");
		Measure m1 = new Measure();
		m1.setMeasureType(weight);
		m1.setDateRegistered(c.getTime());
		m1.setMeasureValue("5");
		Measure m2 = new Measure();
		m2.setMeasureType(height);
		m2.setMeasureValue("165");
		hProfile.add(m1);
		hProfile.add(m2);
		p.setHealthHistory(hProfile);
		
		Person savedP = Person.savePerson(p);
	
		//read person
		Person newP = Person.getPersonById(savedP.getId());
		Assert.assertNotNull(newP);
		
		//check measurements are present
		Assert.assertNotNull(newP.getHealthHistory());
		Assert.assertEquals(newP.getHealthHistory().size(), 2);
		System.out.println("-->Date: "+newP.getHealthHistory().get(0).getDateRegistered());
		System.out.println("-->Date: "+newP.getHealthHistory().get(1).getDateRegistered());
	}
	
	@Test
	public void createMeasureTest(){
		Person p = Person.getAll().get(0);
		int prev = p.getHealthHistory().size();
		System.out.println("----->Number of measures: "+prev);
		
		Measure m = new Measure();
		m.setMeasureType(MeasureType.getMeasureTypeByName("weight"));
		m.setMeasureValue("72");
		m.setPerson(p);
		
		System.out.println(String.format("----->Saving Measure: %s %s for Person: %s %s (%d)", 
				m.getMeasureType().getMeasureType(), m.getMeasureValue(), p.getFirstname(), p.getLastname(), p.getId()));
		Person.addMeasure(p, m);
		
		int post = p.getHealthHistory().size();
		System.out.println("----->Number of measures post: "+post);
		Assert.assertEquals(prev+1, post);
	}
	
	@Test
	public void updateMeasureTest(){
		System.out.println("--> TEST: updateMeasure");
		
		Person p = Person.getAll().get(0);
		Measure m = p.getHealthHistory().get(0);
		m.setMeasureValue("71");
		
		Person.updateMeasure(p, m);
		
		Assert.assertEquals("71", p.getHealthHistory().get(0).getMeasureValue());
	}
	

}
