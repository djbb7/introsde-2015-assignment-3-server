package introsde.assignment.soap;

import introsde.assignment.model.HealthProfileHistory;
import introsde.assignment.model.Measure;
import introsde.assignment.model.Person;
import introsde.assignment.model.request.MeasureCreateRequest;
import introsde.assignment.model.request.PersonCreateRequest;
import introsde.assignment.model.request.PersonUpdateRequest;
import introsde.assignment.model.response.MeasureTypesResponse;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface People {
	
	@WebMethod(operationName="readPersonList")
	@WebResult(name="people")
	public List<Person> readPeopleList();
	
    @WebMethod(operationName="readPerson")
    @WebResult(name="person") 
    public Person readPerson(@WebParam(name="personId") Long id);
 
    @WebMethod(operationName="updatePerson")
    @WebResult(name="person") 
    public Person updatePerson(@WebParam(name="person") PersonUpdateRequest person);
    
    @WebMethod(operationName="createPerson")
    @WebResult(name="person") 
    public Person createPerson(@WebParam(name="person") PersonCreateRequest person);
    
    @WebMethod(operationName="deletePerson")
    @WebResult(name="deleted") 
    public boolean deletePerson(@WebParam(name="personId") Long id);
    
    @WebMethod(operationName="readPersonHistory")
    @WebResult(name="healthProfile-history")
    public HealthProfileHistory readPersonHistory(@WebParam(name="personId") Long id, @WebParam(name="measureType") String measureType);
    
    @WebMethod(operationName="readPersonMeasurement")
    @WebResult(name="measure")
    public Measure readPersonMeasurement(@WebParam(name="personId") Long id, @WebParam(name="measureType") String measureType, @WebParam(name="measureId") Long mid);
    
    @WebMethod(operationName="savePersonMeasurement")
    @WebResult(name="measure")
    public Measure savePersonMeasurement(@WebParam(name="personId") Long id,@WebParam(name="measure")  MeasureCreateRequest m);

    @WebMethod(operationName="readMeasureTypes")
    @WebResult(name="measureTypes") 
    public MeasureTypesResponse readMeasureTypes();
    
    @WebMethod(operationName="updatePersonMeasure")
    @WebResult(name="measure")
    public Measure updatePersonMeasure(@WebParam(name="personId") Long id, @WebParam(name="measure") Measure m);
    
    
    
    /*
 	 * Extra points
 	 * 
 	 * 
    @WebMethod(operationName="updatePersonMeasure")
    @WebResult(name="measure")
    public Measure updatePersonMeasure(Long id, Measure m); 	 
 	 
    @WebMethod(operationName="readPersonMeasureByDates")
    @WebResult(name="measures")
    public List<Measure> readPersonMeasureByDates(Long id, String measureType, Date before, Date after);

	@WebMethod(operationName="readPersonListByMeasurement")
    @WebResult(name="people")
    public List<Person> (String measureType, String maxValue, String minValue); 
    
     */
}
