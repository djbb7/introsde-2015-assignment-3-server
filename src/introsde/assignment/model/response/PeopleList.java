package introsde.assignment.model.response;

import introsde.assignment.model.Person;

import java.util.List;

/**
 * Convenience wrapper for returning the list of
 * all people to the client.
 */
public class PeopleList {

	private List<Person> people;
	
	public PeopleList(){
		
	}
	
	public PeopleList(List<Person> p){
		this.people = p;
	}

	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}
	
	
}
