package introsde.assignment.model.request;

import introsde.assignment.model.Measure;
import introsde.assignment.model.MeasureType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MeasureCreateRequest {

	private Date dateRegistered = new Date();
	
	private String measureValue;
	
	private String measureType;
	
	public MeasureCreateRequest(){
		
	}

	public Date getDateRegistered() {
		return dateRegistered;
	}

	public String getMeasureValue() {
		return measureValue;
	}

	public String getMeasureType() {
		return measureType;
	}

	public void setDateRegistered(Date dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	public void setMeasureValue(String measureValue) {
		this.measureValue = measureValue;
	}

	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}
	
	public static Measure createMeasureObject(MeasureCreateRequest mR){
		MeasureType mType = MeasureType.getMeasureTypeByName(mR.getMeasureType());
		if(mType == null)
			return null;
		
		Measure m = new Measure();
		m.setMeasureType(mType);
		m.setMeasureValue(mR.getMeasureValue());
		m.setDateRegistered(mR.getDateRegistered());
		return m;
	}
	
	public static List<Measure> createMeasureListObject(List<MeasureCreateRequest> mRList){
		ArrayList<Measure> mList = new ArrayList<Measure>();
		for(MeasureCreateRequest mR : mRList){
			mList.add(MeasureCreateRequest.createMeasureObject(mR));
		}
		return mList;
	}
}
