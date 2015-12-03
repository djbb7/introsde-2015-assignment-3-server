package introsde.assignment.model.request;

import introsde.assignment.model.Measure;
import introsde.assignment.model.Person;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PersonCreateRequest {
	
	private String firstname;

	private String lastname;
	
	private Date birthdate;
	
	private List<MeasureCreateRequest> currentHealth;
	
	public PersonCreateRequest(){
		
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
	
	@XmlElementWrapper(name="currentHealth")
	@XmlElement(name="measure")
	public List<MeasureCreateRequest> getCurrentHealth() {
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

	public void setCurrentHealth(List<MeasureCreateRequest> currentHealth) {
		this.currentHealth = currentHealth;
	}
	
	public static Person createPersonObject(PersonCreateRequest pR){
		Person p = new Person();
		p.setFirstname(pR.getFirstname());
		p.setLastname(pR.getLastname());
		p.setBirthdate(pR.getBirthdate());
		
		List<Measure> mList = MeasureCreateRequest.createMeasureListObject(pR.getCurrentHealth());
		p.setHealthHistory(mList);
		return p;
	}
}
