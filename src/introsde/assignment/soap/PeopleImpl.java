package introsde.assignment.soap;

import introsde.assignment.model.HealthProfileHistory;
import introsde.assignment.model.Measure;
import introsde.assignment.model.MeasureType;
import introsde.assignment.model.Person;
import introsde.assignment.model.request.MeasureCreate;
import introsde.assignment.model.request.MeasureUpdate;
import introsde.assignment.model.request.PersonCreate;
import introsde.assignment.model.request.PersonUpdate;
import introsde.assignment.model.response.MeasureTypes;
import introsde.assignment.model.response.PeopleList;

import javax.jws.WebService;


@WebService(endpointInterface="introsde.assignment.soap.People", serviceName="People")
public class PeopleImpl implements People {

	@Override
	public PeopleList readPeopleList() {
		return new PeopleList(Person.getAll());
	}

	@Override
	public Person readPerson(Long id) {
		return Person.getPersonById(id);
	}

	@Override
	public Person updatePerson(PersonUpdate person) {
		Person p = PersonUpdate.createPersonObject(person);
		return Person.updatePerson(p);
	}

	@Override
	public Person createPerson(PersonCreate person) {
		Person p = PersonCreate.createPersonObject(person);
		return Person.savePerson(p);
	}

	@Override
	public boolean deletePerson(Long id) {
		Person del = Person.getPersonById(id);
		if(del == null){
			return false;
		}
		Person.removePerson(del);
		return true;
	}

	@Override
	public HealthProfileHistory readPersonHistory(Long id, String measureType) {
		HealthProfileHistory hist = new HealthProfileHistory();
		hist.setHealthProfile_history(Measure.getMeasureHistory(id, measureType));
		return hist;
	}

	@Override
	public Measure readPersonMeasure(Long id, String measureType, Long mid) {
		return Measure.getMeasureById(id, measureType, mid);
	}
	
	@Override
	public Measure savePersonMeasure(Long id, MeasureCreate mCR) {
		Person p = Person.getPersonById(id);
		Measure m = MeasureCreate.createMeasureObject(mCR);
		Person.addMeasure(p, m);
		return m;
	}

	@Override
	public Measure updatePersonMeasure(Long id, MeasureUpdate m){
		Person p = Person.getPersonById(id);
		Measure n = Person.updateMeasure(p, MeasureUpdate.createMeasureObject(id, m));
		return n;
	}
	
	@Override
	public MeasureTypes readMeasureTypes() {
		return new MeasureTypes(MeasureType.getMeasureTypes());
	}



}
