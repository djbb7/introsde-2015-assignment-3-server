package introsde.assignment.model.response;

import introsde.assignment.model.Person;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PeopleResponse {

	private List<Person> people;
	
	public PeopleResponse(){
		
	}
	
	public PeopleResponse(List<Person> p){
		this.people = p;
	}

	@XmlElement(name="person")
	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}
	
	
}
