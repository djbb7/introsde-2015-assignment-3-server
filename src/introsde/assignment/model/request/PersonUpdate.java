package introsde.assignment.model.request;

import introsde.assignment.model.Person;

import java.util.Date;

/**
 * Represents the Person object that is expected
 * in order to update a Person in the database.
 */
public class PersonUpdate {
	
	private Long id;

	private String firstname;

	private String lastname;
	
	private Date birthdate;
	
	public PersonUpdate(){
		
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
	
	public static Person createPersonObject(PersonUpdate pR){
		Person p = new Person();
		p.setId(pR.getId());
		p.setFirstname(pR.getFirstname());
		p.setLastname(pR.getLastname());
		p.setBirthdate(pR.getBirthdate());
		return p;
	}
}
