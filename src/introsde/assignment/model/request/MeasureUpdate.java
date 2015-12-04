package introsde.assignment.model.request;

import introsde.assignment.model.Measure;
import introsde.assignment.model.MeasureType;

import java.util.Date;

public class MeasureUpdate {

	private Long mid;
	
	private Date dateRegistered = new Date();

	private String measureValue;

	private String measureType;

	public MeasureUpdate(){

	}

	public Long getMid(){
		return mid;
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

	public void setMid(Long mid){
		this.mid = mid;
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

	public static Measure createMeasureObject(Long personId, MeasureUpdate mU){
		if(mU.getMid() == null){
			return null;
		}
		
		Measure m = Measure.getMeasureById(personId, mU.getMeasureType(), mU.getMid());

		
		if(mU.getMeasureType() != m.getMeasureType().getMeasureType() && mU.getMeasureType()!=null){
			MeasureType mType = MeasureType.getMeasureTypeByName(mU.getMeasureType());
			if(mType == null){
				return null;
			}
			m.setMeasureType(mType);
		}
	
		m.setMeasureValue(mU.getMeasureValue());
		m.setDateRegistered(mU.getDateRegistered());
		return m;
	}
}