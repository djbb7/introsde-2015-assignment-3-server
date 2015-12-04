package introsde.assignment.model.request;

import introsde.assignment.model.Measure;
import introsde.assignment.model.Person;

import java.util.Date;
import java.util.List;

public class PersonCreate {
	
	private String firstname;

	private String lastname;
	
	private Date birthdate;
	
	private List<MeasureCreate> currentHealth;
	
	public PersonCreate(){
		
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public Date getBirthdate() {
		return birthdate;
	}
	
	public List<MeasureCreate> getCurrentHealth() {
		return currentHealth;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public void setCurrentHealth(List<MeasureCreate> currentHealth) {
		this.currentHealth = currentHealth;
	}
	
	public static Person createPersonObject(PersonCreate pR){
		Person p = new Person();
		p.setFirstname(pR.getFirstname());
		p.setLastname(pR.getLastname());
		p.setBirthdate(pR.getBirthdate());
		
		List<Measure> mList = MeasureCreate.createMeasureListObject(pR.getCurrentHealth());
		p.setHealthHistory(mList);
		return p;
	}
}
