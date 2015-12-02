package introsde.assignment.soap;

import introsde.assignment.model.Measure;
import introsde.assignment.model.MeasureType;
import introsde.assignment.model.Person;

import java.util.List;

import javax.jws.WebService;

@WebService(endpointInterface="introsde.assignment.soap.People", serviceName="People")
public class PeopleImpl implements People {

	@Override
	public List<Person> readPeopleList() {
		return Person.getAll();
	}

	@Override
	public Person readPerson(Long id) {
		return Person.getPersonById(id);
	}

	@Override
	public Person updatePerson(Person person) {
		return Person.updatePerson(person);
	}

	@Override
	public Person createPerson(Person person) {
		return Person.savePerson(person);
	}

	@Override
	public int deletePerson(Long id) {
		Person del = Person.getPersonById(id);
		if(del != null)
			Person.removePerson(del);
		return -1;
	}

	@Override
	public List<Measure> readPersonHistory(Long id, String measureType) {
		return null;
	}

	@Override
	public Measure savePersonMeasure(Long id, Measure m) {
		Person p = Person.getPersonById(id);
		Person.addMeasure(p, m);
		return m;
	}

	@Override
	public Measure updatePersonMeasure(Long id, Measure m){
		Person p = Person.getPersonById(id);
		Person.updateMeasure(p, m);
		return m;
	}
	
	@Override
	public List<MeasureType> readMeasureTypes() {
		return MeasureType.getMeasureTypes();
	}

	@Override
	public Measure readPersonMeasure(Long id, String measureType, Long mid) {
		return Measure.getMeasureById(id, measureType, mid);
	}

}
