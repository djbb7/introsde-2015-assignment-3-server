package introsde.assignment.soap;

import introsde.assignment.model.Measure;
import introsde.assignment.model.MeasureType;
import introsde.assignment.model.Person;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

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
	public List<Person> readPeopleList() {
		return Person.getAll();
	}

	@Override
	public Person readPerson(Long id) {
		try{
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Marshaller marshaller = JAXBContext.newInstance(Person.class).createMarshaller();
			marshaller.marshal(Person.getPersonById(id), document);
			SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
			soapMessage.getSOAPBody().addDocument(document);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			soapMessage.writeTo(outputStream);
			String output = new String(outputStream.toByteArray());
			System.out.println(output);
			} catch (ParserConfigurationException e){
				System.out.println("Parser "+ e.getMessage());
			} catch (JAXBException e){
				System.out.println("JAXB "+e.getMessage());
			} catch (SOAPException e){
				System.out.println("SOAP "+e.getMessage());
			} catch (IOException e){			
				System.out.println("IO "+e.getMessage());
			}
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
