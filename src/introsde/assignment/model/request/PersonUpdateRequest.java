package introsde.assignment.model.request;

import introsde.assignment.model.Person;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PersonUpdateRequest {
	
	private Long id;

	private String firstname;

	private String lastname;
	
	private Date birthdate;
	
	public PersonUpdateRequest(){
		
	}

	public Long getId(){
		return id;
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
	
	public void setId(Long id){
		this.id = id;
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
	
	public static Person createPersonObject(PersonUpdateRequest pR){
		Person p = new Person();
		p.setId(pR.getId());
		p.setFirstname(pR.getFirstname());
		p.setLastname(pR.getLastname());
		p.setBirthdate(pR.getBirthdate());
		return p;
	}
}
