package introsde.assignment.soap;

import introsde.assignment.model.HealthProfileHistory;
import introsde.assignment.model.Measure;
import introsde.assignment.model.MeasureType;
import introsde.assignment.model.Person;
import introsde.assignment.model.request.MeasureCreateRequest;
import introsde.assignment.model.request.PersonCreateRequest;
import introsde.assignment.model.request.PersonUpdateRequest;
import introsde.assignment.model.response.MeasureTypesResponse;
import introsde.assignment.model.response.PeopleResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Document;


@WebService(endpointInterface="introsde.assignment.soap.People", serviceName="People")
public class PeopleImpl implements People {

	@Override
	public PeopleResponse readPeopleList() {
		return new PeopleResponse(Person.getAll());
	}

	@Override
	public Person readPerson(Long id) {
		return Person.getPersonById(id);
	}

	@Override
	public Person updatePerson(PersonUpdateRequest person) {
		Person p = PersonUpdateRequest.createPersonObject(person);
		return Person.updatePerson(p);
	}

	@Override
	public Person createPerson(PersonCreateRequest person) {
		Person p = PersonCreateRequest.createPersonObject(person);
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
	public Measure savePersonMeasure(Long id, MeasureCreateRequest mCR) {
		Person p = Person.getPersonById(id);
		Measure m = MeasureCreateRequest.createMeasureObject(mCR);
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
	public MeasureTypesResponse readMeasureTypes() {
		return new MeasureTypesResponse(MeasureType.getMeasureTypes());
	}



}
